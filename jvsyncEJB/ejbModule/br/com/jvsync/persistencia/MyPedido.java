package br.com.jvsync.persistencia;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="saida")

public class MyPedido {
	
	@Transient
	public MyPedidoMS classeCorrespondente; // do tipo da classe correspondente no banco SQLserver.

	@Id 
	@Column(name="referencia", columnDefinition="int")
	private Long id;
	
	@Column(name="data_abertura", columnDefinition="datetime")
	private Date dataAbertura;
	
	@Column(name="data_saida", columnDefinition="datetime")
	private Date dataSaida;
	
	@Column(name="codigo_empresa", columnDefinition="varchar")
	private Integer codigoCliente;
	
	@Column(name="subs_tributaria", columnDefinition="decimal")
	private BigDecimal valorSt;
	
	@Column(name="desconto_1", columnDefinition="decimal")
	private BigDecimal valorDesconto;
	
	@Column(name="despesas_acessorias", columnDefinition="decimal")
	private BigDecimal valorDespesaAcessoria;
	
	@Column(name="seguro", columnDefinition="decimal")
	private BigDecimal valorSeguro;
	
	@Column(name="outros", columnDefinition="decimal")
	private BigDecimal valorOutros;
	
	@Column(name="valor_1", columnDefinition="decimal")
	private BigDecimal valorTotal;
	
	@Column(name="nome_transportadora", columnDefinition="varchar")
	private String nomeTransportadora;	
	
	@Column(name="vendedor", columnDefinition="varchar")
	private String vendedor;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}	
	
}
