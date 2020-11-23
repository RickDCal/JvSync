package br.com.jvsync.persistencia;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
//@Table(name="view_jvsync_tipmov_jiva")
@Table(name="jvsync_tipmov_jiva") //testes

public class TipoMovimentoJiva {
	
	@Id 
	@Column(name="tipmov", columnDefinition="nvarchar")
	private String tipoMovimento;
	
	@Column(name="descricao_jiva", columnDefinition="nvarchar")
	private String descricao;	
	
	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "TipoMovimentoJiva [tipoMovimento=" + tipoMovimento + ", descricao=" + descricao + "]";
	}

	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}	
	
}
