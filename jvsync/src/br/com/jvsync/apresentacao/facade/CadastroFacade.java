package br.com.jvsync.apresentacao.facade;

import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.jvsync.negocio.ICadastroServiceLocal;

public class CadastroFacade {
	
	private Properties p;
	private Context c;

	public ICadastroServiceLocal service;

	public CadastroFacade() throws NamingException {

		p = new Properties();
		c = new InitialContext(p);
		service = (ICadastroServiceLocal)c.lookup("java:global/jvsyncEAR/jvsyncEJB/CadastroService");

	}
	
	public Map<String, String> ultimaAtualizacao() {
		return service.ultimaAtualizacao();
	}		

}
