package br.com.suprasync.apresentacao.ws;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.suprasync.apresentacao.facade.cadastro.ClienteFacade;
import br.com.suprasync.apresentacao.facade.financeiro.ContaFacade;
import br.com.suprasync.apresentacao.facade.versao.VersaoSistemaFacade;
import br.com.suprasync.persistencia.Cliente;
import br.com.suprasync.persistencia.Parcela;
import br.com.suprasync.persistencia.VersaoSistema;
import br.com.suprasync.persistencia.enumerate.ProdutoSuprasoftEnum;
import br.com.suprasync.persistencia.enumerate.TempoEnum;
import br.com.suprasync.util.Utilities;

@Path("/supraMais")
public class LoginHelper {

	private VersaoSistemaFacade versaoFacade;
	private ContaFacade contaFacade;
	private ClienteFacade parceiroFacade;
	private JsonParser parser = new JsonParser();

	private boolean success;
	private String responseText;
	private JsonObject retorno = new JsonObject();

	private StringBuilder mensagemUsuario = new StringBuilder();
	private StringBuilder mensagemUsuarioFinanceiro = new StringBuilder();
	private StringBuilder mensagemUsuarioAdmin = new StringBuilder();		
	private int indVersaoValida = 1; // começa sendo válida


	public LoginHelper() throws NamingException{		
		versaoFacade = new VersaoSistemaFacade();
		contaFacade = new ContaFacade();
		parceiroFacade = new ClienteFacade();
		/* Somente SupraMWEB:
		 * InitialContext context = new InitialContext();
		 * versaoFacade = (VersaoSistemaFacade) context.lookup("java:global/suprasyncEAR/suprasync/VersaoSistemaFacade");
		 * contaFacade = (ContaFacade) context.lookup("java:global/suprasyncEAR/suprasync/ContaFacade");
		 * parceiroFacade = (ClienteFacade) context.lookup("java:global/suprasyncEAR/suprasync/ClienteFacade");
		*/
	}

	@POST
	@Path("/loginHelper")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json") 
	public String validaLoginEmpresa(String jsonString) throws Exception {

//		StringBuilder msgAdmin = new StringBuilder();
//		StringBuilder msgFinanceiro = new StringBuilder();
//		StringBuilder msgUsuario = new StringBuilder();		

		try {
			JsonObject json =  null;
			if (null != jsonString && !jsonString.isEmpty()) {
				json = (JsonObject) parser.parse(jsonString);

				VersaoSistema versao = versaoFacade.obterPorNumeroVersao(json.get("numeroVersao").getAsString(), ProdutoSuprasoftEnum.SUPRA_MAIS);

				if (null != versao) {
					Gson gson = new Gson();
					JsonParser parser = new JsonParser();
					JsonObject jVersao = parser.parse(gson.toJson(versao)).getAsJsonObject();
					retorno.add("versao", jVersao);

					if (null != versao.getDataSuspensao() && versao.getDataSuspensao().before(new Date())) {
						indVersaoValida = 0;
						mensagemUsuario.append("Esta versão do sistema foi descontinuada e portanto não pode mais ser acessada.")
						.append(System.lineSeparator()).append("Proceda com a atualização acessando o site da SupraSoft.");
						//mensagemUsuarioAdmin = mensagemUsuarioFinanceiro = mensagemUsuario;
						montaRetorno();
						setSuccess(true);//requisição processada com sucesso apesar de a versão não ser válida
						return enviaRetorno();			
					} else if (null != versao.getDataSuspensao() && Utilities.incrementaData(versao.getDataSuspensao(), TempoEnum.DIA, -15).before(new Date())) {
						mensagemUsuarioAdmin.append("Esta versão do sistema será descontinuada em ")
						.append((versao.getDataSuspensao().getTime() - new Date().getTime()) / 86400000L ).append(" dias.")
						.append(System.lineSeparator()).append("Proceda com a atualização acessando o site da SupraSoft.");
						//mensagemUsuarioAdmin = msgAdmin.toString();				
					} 
					
					VersaoSistema ultimaVersao = versaoFacade.obterVersaoAtivaMaisRecente(ProdutoSuprasoftEnum.SUPRA_MAIS);
					if (versao.getDataLancamento().before(ultimaVersao.getDataLancamento())) {
						retorno.addProperty("ultimaVersao", ultimaVersao.getNumeroVersao());
					}

					//FilterParceiroDTO filter = new FilterParceiroDTO(null, json.get("cnpjCliente").getAsString(),null);
					int i = 0;
					//for (Cliente parceiro : parceiroFacade.pesquisarPorFiltro(filter)) {	// porque pode haver mais de um cliente com mesmo cnpj	
					for (Cliente parceiro : parceiroFacade.pesquisarPorCNPJ(null, null, json.get("cnpjCliente").getAsString())) {	// porque pode haver mais de um cliente com mesmo cnpj	
						List<Parcela> parcelas = contaFacade.buscarParcelasEmAtrasoPorParceiro(Long.valueOf(parceiro.getId()));					
						if (null != parcelas && parcelas.size() > 0) {
							i++;
						}					
					}	
					if (i > 0) mensagemUsuarioFinanceiro.append("Foram encontradas pendências financeiras junto à Suprasoft.")
					.append(System.lineSeparator()).append("Evite a suspensão da licença de uso entrando em contato com a Suprasoft para regularização.");		

					setResponseText("ok");
					setSuccess(true);
				} else {
					indVersaoValida = 0;
					mensagemUsuario.append("Não foi possivel efetuar a verificação da versão em uso. Entre em contato com a Suprasoft");
					setSuccess(true);
					setResponseText("A versão informada não foi encontrada no banco de dados.");			
				}
			} else {
				setResponseText("Não foram enviados dados para a requisição ou estão incorretos.");
			}
			montaRetorno();
			return enviaRetorno();
		} catch (Exception e) {
			e.printStackTrace();
			responseText = "Ocorreu uma falha ao tentar validar os dados da versão em uso.";
			return enviaRetorno();
		}
	}

	public void montaRetorno() {
		if (mensagemUsuario != null && !mensagemUsuario.toString().isEmpty()) retorno.addProperty("mensagemUsuario", mensagemUsuario.toString());
		if (mensagemUsuarioFinanceiro != null && !mensagemUsuarioFinanceiro.toString().isEmpty()) retorno.addProperty("mensagemUsuarioFinanceiro", mensagemUsuarioFinanceiro.toString());
		if (mensagemUsuarioAdmin != null && !mensagemUsuarioAdmin.toString().isEmpty()) retorno.addProperty("mensagemUsuarioAdmin", mensagemUsuarioAdmin.toString());
		retorno.addProperty("indVersaoValida", indVersaoValida);
	}	

	public String enviaRetorno() {
		retorno.addProperty("success", isSuccess());
		retorno.addProperty("responseText", responseText);		
		return retorno.toString();	
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

}