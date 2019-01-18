package br.com.suprasync.apresentacao.facade.versao;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.suprasync.negocio.IVersaoSistemaServiceLocal;
import br.com.suprasync.persistencia.VersaoSistema;
import br.com.suprasync.persistencia.enumerate.ProdutoSuprasoftEnum;


public class VersaoSistemaFacade {

	private Properties p;
	private Context c;
	
	public IVersaoSistemaServiceLocal service;
	
	public VersaoSistemaFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (IVersaoSistemaServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/VersaoSistemaService");
		
	}
	public VersaoSistema obterPorNumeroVersao(String numeroVersao, ProdutoSuprasoftEnum produtoEnum) {
		return service.obterPorNumeroVersao(numeroVersao, produtoEnum);
	}
	
	public VersaoSistema obterVersaoAtivaMaisRecente(ProdutoSuprasoftEnum produtoEnum) {
		return service.obterVersaoAtivaMaisRecente(produtoEnum);
	}
	
}
