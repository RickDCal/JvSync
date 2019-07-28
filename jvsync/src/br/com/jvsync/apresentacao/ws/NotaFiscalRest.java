package br.com.jvsync.apresentacao.ws;

import java.util.ArrayList;
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

import br.com.jvsync.apresentacao.facade.NotaFiscalFacade;
import br.com.jvsync.negocio.dto.FilterNotaFiscalDTO;
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.ItemNotaFiscal;
import br.com.jvsync.persistencia.MSCabecalhoNotaFiscal;

@Path("/notaFiscal")
public class NotaFiscalRest {

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
	@Path("/tgfcab")
	@Produces(MediaType.APPLICATION_JSON)
	public String obterCabecalhosNotas(String jsFilter) {
		setSuccess(false);		
		FilterNotaFiscalDTO filter = new Gson().fromJson((JsonObject) parser.parse(jsFilter), FilterNotaFiscalDTO.class);		
		try {
			NotaFiscalFacade notaFacade = new NotaFiscalFacade();
			List<CabecalhoNotaFiscal> cabecalhos = notaFacade.pesquisar(filter);
			List <MSCabecalhoNotaFiscal> cabs = new ArrayList<>();			
			
			for (Iterator<CabecalhoNotaFiscal> iterator = cabecalhos.iterator(); iterator.hasNext();) {
				CabecalhoNotaFiscal cabecalho = iterator.next();	
				MSCabecalhoNotaFiscal cab = new MSCabecalhoNotaFiscal(cabecalho);
				cabs.add(cab);
				jdados.add(cabecalho.toJson());				
			}
			
			for (MSCabecalhoNotaFiscal msCabecalhoNotaFiscal : cabs) {
				notaFacade.gravaCabecalho(msCabecalhoNotaFiscal);
			}
			
			setSuccess(true);			
		} catch (NamingException e) {
			e.printStackTrace();
		} 			
		return montaResposta();
	}
	
	@GET
	@Path("/tgfite")
	@Produces(MediaType.APPLICATION_JSON)
	public String obterItensNotas(String jsFilter) {
		setSuccess(false);		
		FilterNotaFiscalDTO filter = new Gson().fromJson((JsonObject) parser.parse(jsFilter), FilterNotaFiscalDTO.class);		
		try {
			NotaFiscalFacade notaFacade = new NotaFiscalFacade();
			List<ItemNotaFiscal> itens = notaFacade.obterItens(filter);
			for (Iterator<ItemNotaFiscal> iterator = itens.iterator(); iterator.hasNext();) {
				ItemNotaFiscal item = iterator.next();				
				jdados.add(item.toJson());	
			}			
			setSuccess(true);			
		} catch (NamingException e) {
			e.printStackTrace();
		} 			
		return montaResposta();
	}
	
}
