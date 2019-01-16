package br.com.suprasync.persistencia;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table (name="sac_ocorrencia_arquivo")
@IdClass(value = SacArquivoPK.class)
public class SacOcorrenciaArquivo {
	
	@Id
	@Expose
	@Column(name = "sac_codigo", columnDefinition="int")
	private int idSacOcorrencia;

	@Id
	@Expose
	@Column(name = "codigo", columnDefinition="int")
	private int codigo;
	
	@Expose
	@Column(name = "nome_arquivo", columnDefinition="nvarchar")
	private String nomeArquivo;
	
	@Expose
	@Column(name = "descricao", columnDefinition="nvarchar")
	private String desricao;
	
	
	@Expose
	@Column(name = "data", columnDefinition="datetime")
	private Date data;
	
	@Column(name = "imagem", columnDefinition="image")
	private byte[] arquivo;
	//private Blob arquivo;	

	public SacOcorrenciaArquivo() {
		// TODO Auto-generated constructor stub
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

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getDesricao() {
		return desricao;
	}

	public void setDesricao(String desricao) {
		this.desricao = desricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

//	public Blob getArquivo() {
//		return arquivo;
//	}
//
//	public void setArquivo(Blob arquivo) {
//		this.arquivo = arquivo;
//	}
	

}
