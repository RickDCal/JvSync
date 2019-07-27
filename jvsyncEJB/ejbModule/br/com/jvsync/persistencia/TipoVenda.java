package br.com.jvsync.persistencia;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="TGFTPV")

public class TipoVenda {

	@Id 
	@Column(name="codtipvenda", columnDefinition="number")
	private Long id;
	
	@Column(name="descrtipvenda", columnDefinition="varchar")
	private String descrTipVenda;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrTipVenda() {
		return descrTipVenda;
	}

	public void setDescrTipVenda(String descrTipVenda) {
		this.descrTipVenda = descrTipVenda;
	}	
	
	
	
}
