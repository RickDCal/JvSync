package br.com.jvsync.persistencia;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="TGFVOA")

public class MSTipoVolume {

	@Id 
	@Column(name="codprod", columnDefinition="bigint")
	private Long idProduto;
	
	@Column(name="codvol", columnDefinition="nvarchar")
	private String codVol;
	
	@Column(name="dividemultiplica", columnDefinition="nvarchar")
	private String divideMultiplica;
	
	@Column(name="quantidade", columnDefinition="float")
	private Double quantidade;
	
	@Column(name="multipvlr", columnDefinition="float")
	private Double multipVlr;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getCodVol() {
		return codVol;
	}

	public void setCodVol(String codVol) {
		this.codVol = codVol;
	}

	public String getDivideMultiplica() {
		return divideMultiplica;
	}

	public void setDivideMultiplica(String divideMultiplica) {
		this.divideMultiplica = divideMultiplica;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getMultipVlr() {
		return multipVlr;
	}

	public void setMultipVlr(Double multipVlr) {
		this.multipVlr = multipVlr;
	}	
	
	
	
}
