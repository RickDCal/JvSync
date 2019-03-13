package br.com.suprasync.apresentacao.ws.supramais;

import java.nio.charset.Charset;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import org.apache.commons.codec.binary.Base64;

//import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.suprasync.apresentacao.facade.GenericFacade;
import br.com.suprasync.apresentacao.facade.cadastro.ClienteFacade;
import br.com.suprasync.apresentacao.facade.financeiro.ContaFacade;
import br.com.suprasync.apresentacao.facade.versao.VersaoSistemaFacade;
import br.com.suprasync.persistencia.Cliente;
import br.com.suprasync.persistencia.Parcela;
import br.com.suprasync.persistencia.VersaoSistema;
import br.com.suprasync.persistencia.enumerate.ProdutoSuprasoftEnum;
import br.com.suprasync.persistencia.enumerate.TempoEnum;
import br.com.suprasync.util.Utilities;
import jdk.management.resource.internal.TotalResourceContext;

//import org.apache.commons.codec.binary.Base64;

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
				
//				JsonObject jsonEncriptado = (JsonObject) parser.parse(jsonString);
//				if (jsonEncriptado.get("text") != null && !jsonEncriptado.get("text").isJsonNull()) {
//					jsonString = Utilities.stringDecrypt(jsonEncriptado.get("text").getAsString()/*"12345"*/);
//				} else {
//					return enviaRetorno();
//				}							

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
	
	
	@PUT
	@Path("/obterVersoesSupraMais")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json") 
	public String obterVersoesSupraMais(String jsonString) throws Exception {
		GenericFacade genericFacade = new GenericFacade();
		
		Integer position = 0;
		Integer max = 50;
		Integer totalRegistros = null;
		JsonObject jFilter = null;
		
		if (jsonString != null && !jsonString.isEmpty()) {
			jFilter = (JsonObject) parser.parse(jsonString);
			position = jFilter.get("position").getAsInt();
			max = jFilter.get("max").getAsInt();
		}		
		
		List<VersaoSistema> versoes = genericFacade.pesquisar(VersaoSistema.class, position, max);
		Gson gson = new Gson();
		JsonArray jDados = new JsonArray();
		
		for (VersaoSistema versaoSistema : versoes) {
			JsonObject jVersao = versaoSistema.getAsJson();//(JsonObject) parser.parse(gson.toJson(versaoSistema));	
			//jVersao.addProperty("idProdutoSuprasoft", versaoSistema.getProdutoSuprasoft().getValue());
			jDados.add(jVersao);
		}
		
		totalRegistros = genericFacade.pesquisar(VersaoSistema.class, position, null).size();
		retorno.addProperty("total", totalRegistros);		
		
		setSuccess(true);
		retorno.add("data", jDados);
		return enviaRetorno();		
	}
	
	@PUT
	@Path("/cadastrarVersoesSupraMais")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json") 
	public String cadastrarVersoesSupraMais(String jsonString) throws Exception {
		
		try {
			VersaoSistema versao = new VersaoSistema(jsonString);
			GenericFacade genericFacade = new GenericFacade();
			genericFacade.cadastrar(versao);
			
			Gson gson = new Gson();
			
			JsonArray jDados = new JsonArray();
			jDados.add(gson.toJsonTree(versao));
			retorno.add("data", jDados);
			setSuccess(true);				
		} catch (Exception e) {
			e.printStackTrace();
			setResponseText("Ocorreu uma falha ao tentar gravar o registro.");
			return enviaRetorno();
		}
		return enviaRetorno();		
	}	
	
	
	@PUT
	@Path("/editarVersoesSupraMais")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json") 
	public String editarVersoesSupraMais(String jsonString) throws Exception {
		
		try {
			VersaoSistema versao = new VersaoSistema(jsonString);
			GenericFacade genericFacade = new GenericFacade();
			
			genericFacade.atualizar(versao);
			
			JsonArray jDados = new JsonArray();
			jDados.add(versao.getAsJson());
			retorno.add("data", jDados);
			setResponseText("Registro atualizado");
			setSuccess(true);				
		} catch (Exception e) {
			e.printStackTrace();
			setResponseText("Ocorreu uma falha ao tentar persistir o registro editado. Recarregue os dados do registro para conferência.");
			return enviaRetorno();
		}
		return enviaRetorno();		
	}	
	
	@PUT
	@Path("/removerVersoesSupraMais")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json") 
	public String removerVersoesSupraMais(String jsonString) throws Exception {
		
		try {
			VersaoSistema versao = new VersaoSistema(jsonString);			
			GenericFacade genericFacade = new GenericFacade();
			versao = (VersaoSistema) genericFacade.pesquisar(VersaoSistema.class, versao.getId().intValue());			
			genericFacade.remover(versao);
			setSuccess(true);				
		} catch (Exception e) {
			e.printStackTrace();
			setResponseText("Ocorreu uma falha ao tentar remover o registro.");
			return enviaRetorno();
		}
		return enviaRetorno();		
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
	
	@PUT
	@Path("/encripta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json") 	 
	public String encrypt(String text) {
		return Utilities.encryptAESCBC(text);
//		String initVector = "suprasoftEncrypt";//16bytes - pode ser encontrato tbem como salt em alguns exemplos
//		String key = "aesEncryptionKeyaesEncryptionKey"; 
//		/*o tamanho da chave determina se será AES256 ou AES128,
//		 * chave de 32 bytes corresponde a criptografia de 256 enquanto chave de 16 bytes passa criptografia para 128*/
//	    try {
//	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes());
//	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//	 
//	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//	 
//	        byte[] encrypted = cipher.doFinal(value.getBytes());
//	        return Base64.getEncoder().encodeToString(encrypted);
//	        //return Utilities.bytesToHex(encrypted);
//	    } catch (Exception ex) {
//	        ex.printStackTrace();
//	    }
//	    return null;
	}
	
//public static String decrypt(String strToDecrypt, String secret) {
//		
//		String secretKeys = "boooooooooom!!!!";
//		String salt = "ssshhhhhhhhhhh!!!!";
//	    try
//	    {
//	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//	        IvParameterSpec ivspec = new IvParameterSpec(iv);
//	         
//	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//	        KeySpec spec = new PBEKeySpec(secretKeys.toCharArray(), salt.getBytes(), 65536, 256);
//	        SecretKey tmp = factory.generateSecret(spec);
//	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
//	         
//	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//	        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
//	        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
//	    }
//	    catch (Exception e) {
//	        System.out.println("Error while decrypting: " + e.toString());
//	    }
//	    return null;
//	}
	
	@PUT
	@Path("/decripta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json") 	 
	public String decrypt(String text) {
		return Utilities.decryptAESCBC(text);
//		String key = "aesEncryptionKeyaesEncryptionKey";
//		String initVector = "suprasoftEncrypt";		
//	    try {
//	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
//	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//	 
//	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//	        byte[] original = cipher.doFinal(Base64.getDecoder().decode(text));
//	 
//	        return new String(original);
//	    } catch (Exception ex) {
//	        ex.printStackTrace();
//	    }	 
//	    return null;
	}
}