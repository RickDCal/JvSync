package br.com.suprasync.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.suprasync.persistencia.VersaoSistema;
import br.com.suprasync.persistencia.enumerate.ProdutoSuprasoftEnum;

@Stateless
public class VersaoSistemaDAO extends GenericDAO implements IVersaoSistemaDAO {

	public br.com.suprasync.persistencia.VersaoSistema obterPorNumeroVersao(String numeroVersao, ProdutoSuprasoftEnum produtoEnum) {

		StringBuilder queryBuilder = new StringBuilder("select v from VersaoSistema v where v.numeroVersao = '");
		queryBuilder.append(numeroVersao).append("'").append(" and v.produtoSuprasoft = :produtoSuprasoft ");
		Query query = entityManager.createQuery(queryBuilder.toString());
		query.setParameter("produtoSuprasoft", produtoEnum);
		query.setMaxResults(1);
		List<VersaoSistema> versoes = query.getResultList();
		if (versoes != null && versoes.size() > 0) {
			return versoes.get(0);
		}
		return null;			
	}

//	public VersaoSistema obterVersaoAtivaMaisRecente (ProdutoSuprasoftEnum produtoEnum) {
//		StringBuilder queryBuilder = new StringBuilder("select v from VersaoSistema v where v.dataSuspensao > CURRENT_DATE and v.produtoSuprasoft = :produtoSuprasoft");
//		queryBuilder.append(" order by v.dataLancamento desc ");
//		Query query = entityManager.createQuery(queryBuilder.toString());
//		query.setParameter("produtoSuprasoft", produtoEnum);
//		query.setMaxResults(1);
//		List<VersaoSistema> versoes = query.getResultList();
//		if (versoes != null && versoes.size() > 0) {
//			return versoes.get(0);
//		}
//		return null;
//
//	}
	
	public VersaoSistema obterVersaoAtivaMaisRecente (ProdutoSuprasoftEnum produtoEnum) {
		StringBuilder queryBuilder = new StringBuilder("select v from VersaoSistema v where v.dataSuspensao > :hoje and v.produtoSuprasoft = :produtoSuprasoft");
		queryBuilder.append(" order by v.dataLancamento desc ");
		Query query = entityManager.createQuery(queryBuilder.toString());
		query.setParameter("produtoSuprasoft", produtoEnum);
		query.setParameter("hoje", new Date());
		query.setMaxResults(1);
		List<VersaoSistema> versoes = query.getResultList();
		if (versoes != null && versoes.size() > 0) {
			return versoes.get(0);
		}
		return null;

	}

}
