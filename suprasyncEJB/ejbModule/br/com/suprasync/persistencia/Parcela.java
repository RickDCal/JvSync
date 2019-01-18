package br.com.suprasync.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity  
@Table(name="parcela")// o nome da tabela correspondente quando for entity
public class Parcela implements Serializable{

	@Id
	@Column(name = "codigo", columnDefinition= "int")
	private Long id;
	
	@Id
	@ManyToOne
	@JoinColumn(name="cont_codigo", columnDefinition= "int")
	private Conta conta;
	
	@Column(name = "data_emissao", columnDefinition= "datetime")
	private Date dataEmissao;
	
	@Column(name = "data_vencimento", columnDefinition= "datetime")
	private Date dataVencimento;
	
	@Column(name = "data_quitacao", columnDefinition= "datetime")
	private Date dataQuitacao;
	
	@Column(name = "valor", columnDefinition = "money")
	private BigDecimal valor;	
	
	@Column(name = "cont_codigo_consolidada", columnDefinition = "int")
	private Long idContaConsolidada;
	
	public Parcela() {
		
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Conta getConta() {
		return conta;
	}



	public void setConta(Conta conta) {
		this.conta = conta;
	}



	public Date getDataEmissao() {
		return dataEmissao;
	}



	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}



	public Date getDataVencimento() {
		return dataVencimento;
	}



	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}



	public Date getDataQuitacao() {
		return dataQuitacao;
	}



	public void setDataQuitacao(Date dataQuitacao) {
		this.dataQuitacao = dataQuitacao;
	}



	public BigDecimal getValor() {
		return valor;
	}



	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}



	public Long getIdContaConsolidada() {
		return idContaConsolidada;
	}



	public void setIdContaConsolidada(Long idContaConsolidada) {
		this.idContaConsolidada = idContaConsolidada;
	}
	
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "sac_etapa_atendimento_responsavel", joinColumns = {@JoinColumn(name = "func_codigo_responsavel")},
//	inverseJoinColumns = {@JoinColumn(name = "sacetapa_codigo")})
//	@OrderBy(value="nome asc")
//	public List<SacEtapa> getEtapas() {
//		return etapas;
//	}
//
//	public void setEtapas(List<SacEtapa> etapas) {
//		this.etapas = etapas;
//	}

	
}
