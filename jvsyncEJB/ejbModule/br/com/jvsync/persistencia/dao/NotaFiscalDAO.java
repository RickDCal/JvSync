package br.com.jvsync.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.jvsync.negocio.dto.FilterNotaFiscalDTO;
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.ItemNotaFiscal;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Stateless
public class NotaFiscalDAO extends GenericDAO implements INotaFiscalDAO {


	public NotaFiscalDAO() {

	}	

	public List<CabecalhoNotaFiscal> obterCabecalhos(Integer position, Integer max) throws ObjetoNaoEncontradoException{

		String consulta =  "select c from CabecalhoNotaFiscal c where c.id is not null";		
		Query query = entityManager.createQuery(consulta);

		if (position !=null) {
			query.setFirstResult(position);
		}
		if (max != null) {
			query.setMaxResults(max);
		}

		List<CabecalhoNotaFiscal> cabecalhos = query.getResultList();

		return cabecalhos;		

	}
	
	public List<CabecalhoNotaFiscal> obter (FilterNotaFiscalDTO filter) {
		
		StringBuilder queryBuilder = new StringBuilder("select c from CabecalhoNotaFiscal c where c.id is not null");
				
		queryBuilder = aplicaFilter(queryBuilder, filter);
		
		queryBuilder.append(" order by c.id ");
		
		Query query = entityManager.createQuery(queryBuilder.toString());
		
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}
		
		if (filter.getPosition() != null) {
			query.setFirstResult(filter.getPosition());
		}

		if (filter.getMax() != null) {
			query.setMaxResults(filter.getMax());
		}
		

		return query.getResultList();			
		
	}
	
public List<ItemNotaFiscal> obterItens (FilterNotaFiscalDTO filter) {
		
		StringBuilder queryBuilder = new StringBuilder("select i from ItemNotaFiscal i where i.idNota is not null");
				
		queryBuilder = aplicaFilter(queryBuilder, filter);
		
		queryBuilder.append(" order by i.idNota, i.sequencia ");
		
		Query query = entityManager.createQuery(queryBuilder.toString());
		
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}
		
		if (filter.getPosition() != null) {
			query.setFirstResult(filter.getPosition());
		}

		if (filter.getMax() != null) {
			query.setMaxResults(filter.getMax());
		}
		

		return query.getResultList();			
		
	}
	
	public StringBuilder aplicaFilter (StringBuilder queryBuilder, FilterNotaFiscalDTO filter) {
		
		if (filter.getDataFinal() != null) {
			queryBuilder.append(" and c.dtMov >= :dtFinal" );
			parametros.put("dtInicial", filter.getDataFinal());
		}
		
		if (filter.getDataInicial() != null) {
			queryBuilder.append(" and c.dtMov >= :dtInicial" );
			parametros.put("dtInicial", filter.getDataInicial());
		}
		
		if (filter.getId() != null) {
			queryBuilder.append(" and c.id = :id" );
			parametros.put("id", filter.getId());
		}
		
		if (filter.getIdInicial() != null) {
			queryBuilder.append(" and c.id >= :idInicial" );
			parametros.put("idInicial", filter.getIdInicial());
		}
		
		if (filter.getNumNota() != null) {
			queryBuilder.append(" and c.numNota = :numNota" );
			parametros.put("numNota", filter.getNumNota());
		}		
		
		return queryBuilder;
	}
	
	

}
