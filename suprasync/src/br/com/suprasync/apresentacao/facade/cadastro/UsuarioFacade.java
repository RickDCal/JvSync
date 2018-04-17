package br.com.suprasync.apresentacao.facade.cadastro;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.suprasync.negocio.IUsuarioServiceLocal;
import br.com.suprasync.negocio.exception.UsuarioInexistenteException;
import br.com.suprasync.persistencia.Usuario;

public class UsuarioFacade {
	
	private Properties p;
	private Context c;
	
	public IUsuarioServiceLocal service;
	
	public UsuarioFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IUsuarioServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/UsuarioService");
		
	}
	
	public Usuario pesquisar(String userName, String password) throws UsuarioInexistenteException {
		return service.pesquisar(userName, password);
	};

	public Usuario pesquisar(int id) throws UsuarioInexistenteException {
		return service.pesquisar(id);
	};
	
	public List<Usuario> pesquisar(Integer position, Integer max) throws UsuarioInexistenteException {
		return service.pesquisar(position, max);
	}

	public List<Usuario> obterPorIdFuncionario(Integer id) {
		return service.obterPorIdFuncionario(id);
	}

}
