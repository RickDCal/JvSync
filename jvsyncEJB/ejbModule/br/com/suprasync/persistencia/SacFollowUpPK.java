package br.com.suprasync.persistencia;

import java.io.Serializable;

import javax.persistence.Column;

public class SacFollowUpPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ocor_codigo", columnDefinition="int")
	private int idSacOcorrencia;

	@Column(name = "codigo")
	private int sequencia;

	public SacFollowUpPK() {
		
	}
}