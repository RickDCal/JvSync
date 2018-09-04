package br.com.suprasync.negocio.dto;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SacOcorrenciaFollowUpDTO {

	private int idSacOcorrencia;	
	private Integer idUsuario;
	private String nomeUsuario;
	private Date data;
	private String historico;
			
	public SacOcorrenciaFollowUpDTO() {
		
	}
	
	
	public int getIdSacOcorrencia() {
		return idSacOcorrencia;
	}


	public void setIdSacOcorrencia(int idSacOcorrencia) {
		this.idSacOcorrencia = idSacOcorrencia;
	}


	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public String getHistorico() {
		return historico;
	}


	public void setHistorico(String historico) {
		this.historico = historico;
	}


	public JsonObject getAsJson() {
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		return (JsonObject) jsonParser.parse(gson.toJson(this));		
	}
		
}
