package br.com.jvsync.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Local
public interface IGenericDAO {
	
	public <T> Object obter(Class<T> classe, Object codigo);

	public <E, T> List<E> obter(Class<T> classe, Integer position, Integer max) throws ObjetoNaoEncontradoException;
	
	public String atualizaDados (List<Object> entidadesAtualizar);
	
	public <T> Long totalRegistros(Class<T> classe);
	
	public <E, T> List<E> obterDeSqlServer (Class<T> classe, Integer position, Integer max);
	
	public Object inserirSqlServer (Object entity);
	
	public Object alterarSqlServer (Object entity);

}