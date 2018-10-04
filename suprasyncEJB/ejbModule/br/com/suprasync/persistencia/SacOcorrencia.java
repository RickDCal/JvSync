package br.com.suprasync.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.suprasync.negocio.dto.SacOcorrenciaDTO;
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
	private String solucao;
	private String comentario;
	private Integer estimativa;
	private String descricaoDesenvolvimento;
	private String comentarioDesenvolvimento;
	private Date dataUltimoRedirecionamento;
	private Funcionario funcionarioCadastro;
	private Funcionario funcionarioRedirecionamento;
	private Date dataPrevisaoTermino;
	private String numeroVersao;
	private Integer prioridade;
	private String ready;
	private List<SacDesenvolvimento> sacDesenvolvimento;
	private List<SacOcorrenciaFollowUp> sacFollowUp;
	private Date dataSolucao;
			
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
	
	@Column(name="descricao_solucao", columnDefinition="ntext")
	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	@Column(name="comentario", columnDefinition="ntext")
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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
	
	@Column(name="versao_atualizador", columnDefinition="nvarchar")
	public String getNumeroVersao() {
		return numeroVersao;
	}

	public void setNumeroVersao(String numeroVersao) {
		this.numeroVersao = numeroVersao;
	}

	@Column(name="rrc", columnDefinition="nvarchar")
	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}
	
	@Column(name="ddd_distribuidor", columnDefinition="nvarchar")
	public String getReady() {
		if(ready == null || ready.equalsIgnoreCase("0")) {
			ready = "0";
		} else {
			ready = "1";
		}
		return ready;
	}

	public void setReady(String ready) {
		this.ready = ready;
	}
	
	@OneToMany(mappedBy = "sacOcorrencia")
	public List<SacDesenvolvimento> getSacDesenvolvimento() {
		return sacDesenvolvimento;
	}

	public void setSacDesenvolvimento(List<SacDesenvolvimento> sacDesenvolvimento) {
		this.sacDesenvolvimento = sacDesenvolvimento;
	}

	@OneToMany
	@JoinColumn(name = "ocor_codigo")
	public List<SacOcorrenciaFollowUp> getSacFollowUp() {
		return sacFollowUp;
	}

	public void setSacFollowUp(List<SacOcorrenciaFollowUp> sacFollowUp) {
		this.sacFollowUp = sacFollowUp;
	}

	@Column(name="data_solucao", columnDefinition="datetime")
	public Date getDataSolucao() {
		return dataSolucao;
	}

	public void setDataSolucao(Date dataSolucao) {
		this.dataSolucao = dataSolucao;
	}

	public SacOcorrenciaDTO getOcorrenciaDTO(SacOcorrenciaDTO dto) {
		if (dto == null) {
			dto = new SacOcorrenciaDTO();
		}		
		dto.setId(this.getId());
		dto.setIdCliente(this.getCliente() != null ? this.getCliente().getId():null);
		dto.setNomeCliente(this.getCliente() != null ? this.getCliente().getNome():null);
		dto.setDataCadastro(this.getDataCadastro());
		dto.setIdSituacao(this.getSituacaoOcorrencia() != null ? this.getSituacaoOcorrencia().getCode():null);
		dto.setAssunto(this.getAssunto());
		dto.setIdEtapa(this.getEtapa() != null ? this.getEtapa().getId():null);
		dto.setNomeEtapa(this.getEtapa() != null ? this.getEtapa().getNome():null);
		dto.setSolicitacao(this.getSolicitacao());
		dto.setSolucao(this.getSolucao());
		dto.setComentario(this.getComentario());
		dto.setEstimativa(this.getEstimativa());
		dto.setDescricaoDesenvolvimento(this.getDescricaoDesenvolvimento());
		dto.setComentarioDesenvolvimento(this.getComentarioDesenvolvimento());
		dto.setDataUltimoRedirecionamento(this.getDataUltimoRedirecionamento());
		if (this.getFuncionarioRedirecionamento() != null ) {
			dto.setIdFuncionario(this.getFuncionarioRedirecionamento().getId());
		} else if (this.getFuncionarioCadastro() != null) {
			dto.setIdFuncionario(this.getFuncionarioCadastro().getId());
		}
		dto.setDataPrevisaoTermino(this.getDataPrevisaoTermino());	
		dto.setNumeroVersao(this.getNumeroVersao());
		dto.setPrioridade(this.getPrioridade());
		dto.setReady((this.getReady().equalsIgnoreCase("1")) ? true : false);
		dto.setDataSolucao(this.getDataSolucao());
		return dto;		
	}
	
}
