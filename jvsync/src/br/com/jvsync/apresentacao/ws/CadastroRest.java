package br.com.jvsync.apresentacao.ws;

import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.jvsync.apresentacao.facade.CadastroFacade;
import br.com.jvsync.negocio.dto.FilterParceiroDTO;
import br.com.jvsync.negocio.dto.FilterProdutoDTO;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;
import br.com.jvsync.persistencia.TipoVenda;
import br.com.jvsync.persistencia.TipoVolume;
import br.com.jvsync.persistencia.Vendedor;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Path("/cadastro")
public class CadastroRest {

	private JsonObject retorno = new JsonObject();
	private JsonArray jdados = new JsonArray();	
	private boolean success = false;
	private JsonParser parser = new JsonParser();
	private String mensagemRetorno = null;	
	
	public String montaResposta() {
		retorno.addProperty("success", success);
		retorno.add("data", jdados);
		if (mensagemRetorno != null) {
			retorno.addProperty("mensagemRetorno", mensagemRetorno);
		}
		return retorno.toString();
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	@GET
	@Path("/tgfpar")
	@Produces(MediaType.APPLICATION_JSON)
	public String obterCabecalhosNotas(String jsFilter) {
		setSuccess(false);		
		FilterParceiroDTO filter = new Gson().fromJson((JsonObject) parser.parse(jsFilter), FilterParceiroDTO.class);		
		try {
			CadastroFacade cadastroFacade = new CadastroFacade();
			List<Parceiro> parceiros = cadastroFacade.pesquisarParceiros(filter);
			for (Iterator<Parceiro> iterator = parceiros.iterator(); iterator.hasNext();) {
				Parceiro parceiro = iterator.next();				
				jdados.add(parceiro.toJson());	
			}			
			setSuccess(true);			
		} catch (NamingException e) {
			e.printStackTrace();
		} 			
		return montaResposta();
	}
	
	@GET
	@Path("/tgfpro")
	@Produces(MediaType.APPLICATION_JSON)
	public String obterProdtos(String jsFilter) {
		setSuccess(false);		
		FilterProdutoDTO filter = new Gson().fromJson((JsonObject) parser.parse(jsFilter), FilterProdutoDTO.class);		
		try {
			CadastroFacade cadastroFacade = new CadastroFacade();
			List<Produto> produtos = cadastroFacade.pesquisarProdutos(filter);
			for (Iterator<Produto> iterator = produtos.iterator(); iterator.hasNext();) {
				Produto produto = iterator.next();				
				jdados.add(produto.toJson());	
			}			
			setSuccess(true);			
		} catch (NamingException e) {
			e.printStackTrace();
		} 			
		return montaResposta();
	}
	
	@GET
	@Path("/tgfvoa")
	@Produces(MediaType.APPLICATION_JSON)
	public String obterTiposVolumes(String jsFilter) throws ObjetoNaoEncontradoException {
		setSuccess(false);		
		FilterProdutoDTO filter = new Gson().fromJson((JsonObject) parser.parse(jsFilter), FilterProdutoDTO.class);		
		try {
			CadastroFacade cadastroFacade = new CadastroFacade();
			List<TipoVolume> volumes = cadastroFacade.pesquisarTipoVolume(filter.getPosition(), filter.getMax());
			for (Iterator<TipoVolume> iterator = volumes.iterator(); iterator.hasNext();) {
				TipoVolume volume = iterator.next();				
				jdados.add(volume.toJson());	
			}			
			setSuccess(true);			
		} catch (NamingException e) {
			e.printStackTrace();
		} 			
		return montaResposta();
	}
	
	@GET
	@Path("/tgfven")
	@Produces(MediaType.APPLICATION_JSON)
	public String obterVendedores(String jsFilter) throws ObjetoNaoEncontradoException {
		setSuccess(false);		
		FilterProdutoDTO filter = new Gson().fromJson((JsonObject) parser.parse(jsFilter), FilterProdutoDTO.class);		
		try {
			CadastroFacade cadastroFacade = new CadastroFacade();
			List<Vendedor> vendedores = cadastroFacade.pesquisarVendedor(filter.getPosition(), filter.getMax());
			for (Iterator<Vendedor> iterator = vendedores.iterator(); iterator.hasNext();) {
				Vendedor vendedor = iterator.next();				
				jdados.add(vendedor.toJson());	
			}			
			setSuccess(true);			
		} catch (NamingException e) {
			e.printStackTrace();
		} 			
		return montaResposta();
	}
	
	@GET
	@Path("/tgftpv")
	@Produces(MediaType.APPLICATION_JSON)
	public String obterTiposVendas(String jsFilter) throws ObjetoNaoEncontradoException {
		setSuccess(false);		
		FilterProdutoDTO filter = new Gson().fromJson((JsonObject) parser.parse(jsFilter), FilterProdutoDTO.class);		
		try {
			CadastroFacade cadastroFacade = new CadastroFacade();
			List<TipoVenda> tiposVendas = cadastroFacade.pesquisarTipoVenda(filter.getPosition(), filter.getMax());
			for (Iterator<TipoVenda> iterator = tiposVendas.iterator(); iterator.hasNext();) {
				TipoVenda tipoVenda = iterator.next();				
				jdados.add(tipoVenda.toJson());	
			}			
			setSuccess(true);			
		} catch (NamingException e) {
			e.printStackTrace();
		} 			
		return montaResposta();
	}
}
