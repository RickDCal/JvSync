package br.com.suprasync.persistencia;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.suprasync.negocio.exception.FalhaAoConverterDataException;
import br.com.suprasync.persistencia.enumerate.ProdutoSuprasoftEnum;
import br.com.suprasync.util.Utilities;

@Entity
@Table(name="versao_sistema")
public class VersaoSistema {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="codigo", columnDefinition = "int")
	private Integer id;
	
	@Column(name="numero_versao", columnDefinition = "nvarchar")
	private String numeroVersao;
	
	@Column(name="script_banco_dados", columnDefinition = "nvarchar")
	private String versaoBancoDados;
	
	@Column(name="data_lancamento", columnDefinition="datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;
	
////	@ObjectTypeConverter(name = "produtoSuprasoftEnum", objectType = ProdutoSuprasoftEnum.class, dataType = Integer.class, conversionValues = {
////			@ConversionValue(objectValue = "SUPRA_MAIS", dataValue = "1"),
////			@ConversionValue(objectValue = "SUPRA_WEB", dataValue = "2"),
////			@ConversionValue(objectValue = "SUPRA_MOBILE", dataValue = "3"),
////			@ConversionValue(objectValue = "SUPRA_PCP", dataValue = "4") 
////		})
	@Column(name = "id_produto_suprasoft", columnDefinition="smallint")
	//@Convert("produtoSuprasoftEnum")
	private ProdutoSuprasoftEnum produtoSuprasoft;
	
	@Column(name="data_suspensao", columnDefinition="datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSuspensao;
	
	@Column(name="observacao", columnDefinition="ntext")
	private String mensagem;
	
	
	public VersaoSistema() {
		
	}
	

	public VersaoSistema(String JsonString) {
		JsonParser parser = new JsonParser();
		JsonObject jVersao = (JsonObject) parser.parse(JsonString);
		
		if (jVersao != null) {
			
			try {
				try {
					this.id = jVersao.get("id").getAsInt();
				} catch (Exception e) {
					this.id = null;
				}	
				
				this.numeroVersao = jVersao.get("numeroVersao").getAsString();
				this.versaoBancoDados = jVersao.get("versaoBancoDados").getAsString();
				this.dataLancamento = Utilities.dataYYYY_MM_DDeHHppmmppss(jVersao.get("dataLancamento").getAsString());
				
				switch (jVersao.get("idProdutoSuprasoft").getAsString()) {
				case "1": this.produtoSuprasoft = ProdutoSuprasoftEnum.SUPRA_MAIS;	break;
				case "2": this.produtoSuprasoft = ProdutoSuprasoftEnum.SUPRA_WEB;	break;
				case "3": this.produtoSuprasoft = ProdutoSuprasoftEnum.SUPRA_MOBILE;	break;
				default: break;
				}	
				
				this.dataSuspensao = Utilities.dataYYYY_MM_DDeHHppmmppss(jVersao.get("dataSuspensao").getAsString());
				if(jVersao.get("mensagem") != null && !jVersao.get("mensagem").isJsonNull()) {
					this.mensagem = jVersao.get("mensagem").getAsString();
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (FalhaAoConverterDataException e) {
				e.printStackTrace();
			}			
		}		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroVersao() {
		return numeroVersao;
	}

	public void setNumeroVersao(String numeroVersao) {
		this.numeroVersao = numeroVersao;
	}

	public String getVersaoBancoDados() {
		return versaoBancoDados;
	}


	public void setVersaoBancoDados(String versaoBancoDados) {
		this.versaoBancoDados = versaoBancoDados;
	}


	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public ProdutoSuprasoftEnum getProdutoSuprasoft() {
		return produtoSuprasoft;
	}

	public void setProdutoSuprasoft(ProdutoSuprasoftEnum produtoSuprasoft) {
		this.produtoSuprasoft = produtoSuprasoft;
	}

	public Date getDataSuspensao() {
		return dataSuspensao;
	}

	public void setDataSuspensao(Date dataSuspensao) {
		this.dataSuspensao = dataSuspensao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public JsonObject getAsJson() {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject jo = (JsonObject) parser.parse(gson.toJson(this));
		jo.addProperty("idProdutoSuprasoft", getProdutoSuprasoft().getValue());
		return jo;				
	}
		
}
