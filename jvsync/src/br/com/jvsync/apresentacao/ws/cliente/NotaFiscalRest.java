package br.com.jvsync.apresentacao.ws.cliente;

import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.jvsync.apresentacao.facade.NotaFiscalFacade;
import br.com.jvsync.negocio.dto.FilterNotaFiscalDTO;
import br.com.jvsync.negocio.exception.ObjetoInexistenteException;
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.ItemNotaFiscal;

@Path("/notaFiscal")
public class NotaFiscalRest {

	//	Construtor com facades
	//	public LogUtilizacaoWS() throws NamingException {
	//		InitialContext context = new InitialContext();
	//		logUtilizacaoFacede = (LogUtilizacaoFacade) context.lookup("java:global/Suprasoft/Suprasoft-ejb/LogUtilizacaoFacade");	
	//	}
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
	@Produces(MediaType.APPLICATION_JSON)//@Produces("text/plain")
	//public String obterCabecalhosNotas(@DefaultValue("") @QueryParam("id") String id, @QueryParam("data") String dados) {
	public String obterCabecalhosNotas(String jsFilter) {
		setSuccess(false);		
		FilterNotaFiscalDTO filter = new Gson().fromJson((JsonObject) parser.parse(jsFilter), FilterNotaFiscalDTO.class);		
		try {
			NotaFiscalFacade notaFacade = new NotaFiscalFacade();
			List<CabecalhoNotaFiscal> cabecalhos = notaFacade.pesquisar(filter);
			for (Iterator<CabecalhoNotaFiscal> iterator = cabecalhos.iterator(); iterator.hasNext();) {
				CabecalhoNotaFiscal cabecalho = iterator.next();				
				jdados.add(cabecalho.toJson());	
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
