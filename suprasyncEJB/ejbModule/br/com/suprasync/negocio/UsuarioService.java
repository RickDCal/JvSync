package br.com.suprasync.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.suprasync.negocio.exception.UsuarioInexistenteException;
import br.com.suprasync.persistencia.Usuario;
import br.com.suprasync.persistencia.dao.IUsuarioDAO;
import br.com.suprasync.persistencia.dao.exception.UsuarioNaoEncontradoException;

@Stateless
public class UsuarioService implements IUsuarioServiceLocal {

	@EJB
	private IUsuarioDAO usuarioDao;

	public UsuarioService() {

	}	

	public Usuario pesquisar(String userName, String password) throws UsuarioInexistenteException {

		try {				
			return usuarioDao.obter(userName, password);
		} catch (UsuarioNaoEncontradoException e) {
			throw new UsuarioInexistenteException();
		}		

	} 

	public Usuario pesquisar (int id) throws  UsuarioInexistenteException {


		try {
			return usuarioDao.obter(id);				
		}catch (UsuarioNaoEncontradoException e){
			throw new UsuarioInexistenteException();				
		}

	} 

	public List<Usuario> pesquisar(Integer position, Integer max) throws UsuarioInexistenteException {

		try {
			return usuarioDao.obter(position, max);
		} catch (UsuarioNaoEncontradoException e) {
			throw new UsuarioInexistenteException();
		}		

	}


}
