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
@Table(name="xp1")

public class MyProduto {

	@Id 
	@Column(name="referencia", columnDefinition="int")
	private Long id;
	
	@Column(name="produto", columnDefinition="varchar")
	private String codigoProduto;
	
	@Column(name="descricao", columnDefinition="varchar")
	private String nome;
	
	@Column(name="resumo_descricao", columnDefinition="varchar")
	private String resumoDescricao;
	
	@Column(name="unidade", columnDefinition="varchar")
	private String unidade;
	
	@Column(name="preco_venda", columnDefinition="decimal")
	private BigDecimal cnpj;
	
	@Column(name="custo_compra", columnDefinition="decimal")
	private BigDecimal custoCompra;
	
	@Column(name="ultimo_reajuste", columnDefinition="datetime")
	private Date dataUltimoReajuste;
	
	@Column(name="ultima_entrada", columnDefinition="datetime")
	private Date dataUltimasEntrada;
	
	@Column(name="peso_bru_por_unidade", columnDefinition="double")
	private BigDecimal pesoBruto;
	
	@Column(name="peso_liq_por_unidade", columnDefinition="double")
	private BigDecimal pesoLiquido;
	
	@Column(name="ncm", columnDefinition="varchar")
	private String ncm;
	
	@Column(name="promocao_1", columnDefinition="decimal")
	private BigDecimal precoPromocao;
	
	@Column(name="status_produto", columnDefinition="varchar")
	private String statusProduto;
	
	@Column(name="tipo_item", columnDefinition="varchar")
	private String tipoItem;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}	
	
}
