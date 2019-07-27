package br.com.jvsync.persistencia;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Entity  
@Table(name="TGFPAR")

public class MSParceiro {

	@Id 
	@Column(name="codparc", columnDefinition="bigint")
	private Long id;
	
	@Column(name="nomeparc", columnDefinition="nvarchar")
	private String nomeParc;
	
	@Column(name="razaosocial", columnDefinition="nvarchar")
	private String razaoSocial;
	
	@Column(name="codend", columnDefinition="bigint")
	private Long codEnd;
	
	@Column(name="numend", columnDefinition="nvarchar")
	private String numEnd;
	
	@Column(name="codbai", columnDefinition="bigint")
	private Long codBai;
	
	@Column(name="codcid", columnDefinition="bigint")
	private Long codcid;
	
	@Column(name="cep", columnDefinition="nvarchar")
	private String cep;
	
	@Column(name="dtcad", columnDefinition="date")
	private Date dtCad;
	
	@Column(name="dtalter", columnDefinition="date")
	private Date dtAlter;
	
	@Column(name="cgc_cpf", columnDefinition="nvarchar")
	private String cgcCpf;
	
	@Column(name="cliente", columnDefinition="nvarchar")
	private String isCliente;
	
	@Column(name="fornecedor", columnDefinition="nvarchar")
	private String isFornecedor;
	
	@Column(name="transportadora", columnDefinition="nvarchar")
	private String isTransportadora;
	
	@Column(name="ativo", columnDefinition="nvarchar")
	private String isAtivo;
	
	@Column(name="limcred", columnDefinition="float")
	private Double limCred;
	
	@Column(name="emailnfe", columnDefinition="nvarchar")
	private String emailNfe;
	
	@Column(name="codrota", columnDefinition="bigint")
	private String codRota;
	
	@Column(name="latitude", columnDefinition="nvarchar")
	private String latitude;
	
	@Column(name="longitude", columnDefinition="nvarchar")
	private String longitude;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeParc() {
		return nomeParc;
	}

	public void setNomeParc(String nomeParc) {
		this.nomeParc = nomeParc;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Long getCodEnd() {
		return codEnd;
	}

	public void setCodEnd(Long codEnd) {
		this.codEnd = codEnd;
	}

	public String getNumEnd() {
		return numEnd;
	}

	public void setNumEnd(String numEnd) {
		this.numEnd = numEnd;
	}

	public Long getCodBai() {
		return codBai;
	}

	public void setCodBai(Long codBai) {
		this.codBai = codBai;
	}

	public Long getCodcid() {
		return codcid;
	}

	public void setCodcid(Long codcid) {
		this.codcid = codcid;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Date getDtCad() {
		return dtCad;
	}

	public void setDtCad(Date dtCad) {
		this.dtCad = dtCad;
	}

	public Date getDtAlter() {
		return dtAlter;
	}

	public void setDtAlter(Date dtAlter) {
		this.dtAlter = dtAlter;
	}

	public String getCgcCpf() {
		return cgcCpf;
	}

	public void setCgcCpf(String cgcCpf) {
		this.cgcCpf = cgcCpf;
	}

	public String getIsCliente() {
		return isCliente;
	}

	public void setIsCliente(String isCliente) {
		this.isCliente = isCliente;
	}

	public String getIsFornecedor() {
		return isFornecedor;
	}

	public void setIsFornecedor(String isFornecedor) {
		this.isFornecedor = isFornecedor;
	}

	public String getIsTransportadora() {
		return isTransportadora;
	}

	public void setIsTransportadora(String isTransportadora) {
		this.isTransportadora = isTransportadora;
	}

	public String getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(String isAtivo) {
		this.isAtivo = isAtivo;
	}

	public Double getLimCred() {
		return limCred;
	}

	public void setLimCred(Double limCred) {
		this.limCred = limCred;
	}

	public String getEmailNfe() {
		return emailNfe;
	}

	public void setEmailNfe(String emailNfe) {
		this.emailNfe = emailNfe;
	}

	public String getCodRota() {
		return codRota;
	}

	public void setCodRota(String codRota) {
		this.codRota = codRota;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Parceiro [id=" + id + ", nomeParc=" + nomeParc + ", razaoSocial=" + razaoSocial + ", codEnd=" + codEnd
				+ ", numEnd=" + numEnd + ", codBai=" + codBai + ", codcid=" + codcid + ", cep=" + cep + ", dtCad="
				+ dtCad + ", dtAlter=" + dtAlter + ", cgcCpf=" + cgcCpf + ", isCliente=" + isCliente + ", isFornecedor="
				+ isFornecedor + ", isTransportadora=" + isTransportadora + ", isAtivo=" + isAtivo + ", limCred="
				+ limCred + ", emailNfe=" + emailNfe + ", codRota=" + codRota + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}	
	
	
	
}
