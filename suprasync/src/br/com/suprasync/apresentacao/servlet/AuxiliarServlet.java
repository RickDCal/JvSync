package br.com.suprasync.apresentacao.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class AuxiliarServlet {

	public JsonArray jsRecebido;
	public Integer position;
	public Integer max;
	public Integer action;
	public String mensagemRetorno;
	public boolean success;
	public JsonArray jsFiltros;
	public Integer id;
	public String uuid;

	public AuxiliarServlet() {
		super();
	}

	public AuxiliarServlet(String mensagemRetorno, boolean success) {
		super();
		this.mensagemRetorno = mensagemRetorno;
		this.success = success;
	}

	public boolean load(HttpServletRequest request) {

		String parametroString = null;

		try {
			
			parametroString = request.getParameter("id");
			if (parametroString != null) {
				this.id = Integer.parseInt(parametroString);
			}
			
			parametroString = request.getParameter("start");
			if (parametroString != null) {
				this.position = Integer.parseInt(parametroString);
			}

			parametroString = request.getParameter("limit");
			if (parametroString != null) {
				this.max = Integer.parseInt(parametroString);
			}
			
			parametroString = request.getParameter("action");
			if (parametroString != null) {
				this.action = Integer.parseInt(parametroString);
			} else {
				this.action = 0;
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
			} else {
				JsonObject objetoJson = new JsonObject();
				JsonParser jparser = new JsonParser();
				StringBuilder sb = new StringBuilder();
			    BufferedReader br = request.getReader();
			    String str;
			    while( (str = br.readLine()) != null ){
			        sb.append(str);
			    } 
			    if (!sb.toString().isEmpty()) {
			    	objetoJson = (JsonObject) jparser.parse(sb.toString());
				    arrayDados.add(objetoJson);
			    }	
			}
			
			if (this.action == 1) {
				this.jsRecebido = this.trataUuid(arrayDados);
			} else {
				this.jsRecebido = arrayDados;
			}						

			parametroString = request.getParameter("filter");	
			if (parametroString != null) {
				try {
					JsonParser jsonParser = new JsonParser();
					this.jsFiltros = (JsonArray)jsonParser.parse(parametroString);
				} catch (Exception e) {
					e.printStackTrace();
					this.setMensagemRetorno(this.mensagemRetorno + "\n <br>Falha: " + e.getCause().getMessage() + ".");
					return false;
				}
			}			
			return true;

		} catch (NumberFormatException | JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;

	}

	public HttpServletResponse montaHeadResposta(HttpServletResponse response) {

		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");

		return response;

	}

	public void despachaResposta(HttpServletResponse response, JsonObject jsonRetorno) throws IOException {

		jsonRetorno.addProperty("success", this.success);
		if (this.success) {
			if (mensagemRetorno == null || mensagemRetorno.isEmpty()) {
				switch (this.action) {
				case 1: this.mensagemRetorno = "Registro adicionado com sucesso!"; break;
				case 2: this.mensagemRetorno = "Sucesso"; break;
				case 3: this.mensagemRetorno = "Registro atualizado com sucesso!"; break;
				case 4: this.mensagemRetorno = "Registro excluído com sucesso!"; break;
				default: break;
				}
			}
		} else {
			if (mensagemRetorno == null || mensagemRetorno.isEmpty()) {
				this.mensagemRetorno = "Ocorreu uma falha de processamento. Verifique o procedimento ou os dados inseridos.";
			}			
		}		
		jsonRetorno.addProperty("mensagemRetorno", this.mensagemRetorno);		
		response = this.montaHeadResposta(response);
		PrintWriter out = response.getWriter();
		out.println(jsonRetorno.toString());
		out.close();
	}

	public String getMensagemRetorno() {
		return mensagemRetorno;
	}

	public void setMensagemRetorno(String mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public JsonArray trataUuid(JsonArray jsonArray) {
		/*Este método tende a funcionar somente naqueles casos em que não há relacionamentos cujo uuis é utilizado como chave estrangeira.*/
		for (int i = 0; i < jsonArray.size(); i++) {			
			JsonObject objetoJson = (JsonObject) jsonArray.get(i);

			String uuid = null;			
			if (objetoJson != null && objetoJson.isJsonObject() && objetoJson.get("id") != null) {
				try {
					Integer id = objetoJson.get("id").getAsInt(); // não remover esta linha
				} catch (Exception e) {
					uuid = objetoJson.get("id").getAsString();
					objetoJson.addProperty("clientIdProperty", uuid);
					objetoJson.remove("id");					
				} finally {

				}
			}
		}

		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	public <E, T> List<E> objetosInserir(Class<T> classe, JsonArray jsonArray) {
		List<E> list = new ArrayList<E>();

		for (int i = 0; i < jsonArray.size(); i++) {			
			JsonObject objetoJson = (JsonObject) jsonArray.get(i);
			//Gson gson = new Gson();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			Object o = gson.fromJson(objetoJson, classe);		

			list.add((E) o);
		}

		return list;
	}

}
