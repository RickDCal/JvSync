package br.com.jvsync.persistencia;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="TSIUFS")

public class MSUF {

	@Id 
	@Column(name="coduf", columnDefinition="bigint")
	private Long codUF;
	
	
	
	@Column(name="uf", columnDefinition="nvarchar")
	private String sigla;
		
		
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	
	
	
	
}
