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
@Table(name="TGFVEN")

public class Vendedor {
	
	@Transient
	public MSVendedor classeCorrespondente; // do tipo da classe correspondente no banco SQLserver.

	@Id 
	@Column(name="codvend", columnDefinition="number")
	private Long id;
	
	@Column(name="apelido", columnDefinition="varchar")
	private String apelido;
		
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}	
	
}
