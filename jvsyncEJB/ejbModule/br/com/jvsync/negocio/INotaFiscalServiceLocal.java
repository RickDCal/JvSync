package br.com.jvsync.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.jvsync.negocio.dto.FilterNotaFiscalDTO;
import br.com.jvsync.negocio.exception.ObjetoInexistenteException;
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.ItemNotaFiscal;
import br.com.jvsync.persistencia.MSCabecalhoNotaFiscal;

@Local
public interface INotaFiscalServiceLocal {
	
	public List<CabecalhoNotaFiscal> pesquisar(Integer position, Integer max) throws ObjetoInexistenteException;
	
	public List<CabecalhoNotaFiscal> pesquisar (FilterNotaFiscalDTO filter);
	
	public List<ItemNotaFiscal> obterItens (FilterNotaFiscalDTO filter);
	
	public boolean gravaCabecalho(MSCabecalhoNotaFiscal cabecalho);
	
	public <T>List<Object> entidadesAtualizar(Class<T> classe);
	
	public String atualizaDados (List<Object> entidadesAtualizar);
	
}