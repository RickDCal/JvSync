package br.com.suprasync.apresentacao.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.Date;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
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

@Path("/abcFarma")
public class AbcFarmaWS {

	@POST
	@Path("/atualizar")
	@Produces("text/plain")
	public String atualizarRomaneioFireBase(@DefaultValue("") @QueryParam("idRomaneio") String idRomaneio , @QueryParam("nomeEmpresa") String nomeEmpresa) throws Exception {

		Integer pagina = 1;
		String retorno = buscaDados(pagina);

		if (retorno != null) {
			Integer totalPaginas = null;
			System.out.println(retorno);
			JsonArray lista = new JsonArray();

			try {
				
				int j = 1;
				int k = 0;
				
				JsonParser jsonParser = new JsonParser();
				JsonObject json = (JsonObject)jsonParser.parse(retorno);
				if (json.get("total_paginas") != null) {
					totalPaginas = json.get("total_paginas").getAsInt();				
				}
				incrementaLista(lista, retorno); //adicionando dados da primeira página

				if (totalPaginas > 1) {//adicionando dados das demais páginas se houver
					for (int i = 2; i <= totalPaginas; i++) {
						incrementaLista(lista, buscaDados(i)); j++;						
					}				
				}
				//FilterProdutoDTO filter = new FilterProdutoDTO();				
				
				j = 1;
				StringBuilder text = new StringBuilder("EAN|Nome|Fabricante|PMC20|PMC18|PMC175|PMC17|PMC12|PMC0|PF20|PF18|PF175|PF17|PF12|PF0|");
				text.append(System.getProperty("line.separator"));
				for (int i = 0; i < lista.size(); i++) {
					JsonObject item = (JsonObject) lista.get(i);
					text.append(new BigInteger(item.get("EAN").getAsString().replace(" ", ""))).append("|");
					text.append(item.get("NOME")).append("|");
					text.append(item.get("NOME_FABRICANTE")).append("|");
					text.append(item.get("PMC_20").getAsDouble()).append("|");
					text.append(item.get("PMC_18").getAsDouble()).append("|");
					text.append(item.get("PMC_17").getAsDouble()).append("|");
					text.append(item.get("PMC_17_5").getAsDouble()).append("|");
					text.append(item.get("PMC_12").getAsDouble()).append("|");
					text.append(item.get("PMC_0").getAsDouble()).append("|");
					text.append(item.get("PF_20").getAsDouble()).append("|");
					text.append(item.get("PF_18").getAsDouble()).append("|");
					text.append(item.get("PF_17").getAsDouble()).append("|");
					text.append(item.get("PF_17_5").getAsDouble()).append("|");
					text.append(item.get("PF_12").getAsDouble()).append("|");
					text.append(item.get("PF_0").getAsDouble()).append("|");
					text.append(System.getProperty("line.separator"));
					k++;
//					if (k>1000) {						
//						k=0;
//						gravaArquivo("ABCFarma_pag" + j + "_" + totalPaginas + "_" + new Date().getTime() +".txt", text.toString());
//						j++;
//						text= new StringBuilder();
//					}
					System.out.println(item.get("EAN")+ " " + "registro: " + k + "página " + j);
					
				}
				
				text = new StringBuilder();
				String termo = "";
				
				for (int i = 0; i < lista.size(); i++) {
					termo = new String(new char[136]).replace("\0", " ");
					text.append(termo);
					JsonObject item = (JsonObject) lista.get(i);
					termo = ("" + item.get("PMC_18").getAsDouble() + "00000000000");
					text.append(termo.substring(0, 11));
					termo = ("" + item.get("PF_18").getAsDouble() + "00000000000");
					text.append(termo.substring(0, 11));
					text.append("00000000000");
					termo = ("" + item.get("PMC_17").getAsDouble() + "00000000000");
					text.append(termo.substring(0, 11));
					termo = ("" + item.get("PF_17").getAsDouble() + "00000000000");
					text.append(termo.substring(0, 11));
					text.append("00000000000");
					termo = ("" + item.get("PMC_12").getAsDouble() + "00000000000");
					text.append(termo.substring(0, 11));
					termo = ("" + item.get("PF_12").getAsDouble() + "00000000000");
					text.append(termo.substring(0, 11));
					text.append("00000000000");
					termo = new String(new char[28]).replace("\0", " "); 
					text.append(termo);
					termo = ("" + new BigInteger(item.get("EAN").getAsString().replace(" ", "")) + "             ").substring(0,13);
					text.append(termo);
					text.append("000");
					switch (item.get("ID_LCCT").getAsString()) {
					case "+": text.append(1); break;
					case "-": text.append(2); break;
					case "N": text.append(3); break;
					case "O": text.append(4); break;
					default: break;
					}
					termo = new String(new char[130]).replace("\0", " "); 
					text.append(termo);
					termo = ("" + item.get("PMC_20").getAsDouble() + "00000000000");
					text.append(termo.substring(0, 11));
					termo = ("" + item.get("PF_20").getAsDouble() + "00000000000");
					text.append(termo.substring(0, 11));
					termo = new String(new char[44]).replace("\0", " ");
					text.append(termo);
					termo = ("" + item.get("PMC_0").getAsDouble() + "00000000000");
					text.append(termo.substring(0, 11));
					termo = ("" + item.get("PF_0").getAsDouble() + "00000000000");
					text.append(termo.substring(0, 11));
					text.append("00000000000");
					text.append((""+item.get("REGISTRO_ANVISA") +"             ").substring(0,13));					
					text.append(System.getProperty("line.separator"));
					k++;
					
					
					
//					if (k>1000) {						
//						k=0;
//						gravaArquivo("ABCFarma_pag" + j + "_" + totalPaginas + "_" + new Date().getTime() +".txt", text.toString());
//						j++;
//						text= new StringBuilder();
//					}
					System.out.println(item.get("EAN")+ " " + "registro: " + k + "página " + j);
					
				}

				gravaArquivo("ABCFarmaLayoutOficial" + new Date().getTime() +".txt", text.toString());
				
				
				////////////////////////////////////////////////////////////////////////////
				text = new StringBuilder();
				k=0;
				j=1;
				int l=0;
				for (int i = 0; i < lista.size(); i++) {
					JsonObject item = (JsonObject) lista.get(i);
					//pmc
					text.append("update produto set preco_medio_consumidor = case produto.aliquota_abc_farma")
					.append(" when 20 then ").append(item.get("PMC_20").getAsDouble())
					.append(" when 18 then ").append(item.get("PMC_18").getAsDouble())
					.append(" when 17.5 then ").append(item.get("PMC_17_5").getAsDouble())
					.append(" when 17 then ").append(item.get("PMC_17").getAsDouble())
					.append(" when 12 then ").append(item.get("PMC_12").getAsDouble())
					.append(" when 0 then ").append(item.get("PMC_0").getAsDouble())
					.append(" else produto.preco_medio_consumidor ").append(" end ")
					.append(" where Codigo_barra = ").append("'")
					.append(new BigInteger(item.get("EAN").getAsString().replace(" ", ""))).append("'");
					text.append(System.getProperty("line.separator"));
					text.append("go");
					text.append(System.getProperty("line.separator"));
					// PF
					text.append("update produto set preco_fabricante = case produto.aliquota_abc_farma")
					.append(" when 20 then ").append(item.get("PF_20").getAsDouble())
					.append(" when 18 then ").append(item.get("PF_18").getAsDouble())
					.append(" when 17.5 then ").append(item.get("PF_17_5").getAsDouble())
					.append(" when 17 then ").append(item.get("PF_17").getAsDouble())
					.append(" when 12 then ").append(item.get("PF_12").getAsDouble())
					.append(" when 0 then ").append(item.get("PF_0").getAsDouble())
					.append(" else produto.preco_medio_consumidor ").append(" end ")
					.append(" where Codigo_barra = ").append("'")
					.append(new BigInteger(item.get("EAN").getAsString().replace(" ", ""))).append("'");
					text.append(System.getProperty("line.separator"));
					text.append("go");
					text.append(System.getProperty("line.separator"));	
					k++; l++;
					if (k>1000) {
						k=0;
						gravaArquivo("ABCFarmaSQL_pag" + j + "_" + totalPaginas + "_" + new Date().getTime() +".sql", text.toString());
						j++;
						text = new StringBuilder();
					}
				}
				if (totalPaginas > 1) {
					gravaArquivo("ABCFarmaSQL_pag" + j + "_" + totalPaginas + "_" + new Date().getTime() +".sql", text.toString());//última página
				}
				
				
				//gravaArquivo("SQL_AbcFarma" + new Date().getTime() +".sql", text.toString());
				
				
				retorno = "Páginas lidas: " + j + " linhas geradas: " +l;

				return retorno;

			} catch (Exception e1) {
				e1.printStackTrace();
				return "Erro";
			}
		}
		return null;
	}

