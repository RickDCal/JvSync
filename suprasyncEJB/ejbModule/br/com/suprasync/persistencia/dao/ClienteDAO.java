package br.com.suprasync.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.suprasync.persistencia.Cliente;
import br.com.suprasync.persistencia.dao.exception.ClienteNaoEncontradoException;

@Stateless
public class ClienteDAO extends GenericDAO implements IClienteDAO {


	public ClienteDAO() {

	}	

	public List<Cliente> obterClientesAtivos(Integer position, Integer max) throws ClienteNaoEncontradoException{

		String consulta =  "select f from Cliente f where f.id is not null and f.dataExclusao is null ";		
		Query query = entityManager.createQuery(consulta);

		if (position !=null) {
			query.setFirstResult(position);
		}
		if (max != null) {
			query.setMaxResults(max);
		}

		List<Cliente> clientes = query.getResultList();

		return clientes;		

	}
	
	public List<Cliente> obterClientesPorCNPJ(Integer position, Integer max, String cnpj) throws ClienteNaoEncontradoException{

		String consulta =  "select f from Cliente f where f.id is not null and f.dataExclusao is null and f.cnpj = :cnpj";		
		Query query = entityManager.createQuery(consulta);
		query.setParameter("cnpj", cnpj);

		if (position !=null) {
			query.setFirstResult(position);
		}
		if (max != null) {
			query.setMaxResults(max);
		}

		List<Cliente> clientes = query.getResultList();

		return clientes;		

	}

}
