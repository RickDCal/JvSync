package br.com.suprasync.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.suprasync.persistencia.Usuario;
import br.com.suprasync.persistencia.dao.exception.UsuarioNaoEncontradoException;

@Local
public interface IUsuarioDAO {

	public Usuario obter(String userName, String password) 	throws UsuarioNaoEncontradoException;

	public Usuario obter(int id) throws UsuarioNaoEncontradoException;
	
	public List<Usuario> obter(Integer position, Integer max) throws UsuarioNaoEncontradoException;
	
	public List<Usuario> obterPorIdFuncionario(Integer id);		

}