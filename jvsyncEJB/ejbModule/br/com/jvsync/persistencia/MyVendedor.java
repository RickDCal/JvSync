package br.com.jvsync.persistencia;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="vendedor")

public class MyVendedor {

	@Id 
	@Column(name="referencia", columnDefinition="int")
	private Long id;
	
	@Column(name="codigo", columnDefinition="varchar")
	private String codigo;
	
	@Column(name="nome_vendedor", columnDefinition="varchar")
	private String nome;
	
	@Column(name="email", columnDefinition="varchar")
	private String email;
	
	@Column(name="telefone", columnDefinition="varchar")
	private String telefone;
	
	@Column(name="cnpj", columnDefinition="varchar")
	private String cnpj;
	
	@Column(name="cpf", columnDefinition="varchar")
	private String cpf;
	
	@Column(name="cidade", columnDefinition="varchar")
	private String sexo;
	
	@Column(name="estado", columnDefinition="varchar")
	private String estado;
	
	@Column(name="cep", columnDefinition="varchar")
	private String cep;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}	
	
}
