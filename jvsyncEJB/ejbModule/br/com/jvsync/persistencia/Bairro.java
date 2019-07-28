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
@Table(name="TSIBAI")

public class Bairro {

	@Id 
	@Column(name="codbai", columnDefinition="number")
	private Long id;
	
	@Column(name="codreg", columnDefinition="number")
	private Long codreg;
	
	@Column(name="descricaocorreio", columnDefinition="varchar")
	private String descricaoCorreio;
	
	@Column(name="dtalter", columnDefinition="date")
	private Date dtAlter;
	
	@Column(name="nomebai", columnDefinition="varchar")
	private String nomeBai;
		
		
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	
	
	
	
}
