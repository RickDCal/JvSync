package br.com.suprasync.persistencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name="funcao_funcionario")// o nome da tabela correspondente quando for entity
public class Funcao {

	private int id;	
	private String nome;
	private Date dataExclusao;
				
	public Funcao() {
		
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
	
}
