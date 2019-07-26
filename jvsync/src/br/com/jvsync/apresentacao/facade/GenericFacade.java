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
import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.ItemNotaFiscal;
import br.com.jvsync.persistencia.MSCabecalhoNotaFiscal;
import br.com.jvsync.persistencia.MSItemNotaFiscal;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;
//import br.com.jvsync.timer.ServiceVerificaSacSuprasoft;

public class GenericFacade {

	private Properties p;
	private Context c;

	public IGenericServiceLocal service;
	//public ServiceVerificaSacSuprasoft timer;

	public GenericFacade() throws NamingException {

		p = new Properties();
		c = new InitialContext(p);
		service = (IGenericServiceLocal)c.lookup("java:global/jvsyncEAR/jvsyncEJB/GenericService");
		//timer = (ServiceVerificaSacSuprasoft)c.lookup("java:global/jvsyncEAR/jvsyncEJB/ServiceVerificaSacSuprasoft");

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
		} catch (ObjetoNaoEncontradoException | NamingException e) {
			stb.append("ocorreu uma falha na atualiação dos dados");
			e.printStackTrace();
		}
		return stb.toString();		
	}	

	@SuppressWarnings("unchecked")
	public <T>String atualizaDados (Class<T> classe) throws ObjetoNaoEncontradoException, NamingException {

		StringBuilder stb = new StringBuilder("Atualizar ").append(classe.getSimpleName()).append(". Total registros de origem: ");		

		int x = service.totalRegistros(classe).intValue();
		int i = 0;
		stb.append(x);

		List<Object> entidadesOrigem;		
		while (i < x) {
			try {
				entidadesOrigem = service.obter(classe, i, 500);			
				List<Object> entidadesPersistir = new ArrayList<>();
				String tipo = classe.getSimpleName().toLowerCase();				
				Class classePersistir = null;	
				Gson gson = new Gson();

				switch (tipo) {
				case "cabecalhonotafiscal": classePersistir = MSCabecalhoNotaFiscal.class; break;
				case "itemnotafiscal": classePersistir = MSItemNotaFiscal.class; break;
				default: break;
				}	
				
				if (classePersistir == null) {
					return "Não foi possível determinar o tipo de entidade a ser perisistida. Classe de origem:" + classe.getSimpleName();
				}

				for (Object object : entidadesOrigem) {
					Object entidadePersistir = gson.fromJson(gson.toJson(object), classePersistir); 
					if (entidadePersistir != null) {
						entidadesPersistir.add(entidadePersistir);
					}						
				}				
				service.atualizaDados(entidadesPersistir);
				i = i + 500;
				System.out.println("" + i + " linhas atualizadas");
			} catch (Exception e) {
				stb.append("Houve uma falha ao atualizar registros. Registros atualizados até o momento: ").append(i);
				e.printStackTrace();
			}			
		}

		stb.append(" Registros atualizados: ").append(x);
		System.out.println(stb.toString());
		return stb.toString();
	}	

	//	public void testaTimer() {
	//		timer.lembrarFeedbacksNovos();
	//	}


}
