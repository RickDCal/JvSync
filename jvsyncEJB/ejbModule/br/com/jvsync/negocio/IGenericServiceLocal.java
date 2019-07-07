package br.com.jvsync.negocio;

import java.util.List;

import javax.ejb.Local;

import com.google.gson.JsonObject;

import br.com.jvsync.negocio.exception.FalhaAoCriarJSONException;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Local
public interface IGenericServiceLocal {

	public <T> Object obter(Class<T> classe, int id) throws ObjetoNaoEncontradoException;

	public <E, T> List<E> obter(Class<T> classe, Integer position, Integer max) throws ObjetoNaoEncontradoException;

	public <E, T> List<E> obter(Class<T> classe, String nome, Integer position, Integer max) throws ObjetoNaoEncontradoException;
	
	public void remover(Object entity);

	public Object inserir(Object entity) throws ObjetoNaoEncontradoException;

	public Object alterar(Object entity) throws ObjetoNaoEncontradoException;
	
	public JsonObject objetoJson(Object objeto) throws FalhaAoCriarJSONException;
	
	public List<Object> obter (String nativeQuery);

}