package br.com.jvsync.negocio;



import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.jvsync.negocio.exception.FalhaAoCriarJSONException;
import br.com.jvsync.persistencia.dao.IGenericDAO;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;



@Stateless
public class GenericService implements IGenericServiceLocal  {

	@EJB
	private IGenericDAO genericDao;

	public GenericService() {

	}

	public <E, T> List<E> obter(Class<T> classe, Integer position, Integer max) throws ObjetoNaoEncontradoException {
		try {
			return genericDao.obter(classe, position, max);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ObjetoNaoEncontradoException();
		}
	}
	
	public JsonObject objetoJson(Object objeto) throws FalhaAoCriarJSONException {
		// um parametro qualquer pode ser recebido aqui e gerar um objeto json desde que não precise ser modificado
		Gson gson = new Gson();		
		JsonParser jp = new JsonParser();
		JsonObject jo = (JsonObject) jp.parse(gson.toJson(objeto));
		return jo;		
	}

	public String atualizaDados (List<Object> entidadesAtualizar) {
		return genericDao.atualizaDados(entidadesAtualizar);
	}
	
	public <T> Long totalRegistros(Class<T> classe) {
		return genericDao.totalRegistros(classe);
	}


}



