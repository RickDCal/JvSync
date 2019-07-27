package br.com.jvsync.persistencia.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;

public class GenericDAO {

	@PersistenceContext(unitName = "dboracle")
	protected EntityManager entityManager; // protected está acessível a classes do mesmo pacote e classes que herdam desta

	@PersistenceUnit(unitName = "dboracle")
	protected EntityManagerFactory entityManagerFactory;

	@PersistenceContext(unitName = "dbsqlserver")
	protected EntityManager em; // protected está acessível a classes do mesmo pacote e classes que herdam desta

	@PersistenceUnit(unitName = "dbsqlserver")
	protected EntityManagerFactory emFactory;

	protected Map<String, Object> parametros = new HashMap();


	public <T> Object obter(Class<T> classe, int id) throws ObjetoNaoEncontradoException {		
		Object entity  =  entityManager.find(classe, id);
		//if (entity == null) throw new ObjetoNaoEncontradoException();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public <E, T> List<E> obter (Class<T> classe, Integer position, Integer max) throws ObjetoNaoEncontradoException {

		String className = classe.getSimpleName();
		String consulta = "select o from " + className + " o";

		Query query = entityManager.createQuery(consulta);		

		if (position != null) {
			query.setFirstResult(position);	
		}
		if (max != null) {
			query.setMaxResults(max);
		}		

		List<E> lista = query.getResultList();	
		entityManager.close();
		return lista;
		
		

	}

	public <T> Object obter (Class<T> classe, String clientIdProperty) throws ObjetoNaoEncontradoException {

		String className = classe.getSimpleName();
		StringBuilder qryBuilder = new StringBuilder().append("select o from ");
		qryBuilder.append(className);
		qryBuilder.append(" o where o.clientIdProperty :=clientIdProperty ");
		Query query = entityManager.createQuery(qryBuilder.toString());
		query.setParameter("clientIdProperty", clientIdProperty);
		return query.getSingleResult();
	}

	public <T>void removerPorId (Class<T> classe, String id) {
		//coloquei o Id já como string para ficar flexível.
		Object entity = null;

		try {
			Integer idInt = Integer.parseInt(id);
			entity  =  entityManager.find(classe, idInt);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity  =  entityManager.find(classe, id);
		}		

		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity)); 
		entityManager.flush(); 
	}

	public void remover (Object entity) {		
		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity)); 
		entityManager.flush(); 
	}

	public Object inserir (Object entity) {
		entityManager.persist(entity);
		entityManager.flush();		
		return entity;
	}

	public Object alterar (Object entity) {
		entityManager.merge(entity);
		entityManager.flush();		
		return entity;
	}

	@SuppressWarnings("unchecked")
	public <E, T> List<E> obter (Class<T> classe, String nome, Integer position, Integer max) {

		String className = classe.getSimpleName();
		String consulta = "select o from " + className + " o where o.nome like '%" + nome + "%' order by nome asc";
		Query query = entityManager.createQuery(consulta);	

		//query.setParameter("nome", nome);

		if (position != null) {
			query.setFirstResult(position);	
		}
		if (max != null) {
			query.setMaxResults(max);
		}		

		List<E> lista = query.getResultList();		
		return lista;	

	}

	@SuppressWarnings("unchecked")
	public List<Object> obter (String nativeQuery) {		
		Query query = entityManager.createNativeQuery(nativeQuery);		
		List<Object> resultList = query.getResultList();			
		return resultList;		
	}

	public String atualizaDados (List<Object> entidadesAtualizar) {
		StringBuilder stb = new StringBuilder();
		int i = 0;
		for (Object object : entidadesAtualizar) {
			em.merge(object);
			em.flush();
			i++;
		}	
		if ( i > 0) {
			stb.append(" Registros atualizados: ").append(i);
		}
		return stb.toString();		
	}

	public <T> Long totalRegistros(Class<T> classe) {
		String consulta = "select count (o) from " + classe.getSimpleName() + " o";
		Query query = entityManager.createQuery(consulta);		
		return (Long) query.getSingleResult();		
	}

}
