package br.com.jvsync.persistencia;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="TGFTOP")

public class MSTipoOperacao {

	@Id 
	@Column(name="codtipoper", columnDefinition="bigint")
	private Long id;

	@Column(name="descroper", columnDefinition="nvarchar")
	private String descroper;
	
	@Column(name="dhalter", columnDefinition="date")
	private Date dhalter;
	
	@Column(name="tipmov", columnDefinition="nvarchar")
	private String tipmov;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescroper() {
		return descroper;
	}

	public void setDescroper(String descroper) {
		this.descroper = descroper;
	}
	
	public Date getDhalter() {
		return dhalter;
	}

	public void setDhalter(Date dhalter) {
		this.dhalter = dhalter;
	}

	public String getTipmov() {
		return tipmov;
	}

	public void setTipmov(String tipmov) {
		this.tipmov = tipmov;
	}

	@Override
	public String toString() {
		return "TipoOperacao [id=" + id + ", descroper=" + descroper + ", dhalter=" + dhalter + "]";
	}	
	
}
