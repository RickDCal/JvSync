package br.com.suprasync.apresentacao.servlet.sac;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.suprasync.apresentacao.facade.GenericFacade;
import br.com.suprasync.apresentacao.facade.sac.SacOcorrenciaFacade;
import br.com.suprasync.apresentacao.servlet.AuxiliarServlet;
import br.com.suprasync.apresentacao.servlet.estaticos.GenericServlet;
import br.com.suprasync.negocio.dto.SacOcorrenciaDTO;
import br.com.suprasync.persistencia.SacEtapa;
import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.suprasync.persistencia.filter.SacOcorrenciaFilter;;

@WebServlet("/ocorrenciaServlet")

public class SacOcorrenciaServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	public SacOcorrenciaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		AuxiliarServlet auxiliar = new AuxiliarServlet();
		JsonObject retorno = new JsonObject();		
		try {		
			auxiliar.load(request);				
			JsonArray dados = new JsonArray();		
			
			Integer position = auxiliar.position;		
			Integer max = auxiliar.max;
			Integer action = auxiliar.action;
			JsonArray arrayDados = auxiliar.jsRecebido;		
			
			GenericFacade genericFacade = new GenericFacade();
			SacOcorrenciaFacade sacFacade = new SacOcorrenciaFacade();
			JsonParser jsonParser = new JsonParser();
			Gson gson = new Gson();

			try {

				switch (action) {
				case 2:
					if (auxiliar.id != null) {
						Integer id = auxiliar.id;

						SacOcorrencia ocorrencia = (SacOcorrencia) genericFacade.pesquisar(SacOcorrencia.class, id);				
						if (ocorrencia != null) {
							SacOcorrenciaDTO ocorrenciaDTO = new SacOcorrenciaDTO();							
							JsonObject objetoJson = (JsonObject) jsonParser.parse(gson.toJson(ocorrenciaDTO.convertToDTO(ocorrencia)));
							dados.add(objetoJson);
						}					
						auxiliar.setSuccess(true);
					} else {

						List<SacOcorrencia> ocorrencias = new ArrayList<SacOcorrencia>();	
						Integer totalRegistros = 0;		

						if (auxiliar.jsFiltros != null) {
							
							SacOcorrenciaFilter filter = new SacOcorrenciaFilter(auxiliar.jsFiltros);
							ocorrencias = sacFacade.obter(filter);
							totalRegistros = ocorrencias.size();

//							String tipoFiltro = null;
//							String valorFiltro = null;
//							try {
//								JsonArray filters = (auxiliar.jsFiltros);
//								JsonObject objetoJson = (JsonObject) filters.get(0);
//								tipoFiltro = objetoJson.get("property").getAsString();
//								valorFiltro = objetoJson.get("value").getAsString();
//							} catch (Exception e) {
//								e.printStackTrace();
//								auxiliar.setMensagemRetorno("Falha: " + e.getCause().getMessage() + ".");
//							}
//
//							//TODO criar SACOcorrenciaFilter
//
//							if (tipoFiltro != null && tipoFiltro.equalsIgnoreCase("id") && valorFiltro != null) {							
//								SacOcorrencia ocorrencia = (SacOcorrencia) genericFacade.pesquisar(SacOcorrencia.class,(Integer.parseInt(valorFiltro)));						
//								ocorrencias.add(ocorrencia);
//								totalRegistros = ocorrencias.size();							
//							}						

						}else{
							ocorrencias = genericFacade.pesquisar(SacOcorrencia.class, position, max);
						}

						for (SacOcorrencia ocorrencia : ocorrencias) {	
							SacOcorrenciaDTO ocorrenciaDTO = new SacOcorrenciaDTO();
							JsonObject objetoJson = (JsonObject) jsonParser.parse(gson.toJson(ocorrenciaDTO.convertToDTO(ocorrencia)));							
							dados.add(objetoJson);											
						} 
						auxiliar.setSuccess(true);
						retorno.addProperty("total", totalRegistros);
					}
					retorno.add("data", dados);

					break;

				case 3:				
					try {
						for (int i = 0; i < arrayDados.size(); i++) {
							JsonObject objetoJson = (JsonObject) arrayDados.get(i);
							if(objetoJson.get("id").getAsInt() > 0) {

								JsonObject objetoJsonResposta = new JsonObject();

								SacOcorrencia ocorrencia = gson.fromJson(objetoJson, SacOcorrencia.class);

								genericFacade.atualizar(ocorrencia);
								ocorrencia = (SacOcorrencia) genericFacade.pesquisar(SacOcorrencia.class, objetoJson.get("id").getAsInt()); //recarrega o objeto

								auxiliar.setMensagemRetorno("Ocorrência atualizada com sucesso!");
								
								SacOcorrenciaDTO ocorrenciaDTO = new SacOcorrenciaDTO();								
								objetoJsonResposta = (JsonObject) jsonParser.parse(gson.toJson(ocorrenciaDTO.convertToDTO(ocorrencia)));										

								dados.add(objetoJsonResposta);
								retorno.add("data", dados);
							}
						}

						auxiliar.setSuccess(true); 
					} catch (Exception e) {
						e.printStackTrace();
						auxiliar.setMensagemRetorno("Falha: " + e.getCause().getMessage() + ".");
					}				
					break;
				case 6: 
					List<SacEtapa>etapas = genericFacade.pesquisar(SacEtapa.class, position, max);
					for (SacEtapa sacEtapa : etapas) {
						if (sacEtapa.getDataExclusao() == null) {
							dados.add(genericFacade.objetoJson(sacEtapa));
						}
					}
					retorno.add("data", dados);	
					auxiliar.setSuccess(true);
				break;
				default:
					break;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ObjetoNaoEncontradoException e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			
		}
		
		auxiliar.despachaResposta(response, retorno);	
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
