package br.com.suprasync.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.suprasync.negocio.exception.ClienteInexistenteException;
import br.com.suprasync.persistencia.Cliente;
import br.com.suprasync.persistencia.dao.IClienteDAO;
import br.com.suprasync.persistencia.dao.exception.ClienteNaoEncontradoException;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Stateless
public class ClienteService implements IClienteServiceLocal {

	@EJB
	private IClienteDAO clienteDao;

	public ClienteService() {

	}	

	public Cliente pesquisar (int id) throws  ClienteInexistenteException {
		try {
			return (Cliente) clienteDao.obter(Cliente.class, id);				
		}catch (ObjetoNaoEncontradoException e){
			throw new ClienteInexistenteException();				
		}

	} 

	public List<Cliente> pesquisar(Integer position, Integer max) throws ClienteInexistenteException {

		try {
			return clienteDao.obterClientesAtivos(position, max);
		} catch (ClienteNaoEncontradoException e) {
			throw new ClienteInexistenteException();
		}		

	}
	
	public List<Cliente> pesquisarPorCNPJ(Integer position, Integer max, String cnpj) throws ClienteInexistenteException {
		try {
			return clienteDao.obterClientesPorCNPJ(position, max, cnpj);
		} catch (ClienteNaoEncontradoException e) {
			throw new ClienteInexistenteException();
		}
	}
	
	


}
