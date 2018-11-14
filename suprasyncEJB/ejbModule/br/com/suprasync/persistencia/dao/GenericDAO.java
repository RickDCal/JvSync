package br.com.suprasync.persistencia.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;

public class GenericDAO {

	@PersistenceContext(unitName = "transcal")

	protected EntityManager entityManager; // protected está acessível a classes do mesmo pacote e classes que herdam desta

	@PersistenceUnit(unitName = "transcal")

	protected EntityManagerFactory entityManagerFactory;


	public <T> Object obter(Class<T> classe, int id) throws ObjetoNaoEncontradoException {		
		Object entity  =  entityManager.find(classe, id);
		//if (entity == null) throw new ObjetoNaoEncontradoException();
		return entity;
	}

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

	public List<Object> obter (String nativeQuery) {
		
		
//		StringBuilder testes = new StringBuilder();
//		testes.append("select data as coluna1, sum(cadastradas) as coluna2, sum(solucionadas) as coluna3")
//		.append(" from")
//		.append(" (	select datepart(year, data_cadastro) as ano , substring(convert(varchar, data_cadastro, 3), 4,5) as data,")
//		.append(" 1 as cadastradas, 0 as solucionadas")
//		.append(" from sac_ocorrencia")
//		.append(" Union ALL")
//		.append(" select datepart(year, data_solucao) as ano, substring(convert(varchar, data_solucao, 3), 4,5) as data,")
//		.append(" 0 as cadastradas, 1 as solucionadas")
//		.append(" from sac_ocorrencia where data_solucao is not null")
//		.append(" ) todos")
//		.append(" group by ano, data");		
//
//		nativeQuery = testes.toString();
		
		
		
		

		Query query = entityManager.createNativeQuery(nativeQuery);
		List resultList = query.getResultList();
		

		
		List<Object[]> results =  query.getResultList(); //ou singleResult
        Iterator<Object[]> ite =results.iterator();
        while (ite.hasNext()) {
            Object[] result = (Object[]) ite.next();
            String codigo = (String) result[0];
            Integer contexto = (Integer) result[1];
            Integer tipoLigacao = (Integer) result[2];
            System.out.println(codigo + contexto + tipoLigacao);
            
            }
		
		return resultList;		
	}

}

















//package br.com.mynerp.persistencia.dao;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceUnit;
//
//public class GenericDAO {
//	
//	@PersistenceContext(unitName = "transcal")
//
//	protected EntityManager entityManager;
//	
//	@PersistenceUnit(unitName = "transcal")
//	
////	@PersistenceContext(unitName = "mynerp")
////
////	protected EntityManager entityManager;
////	
////	@PersistenceUnit(unitName = "mynerp")
////	
//	protected EntityManagerFactory entityManagerFactory;
//
//}
