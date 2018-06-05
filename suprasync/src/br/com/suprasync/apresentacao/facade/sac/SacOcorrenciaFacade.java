package br.com.suprasync.apresentacao.facade.sac;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import br.com.suprasync.negocio.ISacOcorrenciaServiceLocal;
import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.SacOcorrenciaArquivo;
import br.com.suprasync.persistencia.dao.exception.SacOcorrenciaNaoEncontradaException;
import br.com.suprasync.persistencia.filter.SacOcorrenciaFilter;

public class SacOcorrenciaFacade {
	
	private Properties p;
	private Context c;
	
	public ISacOcorrenciaServiceLocal service;
	
	public SacOcorrenciaFacade() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		service = (ISacOcorrenciaServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/SacOcorrenciaService");
		
	}
	
	public SacOcorrencia pesquisar(int id) throws SacOcorrenciaNaoEncontradaException {
		return service.pesquisar(id);
	}
	
	public List<SacOcorrencia> pesquisar(Integer position, Integer max) throws SacOcorrenciaNaoEncontradaException {
		return service.pesquisar(position, max);
	}	
	
	public void cadastrar(SacOcorrencia sacOcorrencia) {
		service.cadastrar(sacOcorrencia);
	}

	public void atualizar(SacOcorrencia sacOcorrencia) throws SacOcorrenciaNaoEncontradaException {
		service.atualizar(sacOcorrencia);
	}

	public void remover(SacOcorrencia sacOcorrencia) throws SacOcorrenciaNaoEncontradaException {
		service.remover(sacOcorrencia);
	}

	public List<SacOcorrencia> obter(SacOcorrenciaFilter filter) throws SacOcorrenciaNaoEncontradaException {
		return service.obter(filter);
	}

	public boolean liberarVersao (Integer id, String numeroVersao) {
		return service.liberarVersao(id, numeroVersao);
	}
	
	public List<Integer> followUp(int id, int idUsuarioSupraMais, String mensagem) {
		return service.followUp(id, idUsuarioSupraMais, mensagem);
	}
	
	public void consultaNativa (String consulta) {
		service.consultaNativa(consulta);
	}
	
	public List <SacOcorrenciaArquivo> obterAnexosSac(int idOcorrencia, Integer codigo, String nomeArquivo) {
		return service.obterAnexosSac(idOcorrencia, codigo, nomeArquivo);
	}
	
	public JsonArray listAnexosJson (int idOcorrencia, Integer codigo, String nomeArquivo) {
		List <SacOcorrenciaArquivo> anexos = service.obterAnexosSac(idOcorrencia, codigo, nomeArquivo);
		if (anexos != null && anexos.size() > 0) {
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			for (SacOcorrenciaArquivo anexo : anexos) {
				anexo.setArquivo(null);				
			}
			return (JsonArray) parser.parse(gson.toJson(anexos));
		}
		return null;
	}
		
	
}
