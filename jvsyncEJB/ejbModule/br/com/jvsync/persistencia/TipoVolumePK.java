package br.com.jvsync.persistencia;

import java.io.Serializable;

import javax.persistence.Column;

public class TipoVolumePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "codprod", columnDefinition="number")
	private int idproduto;

	@Column(name = "codvol" , columnDefinition="varchar2")
	private int codVol;

}