package br.com.suprasync.negocio;

import br.com.suprasync.persistencia.VersaoSistema;
import br.com.suprasync.persistencia.enumerate.ProdutoSuprasoftEnum;

public interface IVersaoSistemaServiceLocal {
	
	public VersaoSistema obterPorNumeroVersao(String numeroVersao, ProdutoSuprasoftEnum produtoEnum);
	
	public VersaoSistema obterVersaoAtivaMaisRecente (ProdutoSuprasoftEnum produtoEnum);

}