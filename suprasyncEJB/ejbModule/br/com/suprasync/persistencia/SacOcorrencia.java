package br.com.suprasync.persistencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.suprasync.persistencia.enumerate.SACOcorrenciaEnum;

@Entity  
@Table(name="sac_ocorrencia")
public class SacOcorrencia {

	private int id;	
	private Cliente cliente;
	private Date dataCadastro;
	private SACOcorrenciaEnum situacaoOcorrencia;
	private String assunto;
	private SacEtapa etapa;
	private String solicitacao;
	private Integer estimativa;
	private String descricaoDesenvolvimento;
	private String comentarioDesenvolvimento;
	private Date dataUltimoRedirecionamento;
	private Funcionario funcionarioCadastro;
	private Funcionario funcionarioRedirecionamento;
	private Date dataPrevisaoTermino;
			
	public SacOcorrencia() {
		
	}
	
	@Id //indica chave prim�ria
	@Column(name="codigo")//nome da coluna no bd
	//@GeneratedValue(strategy = GenerationType.IDENTITY) //auto incermento na chave
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "clifor_codigo")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Column(name="data_cadastro", columnDefinition="datetime")
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Enumerated
	@Column(name="id_situacao", columnDefinition="smallint")
	public SACOcorrenciaEnum getSituacaoOcorrencia() {
		return situacaoOcorrencia;
	}
	public void setSituacaoOcorrencia(SACOcorrenciaEnum situacaoOcorrencia) {
		this.situacaoOcorrencia = situacaoOcorrencia;
	}

	@Column(name="assunto_protocolo", columnDefinition="nvarchar")
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	@OneToOne
	@JoinColumn(name = "sacetapa_codigo")
	public SacEtapa getEtapa() {
		return etapa;
	}
	public void setEtapa(SacEtapa etapa) {
		this.etapa = etapa;
	}

	@Column(name="descricao_solicitacao", columnDefinition="ntext")
	public String getSolicitacao() {
		return solicitacao;
	}
	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}

	@Column(name="tempo_estimado_desenv", columnDefinition="int")
	public Integer getEstimativa() {
		return estimativa;
	}
	public void setEstimativa(Integer estimativa) {
		this.estimativa = estimativa;
	}

	@Column(name="descricao_desenvolvimento", columnDefinition="ntext")
	public String getDescricaoDesenvolvimento() {
		return descricaoDesenvolvimento;
	}
	public void setDescricaoDesenvolvimento(String descricaoDesenvolvimento) {
		this.descricaoDesenvolvimento = descricaoDesenvolvimento;
	}

	@Column(name="comentario_desenvolvimento", columnDefinition="ntext")
	public String getComentarioDesenvolvimento() {
		return comentarioDesenvolvimento;
	}
	public void setComentarioDesenvolvimento(String comentarioDesenvolvimento) {
		this.comentarioDesenvolvimento = comentarioDesenvolvimento;
	}

	@Column(name="data_ultimo_redirecionamento", columnDefinition="datetime")
	public Date getDataUltimoRedirecionamento() {
		return dataUltimoRedirecionamento;
	}
	public void setDataUltimoRedirecionamento(Date dataUltimoRedirecionamento) {
		this.dataUltimoRedirecionamento = dataUltimoRedirecionamento;
	}
	
	@OneToOne
	@JoinColumn(name = "func_codigo_solicitacao")
	public Funcionario getFuncionarioCadastro() {
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	@OneToOne
	@JoinColumn(name = "func_codigo_redirecionamento")
	public Funcionario getFuncionarioRedirecionamento() {
		return funcionarioRedirecionamento;
	}
	public void setFuncionarioRedirecionamento(Funcionario funcionarioRedirecionamento) {
		this.funcionarioRedirecionamento = funcionarioRedirecionamento;
	}

	@Column(name="data_previsao_termino", columnDefinition="datetime")
	public Date getDataPrevisaoTermino() {
		return dataPrevisaoTermino;
	}

	public void setDataPrevisaoTermino(Date dataPrevisaoTermino) {
		this.dataPrevisaoTermino = dataPrevisaoTermino;
	}
	
}
