package br.com.suprasync.persistencia.dao;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.suprasync.persistencia.Parcela;

@Stateless
public class ContaDAO extends GenericDAO implements IContaDAO {


	public ContaDAO() {

	}	

	public List<Parcela> buscarParcelasEmAbertoPorParceiro(Long idParceiro) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		StringBuilder queryBuilder = new StringBuilder("select p from Parcela p ");
		queryBuilder.append(" inner join p.conta c  ");
		queryBuilder.append(" where p.dataQuitacao is null and c.parceiro.id = :idParceiro  ");        
		queryBuilder.append(" and p.idContaConsolidada is null ");
		queryBuilder.append(" order by p.dataVencimento ");
		parameters.put("idParceiro", idParceiro);

		/** seta os parâmetros na query */
		Query query = entityManager.createQuery(queryBuilder.toString());
		for (String key : parameters.keySet()) {
			query.setParameter(key, parameters.get(key));
		}
		return query.getResultList();
	}

	public List<Parcela> buscarParcelasEmAtrasoPorParceiro(Long idParceiro) {	

		Map<String, Object> parameters = new HashMap<String, Object>();

		StringBuilder queryBuilder = new StringBuilder("select p from Parcela p ");
		queryBuilder.append(" inner join p.conta c  ");
		queryBuilder.append(" where p.dataQuitacao is null and c.parceiro.id = :idParceiro  ");        
		queryBuilder.append(" and p.idContaConsolidada is null ");
		//queryBuilder.append(" and p.dataVencimento < current_date ");  //para supraweb
		queryBuilder.append(" and p.dataVencimento < :hoje "); //para suprasync
		queryBuilder.append(" order by p.dataVencimento ");
		parameters.put("idParceiro", idParceiro.intValue());
		parameters.put("hoje", new Date()); //para suprasync

		/** seta os parâmetros na query */
		Query query = entityManager.createQuery(queryBuilder.toString());
		for (String key : parameters.keySet()) {
			query.setParameter(key, parameters.get(key));
		}
		return query.getResultList();		

	}


}
