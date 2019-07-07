package br.com.jvsync.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.jvsync.negocio.dto.FilterParceiroDTO;
import br.com.jvsync.negocio.dto.FilterProdutoDTO;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;

@Local
public interface ICadastroDAO {
	
	public List<Parceiro> obterParceiros (FilterParceiroDTO filter);
	
	public List<Produto> obterProdutos (FilterProdutoDTO filter);
	
}