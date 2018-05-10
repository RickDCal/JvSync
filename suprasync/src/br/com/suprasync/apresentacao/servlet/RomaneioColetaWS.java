package br.com.suprasync.apresentacao.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.http.client.HttpClient;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

@Path("/romaneioColeta")
public class RomaneioColetaWS {

	@GET
	@Path("/obterRomaneio")
	@Produces("text/plain")
	public String atualizarRomaneioFireBase(@DefaultValue("") @QueryParam("idRomaneio") String idRomaneio , @QueryParam("nomeEmpresa") String nomeEmpresa) throws Exception {

		TimeUnit.SECONDS.sleep(2);
		return "você invocou a consulta para obter romaneios e aguardou 2 segundos";
		
	}
	
	@PUT
	@Path("/atualizarRomaneio")
	@Produces("text/plain")
	public String atualiza () throws ServletException, IOException, InterruptedException {
		
			TimeUnit.SECONDS.sleep(1);
			return "você invocou a consulta para atualizar romaneios e aguardou 1 segundo";

	}
	
	@POST
	@Path("/incluirRomaneio")
	@Produces("text/plain")
	public String incluir() throws ServletException, IOException, InterruptedException {
		
			TimeUnit.SECONDS.sleep(2);
			return "você invocou a consulta para incluir romaneios e aguardou 2 segundos";			
		
	}
	
	@DELETE
	@Path("/removerRomaneio")
	@Produces("text/plain")
	public String delete() throws ServletException, IOException, InterruptedException {
		
			TimeUnit.SECONDS.sleep(2);
			return "você invocou a consulta para remover romaneios e aguardou 1 segundo";
		
	}


}
