package br.com.suprasync.apresentacao.facade.cadastro;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.suprasync.negocio.IClienteServiceLocal;
import br.com.suprasync.negocio.exception.ClienteInexistenteException;
import br.com.suprasync.persistencia.Cliente;

public class ClienteFacade {
	
	private Properties p;
	private Context c;
	
	public IClienteServiceLocal service;
	
	public ClienteFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IClienteServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/ClienteService");
		
	}

	public Cliente pesquisar(int id) throws ClienteInexistenteException {
		return service.pesquisar(id);
	};
	
	public List<Cliente> pesquisar(Integer position, Integer max) throws ClienteInexistenteException {
		return service.pesquisar(position, max);
	}
	
	public List<Cliente> pesquisarPorCNPJ(Integer position, Integer max, String cnpj) throws ClienteInexistenteException {
		return service.pesquisarPorCNPJ(position, max, cnpj);
	}

}
