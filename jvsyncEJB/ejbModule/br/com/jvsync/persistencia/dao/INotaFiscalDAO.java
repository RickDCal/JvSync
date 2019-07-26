package br.com.jvsync.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.jvsync.negocio.dto.FilterNotaFiscalDTO;
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.ItemNotaFiscal;
import br.com.jvsync.persistencia.MSCabecalhoNotaFiscal;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Local
public interface INotaFiscalDAO {
	
	public List<CabecalhoNotaFiscal> obterCabecalhos(Integer position, Integer max) throws ObjetoNaoEncontradoException;
	
	public List<CabecalhoNotaFiscal> obter (FilterNotaFiscalDTO filter);
	
	public List<ItemNotaFiscal> obterItens (FilterNotaFiscalDTO filter);
	
	public boolean gravaCabecalho(MSCabecalhoNotaFiscal cabecalho);
	
}