package br.com.jvsync.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.jvsync.negocio.dto.FilterParceiroDTO;
import br.com.jvsync.negocio.dto.FilterProdutoDTO;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;

@Stateless
public class CadastroDAO extends GenericDAO implements ICadastroDAO {


	public CadastroDAO() {

	}	
	
public List<Parceiro> obterParceiros (FilterParceiroDTO filter) {
		
		StringBuilder queryBuilder = new StringBuilder("select p from Parceiro p where p.id is not null");
				
		if (filter.getDataFinalCadastro() != null) {
			queryBuilder.append(" and c.dtCad <= :dtFinal" );
			parametros.put("dtFinal", filter.getDataFinalCadastro());
		}
		
		if (filter.getDataInicialCadastro() != null) {
			queryBuilder.append(" and c.dtCad >= :dtInicial" );
			parametros.put("dtInicial", filter.getDataInicialCadastro());
		}
		
		if (filter.getId() != null) {
			queryBuilder.append(" and p.id = :id" );
			parametros.put("id", filter.getId());
		}
		
		if (filter.getCgcCpf() != null) {
			queryBuilder.append(" and p.cgcCpf = :cgcCpf" );
			parametros.put("cgcCpf", filter.getCgcCpf());
		}
		
		queryBuilder.append(" order by p.id ");
		
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
	
	public List<Produto> obterProdutos (FilterProdutoDTO filter) {
		
		StringBuilder queryBuilder = new StringBuilder("select p from Produto p where p.id is not null");
				
		if (filter.getDataFinalCadastro() != null) {
			queryBuilder.append(" and c.dtCad <= :dtFinal" );
			parametros.put("dtFinal", filter.getDataFinalCadastro());
		}
		
		if (filter.getDataInicialCadastro() != null) {
			queryBuilder.append(" and c.dtCad >= :dtInicial" );
			parametros.put("dtInicial", filter.getDataInicialCadastro());
		}
		
		if (filter.getId() != null) {
			queryBuilder.append(" and p.id = :id" );
			parametros.put("id", filter.getId());
		}
		
		queryBuilder.append(" order by p.id ");
		
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
	
	
	

}
