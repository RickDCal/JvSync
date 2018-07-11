package br.com.suprasync.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.Part;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.suprasync.negocio.exception.FalhaAoConverterDataException;
import br.com.suprasync.negocio.exception.FalhaAoRemoverArquivoException;
import br.com.suprasync.negocio.exception.FalhaAoSalvarArquivoException;
import br.com.suprasync.persistencia.dao.IUsuarioDAO;

@Stateless
public class Utilities { 
	//removi as interfaces e facades para esta classe e coloquei todos os mÈtodos est·ticos em 11/07/2018

	@EJB
	private IUsuarioDAO usuarioDao;
	
	final static Integer multKey = 34378;
	final static Integer addKey = 41529;

	public Utilities() {

	}	

	public static void salvarArquivoDisco(Part parte, String caminhoArquivo, String nomeArquivo) throws FalhaAoSalvarArquivoException {

		if (parte != null && nomeArquivo == null) {
			nomeArquivo = obterNomeArquivo(parte);
		}

		File arquivo = new File(caminhoArquivo + "/" + nomeArquivo );
		try {
			parte.write(arquivo.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	} 

	public static void salvarArquivoDisco(String caminhoArquivo, String nomeArquivo, String conteudoString) {
		try {

			File diretorio = new File(caminhoArquivo);
			if (diretorio.exists() == false) {
				diretorio.mkdir();
			}

			File file = new File(caminhoArquivo + "/" + nomeArquivo);
			if(file.exists()){
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(conteudoString);
			bw.flush();
			bw.close();

		} catch (Exception ex) {
			ex.printStackTrace();     	
		}
	}
	
	public static void salvarArquivoDisco(String caminhoArquivo, String nomeArquivo, byte[] arquivo) {
		try {
			
			File diretorio = new File(caminhoArquivo);
			if (diretorio.exists() == false) {
				diretorio.mkdir();
			}

			File file = new File(caminhoArquivo + "/" + nomeArquivo);
			if(file.exists()){
				file.createNewFile();
			}
			
			try (FileOutputStream fos = new FileOutputStream(caminhoArquivo + "/" + nomeArquivo)) {
				   fos.write(arquivo);
				   //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
				}
		} catch (Exception ex) {
			ex.printStackTrace();     	
		}
	}

	public static void removerArquivoDisco(String caminhoArquivo, String nomeArquivo) throws FalhaAoRemoverArquivoException {

		File arquivo = new File(caminhoArquivo + "/" + nomeArquivo );
		arquivo.delete();	
	}

	public static String obterNomeArquivo (Part parte) {
		String nome = null;
		Collection<String> header = parte.getHeaders("content-disposition");
		for (String texto : header) {			
			for( String tmp : texto.split(";") ){
				System.out.println(tmp);
				if(tmp.trim().startsWith("filename")){
					int a = tmp.indexOf("=")+2;
					nome =  tmp.substring(a , tmp.length()-(1));
				}
			}			
		}
		if (nome.equalsIgnoreCase("")) {
			return null;
		} else {
			return nome;
		}

	}


	/**
	 * Metodo para realizar a criptografia de cesar
	 * esta criptografia √© simples e facilmente descriptografada, n√£o serve para proteger comunica√ß√£o, 
	 * serve apenas para n√£o deixar muito explicita a senha no banco de dados.
	 * @param texto mensagem a ser codificada
	 * @param chave numero de saltos
	 * @return mensagem codificada
	 */
	public static String encriptarCesar(int chave, String texto)  {

		StringBuilder textoCifrado = new StringBuilder();
		int tamanhoTexto = texto.length();
		for(int c=0; c < tamanhoTexto; c++){
			int letraCifradaASCII = ((int) texto.charAt(c)) + chave;
			while(letraCifradaASCII > 126)
				letraCifradaASCII -= 94;
			textoCifrado.append( (char)letraCifradaASCII );
		}

		return textoCifrado.toString();
	}

	public static String criptografiaSenha(String senha) {

		int salto = 7907;    		        

		//aqui criptografa 
		StringBuilder textoCifrado = new StringBuilder();
		int tamanhoTexto = senha.length();
		for(int c=0; c < tamanhoTexto; c++){
			int letraCifradaASCII = ((int) senha.charAt(c)) + salto;
			while(letraCifradaASCII > 126)
				letraCifradaASCII -= 94;
			textoCifrado.append( (char)letraCifradaASCII );
		}

		String senhaCriptografada = textoCifrado.toString();		
		//o que criptografar coloca em hexadecimal
		byte[] bytes = senhaCriptografada.getBytes();
		//senhaCriptografada = Hex.encodeHexString(bytes); esta classe hex depende de import da biblioteca apache.
		senhaCriptografada = bytesToHex(bytes);//este bytesToHex √© um m√©todo que faz o mesmo que o m√©todo da biblioteca apache mas teve que ser colado nesta classe.
		return senhaCriptografada;
	}
	
	public static String criptografiaSenhaSupraMais(String value) {
		Integer startKey = 673;
		String result = "";
		int letra;
		byte[] senhaCriptografada = value.getBytes();
		byte[] bResult = value.getBytes();
		for (int i = 0; i < value.length(); i++) {
			letra = senhaCriptografada[i];
			if(letra < 0){
				letra += 256;
			}
			int b = ((byte)(letra ^ Integer.rotateRight(startKey, 8)));
			if(b < 0){
				b += 256;
			}

			bResult[i] = (byte)b;
			letra = bResult[i];
			if(letra < 0){
				letra += 256;
			}
			startKey = ((int) letra + startKey) * multKey + addKey;
		}
		result = new String(bResult);
		return result;
	}

	public static String removeAcentos(final String texto) {
		String textoSemAcentos = Normalizer.normalize(texto, Normalizer.Form.NFD);
		textoSemAcentos = textoSemAcentos.replaceAll("[^\\p{ASCII}]", "");
		return textoSemAcentos;
		/* m√©todo copiado de: 
		 * https://ldiasrs.wordpress.com/2014/07/09/java-como-remover-acentos-e-caracteres-especiais*/ 
	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexArray = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		for ( int j = 0; j < bytes.length; j++ ) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	/*datas para String*/

	public static String dataYYYY_MM_DD(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("YYYY-MM-dd");		
		String date = dataFormat.format(data.getTime());
		return date;		
	}

	public static String dataYYYYMMDD(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("YYYYMMdd");
		String date = dataFormat.format(data.getTime());		
		return date;		
	}

	public static String dataDD_MM_YYYY(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-YYYY");
		String date = dataFormat.format(data.getTime());		
		return date;		
	}

	public static String dataMM_DD_YYYY(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("MM-dd-YYYY");
		String date = dataFormat.format(data.getTime());		
		return date;		
	}

	public static String dataDDMMYYYY(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("ddMMYYYY");
		String date = dataFormat.format(data.getTime());		
		return date;		
	}

	public static String dataDDIMMIYYYY(Date data) {		
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/YYYY");
		String date = dataFormat.format(data.getTime());		
		return date;		
	}	

	public static String dataYYYY_MM_DDeHHppmmppss(Date data) {	//e = espaÁo pp= :
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String date = dataFormat.format(data.getTime());
		return date;		
	}

	/*inteiro para Date*/

	public static Date IntegerToDate(int numero) throws FalhaAoConverterDataException{		

		Date data = new Date(numero *1000L);		
		return data;		
	}

	/*String para Date*/	
	public static Date dataYYYY_MM_DDeHHppmmppss(String data) throws ParseException, FalhaAoConverterDataException {	//e = espaÁo pp= :
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data);
		return date;
	}

	public static Date dataYYYY_MM_DDTHHppmmppss(String data) throws ParseException, FalhaAoConverterDataException {	//e = espaÁo pp= :
		data = data.replace("T"," ");
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data);
		return date;
	}

	public static Date dataDD_MM_YYYY (String data) throws ParseException, FalhaAoConverterDataException {
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(data);
		return date;
	}

	public static Date dataDDIMMIYYYY (String data) throws ParseException, FalhaAoConverterDataException { // I = barra
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		return date;
	}

	public static Date dataYYYY_MM_DD (String data) throws ParseException, FalhaAoConverterDataException { // _ = -
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data);
		return date;
	}

	

	public static String enviaMensagemSlack(String usuarioSlack, String mensagem, String webHook) throws ParseException, IOException {
		/***Ricardo Dias 06/03/2018
		 * ObservaÁ„o importante:
		 * No final de fevereiro de 2018 o certificado SSL do Slack foi atualizado e a cadeia de certificados que vem no glassfish 3 ficou desatualizada
		 * Isso acarreta um erro ao fazer a requisiÁ„o: PKIX path building failed: .... unable to find valid certification path to requested target
		 * Para resolver (sem complicaÁ„o) substitua os arquivos cacerts.jks, keyfile e keystore.jks do glasfish 3 pelos arquivos de mesmo nome que vem no glassfish 4
		 * Estes arquivos est„o em: ...glassfish\domains\domain1\config
		 * */    	
		if (!validaMensagem(webHook, mensagem)){
			return "Dados insuficientes ou incorretos para enviar a requisiÁ„o";
		}  

		JsonObject jObject = new JsonObject();
		jObject.addProperty("text", mensagem);
		if (usuarioSlack != null && !usuarioSlack.isEmpty()){
			jObject.addProperty("channel", usuarioSlack);
		}

		Gson gson = new Gson();
		StringEntity entity = new StringEntity(gson.toJson(jObject), "utf-8");
		//StringEntity entity = new StringEntity(gson.toJson(jObject), "iso-8859-1"); // tem que ser igual ao encoding desta classe (ver nas propriedades) sen„o distorce. 

		HttpPost post = new HttpPost(webHook);
		post.addHeader(HTTP.CONTENT_TYPE, "application/json");

		post.setEntity(entity);

		HttpClient httpClient = HttpClientBuilder.create().build();
		//entity.setContentEncoding("iso-8859-1");
		//entity.setContentEncoding("UTF-8");
		//entity.setContentEncoding(HTTP.UTF_8);

		HttpResponse response = httpClient.execute(post);

		// processando a resposta
		String resposta = null;
		Integer status = response.getStatusLine().getStatusCode();
		if (status == 200) {
			return "ok";
		}
		if (response.getEntity() != null) {
			resposta = EntityUtils.toString(response.getEntity());
		}
		return resposta;
	}

	private static boolean validaMensagem(String webHook, String mensagem) {
		if (webHook != null && !webHook.isEmpty() && mensagem != null && !webHook.isEmpty()) {
			return true;
		}		
		return false;
	}

	public static String encodeMensagemUtfToIso(String mensagem) throws UnsupportedEncodingException {
		byte byteText[] = mensagem.getBytes("UTF-8");
		mensagem = new String(byteText, "ISO_8859_1");
		return mensagem;
	}

	public static boolean isDiaUtil (Calendar data) { 
		int diaSemana = data.get (Calendar.DAY_OF_WEEK);
		return ((diaSemana >= Calendar.MONDAY) && (diaSemana <= Calendar.FRIDAY));   	
	}

}
