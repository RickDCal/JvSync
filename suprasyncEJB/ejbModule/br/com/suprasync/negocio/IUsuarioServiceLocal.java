package br.com.suprasync.negocio;

import java.util.List;

import javax.ejb.Local;

import com.google.gson.JsonObject;

import br.com.suprasync.negocio.exception.FalhaAoCriarJSONException;
import br.com.suprasync.negocio.exception.UsuarioInexistenteException;
import br.com.suprasync.persistencia.Usuario;

@Local
public interface IUsuarioServiceLocal {

	public Usuario pesquisar(String userName, String password) throws UsuarioInexistenteException;

	public Usuario pesquisar(int id) throws UsuarioInexistenteException;
	
	public List<Usuario> pesquisar(Integer position, Integer max) throws UsuarioInexistenteException;
	
	public void cadastrar (Usuario usuario) throws UsuarioInexistenteException;
	
	public void atualizar (Usuario usuario) throws UsuarioInexistenteException;
		

}