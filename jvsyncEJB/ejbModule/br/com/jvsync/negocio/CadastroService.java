package br.com.jvsync.negocio;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.jvsync.negocio.dto.FilterParceiroDTO;
import br.com.jvsync.negocio.dto.FilterProdutoDTO;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;
import br.com.jvsync.persistencia.dao.ICadastroDAO;

@Stateless
public class CadastroService implements ICadastroServiceLocal {

	@EJB
	private ICadastroDAO cadastroDao;

	public CadastroService() {

	}	

	
	public List<Parceiro> pesquisarParceiros (FilterParceiroDTO filter) {
		return cadastroDao.obterParceiros(filter);
	}
	
	public List<Produto> pesquisarProdutos (FilterProdutoDTO filter) {
		return cadastroDao.obterProdutos(filter);
	}
	
	public Map<String, String> ultimaAtualizacao() {
		return cadastroDao.ultimaAtualizacao();
	}
	
}
