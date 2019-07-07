package br.com.suprasync.apresentacao.servlet.cadastro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.suprasync.apresentacao.facade.cadastro.UsuarioFacade;
import br.com.suprasync.apresentacao.servlet.AuxiliarServlet;
import br.com.suprasync.apresentacao.servlet.estaticos.GenericServlet;
import br.com.suprasync.persistencia.Usuario;;

@WebServlet("/syncUsuarioServlet")

public class UsuarioServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	public UsuarioServlet() {
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
			//JsonArray arrayDados = auxiliar.jsRecebido;		

			UsuarioFacade usuarioFacade = new UsuarioFacade();
			try {

				switch (action) {
				case 2:

					try {
						Integer id = auxiliar.id;
						
						List<Usuario> usuarios = new ArrayList<Usuario>();	
						Integer totalRegistros = 0;	
						
						if(id != null) {
							usuarios.add((Usuario) usuarioFacade.pesquisar(id));
						} else {
							usuarios = usuarioFacade.pesquisar(position, max);							
						}
						
						for (Usuario usuario : usuarios) {
							if (usuario.getDataExclusao() == null && usuario.getSenha() != null) {								
								JsonObject objetoJson = new JsonObject();
								objetoJson.addProperty("id", usuario.getId());
								objetoJson.addProperty("userName", usuario.getId());
								objetoJson.addProperty("apelido", usuario.getNome());
								objetoJson.addProperty("password", usuario.getSenha());
								objetoJson.addProperty("idFuncionario", usuario.getIdFuncionario());
								objetoJson.addProperty("idPerfil", usuario.getIdPerfil());
								dados.add(objetoJson);
							}							
						}
						totalRegistros = dados.size();

						auxiliar.setSuccess(true);
						retorno.addProperty("total", totalRegistros);
						retorno.add("data", dados);
					} catch (Exception e1) {
						e1.printStackTrace();
						auxiliar.setMensagemRetorno("Falha: " + e1.getCause().getMessage() + ".");
					}
					break;
				case 3:						
					break;
				case 6: 
					break;
				default:
					break;
				}
			} catch (NumberFormatException e) {
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
