package br.com.suprasync.apresentacao.facade.cadastro;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.suprasync.negocio.IFuncionarioServiceLocal;
import br.com.suprasync.negocio.exception.UsuarioInexistenteException;
import br.com.suprasync.persistencia.Funcionario;

public class FuncionarioFacade {
	
	private Properties p;
	private Context c;
	
	public IFuncionarioServiceLocal service;
	
	public FuncionarioFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IFuncionarioServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/FuncionarioService");
		
	}
	
	public Funcionario pesquisar(int id) throws UsuarioInexistenteException {
		return service.pesquisar(id);
	};
	
	public List<Funcionario> pesquisar(Integer position, Integer max) throws UsuarioInexistenteException {
		return service.pesquisar(position, max);
	}


}
