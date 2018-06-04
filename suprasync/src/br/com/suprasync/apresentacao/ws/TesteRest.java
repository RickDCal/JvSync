package br.com.suprasync.apresentacao.ws;

import java.util.List;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.suprasync.apresentacao.facade.sac.SacOcorrenciaFacade;
import br.com.suprasync.negocio.dto.SacOcorrenciaDTO;
import br.com.suprasync.persistencia.SacOcorrenciaArquivo;

@Path("/teste")
public class TesteRest {

	//	Construtor com facades
	//	public LogUtilizacaoWS() throws NamingException {
	//		InitialContext context = new InitialContext();
	//		logUtilizacaoFacede = (LogUtilizacaoFacade) context.lookup("java:global/Suprasoft/Suprasoft-ejb/LogUtilizacaoFacade");	
	//	}
	private JsonObject retorno = new JsonObject();
	private JsonArray jdados = new JsonArray();	
	private boolean success = false;

	@POST
	@Path("/post")
	@Produces("text/plain")
	//@Consumes("application/x-www-form-urlencoded")
	public String inserirPrioridadesSac(@DefaultValue("") @QueryParam("id") String id, @QueryParam("data") String dados, @QueryParam("action") String action  ) {
		try {

			System.out.println("testes post");

		} catch (Exception e) {
			e.printStackTrace();
			return montaResposta();
		}		
		setSuccess(true);
		retorno.add("data", jdados);
		return retorno.toString();	
	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)//@Produces("text/plain")
	public String obterPrioridadesSac(@DefaultValue("") @QueryParam("id") String id, @QueryParam("data") String dados, @QueryParam("action") String action  ) {
		try {

			SacOcorrenciaFacade sacFacade = new SacOcorrenciaFacade();
			
			List<SacOcorrenciaArquivo> arquivos = sacFacade.obterAnexosSac(71046, null, null);
			
			for (SacOcorrenciaArquivo arquivo : arquivos) {
				System.out.println(arquivo.getNomeArquivo());
			}


		} catch (Exception e) {
			e.printStackTrace();
			return montaResposta();
		}		
		setSuccess(true);
		retorno.add("data", jdados);
		return retorno.toString();	

	}

	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)//@Produces("text/plain")
	public String atualizarPrioridadesSac(SacOcorrenciaDTO ocorrenciaDto ) {		
		//public String atualizarPrioridadesSac(@DefaultValue("") @QueryParam("id") String id, @QueryParam("data") String dados, @QueryParam("action") String action  ) {

		try {

			System.out.println("testes put");

		} catch (Exception e) {
			e.printStackTrace();
			return montaResposta();
		}		
		setSuccess(true);
		retorno.add("data", jdados);
		return retorno.toString();	
	}

	@DELETE
	@Path("/delete")
	@Produces("text/plain")
	public String delete (SacOcorrenciaDTO ocorrenciaDto) {
		try {

			System.out.println("testes delete");

		} catch (Exception e) {
			e.printStackTrace();
			return montaResposta();
		}		
		setSuccess(true);
		retorno.add("data", jdados);
		return retorno.toString();	
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
