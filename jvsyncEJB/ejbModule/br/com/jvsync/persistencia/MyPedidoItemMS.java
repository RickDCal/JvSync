package br.com.jvsync.persistencia;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="aliar_xsaida_item_pedido")

public class MyPedidoItemMS {

	@Id 
	@Column(name="referencia", columnDefinition="int")
	private Long id;
	
	@Column(name="referencia_interna", columnDefinition="int")
	private Long idPedido;
	
	@Column(name="item", columnDefinition="int")
	private Integer item;
	
	@Column(name="produto", columnDefinition="varchar")
	private String codigoProduto;
	
	@Column(name="quantidade_impressao", columnDefinition="float")
	private BigDecimal quantidadeUnidadeVenda;
	
	@Column(name="unidade_impressao", columnDefinition="varchar")
	private String unidadeVenda;
	
	@Column(name="unitario_impressao", columnDefinition="float")
	private BigDecimal valorUnidadeVenda;
	
	@Column(name="quantidade", columnDefinition="float")
	private BigDecimal quantidadeUnidadepadrao;
	
	@Column(name="valor_unitario", columnDefinition="float")
	private BigDecimal valorUnidadepadrao;
	
	@Column(name="desconto", columnDefinition="float")
	private BigDecimal desconto;
	
	@Column(name="icms_subs", columnDefinition="float")
	private BigDecimal icmsSt;
	
	@Column(name="valor_base_icms", columnDefinition="float")
	private BigDecimal baseIcmsSt;
	
	@Column(name="valor_icms", columnDefinition="float")
	private BigDecimal valorIcms;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}	
	
}
