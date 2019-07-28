package br.com.jvsync.persistencia;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="TSIREG")

public class Regiao {

	@Id 
	@Column(name="codreg", columnDefinition="number")
	private Long id;
	
	@Column(name="nomereg", columnDefinition="varchar")
	private String nomeReg;
		
		
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	
	
	
	
}
