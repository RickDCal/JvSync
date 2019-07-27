package br.com.jvsync.persistencia;

import java.io.Serializable;

import javax.persistence.Column;

public class MSTipoVolumePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "codprod", columnDefinition="bigint")
	private Long idproduto;

	@Column(name = "codvol" , columnDefinition="nvarchar")
	private String codVol;

}