package br.com.suprasync.persistencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.suprasync.persistencia.enumerate.ProdutoSuprasoftEnum;

@Entity
@Table(name="versao_sistema")
public class VersaoSistema {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="codigo", columnDefinition = "int")
	private Long id;
	
	@Column(name="numero_versao", columnDefinition = "nvarchar")
	private String numeroVersao;
	
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
	
	@Column(name="mensagem", columnDefinition="ntext")
	private String mensagem;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroVersao() {
		return numeroVersao;
	}

	public void setNumeroVersao(String numeroVersao) {
		this.numeroVersao = numeroVersao;
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
}
