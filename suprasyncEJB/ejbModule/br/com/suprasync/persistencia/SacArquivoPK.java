package br.com.suprasync.persistencia;

import java.io.Serializable;

import javax.persistence.Column;

public class SacArquivoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "sac_codigo", columnDefinition="int")
	private int idSacOcorrencia;

	@Column(name = "codigo")
	private int codigo;

	public SacArquivoPK() {
		// TODO Auto-generated constructor stub
	}
}