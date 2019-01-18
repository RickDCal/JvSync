package br.com.suprasync.negocio;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.suprasync.persistencia.VersaoSistema;
import br.com.suprasync.persistencia.dao.IVersaoSistemaDAO;
import br.com.suprasync.persistencia.enumerate.ProdutoSuprasoftEnum;

@Stateless
public class VersaoSistemaService implements IVersaoSistemaServiceLocal {

	@EJB
	private IVersaoSistemaDAO versaoSistemaDao;

	public VersaoSistemaService() {

	}	
	
	public VersaoSistema obterPorNumeroVersao(String numeroVersao, ProdutoSuprasoftEnum produtoEnum) {
		return versaoSistemaDao.obterPorNumeroVersao(numeroVersao, produtoEnum);
	}
	
	public VersaoSistema obterVersaoAtivaMaisRecente (ProdutoSuprasoftEnum produtoEnum) {
		return versaoSistemaDao.obterVersaoAtivaMaisRecente(produtoEnum);		
	}

}
