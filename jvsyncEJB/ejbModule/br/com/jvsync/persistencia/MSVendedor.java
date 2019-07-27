package br.com.jvsync.persistencia;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="TGFVEN")

public class MSVendedor {

	@Id 
	@Column(name="codvend", columnDefinition="bigint")
	private Long id;
	
	@Column(name="apelido", columnDefinition="nvarchar")
	private String apelido;
		
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	@Override
	public String toString() {
		return "MSVendedor [id=" + id + ", apelido=" + apelido + "]";
	}
		
}
