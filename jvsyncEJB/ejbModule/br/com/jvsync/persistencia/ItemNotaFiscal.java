package br.com.jvsync.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Entity  
@Table(name="TGFITE")
@IdClass(value = ItemNotaPK.class)
public class ItemNotaFiscal {
	
	@Id 
	@Column(name="nunota", columnDefinition="number")
	private Long idNota;
	
	@Column(name="sequenciaFiscal", columnDefinition="number")
	private Long sequenciaFiscal;
	
	@Id 
	@Column(name="sequencia", columnDefinition="number")
	private Long sequencia;
	
	@Column(name="codProd", columnDefinition="number")
	private Long codProd;
	
	@Column(name="codCfo", columnDefinition="number")
	private Long codCfo;
	
	@Column(name="qtdNeg", columnDefinition="float")
	private Double qtdNeg;
	
	@Column(name="codVol", columnDefinition="varchar2")
	private String codVol;
	
	@Column(name="vlrUnit", columnDefinition="float")
	private Double vlrUnit;
	
	@Column(name="vlrDesc", columnDefinition="float")
	private Double vlrDesc;
	
	@Column(name="vlrTot", columnDefinition="float")
	private Double vlrTot;
	
	@Column(name="baseIcms", columnDefinition="float")
	private Double baseIcms;
	
	@Column(name="vlrIcms", columnDefinition="float")
	private Double vlrIcms;
	
	@Column(name="aliqIcms", columnDefinition="float")
	private Double aliqIcms;
	
	@Column(name="custo", columnDefinition="float")
	private Double custo;
	
	@Column(name="codvend", columnDefinition="number")
	private Long codvend;
	
	@Column(name="vlrsubst", columnDefinition="float")
	private Double vlrsubst;

	public JsonObject toJson() {
		return (JsonObject) new JsonParser().parse(new Gson().toJson(this));		
	}

	public Long getIdNota() {
		return idNota;
	}

	public void setIdNota(Long idNota) {
		this.idNota = idNota;
	}

	public Long getSequenciaFiscal() {
		return sequenciaFiscal;
	}

	public void setSequenciaFiscal(Long sequenciaFiscal) {
		this.sequenciaFiscal = sequenciaFiscal;
	}

	public Long getSequencia() {
		return sequencia;
	}

	public void setSequencia(Long sequencia) {
		this.sequencia = sequencia;
	}

	public Long getCodProd() {
		return codProd;
	}

	public void setCodProd(Long codProd) {
		this.codProd = codProd;
	}

	public Long getCodCfo() {
		return codCfo;
	}

	public void setCodCfo(Long codCfo) {
		this.codCfo = codCfo;
	}

	public Double getQtdNeg() {
		return qtdNeg;
	}

	public void setQtdNeg(Double qtdNeg) {
		this.qtdNeg = qtdNeg;
	}

	public String getCodVol() {
		return codVol;
	}

	public void setCodVol(String codVol) {
		this.codVol = codVol;
	}

	public Double getVlrUnit() {
		return vlrUnit;
	}

	public void setVlrUnit(Double vlrUnit) {
		this.vlrUnit = vlrUnit;
	}

	public Double getVlrDesc() {
		return vlrDesc;
	}

	public void setVlrDesc(Double vlrDesc) {
		this.vlrDesc = vlrDesc;
	}

	public Double getVlrTot() {
		return vlrTot;
	}

	public void setVlrTot(Double vlrTot) {
		this.vlrTot = vlrTot;
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

	public Double getAliqIcms() {
		return aliqIcms;
	}

	public void setAliqIcms(Double aliqIcms) {
		this.aliqIcms = aliqIcms;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public Long getCodvend() {
		return codvend;
	}

	public void setCodvend(Long codvend) {
		this.codvend = codvend;
	}
	
	public Double getVlrsubst() {
		return vlrsubst;
	}
	
	public void setVlrsubst(Double vlrsubst) {
		this.vlrsubst = vlrsubst;
	}

	@Override
	public String toString() {
		return "ItemNotaFiscal [idNota=" + idNota + ", sequenciaFiscal=" + sequenciaFiscal + ", sequencia=" + sequencia
				+ ", codProd=" + codProd + ", codCfo=" + codCfo + ", qtdNeg=" + qtdNeg + ", codVol=" + codVol
				+ ", vlrUnit=" + vlrUnit + ", vlrDesc=" + vlrDesc + ", vlrTot=" + vlrTot + ", baseIcms=" + baseIcms
				+ ", vlrIcms=" + vlrIcms + ", aliqIcms=" + aliqIcms + ", custo=" + custo + ", codvend=" + codvend
				+ ", vlrsubst=" + vlrsubst + "]";
	}	

}
