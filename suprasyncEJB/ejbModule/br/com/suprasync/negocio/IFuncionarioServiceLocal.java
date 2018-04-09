package br.com.suprasync.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.suprasync.negocio.exception.UsuarioInexistenteException;
import br.com.suprasync.persistencia.Funcionario;

@Local
public interface IFuncionarioServiceLocal {

	public Funcionario pesquisar(int id) throws UsuarioInexistenteException;
	
	public List<Funcionario> pesquisar(Integer position, Integer max) throws UsuarioInexistenteException;		

}