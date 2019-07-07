package br.com.jvsync.negocio.dto;

import java.util.Date;

public class FilterProdutoDTO {
	
	private Integer position;
	private Integer max;
	private Integer id;
	private Date dataInicialCadastro;
	private Date dataFinalCadastro;	
	
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataInicialCadastro() {
		return dataInicialCadastro;
	}
	public void setDataInicialCadastro(Date dataInicialCadastro) {
		this.dataInicialCadastro = dataInicialCadastro;
	}
	public Date getDataFinalCadastro() {
		return dataFinalCadastro;
	}
	public void setDataFinalCadastro(Date dataFinalCadastro) {
		this.dataFinalCadastro = dataFinalCadastro;
	}
	
}
