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
@Table(name="TSICID")

public class MSCidade {

	@Id 
	@Column(name="codcid", columnDefinition="bigint")
	private Long id;
	
	@Column(name="codreg", columnDefinition="bigint")
	private Long codReg;
	
	@Column(name="uf", columnDefinition="bigint")
	private Long uf;
	
	@Column(name="descricaocorreio", columnDefinition="nvarchar")
	private String descricaoCorreio;
	
	@Column(name="dtalter", columnDefinition="date")
	private Date dtAlter;
	
	@Column(name="nomecid", columnDefinition="nvarchar")
	private String nomeCid;
		
		
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	
	
	
	
}
