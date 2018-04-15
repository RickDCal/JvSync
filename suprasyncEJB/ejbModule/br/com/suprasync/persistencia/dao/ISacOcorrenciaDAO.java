package br.com.suprasync.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.dao.exception.SacOcorrenciaNaoEncontradaException;
import br.com.suprasync.persistencia.filter.SacOcorrenciaFilter;

@Local
public interface ISacOcorrenciaDAO {
	
//public SacOcorrencia inserir (SacOcorrencia SacOcorrencia);
	
//public SacOcorrencia alterar (SacOcorrencia SacOcorrencia);
	
//public void remover (SacOcorrencia SacOcorrencia);

//public SacOcorrencia obter(int id) throws SacOcorrenciaNaoEncontradaException;
	
//public List<SacOcorrencia> obter(Integer position, Integer max) throws SacOcorrenciaNaoEncontradaException;
	
//public <T>Integer totalRegistros (Class<T> classe, Empresa empresa) throws ObjetoNaoEncontradoException;
	
public List<SacOcorrencia> obter(SacOcorrenciaFilter filter) throws SacOcorrenciaNaoEncontradaException;

public boolean liberarVersao (Integer id, String numeroVersao);


}