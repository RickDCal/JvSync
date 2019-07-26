package br.com.jvsync.persistencia;

import java.io.Serializable;

import javax.persistence.Column;

public class ItemNotaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "nunota", columnDefinition="number")
	private Long idNota;

	@Column(name = "sequencia" , columnDefinition="number")
	private Long sequencia;

	public ItemNotaPK() {
		// TODO Auto-generated constructor stub
	}
}