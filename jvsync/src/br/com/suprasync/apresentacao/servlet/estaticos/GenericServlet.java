package br.com.suprasync.apresentacao.servlet.estaticos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.suprasync.apresentacao.facade.GenericFacade;
import br.com.suprasync.apresentacao.servlet.AuxiliarServlet;
import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;

@WebServlet("/genericServlet")

public class GenericServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*SOMETE OBJETOS MUITO SIMPLES PODEM USAR ESTA CLASSE*/

	//	@EJB
	//	private GenericFacade genericFacade;

	public GenericServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@SuppressWarnings("unchecked")
	protected <T> void  doPost(HttpServletRequest request, HttpServletResponse response, Class<T> classe, String nomeObjeto, Boolean gsonExposeAnotation) throws ServletException, IOException, NamingException {

		GenericFacade genericFacade = new GenericFacade();

		JsonObject retorno = new JsonObject();	
		JsonArray dados = new JsonArray();
		String mensagemRetorno = "Ocorreu uma falha de processamento. Verifique o procedimento ou os dados inseridos.";
		Boolean success = false;
		//retorno.addProperty("success", false);	

		String parametroString = null;

		Integer position = null;		
		parametroString = request.getParameter("start");
		if (parametroString != null) {
			position = Integer.parseInt(parametroString);
		}

		Integer max = null;
		parametroString = request.getParameter("limit");
		if (parametroString != null) {
			max = Integer.parseInt(parametroString);
		}	

		parametroString = request.getParameter("data");
		JsonArray arrayDados = new JsonArray();			

		if (parametroString != null) {
			JsonObject objetoJson = new JsonObject();
			JsonParser jparser = new JsonParser();
			try {
				objetoJson = (JsonObject) jparser.parse(parametroString);
				arrayDados.add(objetoJson);
			} catch (Exception e) {
				arrayDados = (JsonArray) jparser.parse(parametroString);
			}
		}

		String act = request.getParameter("action");

		int action = 0;
		if (act != null) {
			action = Integer.parseInt(act);
		}

		try {

			JsonParser jsonParser = new JsonParser();
			Gson gson;
			if (gsonExposeAnotation) {
				gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").excludeFieldsWithoutExposeAnnotation().create();	
			} else {
				gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			}

			switch (action) {
			case 1:
				try {					
					for (int i = 0; i < arrayDados.size(); i++) {					

						JsonObject objetoJson = (JsonObject) arrayDados.get(i);

						//Gson gson = new Gson();

						objetoJson.remove("id");
						objetoJson.addProperty("id", 0);						

						Object objeto = gson.fromJson(objetoJson, classe);

						genericFacade.cadastrar(objeto); 				

						objetoJson = new JsonObject();						
						objetoJson = (JsonObject) jsonParser.parse(gson.toJson(objeto));

						String novoId = objetoJson.get("id").getAsString();		

						mensagemRetorno = "Registro adicionado com sucesso! " + nomeObjeto + " <b>Código: " + novoId + ".</b>";
						success = true;

						retorno.add("data", objetoJson);							
					}										

				} catch (Exception e) {
					e.printStackTrace();
					mensagemRetorno = mensagemRetorno + "\n <br>Falha: " + e.getCause().getMessage() + ".";
				}
				break;

			case 2:


				parametroString = request.getParameter("id");
				if (parametroString != null) {
					Integer id = Integer.parseInt(parametroString);

					Object objeto = genericFacade.pesquisar(classe, id);				
					if (objeto != null) {
						JsonObject objetoJson = (JsonObject) jsonParser.parse(gson.toJson(objeto));
						if (objeto.getClass() == SacOcorrencia.class){
							try {
								objetoJson.get("usuarioSupra").getAsJsonObject().remove("senha");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						dados.add(objetoJson);
					}					
					success = true;
				} else {

					@SuppressWarnings("rawtypes")
					List objetos = new ArrayList();	
					Integer totalRegistros = 0;
					parametroString = request.getParameter("filter");	//era query				

					if (parametroString != null) {						

						String tipoFiltro = null;
						String valorFiltro = null;
						try {
							JsonArray filters = (JsonArray)jsonParser.parse(parametroString);
							JsonObject objetoJson = (JsonObject) filters.get(0);
							tipoFiltro = objetoJson.get("property").getAsString();
							valorFiltro = objetoJson.get("value").getAsString();

						} catch (Exception e) {
							e.printStackTrace();
							mensagemRetorno = mensagemRetorno + "\n <br>Falha: " + e.getCause().getMessage() + ".";

						}

						//TODO tratar aplica��o dos filtros

						if (tipoFiltro != null && tipoFiltro.equalsIgnoreCase("id") && valorFiltro != null) {							
							Object objeto = genericFacade.pesquisar(classe,(Integer.parseInt(valorFiltro)));
							JsonObject objetoJson = (JsonObject) jsonParser.parse(gson.toJson(objeto));
							if (objeto.getClass() == SacOcorrencia.class){
								try {
									objetoJson.get("usuarioSupra").getAsJsonObject().remove("senha");
								} catch (Exception e) {
									e.printStackTrace();
								}
							}							
							objetos.add(objeto);
							totalRegistros = objetos.size();							
						}						

					}else{
						objetos = genericFacade.pesquisar(classe, position, max);
					}

					for (Object objeto : objetos) {						
						JsonObject objetoJson = (JsonObject) jsonParser.parse(gson.toJson(objeto));
						if (objeto.getClass() == SacOcorrencia.class){
							try {
								objetoJson.get("usuarioSupra").getAsJsonObject().remove("senha");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						dados.add(objetoJson);											
					} 
					success = true;
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

							Object objeto = gson.fromJson(objetoJson, classe);

							genericFacade.atualizar(objeto);
							objeto = genericFacade.pesquisar(classe, objetoJson.get("id").getAsInt()); //recarrega o objeto

							mensagemRetorno = "Registro de " + nomeObjeto + " atualizado com sucesso!";

							objetoJsonResposta = (JsonObject) jsonParser.parse(gson.toJson(objeto));													

							dados.add(objetoJsonResposta);
							retorno.add("data", dados);
						}
					}

					success = true;
				} catch (Exception e) {
					e.printStackTrace();
					mensagemRetorno = mensagemRetorno + "\n <br>Falha: " + e.getCause().getMessage() + ".";
				}				
				break;

			case 4:				
				try {

					for (int i = 0; i < arrayDados.size(); i++) {
						JsonObject objetoJson = (JsonObject) arrayDados.get(i);					

						if(objetoJson.get("id").getAsString() != "0") {
							Object objetoExistente =  genericFacade.pesquisar(classe, objetoJson.get("id").getAsInt());
							genericFacade.remover(objetoExistente);
							success = true;
							mensagemRetorno = "Registro de " + nomeObjeto + " Nº " + objetoJson.get("id").getAsString() + " excluído.";
						}
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ObjetoNaoEncontradoException e) {
					e.printStackTrace();
				}			
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
		}

		//Montando os Header de resposta da requisi��o HTTP
		AuxiliarServlet auxiliar = new AuxiliarServlet();
		response = auxiliar.montaHeadResposta(response);

		//Escrevendo e enviando a resposta
		retorno.addProperty("success", success);
		retorno.addProperty("mensagemRetorno", mensagemRetorno);
		PrintWriter out = response.getWriter();
		out.println(retorno.toString());
		out.close();	

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
