package br.com.jvsync.negocio.dto;

import java.util.Date;

public class FilterNotaFiscalDTO {
	
	private Integer position;
	private Integer max;
	private Long id;
	private Date dataInicial;
	private Date dataFinal;	
	private Long numNota;
	private Long idInicial;	
	
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Long getNumNota() {
		return numNota;
	}
	public void setNumNota(Long numNota) {
		this.numNota = numNota;
	}
	public Long getIdInicial() {
		return idInicial;
	}
	public void setIdInicial(Long idInicial) {
		this.idInicial = idInicial;
	}
	
}
