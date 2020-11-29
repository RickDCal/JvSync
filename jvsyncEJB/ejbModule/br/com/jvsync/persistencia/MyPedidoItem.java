package br.com.jvsync.persistencia;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="xsaida")

public class MyPedidoItem {
	
	@Transient
	public MyPedidoItemMS classeCorrespondente; // do tipo da classe correspondente no banco SQLserver.

	@Id 
	@Column(name="referencia", columnDefinition="int")
	private Long id;
	
	@Column(name="referencia_interna", columnDefinition="int")
	private Long idPedido;
	
	@Column(name="item", columnDefinition="int")
	private Integer item;
	
	@Column(name="produto", columnDefinition="varchar")
	private String codigoProduto;
	
	@Column(name="quantidade_impressao", columnDefinition="double")
	private BigDecimal quantidadeUnidadeVenda;
	
	@Column(name="unidade_impressao", columnDefinition="varchar")
	private String unidadeVenda;
	
	@Column(name="unitario_impressao", columnDefinition="double")
	private BigDecimal valorUnidadeVenda;
	
	@Column(name="quantidade", columnDefinition="double")
	private BigDecimal quantidadeUnidadepadrao;
	
	@Column(name="valor_unitario", columnDefinition="double")
	private BigDecimal valorUnidadepadrao;
	
	@Column(name="desconto", columnDefinition="double")
	private BigDecimal desconto;
	
	@Column(name="icms_subs", columnDefinition="double")
	private BigDecimal icmsSt;
	
	@Column(name="valor_base_icms", columnDefinition="double")
	private BigDecimal baseIcmsSt;
	
	@Column(name="valor_icms", columnDefinition="double")
	private BigDecimal valorIcms;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}	
	
}
