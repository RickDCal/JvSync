package br.com.jvsync.persistencia;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="TSICID")

public class Cidade {
	
	@Transient
	public MSCidade classeCorrespondente; // do tipo da classe correspondente no banco SQLserver.

	@Id 
	@Column(name="codcid", columnDefinition="number")
	private Long id;
	
	@Column(name="codreg", columnDefinition="number")
	private Long codReg;
	
	@Column(name="uf", columnDefinition="number")
	private Long uf;
	
	@Column(name="descricaocorreio", columnDefinition="varchar")
	private String descricaoCorreio;
	
	@Column(name="dtalter", columnDefinition="date")
	private Date dtAlter;
	
	@Column(name="nomecid", columnDefinition="varchar")
	private String nomeCid;
		
		
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}	
	
}
