package br.com.jvsync.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.jvsync.negocio.dto.FilterNotaFiscalDTO;
import br.com.jvsync.negocio.exception.ObjetoInexistenteException;
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.ItemNotaFiscal;
import br.com.jvsync.persistencia.MSCabecalhoNotaFiscal;
import br.com.jvsync.persistencia.dao.INotaFiscalDAO;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Stateless
public class NotaFiscalService implements INotaFiscalServiceLocal {

	@EJB
	private INotaFiscalDAO nfDao;

	public NotaFiscalService() {

	}	

	public List<CabecalhoNotaFiscal> pesquisar(Integer position, Integer max) throws ObjetoInexistenteException {

		try {
			return nfDao.obterCabecalhos(position, max);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ObjetoInexistenteException();
		}		

	}

	public List<CabecalhoNotaFiscal> pesquisar (FilterNotaFiscalDTO filter) {
		return nfDao.obter(filter);
	}

	public List<ItemNotaFiscal> obterItens (FilterNotaFiscalDTO filter) {
		return nfDao.obterItens(filter);
	}

	public boolean gravaCabecalho(MSCabecalhoNotaFiscal cabecalho) {
		return nfDao.gravaCabecalho(cabecalho);
	}
	
	public <T>List<Object> entidadesAtualizar(Class<T> classe) {
		return nfDao.entidadesAtualizar(classe);
	}
	
	public String atualizaDados (List<Object> entidadesAtualizar) {
		return nfDao.atualizaDados(entidadesAtualizar);
	}

}
