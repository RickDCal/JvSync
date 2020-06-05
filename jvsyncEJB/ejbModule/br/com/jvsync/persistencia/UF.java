package br.com.jvsync.persistencia;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="TSIUFS")

public class UF {
	
	@Transient
	public MSUF classeCorrespondente; // do tipo da classe correspondente no banco SQLserver.

	@Id 
	@Column(name="coduf", columnDefinition="number")
	private Long codUF;	
	
	@Column(name="uf", columnDefinition="varchar")
	private String sigla;		
		
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}	
	
}
