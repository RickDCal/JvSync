package br.com.jvsync.apresentacao.facade;

import java.util.ArrayList;
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
import br.com.jvsync.persistencia.MSBairro;
import br.com.jvsync.persistencia.MSCabecalhoNotaFiscal;
import br.com.jvsync.persistencia.MSCidade;
import br.com.jvsync.persistencia.MSEndereco;
import br.com.jvsync.persistencia.MSItemNotaFiscal;
import br.com.jvsync.persistencia.MSLogradouro;
import br.com.jvsync.persistencia.MSParceiro;
import br.com.jvsync.persistencia.MSProduto;
import br.com.jvsync.persistencia.MSRegiao;
import br.com.jvsync.persistencia.MSRota;
import br.com.jvsync.persistencia.MSTipoOperacao;
import br.com.jvsync.persistencia.MSTipoVenda;
import br.com.jvsync.persistencia.MSTipoVolume;
import br.com.jvsync.persistencia.MSUF;
import br.com.jvsync.persistencia.MSVendedor;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;
import br.com.jvsync.persistencia.Regiao;
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

	public <T> Object pesquisar(Class<T> classe, int id) throws ObjetoNaoEncontradoException {
		return service.obter(classe, id);
	}

	public <E, T> List<E> pesquisar(Class<T> classe, Integer position, Integer max) throws ObjetoNaoEncontradoException {
		return service.obter(classe, position, max);
	}

	public <E, T> List<E> pesquisar(Class<T> classe, String nome, Integer position, Integer max) throws ObjetoNaoEncontradoException {
		return service.obter(classe, nome, position, max);
	}

	public void remover(Object entity) {
		service.remover(entity);
	}

	public Object cadastrar(Object entity) throws ObjetoNaoEncontradoException {
		return service.inserir(entity);
	}

	public Object atualizar(Object entity) throws ObjetoNaoEncontradoException {
		return service.alterar(entity);

	}

	public JsonObject objetoJson(Object objeto) throws FalhaAoCriarJSONException {
		return service.objetoJson(objeto);
	}

	public List<Object> obter (String nativeQuery) {
		return service.obter(nativeQuery);
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

		System.out.println(stb.toString());
		List<Object> entidadesOrigem;		
		while (i < x) {
			try {
				if (i > 0){ System.out.println("" + i + " linhas atualizadas");}
				entidadesOrigem = service.obter(classe, i, 500);			
				List<Object> entidadesPersistir = new ArrayList<>();
				String tipo = classe.getSimpleName().toLowerCase();				
				Class classePersistir = null;	
				Gson gson = new Gson();

				switch (tipo) {
				case "cabecalhonotafiscal": classePersistir = MSCabecalhoNotaFiscal.class; break;
				case "itemnotafiscal": classePersistir = MSItemNotaFiscal.class; break;
				case "parceiro": classePersistir = MSParceiro.class; break;
				case "produto": classePersistir = MSProduto.class; break;
				case "tipovenda": classePersistir = MSTipoVenda.class; break;
				case "tipovolume": classePersistir = MSTipoVolume.class; break;
				case "vendedor": classePersistir = MSVendedor.class; break;
				case "tipooperacao": classePersistir = MSTipoOperacao.class; break;
				case "bairro": classePersistir = MSBairro.class; break;
				case "cidade": classePersistir = MSCidade.class; break;
				case "endereco": classePersistir = MSEndereco.class; break;
				case "regiao": classePersistir = MSRegiao.class; break;
				case "rota": classePersistir = MSRota.class; break;
				case "logradouro": classePersistir = MSLogradouro.class; break;
				case "uf": classePersistir = MSUF.class; break;
				default: break;
				}					
				
				if (classePersistir == null) {
					return "Não foi possível determinar o tipo de entidade a ser persistida. Classe de origem:" + classe.getSimpleName();
				}

				for (Object object : entidadesOrigem) {
					Object entidadePersistir = gson.fromJson(gson.toJson(object), classePersistir); 
					if (entidadePersistir != null) {
						entidadesPersistir.add(entidadePersistir);
					}						
				}				
				service.atualizaDados(entidadesPersistir);
				j = i = i + 500;
				
			} catch (Exception e) {
				System.out.println("Houve uma falha ao atualizar " + classe.getSimpleName() +". Registros atualizados até o momento: " + i);
				i = x;
			}			
		}
		
		stb.append(" Registros atualizados: ").append(j > x ? x : j);
		System.out.println(stb.toString());
		return stb.toString();
	}	

}
