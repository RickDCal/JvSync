package br.com.suprasync.persistencia.dao;

import javax.ejb.Local;

import br.com.suprasync.persistencia.VersaoSistema;
import br.com.suprasync.persistencia.enumerate.ProdutoSuprasoftEnum;



@Local
public interface IVersaoSistemaDAO {

	public VersaoSistema obterPorNumeroVersao(String numeroVersao, ProdutoSuprasoftEnum produtoEnum);
	
	public VersaoSistema obterVersaoAtivaMaisRecente (ProdutoSuprasoftEnum produtoEnum);
		

}