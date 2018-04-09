package br.com.suprasync.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.suprasync.persistencia.Funcionario;
import br.com.suprasync.persistencia.dao.exception.UsuarioNaoEncontradoException;

@Local
public interface IFuncionarioDAO {

	public Funcionario obter(int id) throws UsuarioNaoEncontradoException;
	
	public List<Funcionario> obterAtivos(Integer position, Integer max) throws UsuarioNaoEncontradoException;
		

}