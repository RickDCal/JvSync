package br.com.suprasync.apresentacao.servlet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import br.com.suprasync.apresentacao.servlet.estaticos.GenericServlet;;

@WebServlet("/romaneioColeta")

public class RomaneioColetaServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
	
	private AuxiliarServlet auxiliar = new AuxiliarServlet();
	private JsonObject retorno = new JsonObject();	

	public RomaneioColetaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			auxiliar.load(request);
			retorno.addProperty("idRomaneio", request.getParameter("idRomaneio"));
			retorno.addProperty("nomeEmpresa", request.getParameter("nomeEmpresa"));
			TimeUnit.SECONDS.sleep(3);
			auxiliar.setMensagemRetorno("você invocou a consulta para pesquisar romaneios e aguardou 3 segundos");
			auxiliar.setSuccess(true);
			auxiliar.despachaResposta(response, retorno);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			auxiliar.load(request);
			retorno.addProperty("idRomaneio", request.getParameter("idRomaneio"));
			retorno.addProperty("nomeEmpresa", request.getParameter("nomeEmpresa"));
			TimeUnit.SECONDS.sleep(2);
			auxiliar.setMensagemRetorno("você invocou a consulta para inserir romaneios e aguardou 2 segundos");
			auxiliar.setSuccess(true);
			auxiliar.despachaResposta(response, retorno);
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			auxiliar.load(request);
			retorno.addProperty("idRomaneio", request.getParameter("idRomaneio"));
			retorno.addProperty("nomeEmpresa", request.getParameter("nomeEmpresa"));
			TimeUnit.SECONDS.sleep(3);
			auxiliar.setMensagemRetorno("você invocou a consulta para alterar romaneios e aguardou 3 segundos");
			auxiliar.setSuccess(true);
			auxiliar.despachaResposta(response, retorno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			auxiliar.load(request);
			retorno.addProperty("idRomaneio", request.getParameter("idRomaneio"));
			retorno.addProperty("nomeEmpresa", request.getParameter("nomeEmpresa"));
			TimeUnit.SECONDS.sleep(2);
			auxiliar.setMensagemRetorno("você invocou a consulta para excluir romaneios e aguardou 2 segundos");
			auxiliar.setSuccess(true);
			auxiliar.despachaResposta(response, retorno);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
