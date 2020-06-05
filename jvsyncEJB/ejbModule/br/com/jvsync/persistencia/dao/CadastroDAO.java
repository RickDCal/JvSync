package br.com.jvsync.persistencia.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.jvsync.negocio.dto.FilterParceiroDTO;
import br.com.jvsync.negocio.dto.FilterProdutoDTO;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;
import br.com.jvsync.util.Utilities;

@Stateless
public class CadastroDAO extends GenericDAO implements ICadastroDAO {


	public CadastroDAO() {

	}	
	
public List<Parceiro> obterParceiros (FilterParceiroDTO filter) {
		
		StringBuilder queryBuilder = new StringBuilder("select p from Parceiro p where p.id is not null");
				
		if (filter.getDataFinalCadastro() != null) {
			queryBuilder.append(" and c.dtCad <= :dtFinal" );
			parametros.put("dtFinal", filter.getDataFinalCadastro());
		}
		
		if (filter.getDataInicialCadastro() != null) {
			queryBuilder.append(" and c.dtCad >= :dtInicial" );
			parametros.put("dtInicial", filter.getDataInicialCadastro());
		}
		
		if (filter.getId() != null) {
			queryBuilder.append(" and p.id = :id" );
			parametros.put("id", filter.getId());
		}
		
		if (filter.getCgcCpf() != null) {
			queryBuilder.append(" and p.cgcCpf = :cgcCpf" );
			parametros.put("cgcCpf", filter.getCgcCpf());
		}
		
		queryBuilder.append(" order by p.id ");
		
		Query query = entityManager.createQuery(queryBuilder.toString());
		
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}
		
		if (filter.getPosition() != null) {
			query.setFirstResult(filter.getPosition());
		}

		if (filter.getMax() != null) {
			query.setMaxResults(filter.getMax());
		}		

		return query.getResultList();			
		
	}
	
	public List<Produto> obterProdutos (FilterProdutoDTO filter) {
		
		StringBuilder queryBuilder = new StringBuilder("select p from Produto p where p.id is not null");
				
		if (filter.getDataFinalCadastro() != null) {
			queryBuilder.append(" and c.dtCad <= :dtFinal" );
			parametros.put("dtFinal", filter.getDataFinalCadastro());
		}
		
		if (filter.getDataInicialCadastro() != null) {
			queryBuilder.append(" and c.dtCad >= :dtInicial" );
			parametros.put("dtInicial", filter.getDataInicialCadastro());
		}
		
		if (filter.getId() != null) {
			queryBuilder.append(" and p.id = :id" );
			parametros.put("id", filter.getId());
		}
		
		queryBuilder.append(" order by p.id ");
		
		Query query = entityManager.createQuery(queryBuilder.toString());
		
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}
		
		if (filter.getPosition() != null) {
			query.setFirstResult(filter.getPosition());
		}

		if (filter.getMax() != null) {
			query.setMaxResults(filter.getMax());
		}		

		return query.getResultList();			
		
	}
	
	
	public void removeDadosTabelas (String senha) {
		
		Query query = entityManager.createNativeQuery("SELECT table_name  FROM all_tables where tablespace_name = 'JIVA' and num_rows > 0");
		
		List<String> tabelas = query.getResultList();
		
		for (Iterator iterator = tabelas.iterator(); iterator.hasNext();) {
			String tabela = (String) iterator.next();
			query = entityManager.createNativeQuery("delete from " + tabela);
			if (query.executeUpdate() > 0 ) {
				System.out.println("dados removidos da tabela" + tabela);
			}			
		}		
	}
	
	public Map<String, String> ultimaAtualizacao() {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select 'Cabeçalhos de Notas' as tabela , max (data_registro) as dia from TGFCAB ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Itens de Notas', max (data_registro) from TGFITE ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Ruas', max (data_registro) from TFPLGR ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Clientes/Fornecedores', max (data_registro) from TGFPAR ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Cadastro de Produtos', max (data_registro) from TGFPRO ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Rotas', max (data_registro) from TGFROT ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tipos de Operações', max (data_registro) from TGFTOP ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tipos de Vendas', max (data_registro) from TGFTPV ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Cadastro de Vendedores', max (data_registro) from TGFVEN ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Volumes', max (data_registro) from TGFVOA ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Bairros', max (data_registro) from TSIBAI ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Cidades', max (data_registro) from TSICID ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Endereços', max (data_registro) from TSIEND ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Regiões', max (data_registro) from TSIREG ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Estados', max (data_registro) from TSIUFS ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Clientes - Aliar', max (data_registro) from EMPRESA_parceiro ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Clientes Auxiliar - Aliar', max (data_registro) from XEMPRESA_parceiro_auxiliar ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Cabeçalho Pedidos - Aliar', max (data_registro) from SAIDA_cabecalho_pedido ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Itens de Pedidos - Aliar', max (data_registro) from XSAIDA_item_pedido ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Produtos - Aliar', max (data_registro) from XP1_produto ").append(" union ").append(System.lineSeparator());
		queryBuilder.append("select 'Tabela de Vendedores - Aliar', max (data_registro) from VENDEDOR ");
		
		Query query = em.createNativeQuery(queryBuilder.toString());
		
		
		List<Object[]> resultQuery = query.getResultList();
		Map<String, String> map = new HashMap<>();

		for (Object[] row: resultQuery) {
			map.put((String) row[0], row[1] != null ? Utilities.dataYYYY_MM_DDeHHppmmppss((Date) row[1]) : "");
		}
		
		return map;		
	}
	
	
	

}
