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
	protected EntityManager entityManager; 

	@PersistenceUnit(unitName = "dboracle")
	protected EntityManagerFactory entityManagerFactory;

	@PersistenceContext(unitName = "dbsqlserver")
	protected EntityManager em; 

	@PersistenceUnit(unitName = "dbsqlserver")
	protected EntityManagerFactory emFactory;
	
	@PersistenceContext(unitName = "dbmysql")
	protected EntityManager emmy; 

	@PersistenceUnit(unitName = "dbmysql")
	protected EntityManagerFactory emmyFactory;

	protected Map<String, Object> parametros = new HashMap();

	@SuppressWarnings("unchecked")
	public <E, T> List<E> obter (Class<T> classe, Integer position, Integer max) throws ObjetoNaoEncontradoException {

		String className = classe.getSimpleName();
		String consulta = "select o from " + className + " o";		
		Query query;		
		switch (className.toLowerCase()) {				
		case "myparceiro": 
		case "myparceirox": 
		case "mypedido": 
		case "mypedidoitem": 
		case "myproduto": 
		case "myvendedor": query = emmy.createQuery(consulta); break; /*se for uma destas acima busca do Mysql*/
		default: query = entityManager.createQuery(consulta); break; /*senão busca do sqlserver*/		
		}
		
		if (position != null) {
			query.setFirstResult(position);	
		}
		if (max != null) {
			query.setMaxResults(max);
		}	
		List<E> lista = query.getResultList();	
		return lista;
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
		
		Query query;		
		switch (classe.getSimpleName().toLowerCase()) {
				
		case "myparceiro": 
		case "myparceirox": 
		case "mypedido": 
		case "mypedidoitem": 
		case "myproduto": 
		case "myvendedor": query = emmy.createQuery(consulta); break; /*se for uma destas acima busca do Mysql*/
		default: query = entityManager.createQuery(consulta); break; /*senão busca do sqlserver*/
		
		}	
		return (Long) query.getSingleResult();		
	}

}
