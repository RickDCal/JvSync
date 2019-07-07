package br.com.jvsync.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.jvsync.negocio.dto.FilterParceiroDTO;
import br.com.jvsync.negocio.dto.FilterProdutoDTO;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;

@Local
public interface ICadastroServiceLocal {
		
	public List<Parceiro> pesquisarParceiros (FilterParceiroDTO filter);
	
	public List<Produto> pesquisarProdutos (FilterProdutoDTO filter);
	
}