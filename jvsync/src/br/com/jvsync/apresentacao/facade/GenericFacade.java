package br.com.jvsync.apresentacao.facade;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.jvsync.negocio.IGenericServiceLocal;
import br.com.jvsync.negocio.exception.FalhaAoCriarJSONException;
import br.com.jvsync.persistencia.Bairro;
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.Cidade;
import br.com.jvsync.persistencia.Endereco;
import br.com.jvsync.persistencia.ItemNotaFiscal;
import br.com.jvsync.persistencia.Logradouro;
import br.com.jvsync.persistencia.MyParceiro;
import br.com.jvsync.persistencia.MyParceiroX;
import br.com.jvsync.persistencia.MyPedido;
import br.com.jvsync.persistencia.MyPedidoItem;
import br.com.jvsync.persistencia.MyProduto;
import br.com.jvsync.persistencia.MyVendedor;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;
import br.com.jvsync.persistencia.Regiao;
import br.com.jvsync.persistencia.RelacionamentoProduto;
import br.com.jvsync.persistencia.Rota;
import br.com.jvsync.persistencia.TipoOperacao;
import br.com.jvsync.persistencia.TipoVenda;
import br.com.jvsync.persistencia.TipoVolume;
import br.com.jvsync.persistencia.UF;
import br.com.jvsync.persistencia.Vendedor;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;


public class GenericFacade {

	private Properties p;
	private Context c;

	private IGenericServiceLocal service;

	public GenericFacade() throws NamingException {

		p = new Properties();
		c = new InitialContext(p);
		service = (IGenericServiceLocal)c.lookup("java:global/jvsyncEAR/jvsyncEJB/GenericService");

	}

	/*Genéricos*/
	
	public <T> Object obter(Class<T> classe, Object codigo) {
		return service.obter(classe, codigo);
	}

	public <E, T> List<E> pesquisar(Class<T> classe, Integer position, Integer max) throws ObjetoNaoEncontradoException {
		return service.obter(classe, position, max);
	}

	public JsonObject objetoJson(Object objeto) throws FalhaAoCriarJSONException {
		return service.objetoJson(objeto);
	}
	
	public String atualizaBancoDados() {
	
		StringBuilder stb = new StringBuilder();
		try {
			stb.append(atualizaDados(CabecalhoNotaFiscal.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(ItemNotaFiscal.class));
			stb.append(System.lineSeparator());	
			stb.append(atualizaDados(Parceiro.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(Produto.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(TipoVenda.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(TipoVolume.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(Vendedor.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(TipoOperacao.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(Bairro.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(Cidade.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(Regiao.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(Endereco.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(Rota.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(Logradouro.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(UF.class));
			stb.append(System.lineSeparator());
			
			stb.append(atualizaDados(MyParceiro.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(MyParceiroX.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(MyPedido.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(MyPedidoItem.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(MyProduto.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(MyVendedor.class));
			
			stb.append("Sincronização finalizada com sucesso!");			
			System.out.println("Sincronização finalizada com sucesso!");
			
		} catch (ObjetoNaoEncontradoException e) {
			stb.append("ocorreu uma falha na atualização dos dados");
			e.printStackTrace();
		}
		return stb.toString();		
	}	

	@SuppressWarnings("unchecked")
	public <T>String atualizaDados (Class<T> classe) throws ObjetoNaoEncontradoException {

		StringBuilder stb = new StringBuilder("Atualizar ").append(classe.getSimpleName()).append(". Total registros de origem: ");		

		int x = service.totalRegistros(classe).intValue();
		int i = 0;
		int j = 0;
		stb.append(x);
		
		Double x1 = new Double(x);
		Double i1 = 0.0;
		Double percentual = 0.0;
		

		System.out.println(stb.toString());
		List<Object> entidadesOrigem;		
		while (i < x) {
			try {
				
				percentual = x1 == 0.0 ? 0.0 : (i1/x1)*100.00;
				if (i > 0){ System.out.println(classe.getSimpleName() + " -> " + String.format("%.2f", percentual)  + " % executado.");}
				entidadesOrigem = service.obter(classe, i, 500);
				@SuppressWarnings("rawtypes")
				Class classePersistir = null;
				
				for (Field field : classe.getDeclaredFields()) {
					if (field.getName().equalsIgnoreCase("classeCorrespondente")) {
						classePersistir = field.getType();
						break;
					}
				}
				
				Gson gson = new Gson();
				
				if (classePersistir == null) {
					return "Não foi possível determinar o tipo de entidade a ser persistida. Classe de origem:" + classe.getSimpleName();
				}
				
				List<Object> entidadesPersistir = new ArrayList<>();	

				for (Object object : entidadesOrigem) {
					Object entidadePersistir = gson.fromJson(gson.toJson(object), classePersistir); 
					if (entidadePersistir != null) {
						entidadesPersistir.add(entidadePersistir);
					}						
				}				
				service.atualizaDados(entidadesPersistir);
				j = i = i + 500;
				i1 = i1 + 500.00;
				
			} catch (Exception e) {
				System.out.println("Houve uma falha ao atualizar " + classe.getSimpleName() +". Registros atualizados até o momento: " + i);
				i = x;
			}			
		}
		
		stb.append(" Registros atualizados: ").append(j > x ? x : j);
		System.out.println(stb.toString());
		return stb.toString();
	}	
	
	public <E, T> List<E> obterDeSqlServer (Class<T> classe, Integer position, Integer max) {
		return service.obterDeSqlServer(classe, position, max);
	}
	
	public Object inserirSqlServer (Object entity) {
		return service.inserirSqlServer(entity);
	}
	
	public Object alterarSqlServer (Object entity) {
		return service.alterarSqlServer(entity);
	}
	
	public void sortRelacionamentoProduto(List<RelacionamentoProduto> rels) {
		rels.sort(Comparator.comparing(o -> o.getCodigoAliar()));
	}

}
