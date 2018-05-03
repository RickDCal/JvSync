package br.com.suprasync.apresentacao.servlet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
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
//		try {
//			auxiliar.load(request);
//			retorno.addProperty("idRomaneio", request.getParameter("idRomaneio"));
//			retorno.addProperty("nomeEmpresa", request.getParameter("nomeEmpresa"));
//			TimeUnit.SECONDS.sleep(3);
//			auxiliar.setMensagemRetorno("você invocou a consulta para pesquisar romaneios e aguardou 3 segundos");
//			auxiliar.setSuccess(true);
//			auxiliar.despachaResposta(response, retorno);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}	
		
		request.getParameter("bloco");
		
		//As três variáveis que vem da tela
		int bloco = 10;// Integer.parseInt(request.getParameter("bloco")); //500
		int total = 5;//Integer.parseInt(request.getParameter("total")); //1716
		int inicial = 2;// Integer.parseInt(request.getParameter("total")); //3
		
		
		int pagina = 0;
		int j = 0;
		int k = 1;
		int l = 0;
		
		int blocos = (total / (bloco -(inicial - 1))); //quantidade de blocos inteiros
		int resto = (total % (bloco - (inicial-1)))-1;	//páginas do ultimo bloco fracionado		
		int pgBloco = bloco - inicial; //páginas por bloco regular
		
		JsonArray dados = new JsonArray();	// para testes apenas
		
		while (pagina < total) {
			k = (j > pgBloco) ? k+1 : k;
			j = (j > pgBloco) ? 0 : j;					
			l = (k > blocos) ? resto + inicial : bloco;
			
			//if (inicial + j > 492 || inicial +j < 6 || l < bloco ) { //somente para os testes
				
				System.out.println("Pág " + (inicial +j) +" de " + l + " total " + (pagina + 1) + " de " + total + " bloco " + k); //resultado
				
				JsonObject jo = new JsonObject(); //somente para os testes
				jo.addProperty("Linha", "Pág " + (inicial +j) +" de " + l + " total " + (pagina + 1) + " de " + total + " bloco " + k); ///somente para os testes
				dados.add(jo);//somente para os testes
			//}		//somente para os testes	
			pagina ++; j++;
		}
		
		retorno.add("data", dados);	//somente para os testes
		auxiliar.despachaResposta(response, retorno); //somente para os testes
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
	
	
	
	public void testePag(int total, int inicial) {
		int bloco = 8;
		total = 20;
		inicial = 3;
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		
		int blocos = (total / (bloco -(inicial - 1)));
		int resto = total % bloco;
		
		
		bloco = bloco - inicial;
		
		while (i < 20) {
			j = (j > bloco) ? inicial : j;
			k = (j > bloco) ? k++ : k;		
			l = (k > blocos)?resto:bloco;
			
			System.out.println("Pág" + j +" de " + l);
			i ++; j++;
		}
		
		
		
		
		
		
		
	}

}
