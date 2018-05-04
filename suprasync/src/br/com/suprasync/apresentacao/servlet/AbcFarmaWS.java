package br.com.suprasync.apresentacao.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

@Path("/abcFarma")
public class AbcFarmaWS {
//	private RomaneioFacade romaneioFacade;
//	
//	public AbcFarmaWS() throws NamingException{
//		InitialContext context = new InitialContext();
//		romaneioFacade = (RomaneioFacade) context.lookup("java:global/Suprasoft/Suprasoft-ejb/RomaneioFacade");
//	}
	
	

	@POST
	@Path("/atualizar")
	@Produces("text/plain")
	public String atualizarRomaneioFireBase(@DefaultValue("") @QueryParam("idRomaneio") String idRomaneio , @QueryParam("nomeEmpresa") String nomeEmpresa) throws Exception {
		
		//return "você requisitou abcfarma";		
		Integer numeroPagina = 1;
		String url = "https://webserviceabcfarma.org.br/webservice/";
		
		HttpPost post = new HttpPost(url);
		 
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("cnpj_cpf", "01.003.921/0001-70"));
	    params.add(new BasicNameValuePair("cnpj_sh", "21.499.137/0001-02"));
	    params.add(new BasicNameValuePair("senha", "acessoadmin"));
	    params.add(new BasicNameValuePair("pagina", numeroPagina.toString()));
	    
	    post.setEntity(new UrlEncodedFormEntity(params));
	    
	    
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		
		
		JsonObject json = new JsonObject();
		Integer numeroPagina = 1;
		
		json.addProperty("cnpj_cpf", "01.003.921/0001-70"); //cliente
		json.addProperty("cnpj_sh", "21.499.137/0001-02"); // suprasoft
		json.addProperty("senha", "acessoadmin"); //senha do cliente
		json.addProperty("pagina", numeroPagina.toString());		
		
		Gson gson = new Gson();
		StringEntity entity = new StringEntity(gson.toJson(json));

		HttpPost post = new HttpPost(url);
		post.addHeader(HTTP.CONTENT_TYPE, "application/json");
		//post.addHeader("Authentication", parametroPedidoVenda.getTokenIpsm());
		post.setEntity(entity);
		*/

		HttpResponse response = null;
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			response = httpClient.execute(post);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String mensagem = null;
		Integer status = response.getStatusLine().getStatusCode();

		if (response.getEntity() != null) {
			mensagem = EntityUtils.toString(response.getEntity());
			JsonParser jsonParser = new JsonParser();
			JsonObject respostaJson = null; 	

			try {
				respostaJson = (JsonObject)jsonParser.parse(mensagem);
				
				
				
				
				
				
				
				
				
				
				
				
				
				//this.gravaArquivoResposta("Verifica_elegibilidade-", respostaJson.toString());
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
				//this.gravaArquivoResposta("Verifica_elegibilidade-" + solicitacao + "-" + dataFormat.format(cal.getTime()), mensagem);
			}	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		return mensagem;
		
		
//		final Client client = ClientBuilder.newClient();
//        final WebTarget target = client.target("https://webserviceabcfarma.org.br/webservice/");
//
//        Form form = new Form();
//        form.param("cnpj_cpf", "cnpj/cpf do associado");
//        form.param("cnpj_sh", "cnpj da software house");
//        form.param("senha", "senha do associado no portal");
//        form.param("pagina", "numero da pagina [default=1]");
//
//        final Response response = target.request().post(Entity.form(form));
//        final String json = response.readEntity(String.class);

       // System.out.println(json);	
		
	
	}
	
//	@GET
//	@Path("/obterRomaneio")
//	@Produces("text/plain")
//	public String obterRomaneioFireBase(@DefaultValue("") @QueryParam("idRomaneio") String idRomaneio , @QueryParam("nomeEmpresa") String nomeEmpresa) throws Exception {
//		return romaneioFacade.obterRomaneioFireBase(Long.parseLong(idRomaneio), String nomeEmpresa);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
