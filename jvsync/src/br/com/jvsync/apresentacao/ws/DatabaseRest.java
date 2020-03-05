package br.com.jvsync.apresentacao.ws;

import java.util.Date;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.jvsync.apresentacao.facade.GenericFacade;
import br.com.jvsync.persistencia.Bairro;
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.Cidade;
import br.com.jvsync.persistencia.Endereco;
import br.com.jvsync.persistencia.ItemNotaFiscal;
import br.com.jvsync.persistencia.Logradouro;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;
import br.com.jvsync.persistencia.Regiao;
import br.com.jvsync.persistencia.Rota;
import br.com.jvsync.persistencia.TipoOperacao;
import br.com.jvsync.persistencia.TipoVenda;
import br.com.jvsync.persistencia.TipoVolume;
import br.com.jvsync.persistencia.UF;
import br.com.jvsync.persistencia.Vendedor;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.jvsync.util.EmailTools;

@Path("/database")
public class DatabaseRest {

	private JsonObject retorno = new JsonObject();
	private JsonArray jdados = new JsonArray();	
	private boolean success = false;
	private String mensagemRetorno = null;	
	
	public String montaResposta() {
		retorno.addProperty("success", success);
		retorno.add("data", jdados);
		if (mensagemRetorno != null) {
			retorno.addProperty("mensagemRetorno", mensagemRetorno);
		}
		return retorno.toString();
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	@GET
	@Path("/atualizarDados")
	@Produces(MediaType.APPLICATION_JSON)
	public String atualizarDados(@QueryParam("tabela") String tabela) {
//		setSuccess(false);
//		Object object = null;
//		try {
//			GenericFacade genericFacade = new GenericFacade();
//			switch (tabela.toLowerCase()) {
//			case "all": return genericFacade.atualizaBancoDados();
//			case "tgfcab": object = new CabecalhoNotaFiscal();break;
//			case "tgfite": object = new ItemNotaFiscal();break;
//			case "tgfpar": object = new Parceiro();break;
//			case "tgfpro": object = new Produto();break;
//			case "tgftpv": object = new TipoVenda();break;
//			case "tgfvoa": object = new TipoVolume();break;
//			case "tgfven": object = new Vendedor();break;
//			case "tgftop": object = new TipoOperacao();break;
//			case "tsibai": object = new Bairro();break;
//			case "tsicid": object = new Cidade();break;
//			case "tsireg": object = new Regiao();break;
//			case "tsiend": object = new Endereco();break;
//			case "tgfrot": object = new Rota();break;
//			case "tfplgr": object = new Logradouro();break;
//			case "tsiufs": object = new UF();break;
//			
//
//			default: break;
//			}
//			
//			System.out.println(new Date().toString());
//			
//			//não consegui fazer funcionar
//			//EmailTools email = new EmailTools();
//			//email.enviaEmail();
//			
//			if (object != null  ) {
//				return genericFacade.atualizaDados(object.getClass());
//			} else {
//				return "Não foi possivel determinar a entidade a ser atualizada. Tabela informada: " + tabela;
//			}
//								
//		} catch (NamingException | ObjetoNaoEncontradoException e) {
//			e.printStackTrace();
//		} 			
		return montaResposta();
	}
}
