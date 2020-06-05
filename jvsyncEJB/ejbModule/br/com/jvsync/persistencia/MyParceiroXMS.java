package br.com.jvsync.persistencia;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="xempresa_parceiro_auxiliar")

public class MyParceiroXMS {

	@Id 
	@Column(name="referencia", columnDefinition="int")
	private Long id;
	
	@Column(name="empresa", columnDefinition="varchar")
	private String codigoEmpresa;
	
	@Column(name="item", columnDefinition="int")
	private Integer item;
	
	@Column(name="endereco1", columnDefinition="varchar")
	private String endereco1;
	
	@Column(name="numero", columnDefinition="varchar")
	private String numero;
	
	@Column(name="complemento", columnDefinition="varchar")
	private String complemento;
	
	@Column(name="endereco2", columnDefinition="varchar")
	private String bairro;
	
	@Column(name="cidade", columnDefinition="varchar")
	private String cidade;
	
	@Column(name="cep", columnDefinition="varchar")
	private String cep;
	
	@Column(name="telefone", columnDefinition="varchar")
	private String telefone;
	
	@Column(name="email", columnDefinition="varchar")
	private String email;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}	
	
}
