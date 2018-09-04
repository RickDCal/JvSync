package br.com.suprasync.persistencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.suprasync.negocio.dto.SacOcorrenciaFollowUpDTO;

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

	@OneToOne
	@JoinColumn(name = "usu_codigo", columnDefinition="int")
	private Usuario usuario; 
	
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
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}
	
	public SacOcorrenciaFollowUpDTO getOcorrenciaFollowUpDTO(SacOcorrenciaFollowUpDTO dto) {
		if (dto == null) {
			dto = new SacOcorrenciaFollowUpDTO();
		}
		
		dto.setIdSacOcorrencia(this.getIdSacOcorrencia());
		dto.setIdUsuario(this.getUsuario().getId());
		dto.setNomeUsuario(this.getUsuario().getNome());
		dto.setData(this.getData());
		dto.setHistorico(this.getHistorico());
		return dto;
	}
		

}
