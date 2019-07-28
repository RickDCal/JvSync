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
@Table(name="TGFROT")

public class MSRota {

	@Id 
	@Column(name="codrota", columnDefinition="bigint")
	private Long id;

	
	@Column(name="dtalter", columnDefinition="date")
	private Date dtAlter;
	
	@Column(name="descrrota", columnDefinition="nvarchar")
	private String descrrota;
		
		
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	
	
	
	
}
