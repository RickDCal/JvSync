package br.com.suprasync.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity  
@Table(name="cliente_fornecedor_usuario_b2b")// o nome da tabela correspondente quando for entity
public class UsuarioB2B {

	private int id;	
	private Cliente cliente;
	private String nome;
	private String senha;
	private Integer idAcesso;
	private String login;
	private String email;
	//private List<SacDesenvolvimento> sacDesenvolvimento;
	
			
	public UsuarioB2B() {
		
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
	
	@ManyToOne
	@JoinColumn(name = "clifor_codigo", columnDefinition="nvarchar")
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Column(name = "id_tipo_acesso", columnDefinition="smallint")
	public Integer getIdAcesso() {
		return idAcesso;
	}

	public void setIdAcesso(Integer idAcesso) {
		this.idAcesso = idAcesso;
	}

	@Column(name = "login", columnDefinition="nvarchar")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "email", columnDefinition="nvarchar")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


//	@OneToMany(mappedBy = "usuario")
//	public List<SacDesenvolvimento> getSacDesenvolvimento() {
//		return sacDesenvolvimento;
//	}
//
//	public void setSacDesenvolvimento(List<SacDesenvolvimento> sacDesenvolvimento) {
//		this.sacDesenvolvimento = sacDesenvolvimento;
//	}
	
}
