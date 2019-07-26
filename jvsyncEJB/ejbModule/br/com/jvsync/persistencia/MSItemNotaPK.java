package br.com.jvsync.persistencia;

import java.io.Serializable;

import javax.persistence.Column;

public class MSItemNotaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "nunota", columnDefinition="bigint")
	private Long idNota;

	@Column(name = "sequencia" , columnDefinition="bigint")
	private Long sequencia;

	public MSItemNotaPK() {
		// TODO Auto-generated constructor stub
	}
}