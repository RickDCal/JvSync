package br.com.jvsync.negocio;

import java.util.List;

import javax.ejb.Local;

import com.google.gson.JsonObject;

import br.com.jvsync.negocio.exception.FalhaAoCriarJSONException;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Local
public interface IGenericServiceLocal {
	
	public <T> Object obter(Class<T> classe, Object codigo);

	public <E, T> List<E> obter(Class<T> classe, Integer position, Integer max) throws ObjetoNaoEncontradoException;
	
	public JsonObject objetoJson(Object objeto) throws FalhaAoCriarJSONException;
	
	public String atualizaDados (List<Object> entidadesAtualizar);
	
	public <T> Long totalRegistros(Class<T> classe);
	
	public <E, T> List<E> obterDeSqlServer (Class<T> classe, Integer position, Integer max);
	
	public Object inserirSqlServer (Object entity);
	
	public Object alterarSqlServer (Object entity);

}