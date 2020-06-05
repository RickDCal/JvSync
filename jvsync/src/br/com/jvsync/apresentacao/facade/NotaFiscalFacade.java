package br.com.jvsync.apresentacao.facade;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.jvsync.negocio.INotaFiscalServiceLocal;
import br.com.jvsync.negocio.dto.FilterNotaFiscalDTO;
import br.com.jvsync.negocio.exception.ObjetoInexistenteException;
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.ItemNotaFiscal;
import br.com.jvsync.persistencia.MSCabecalhoNotaFiscal;

public class NotaFiscalFacade {
	
	private Properties p;
	private Context c;

	public INotaFiscalServiceLocal service;

	public NotaFiscalFacade() throws NamingException {

		p = new Properties();
		c = new InitialContext(p);
		service = (INotaFiscalServiceLocal)c.lookup("java:global/jvsyncEAR/jvsyncEJB/NotaFiscalService");

	}
	
	
	public List<CabecalhoNotaFiscal> pesquisar(Integer position, Integer max) throws ObjetoInexistenteException {
		return service.pesquisar(position, max);
	}
	
	public List<CabecalhoNotaFiscal> pesquisar (FilterNotaFiscalDTO filter) {
		return service.pesquisar(filter);
	}
	
	public List<ItemNotaFiscal> obterItens (FilterNotaFiscalDTO filter) {
		return service.obterItens(filter);
	}
	
	public boolean gravaCabecalho(MSCabecalhoNotaFiscal cabecalho) {
		return service.gravaCabecalho(cabecalho);
	}
		
}
