package br.com.suprasync.persistencia;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity  
@Table(name="sac_ocorrencia_desenvolvimento")// o nome da tabela correspondente quando for entity
public class SacDesenvolvimento {

	private int id;	
	private SacOcorrencia sacOcorrencia;	
	private Usuario usuario;
	private Funcionario funcionario;
	private Date dataInicio; 
	private Date dataFim;
	private Integer tempo;
	
			
	public SacDesenvolvimento() {
		
	}
	
	@Id 
	@Column(name="codigo")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	

	@ManyToOne(cascade =
	{CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "sacocor_codigo", columnDefinition= "int")
	public SacOcorrencia getSacOcorrencia() {
		return sacOcorrencia;
	}

	public void setSacOcorrencia(SacOcorrencia sacOcorrencia) {
		this.sacOcorrencia = sacOcorrencia;
	}

	@ManyToOne(cascade =
		{CascadeType.ALL}, fetch = FetchType.LAZY)
		@JoinColumn(name = "usu_codigo", columnDefinition= "int")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(cascade =
		{CascadeType.ALL}, fetch = FetchType.LAZY)
		@JoinColumn(name = "func_codigo", columnDefinition= "int")
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Column(name="data_hora_inicio", columnDefinition="datetime")
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@Column(name="data_hora_fim", columnDefinition="datetime")
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@Column(name="tempo_gasto", columnDefinition="int")
	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}
	
}
