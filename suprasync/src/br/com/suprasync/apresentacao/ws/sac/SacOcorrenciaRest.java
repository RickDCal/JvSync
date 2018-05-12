package br.com.suprasync.apresentacao.ws.sac;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import br.com.suprasync.apresentacao.facade.GenericFacade;
import br.com.suprasync.apresentacao.facade.sac.SacOcorrenciaFacade;
import br.com.suprasync.negocio.dto.SacOcorrenciaDTO;
import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.suprasync.persistencia.dao.exception.SacOcorrenciaNaoEncontradaException;
import br.com.suprasync.persistencia.filter.SacOcorrenciaFilter;

@Path("/sac")
public class SacOcorrenciaRest {
	
//	Construtor com facades
//	public LogUtilizacaoWS() throws NamingException {
//		InitialContext context = new InitialContext();
//		logUtilizacaoFacede = (LogUtilizacaoFacade) context.lookup("java:global/Suprasoft/Suprasoft-ejb/LogUtilizacaoFacade");	
//	}
	private JsonObject retorno = new JsonObject();
	private JsonArray jdados = new JsonArray();	
	private boolean success = false;
	private JsonParser parser = new JsonParser();
	
	@POST
	@Path("/inserirPrioridades")
	@Produces("text/plain")
	//@Consumes("application/x-www-form-urlencoded")
	public String inserirPrioridadesSac(@DefaultValue("") @QueryParam("id") String id, @QueryParam("data") String dados, @QueryParam("action") String action  ) {
		return null;
//		try {
//			GenericFacade genericFacade = new GenericFacade();
//			List<SacPrioridade> prioridades = genericFacade.pesquisar(SacPrioridade.class, 0, 1000, null);
//			JsonArray array = new JsonArray();
//			for (Iterator<SacPrioridade> iterator = prioridades.iterator(); iterator.hasNext();) {
//				SacPrioridade sacPrioridade = (SacPrioridade) iterator.next();
//				array.add(sacPrioridade.prioridadeJson(sacPrioridade));	
//			} 
//			return array.toString();
//		} catch (NamingException | ObjetoNaoEncontradoException e) {
//			e.printStackTrace();
//			return null;
//		}	
	}
	
	@SuppressWarnings("finally")
	@GET
	@Path("/obterPrioridades")
	@Produces(MediaType.APPLICATION_JSON)//@Produces("text/plain")
	public String obterPrioridadesSac(@DefaultValue("") @QueryParam("id") String id, @QueryParam("data") String dados, @QueryParam("action") String action  ) {
		//return null;
		setSuccess(false);
		try {
			SacOcorrenciaFacade ocorrenciaFacade = new SacOcorrenciaFacade();
			SacOcorrenciaFilter filter = new SacOcorrenciaFilter();
			filter.setPrioridade(true);
			List<SacOcorrencia> prioridades = ocorrenciaFacade.obter(filter);
			for (Iterator<SacOcorrencia> iterator = prioridades.iterator(); iterator.hasNext();) {
				SacOcorrencia prioridade = (SacOcorrencia) iterator.next();
				jdados.add(prioridade.getOcorrenciaDTO(null).getAsJson());	
			}			
			setSuccess(true);			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SacOcorrenciaNaoEncontradaException e) {
			e.printStackTrace();
		} finally {
			return montaResposta();
		}
		
	}
	
	@PUT
	@Path("/atualizarOcorrencias")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)//@Produces("text/plain")
	public String atualizarSacOcorrencia(String query) {		
	//public String atualizarPrioridadesSac(@DefaultValue("") @QueryParam("id") String id, @QueryParam("data") String dados, @QueryParam("action") String action  ) {
			
		try {
			//JsonObject json = (JsonObject) parser.parse(jsonObject);
			//String consulta = json.get("query").getAsString();			
			SacOcorrenciaFacade sacFacade = new SacOcorrenciaFacade();
			sacFacade.consultaNativa(query);		
		} catch (NamingException e) {
			e.printStackTrace();
			return montaResposta();
		}		
		setSuccess(true);
		retorno.add("data", jdados);
		return retorno.toString();
	}
	
//	@PUT
//	@Path("/atualizarPrioridades")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)//@Produces("text/plain")
//	public String atualizarPrioridadesSac(SacOcorrenciaDTO ocorrenciaDto ) {		
//	//public String atualizarPrioridadesSac(@DefaultValue("") @QueryParam("id") String id, @QueryParam("data") String dados, @QueryParam("action") String action  ) {
//	
//		try {
//			SacOcorrenciaFacade sacOcorrenciaFacade = new SacOcorrenciaFacade();
//			SacOcorrencia ocorrencia = sacOcorrenciaFacade.pesquisar(ocorrenciaDto.getId());
//			ocorrencia.setPrioridade(ocorrenciaDto.getPrioridade());
//			sacOcorrenciaFacade.atualizar(ocorrencia);
//			jdados.add(ocorrencia.getOcorrenciaDTO(null).getAsJson());			
//		} catch (NamingException | SacOcorrenciaNaoEncontradaException e) {
//			e.printStackTrace();
//			return montaResposta();
//		}		
//		setSuccess(true);
//		retorno.add("data", jdados);
//		return retorno.toString();
//	}
	
	@DELETE
	@Path("/removerPrioridades")
	@Produces("text/plain")
	public String removerPrioridadesSac(@DefaultValue("") @QueryParam("id") String id, @QueryParam("data") String dados, @QueryParam("action") String action  ) {
		return null;
//		try {
//			GenericFacade genericFacade = new GenericFacade();
//			List<SacPrioridade> prioridades = genericFacade.pesquisar(SacPrioridade.class, 0, 1000, null);
//			JsonArray array = new JsonArray();
//			for (Iterator<SacPrioridade> iterator = prioridades.iterator(); iterator.hasNext();) {
//				SacPrioridade sacPrioridade = (SacPrioridade) iterator.next();
//				array.add(sacPrioridade.prioridadeJson(sacPrioridade));	
//			} 
//			return array.toString();
//		} catch (NamingException | ObjetoNaoEncontradoException e) {
//			e.printStackTrace();
//			return null;
//		}	
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String montaResposta() {
		retorno.addProperty("success", success);
		retorno.add("data", jdados);
		return retorno.toString();
	}
	
	

}
