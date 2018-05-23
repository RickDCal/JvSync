package br.com.suprasync.apresentacao.ws.sac;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.google.gson.JsonParser;

import br.com.suprasync.apresentacao.facade.GenericFacade;
import br.com.suprasync.apresentacao.facade.sac.SacOcorrenciaFacade;
import br.com.suprasync.negocio.dto.SacOcorrenciaDTO;
import br.com.suprasync.persistencia.Funcionario;
import br.com.suprasync.persistencia.SacEtapa;
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

	@DELETE
	@PUT
	@Path("/removerPrioridades")
	@Produces("text/plain")
	public String removerPrioridadesSac(Integer id) {
		if (id != null) {
			try {
				SacOcorrenciaFacade sacFacade = new SacOcorrenciaFacade();
				sacFacade.consultaNativa("update sac_ocorrencia set rrc = null where codigo = " + id);
				SacOcorrenciaDTO dto = sacFacade.pesquisar(id).getOcorrenciaDTO(null);
				jdados.add(dto.getAsJson());
				setSuccess(true);
			} catch (NamingException | SacOcorrenciaNaoEncontradaException e) {
				e.printStackTrace();
			}			
		}		
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

	@GET
	@Path("/obterFuncionarios")
	@Produces(MediaType.APPLICATION_JSON)//@Produces("text/plain")
	public String obterFuncionarios(@DefaultValue("") @QueryParam("id") String id, @QueryParam("data") String dados) {
		setSuccess(false);
		Gson gson = new Gson();
		try {
			GenericFacade genericFacade = new GenericFacade();
			List<Funcionario> funcionarios = genericFacade.pesquisar(Funcionario.class, null, null);
			for (Iterator<Funcionario> iterator = funcionarios.iterator(); iterator.hasNext();) {
				Funcionario funcionario = (Funcionario) iterator.next();
				if(funcionario.getDataExclusao() == null && funcionario.isAtivoSac()) {
					List<SacEtapa> etapas = new ArrayList<>();
					if (funcionario.getEtapas() != null) {
						
						for (SacEtapa etapa : funcionario.getEtapas()) {
							if (null == etapa.getDataExclusao()) {
								etapas.add(etapa);
							}
						}
						funcionario.setEtapas(etapas);
					}
					jdados.add(parser.parse(gson.toJson(funcionario)));	
				}
			}							
			setSuccess(true);			
		} catch (NamingException e) {
			e.printStackTrace();			
		} catch (ObjetoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return montaResposta();

	}
	
	@GET
	@Path("/obterTreeFuncionarios")
	@Produces(MediaType.APPLICATION_JSON)//@Produces("text/plain")
	public String obterTreeFuncionario() {
		obterFuncionarios(null,  null);
		//String dados = obterFuncionarios(null, null);
		//JsonObject jo = (JsonObject) parser.parse(dados);
		//JsonArray ja = jo.getAsJsonArray("data");
		if (jdados != null) {
			for (int i = 0; i < jdados.size(); i++) {
				JsonObject jfunc = (JsonObject) jdados.get(i);
				
				jfunc.addProperty("text", jfunc.get("nome").getAsString());
				jfunc.addProperty("expanded", true);
				jfunc.add("children", jfunc.get("etapas"));
				jfunc.remove("nome");
				jfunc.remove("etapas");
				jfunc.remove("efetuarAnalise");
				jfunc.remove("providenciarSolucao");
				jfunc.remove("executarSolucao");
				jfunc.remove("providenciarFeedback");
				jfunc.remove("ativoSac");
				jfunc.remove("dataExclusao");
								
				if (jfunc.get("children") != null) {
					JsonArray jetapas = jfunc.get("children").getAsJsonArray();
					for (int j = 0; j < jetapas.size(); j++) {
						JsonObject jetapa = (JsonObject) jetapas.get(j);
						jetapa.addProperty("text", jetapa.get("nome").getAsString());
						jetapa.remove("nome");
						jetapa.addProperty("leaf", true);
						jetapa.remove("dataExclusao");
						jetapa.remove("situacaoInicial");						
					}
				}
				
			}
		}				
		return montaResposta();
	}



}
