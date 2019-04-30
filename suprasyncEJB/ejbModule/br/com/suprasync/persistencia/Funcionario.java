package br.com.suprasync.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity  
@Table(name="funcionario")
public class Funcionario {

	private int id;	
	private String nome;
	private Date dataExclusao;
	private List<SacEtapa> etapas;
	private boolean efetuarAnalise;
	private boolean providenciarSolucao;
	private boolean executarSolucao;
	private boolean providenciarFeedback;
	private boolean ativoSac;
	private List<SacDesenvolvimento> sacDesenvolvimento;
	private Funcao funcao; 
	private String usuarioSlack; //esta coluna só existe em bancos de dados da Suprasoft
			
	public Funcionario() {
		/*construtor padrão*/
	}
	
	@Id 
	@Column(name="codigo")/*este valor corresponde ao func_codigo do usuario do supra*/
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nome", columnDefinition="nvarchar")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name="data_exclusao", columnDefinition="datetime")
	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sac_etapa_atendimento_responsavel", joinColumns = {@JoinColumn(name = "func_codigo_responsavel")},
	inverseJoinColumns = {@JoinColumn(name = "sacetapa_codigo")})
	@OrderBy(value="nome asc")
	public List<SacEtapa> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<SacEtapa> etapas) {
		this.etapas = etapas;
	}

	@Column(name = "ind_efetuar_analise", columnDefinition="smallint")
	public boolean isEfetuarAnalise() {
		return efetuarAnalise;
	}

	public void setEfetuarAnalise(boolean efetuarAnalise) {
		this.efetuarAnalise = efetuarAnalise;
	}

	@Column(name = "ind_providenciar_solucao", columnDefinition="smallint")
	public boolean isProvidenciarSolucao() {
		return providenciarSolucao;
	}
	
	public void setProvidenciarSolucao(boolean providenciarSolucao) {
		this.providenciarSolucao = providenciarSolucao;
	}

	@Column(name = "ind_executar_solucao", columnDefinition="smallint")
	public boolean isExecutarSolucao() {
		return executarSolucao;
	}

	public void setExecutarSolucao(boolean executarSolucao) {
		this.executarSolucao = executarSolucao;
	}

	@Column(name = "ind_providenciar_feedback", columnDefinition="smallint")
	public boolean isProvidenciarFeedback() {
		return providenciarFeedback;
	}

	public void setProvidenciarFeedback(boolean providenciarFeedback) {
		this.providenciarFeedback = providenciarFeedback;
	}

	@Transient
	public boolean isAtivoSac() {
		return (efetuarAnalise || executarSolucao || providenciarFeedback || providenciarSolucao || ativoSac);
	}

	public void setAtivoSac(boolean ativoSac) {
		this.ativoSac = ativoSac;
	}

	@OneToMany(mappedBy = "funcionario")
	public List<SacDesenvolvimento> getSacDesenvolvimento() {
		return sacDesenvolvimento;
	}

	public void setSacDesenvolvimento(List<SacDesenvolvimento> sacDesenvolvimento) {
		this.sacDesenvolvimento = sacDesenvolvimento;
	}

	@OneToOne
	@JoinColumn(name = "funfun_codigo")
	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	@Column(name = "usuario_slack", columnDefinition="nvarchar")
	public String getUsuarioSlack() {
		return usuarioSlack;
	}

	public void setUsuarioSlack(String usuarioSlack) {
		this.usuarioSlack = usuarioSlack;
	}
	
}
