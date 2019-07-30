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
@Table(name="TFPLGR")

public class Logradouro {

	@Id 
	@Column(name="codlogradouro", columnDefinition="varchar2")
	private String codLogradouro;
	
	@Column(name="descrlogradouro", columnDefinition="varchar")
	private String descrLogradouro;
	
	@Column(name="dhalter", columnDefinition="date")
	private Date dhAlter;
	
	@Column(name="codusu", columnDefinition="number")
	private Long codUsu;
		
		
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	
	
	
	
}
