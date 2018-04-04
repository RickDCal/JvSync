package br.com.suprasync.negocio.dto;

import java.util.Date;

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
	private Integer estimativa;
	private String descricaoDesenvolvimento;
	private String comentarioDesenvolvimento;
	private Date dataUltimoRedirecionamento; 
	private Integer idUsuario;
	private Date dataPrevisaoTermino;
			
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

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getDataPrevisaoTermino() {
		return dataPrevisaoTermino;
	}

	public void setDataPrevisaoTermino(Date dataPrevisaoTermino) {
		this.dataPrevisaoTermino = dataPrevisaoTermino;
	}
	
	public SacOcorrenciaDTO convertToDTO (SacOcorrencia ocorrencia) {
		
		setId(ocorrencia.getId());
		setIdCliente(ocorrencia.getCliente() != null ? ocorrencia.getCliente().getId():null);
		setDataCadastro(ocorrencia.getDataCadastro());
		setIdSituacao(ocorrencia.getSituacaoOcorrencia() != null ? ocorrencia.getSituacaoOcorrencia().getCode():null);
		setAssunto(ocorrencia.getAssunto());
		setIdEtapa(ocorrencia.getEtapa() != null ? ocorrencia.getEtapa().getId():null);
		setSolicitacao(ocorrencia.getSolicitacao());
		setEstimativa(ocorrencia.getEstimativa());
		setDescricaoDesenvolvimento(ocorrencia.getDescricaoDesenvolvimento());
		setComentarioDesenvolvimento(ocorrencia.getComentarioDesenvolvimento());
		setDataUltimoRedirecionamento(ocorrencia.getDataUltimoRedirecionamento());
		setIdUsuario(
				ocorrencia.getUsuarioRedirecionamento() != null ? ocorrencia.getUsuarioRedirecionamento().getId(): 
				ocorrencia.getUsuarioCadastro() != null ? ocorrencia.getUsuarioCadastro().getId() : null 				
		);
		setDataPrevisaoTermino(ocorrencia.getDataPrevisaoTermino());		
		return this;		
	}	
	
}
