package br.com.jvsync.persistencia.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.jvsync.negocio.dto.FilterNotaFiscalDTO;
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.ItemNotaFiscal;
import br.com.jvsync.persistencia.MSCabecalhoNotaFiscal;
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

		StringBuilder queryBuilder = new StringBuilder("select i from CabecalhoNotaFiscal c join c.itens i where c.id is not null");

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
			queryBuilder.append(" and c.dtMov <= :dtFinal" );
			parametros.put("dtFinal", filter.getDataFinal());
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

	public boolean gravaCabecalho(MSCabecalhoNotaFiscal cabecalho) {		
		try {
			em.merge(cabecalho);
			em.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("falha ao persistir cabeçalho de nota. Id: " + cabecalho.getId());
		}		
		return false;		
	}

	public <T> Long totalRegistros(Class<T> classe) {
		String consulta = "select count (o) from " + classe.getSimpleName() + " o";
		Query query = entityManager.createQuery(consulta);		
		return (Long) query.getSingleResult();		
	}

	public <T>List<Object> entidadesAtualizar(Class<T> classe) {	
		
		try {
			List<Object> entidades = obter(classe, 0, 1000);
			List<Object> entidadesPersistir = new ArrayList<>();

			String tipo = classe.getSimpleName().toLowerCase();

			for (Object object : entidades) {
				Object entidadePersistir = null;
				switch (tipo) {
				case "cabecalhonotafiscal": entidadePersistir = new MSCabecalhoNotaFiscal((CabecalhoNotaFiscal) object); break;
				//case "itemnotafiscal": entidadePersistir = new MSItemNotaFiscal((ItemNotaFiscal) object); break;
				default: break;
				}
				if (entidadePersistir != null) {
					entidadesPersistir.add(entidadePersistir);
				}						
			}
			return entidadesPersistir;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;		
	}
	
	public String atualizaDados (List<Object> entidadesAtualizar) {
		StringBuilder stb = new StringBuilder();
		int i = 0;
		try {
			for (Object object : entidadesAtualizar) {
				em.merge(object);
				em.flush();
				i++;
			}
		} catch (Exception e) {
			stb.append(" Ocorreu uma falha ao atualizar os registros. ");
			e.printStackTrace();
		}		
		if ( i > 0) {
			stb.append(" Registros atualizados: ").append(i);
		}
		return stb.toString();		
	}
}
