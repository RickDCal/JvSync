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
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;

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
	
	public <T>String atualizaDados (Class<T> classe) throws ObjetoNaoEncontradoException, NamingException {
		
		StringBuilder stb = new StringBuilder("Atualizar ").append(classe.getSimpleName()).append(". Total registros de origem: ");		
		List<Object> entidadesAtualizar = service.entidadesAtualizar(classe);
		stb.append(entidadesAtualizar.size());
		stb.append(service.atualizaDados(entidadesAtualizar));
		System.out.println(stb.toString());
		return stb.toString();
	}	
}
