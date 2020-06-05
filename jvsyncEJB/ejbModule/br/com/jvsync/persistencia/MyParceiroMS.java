package br.com.jvsync.persistencia;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="empresa_parceiro")

public class MyParceiroMS {

	@Id 
	@Column(name="referencia", columnDefinition="int")
	private Long id;
	
	@Column(name="codigo", columnDefinition="varchar")
	private String codigo;
	
	@Column(name="nome_fantasia", columnDefinition="varchar")
	private String nomeFantasia;
	
	@Column(name="razao_social", columnDefinition="varchar")
	private String razaoSocial;
	
	@Column(name="data_cadastro", columnDefinition="datetime")
	private Date dataCadastro;
	
	@Column(name="cgc", columnDefinition="varchar")
	private String cnpj;
	
	@Column(name="cpf", columnDefinition="varchar")
	private String cpf;
	
	@Column(name="sexo", columnDefinition="varchar")
	private String sexo;
	
	@Column(name="saldo", columnDefinition="float")
	private BigDecimal saldo;
	
	@Column(name="classificacao", columnDefinition="varchar")
	private String classificacao;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}	
	
}
