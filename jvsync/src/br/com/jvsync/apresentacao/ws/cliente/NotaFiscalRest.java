package br.com.suprasync.apresentacao.ws.cliente;

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

import br.com.suprasync.apresentacao.facade.cadastro.ClienteFacade;
import br.com.suprasync.negocio.exception.ClienteInexistenteException;
import br.com.suprasync.persistencia.Cliente;

@Path("/cliente")
public class ClienteRest {

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
	@Path("/obterClientes")
	@Produces(MediaType.APPLICATION_JSON)//@Produces("text/plain")
	public String obterClientes(@DefaultValue("") @QueryParam("id") String id, @QueryParam("data") String dados) {
		//return null;
		setSuccess(false);
		Gson gson = new Gson();
		try {
			ClienteFacade clienteFacade = new ClienteFacade();
			List<Cliente> clientes = clienteFacade.pesquisar(0, 10);
			for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
				Cliente cliente = (Cliente) iterator.next();				
				jdados.add(parser.parse(gson.toJson(cliente)));	
			}			
			setSuccess(true);			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ClienteInexistenteException e) {
			e.printStackTrace();
		} 			
		return montaResposta();
		

	}

}