	private String buscaDados(Integer pagina) {
		
		final Client client = ClientBuilder.newClient();
		final WebTarget target = client.target("https://webserviceabcfarma.org.br/webservice/");

		Form form = new Form();
		form.param("cnpj_cpf", "01.003.921/0001-70");
		form.param("cnpj_sh", "21.499.137/0001-02");
		form.param("senha", "acessoadmin");
		form.param("pagina", pagina.toString());
		//form.param("limit", ""+1);

		try {
			final Response response = target.request().post(Entity.form(form));
			final String retorno = response.readEntity(String.class);
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	private JsonArray incrementaLista(JsonArray lista, String retorno) {
		JsonParser jsonParser = new JsonParser();			
		JsonObject json = (JsonObject)jsonParser.parse(retorno);
		if (json.get("data") != null) {
			JsonArray adicionar = json.get("data").getAsJsonArray();
			lista.addAll(adicionar);
		}
		return lista;
	}

	public void gravaArquivo(String nomeArquivo, String text) {
		try {
			File diretorio = new File("c:\\Supramais-Web");
			if (diretorio.exists() == false) {
				diretorio.mkdir();
			}

			File file = new File("c:/Supramais-Web/" + nomeArquivo);
			if (file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(text);
			bw.flush();
			bw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	

}
