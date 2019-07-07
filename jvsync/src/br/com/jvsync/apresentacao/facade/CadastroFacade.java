package br.com.jvsync.apresentacao.facade;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.jvsync.negocio.ICadastroServiceLocal;
import br.com.jvsync.negocio.dto.FilterParceiroDTO;
import br.com.jvsync.negocio.dto.FilterProdutoDTO;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;
import br.com.jvsync.persistencia.TipoVenda;
import br.com.jvsync.persistencia.TipoVolume;
import br.com.jvsync.persistencia.Vendedor;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;

public class CadastroFacade {
	
	private Properties p;
	private Context c;

	public ICadastroServiceLocal service;

	public CadastroFacade() throws NamingException {

		p = new Properties();
		c = new InitialContext(p);
		service = (ICadastroServiceLocal)c.lookup("java:global/jvsyncEAR/jvsyncEJB/CadastroService");

	}
	
	public List<Parceiro> pesquisarParceiros (FilterParceiroDTO filter) {
		return service.pesquisarParceiros(filter);
	}
	
	public List<Produto> pesquisarProdutos (FilterProdutoDTO filter) {
		return service.pesquisarProdutos(filter);
	}
	
	public List<Vendedor> pesquisarVendedor (Integer position, Integer max) throws ObjetoNaoEncontradoException, NamingException {
		GenericFacade genericFacade = new GenericFacade();
		return genericFacade.pesquisar(Vendedor.class, position, max);
	}
	
	public List<TipoVenda> pesquisarTipoVenda (Integer position, Integer max) throws ObjetoNaoEncontradoException, NamingException {
		GenericFacade genericFacade = new GenericFacade();
		return genericFacade.pesquisar(TipoVenda.class, position, max);
	}
	
	public List<TipoVolume> pesquisarTipoVolume (Integer position, Integer max) throws ObjetoNaoEncontradoException, NamingException {
		GenericFacade genericFacade = new GenericFacade();
		return genericFacade.pesquisar(TipoVolume.class, position, max);
	}
}
