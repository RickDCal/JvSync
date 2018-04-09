package br.com.suprasync.persistencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name="funcionario")// o nome da tabela correspondente quando for entity
public class Funcionario {

	private int id;	
	private String nome;
	private Date dataExclusao;
	//private List<Usuario> usuarios;
	
			
	public Funcionario() {
		
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

//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name = "func_codigo")
//	@OrderBy(value="codigo DESC")
//	public List<Usuario> getUsuarios() {
//		return usuarios;
//	}
//
//	public void setUsuarios(List<Usuario> usuarios) {
//		this.usuarios = usuarios;
//	}
	
}
