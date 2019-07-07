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
@Table(name="TGFCAB")

public class CabecalhoNotaFiscal {

	@Id 
	@Column(name="nunota", columnDefinition="number")
	//@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column(name="numnota", columnDefinition="number")
	private Integer numNota;
	
	@Column(name="chavenfe", columnDefinition="varchar")
	private String chavenfe;
	
	@Column(name="dtmov", columnDefinition="date")
	private Date dtMov;
	
	@Column(name="hrmov", columnDefinition="number")
	private String hrmov;
	
	@Column(name="dtentsai", columnDefinition="date")
	private Date dtEntSai;
	
	@Column(name="codparc", columnDefinition="number")
	private Integer codParc;
	
	@Column(name="baseicms", columnDefinition="float")
	private Double baseIcms;
	
	@Column(name="vlricms", columnDefinition="float")
	private Double vlrIcms;
	
	@Column(name="basesubstit", columnDefinition="float")
	private Double baseSubstit;
	
	@Column(name="vlrsubst", columnDefinition="float")
	private Double vlrSubst;
	
	@Column(name="totalcustoprod", columnDefinition="float")
	private Double totalCustoProd;
	
	@Column(name="vlrfrete", columnDefinition="float")
	private Double vlrFrete;
	
	@Column(name="vlroutros", columnDefinition="float")
	private Double vlroutros;
	
	@Column(name="vlrnota", columnDefinition="float")
	private Double vlrNota;
	
	@Column(name="codparctransp", columnDefinition="number")
	private Double codParcTransp;	
	
	@Column(name="qtdvol", columnDefinition="number")
	private Integer qtdVol;
	
	@Column(name="volume", columnDefinition="varchar2")
	private String volume;
	
	@Column(name="peso", columnDefinition="float")
	private Double peso;
	
	@Column(name="statusnfe", columnDefinition="varchar")
	private String statusnfe;
	
	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumNota() {
		return numNota;
	}

	public void setNumNota(int numNota) {
		this.numNota = numNota;
	}

	public String getChavenfe() {
		return chavenfe;
	}

	public void setChavenfe(String chavenfe) {
		this.chavenfe = chavenfe;
	}

	public Date getDtMov() {
		return dtMov;
	}

	public void setDtMov(Date dtMov) {
		this.dtMov = dtMov;
	}

	public String getHrmov() {
		return hrmov;
	}

	public void setHrmov(String hrmov) {
		this.hrmov = hrmov;
	}

	public Date getDtEntSai() {
		return dtEntSai;
	}

	public void setDtEntSai(Date dtEntSai) {
		this.dtEntSai = dtEntSai;
	}

	public Integer getCodParc() {
		return codParc;
	}

	public void setCodParc(Integer codParc) {
		this.codParc = codParc;
	}

	public Double getBaseIcms() {
		return baseIcms;
	}

	public void setBaseIcms(Double baseIcms) {
		this.baseIcms = baseIcms;
	}

	public Double getVlrIcms() {
		return vlrIcms;
	}

	public void setVlrIcms(Double vlrIcms) {
		this.vlrIcms = vlrIcms;
	}

	public Double getBaseSubstit() {
		return baseSubstit;
	}

	public void setBaseSubstit(Double baseSubstit) {
		this.baseSubstit = baseSubstit;
	}

	public Double getVlrSubst() {
		return vlrSubst;
	}

	public void setVlrSubst(Double vlrSubst) {
		this.vlrSubst = vlrSubst;
	}

	public Double getTotalCustoProd() {
		return totalCustoProd;
	}

	public void setTotalCustoProd(Double totalCustoProd) {
		this.totalCustoProd = totalCustoProd;
	}

	public Double getVlrFrete() {
		return vlrFrete;
	}

	public void setVlrFrete(Double vlrFrete) {
		this.vlrFrete = vlrFrete;
	}

	public Double getVlroutros() {
		return vlroutros;
	}

	public void setVlroutros(Double vlroutros) {
		this.vlroutros = vlroutros;
	}

	public Double getVlrNota() {
		return vlrNota;
	}

	public void setVlrNota(Double vlrNota) {
		this.vlrNota = vlrNota;
	}

	public Double getCodParcTransp() {
		return codParcTransp;
	}

	public void setCodParcTransp(Double codParcTransp) {
		this.codParcTransp = codParcTransp;
	}

	public Integer getQtdVol() {
		return qtdVol;
	}

	public void setQtdVol(Integer qtdVol) {
		this.qtdVol = qtdVol;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getStatusnfe() {
		return statusnfe;
	}

	public void setStatusnfe(String statusnfe) {
		this.statusnfe = statusnfe;
	}

	@Override
	public String toString() {
		return "CabecalhoNotaFiscal [id=" + id + ", numNota=" + numNota + ", chavenfe=" + chavenfe + ", dtMov=" + dtMov
				+ ", hrmov=" + hrmov + ", dtEntSai=" + dtEntSai + ", codParc=" + codParc + ", baseIcms=" + baseIcms
				+ ", vlrIcms=" + vlrIcms + ", baseSubstit=" + baseSubstit + ", vlrSubst=" + vlrSubst
				+ ", totalCustoProd=" + totalCustoProd + ", vlrFrete=" + vlrFrete + ", vlroutros=" + vlroutros
				+ ", vlrNota=" + vlrNota + ", codParcTransp=" + codParcTransp + ", qtdVol=" + qtdVol + ", volume="
				+ volume + ", peso=" + peso + ", statusnfe=" + statusnfe + "]";
	}
	
}
