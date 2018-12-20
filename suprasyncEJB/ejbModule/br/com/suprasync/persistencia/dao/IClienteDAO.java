package br.com.suprasync.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.suprasync.persistencia.Cliente;
import br.com.suprasync.persistencia.dao.exception.ClienteNaoEncontradoException;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Local
public interface IClienteDAO {

	//public Cliente obter(int id) throws ClienteNaoEncontradoException;
	
	public <T> Object obter(Class<T> classe, int id) throws ObjetoNaoEncontradoException;
	
	public List<Cliente> obterClientesAtivos(Integer position, Integer max) throws ClienteNaoEncontradoException;
		

}