package br.com.jvsync.negocio;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.jvsync.negocio.dto.FilterParceiroDTO;
import br.com.jvsync.negocio.dto.FilterProdutoDTO;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;
import br.com.jvsync.persistencia.RelacionamentoProduto;

@Local
public interface ICadastroServiceLocal {
		
	public List<Parceiro> pesquisarParceiros (FilterParceiroDTO filter);
	
	public List<Produto> pesquisarProdutos (FilterProdutoDTO filter);
	
	public Map<String, String> ultimaAtualizacao();
	
	public List<RelacionamentoProduto> obterRelacionamentoProduto (Integer id, String codigoAliar, String codigoJiva);
	
}