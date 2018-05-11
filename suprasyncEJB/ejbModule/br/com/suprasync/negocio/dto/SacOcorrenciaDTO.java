package br.com.suprasync.negocio.dto;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.suprasync.persistencia.SacOcorrencia;

public class SacOcorrenciaDTO {

	private int id;	
	private Integer idFuncionario;
	private Integer idCliente;
	private String nomeCliente;
	private Date dataCadastro;
	private Integer idSituacao;
	private String assunto;
	private Integer idEtapa;
	private String solicitacao;
	private String solucao;
	private String comentario;
	private Integer estimativa;
	private String descricaoDesenvolvimento;
	private String comentarioDesenvolvimento;
	private Date dataUltimoRedirecionamento; 
	private Date dataPrevisaoTermino;
	private String numeroVersao;
	private Integer prioridade;
			
	public SacOcorrenciaDTO() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Integer getIdEtapa() {
		return idEtapa;
	}

	public void setIdEtapa(Integer idEtapa) {
		this.idEtapa = idEtapa;
	}

	public String getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}
	
	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getEstimativa() {
		return estimativa;
	}

	public void setEstimativa(Integer estimativa) {
		this.estimativa = estimativa;
	}

	public String getDescricaoDesenvolvimento() {
		return descricaoDesenvolvimento;
	}

	public void setDescricaoDesenvolvimento(String descricaoDesenvolvimento) {
		this.descricaoDesenvolvimento = descricaoDesenvolvimento;
	}

	public String getComentarioDesenvolvimento() {
		return comentarioDesenvolvimento;
	}

	public void setComentarioDesenvolvimento(String comentarioDesenvolvimento) {
		this.comentarioDesenvolvimento = comentarioDesenvolvimento;
	}

	public Date getDataUltimoRedirecionamento() {
		return dataUltimoRedirecionamento;
	}

	public void setDataUltimoRedirecionamento(Date dataUltimoRedirecionamento) {
		this.dataUltimoRedirecionamento = dataUltimoRedirecionamento;
	}

	public Date getDataPrevisaoTermino() {
		return dataPrevisaoTermino;
	}

	public void setDataPrevisaoTermino(Date dataPrevisaoTermino) {
		this.dataPrevisaoTermino = dataPrevisaoTermino;
	}
	
	public String getNumeroVersao() {
		return numeroVersao;
	}

	public void setNumeroVersao(String numeroVersao) {
		this.numeroVersao = numeroVersao;
	}
	
	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public SacOcorrenciaDTO convertToDTO (SacOcorrencia ocorrencia) {
		return ocorrencia.getOcorrenciaDTO(null);		
	}
	
	public JsonObject getAsJson() {
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		return (JsonObject) jsonParser.parse(gson.toJson(this));		
	}		
	
}
