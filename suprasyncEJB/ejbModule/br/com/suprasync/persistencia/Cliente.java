package br.com.suprasync.persistencia;


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


	public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(super.toString());
	    stringBuilder.append("Id: " + getId() + "\n <br>");
	    stringBuilder.append("Nome: " + getNome() + "\n <br>");
	   	   
	   	return stringBuilder.toString();
	}





	


}
