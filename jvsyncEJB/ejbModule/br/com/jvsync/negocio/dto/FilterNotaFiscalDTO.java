package br.com.jvsync.negocio.dto;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class FilterNotaFiscalDTO {
	
	private Integer position;
	private Integer max;
	private Integer id;
	private Date dataInicial;
	private Date dataFinal;	
	private Integer numNota;
	private Integer idInicial;	
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getNumNota() {
		return numNota;
	}
	public void setNumNota(Integer numNota) {
		this.numNota = numNota;
	}
	public Integer getIdInicial() {
		return idInicial;
	}
	public void setIdInicial(Integer idInicial) {
		this.idInicial = idInicial;
	}
	
}
