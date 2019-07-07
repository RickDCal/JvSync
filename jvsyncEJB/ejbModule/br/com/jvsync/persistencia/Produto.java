package br.com.jvsync.persistencia;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="TGFPRO")

public class Produto {

	@Id 
	@Column(name="codprod", columnDefinition="number")
	private int id;
	
	@Column(name="descrprod", columnDefinition="varchar")
	private String descrProd;
	
	@Column(name="codvol", columnDefinition="varchar")
	private String codVol;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrProd() {
		return descrProd;
	}

	public void setDescrProd(String descrProd) {
		this.descrProd = descrProd;
	}

	public String getCodVol() {
		return codVol;
	}

	public void setCodVol(String codVol) {
		this.codVol = codVol;
	}	
	
}
