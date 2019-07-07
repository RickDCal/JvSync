package br.com.suprasync.persistencia;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity  
@Table(name="conta")// o nome da tabela correspondente quando for entity
public class Conta {

	@Id
	@Column(name = "codigo", columnDefinition = "int")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clifor_codigo", columnDefinition="int")
	private Cliente parceiro;
		
	@Column(name = "data_emissao", columnDefinition="datetime")
	private Date dataEmissao;
	
	@Column(name = "total", columnDefinition = "money")
	private BigDecimal valorTotal;
	
			
	public Conta() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Cliente getParceiro() {
		return parceiro;
	}


	public void setParceiro(Cliente parceiro) {
		this.parceiro = parceiro;
	}


	public Date getDataEmissao() {
		return dataEmissao;
	}


	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}


	public BigDecimal getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}
