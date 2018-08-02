package br.com.suprasync.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity  
@Table(name="usuario")// o nome da tabela correspondente quando for entity
public class Usuario {

	private int id;	
	private String nome;
	private String senha;
	private Integer idFuncionario;
	private Date dataExclusao;
	private Integer idPerfil;
	private String usuarioSlack;
	private List<SacDesenvolvimento> sacDesenvolvimento;
	
			
	public Usuario() {
		
	}
	
	@Id //indica chave prim�ria
	@Column(name="codigo")/*este valor corresponde ao func_codigo do usuario do supra*/
	//@GeneratedValue(strategy = GenerationType.IDENTITY) //auto incermento na chave
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="nome", columnDefinition="nvarchar")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "senha", columnDefinition="nvarchar")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "func_codigo", columnDefinition="int")
	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	@Column(name="data_exclusao", columnDefinition="datetime")
	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	@Column(name="perf_codigo", columnDefinition="int")
	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	@Column(name = "usuario_slack", columnDefinition="nvarchar")
	public String getUsuarioSlack() {
		return usuarioSlack;
	}

	public void setUsuarioSlack(String usuarioSlack) {
		this.usuarioSlack = usuarioSlack;
	}

	@OneToMany(mappedBy = "usuario")
	public List<SacDesenvolvimento> getSacDesenvolvimento() {
		return sacDesenvolvimento;
	}

	public void setSacDesenvolvimento(List<SacDesenvolvimento> sacDesenvolvimento) {
		this.sacDesenvolvimento = sacDesenvolvimento;
	}
	
}
