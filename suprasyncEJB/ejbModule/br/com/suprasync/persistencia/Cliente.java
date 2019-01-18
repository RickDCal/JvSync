package br.com.suprasync.persistencia;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity  
@Table(name="cliente_fornecedor")

public class Cliente {

	@Id 
	@Column(name="codigo", columnDefinition="int")
	//@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;	
	
	@Column(name="nome", columnDefinition="nvarchar")
	private String nome;
	
	@Column(name="cnpj", columnDefinition="nvarchar")
	private String cnpj;
	
	@Column(name="data_exclusao", columnDefinition="datetime")
	private Date dataExclusao;
		
	public Cliente() {
		
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(super.toString());
	    stringBuilder.append("Id: " + getId() + "\n <br>");
	    stringBuilder.append("Nome: " + getNome() + "\n <br>");
	   	   
	   	return stringBuilder.toString();
	}

}
