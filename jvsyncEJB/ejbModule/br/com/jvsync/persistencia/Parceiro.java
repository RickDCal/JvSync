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

public class Parceiro {

	@Id 
	@Column(name="codparc", columnDefinition="number")
	private int id;
	
	@Column(name="nomeparc", columnDefinition="varchar")
	private String nomeParc;
	
	@Column(name="razaosocial", columnDefinition="varchar")
	private String razaoSocial;
	
	@Column(name="codend", columnDefinition="number")
	private Integer codEnd;
	
	@Column(name="numend", columnDefinition="varchar2")
	private String numEnd;
	
	@Column(name="codbai", columnDefinition="number")
	private Integer codBai;
	
	@Column(name="codcid", columnDefinition="number")
	private Integer codcid;
	
	@Column(name="cep", columnDefinition="varchar2")
	private String cep;
	
	@Column(name="dtcad", columnDefinition="date")
	private Date dtCad;
	
	@Column(name="dtalter", columnDefinition="date")
	private Date dtAlter;
	
	@Column(name="cgc_cpf", columnDefinition="varchar2")
	private String cgcCpf;
	
	@Column(name="cliente", columnDefinition="varchar")
	private String isCliente;
	
	@Column(name="fornecedor", columnDefinition="varchar")
	private String isFornecedor;
	
	@Column(name="transportadora", columnDefinition="varchar")
	private String isTransportadora;
	
	@Column(name="ativo", columnDefinition="varchar")
	private String isAtivo;
	
	@Column(name="limcred", columnDefinition="float")
	private Double limCred;
	
	@Column(name="emailnfe", columnDefinition="varchar")
	private String emailNfe;
	
	@Column(name="codrota", columnDefinition="number")
	private String codRota;
	
	@Column(name="latitude", columnDefinition="varchar")
	private String latitude;
	
	@Column(name="longitude", columnDefinition="varchar")
	private String longitude;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Integer getCodEnd() {
		return codEnd;
	}

	public void setCodEnd(Integer codEnd) {
		this.codEnd = codEnd;
	}

	public String getNumEnd() {
		return numEnd;
	}

	public void setNumEnd(String numEnd) {
		this.numEnd = numEnd;
	}

	public Integer getCodBai() {
		return codBai;
	}

	public void setCodBai(Integer codBai) {
		this.codBai = codBai;
	}

	public Integer getCodcid() {
		return codcid;
	}

	public void setCodcid(Integer codcid) {
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
