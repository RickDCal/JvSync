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
@Table(name="TSIEND")

public class Endereco {

	@Id 
	@Column(name="codend", columnDefinition="number")
	private Long id;
	
	@Column(name="descricaocorreio", columnDefinition="varchar")
	private String descricaoCorreio;
	
	@Column(name="dtalter", columnDefinition="date")
	private Date dtAlter;
	
	@Column(name="nomeend", columnDefinition="varchar")
	private String nomeEnd;
	
	@Column(name="tipo", columnDefinition="varchar")
	private String tipo;
		
		
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	
	
	
	
}
