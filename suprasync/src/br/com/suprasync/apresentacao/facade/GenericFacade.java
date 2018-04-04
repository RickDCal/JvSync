package br.com.suprasync.apresentacao.facade;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.JsonObject;

import br.com.suprasync.negocio.IGenericServiceLocal;
import br.com.suprasync.negocio.exception.FalhaAoCriarJSONException;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;

public class GenericFacade {
	
	private Properties p;
	private Context c;
	
	public IGenericServiceLocal service;
	
	public GenericFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IGenericServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/GenericService");
		
	}
	
	/*Genéricos*/
	
	public <T> Object pesquisar(Class<T> classe, int id) throws ObjetoNaoEncontradoException {
		return service.obter(classe, id);
	}

	public <E, T> List<E> pesquisar(Class<T> classe, Integer position, Integer max) throws ObjetoNaoEncontradoException {
		return service.obter(classe, position, max);
	}

	public <E, T> List<E> pesquisar(Class<T> classe, String nome, Integer position, Integer max) throws ObjetoNaoEncontradoException {
		return service.obter(classe, nome, position, max);
	}
	
	public void remover(Object entity) {
		service.remover(entity);
	}

	public Object cadastrar(Object entity) throws ObjetoNaoEncontradoException {
		return service.inserir(entity);
	}

	public Object atualizar(Object entity) throws ObjetoNaoEncontradoException {
		return service.alterar(entity);
		
	}
	
	public JsonObject objetoJson(Object objeto) throws FalhaAoCriarJSONException {
		return service.objetoJson(objeto);
	}
			
	
}
