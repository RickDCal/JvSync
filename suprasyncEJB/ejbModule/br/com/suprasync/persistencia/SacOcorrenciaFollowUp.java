package br.com.suprasync.persistencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table (name="sac_follow_up")
@IdClass(value = SacFollowUpPK.class)
public class SacOcorrenciaFollowUp {
	
	@Id
	@Column(name = "ocor_codigo", columnDefinition="int")
	private int idSacOcorrencia;
	
	@Id
	@Column(name = "codigo", columnDefinition="int")
	private int codigo;
	
	@Column(name = "data", columnDefinition="datetime")
	private Date data;
	
	@Column(name = "usu_codigo", columnDefinition="int")
	private int idUsuario;
	
	@Column(name = "historico", columnDefinition="nvarchar")
	private String historico;	
	
	public SacOcorrenciaFollowUp() {

	}
	
	public int getIdSacOcorrencia() {
		return idSacOcorrencia;
	}

	public void setIdSacOcorrencia(int idSacOcorrencia) {
		this.idSacOcorrencia = idSacOcorrencia;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}	
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

}
