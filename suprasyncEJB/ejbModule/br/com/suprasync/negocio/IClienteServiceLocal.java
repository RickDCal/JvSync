package br.com.suprasync.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.suprasync.negocio.exception.ClienteInexistenteException;
import br.com.suprasync.persistencia.Cliente;

@Local
public interface IClienteServiceLocal {

	public Cliente pesquisar(int id) throws ClienteInexistenteException;
	
	public List<Cliente> pesquisar(Integer position, Integer max) throws ClienteInexistenteException;		

}