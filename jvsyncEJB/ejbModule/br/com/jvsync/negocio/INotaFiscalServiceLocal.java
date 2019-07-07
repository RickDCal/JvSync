package br.com.jvsync.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.jvsync.negocio.dto.FilterNotaFiscalDTO;
import br.com.jvsync.negocio.exception.ObjetoInexistenteException;
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.ItemNotaFiscal;

@Local
public interface INotaFiscalServiceLocal {
	
	public List<CabecalhoNotaFiscal> pesquisar(Integer position, Integer max) throws ObjetoInexistenteException;
	
	public List<CabecalhoNotaFiscal> pesquisar (FilterNotaFiscalDTO filter);
	
	public List<ItemNotaFiscal> obterItens (FilterNotaFiscalDTO filter);
	
}