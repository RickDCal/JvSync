package br.com.suprasync.negocio;

import java.util.List;
import java.util.Map;

import br.com.suprasync.negocio.dto.SacOcorrenciaDTO;
import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.suprasync.persistencia.dao.exception.SacOcorrenciaNaoEncontradaException;
import br.com.suprasync.persistencia.filter.SacOcorrenciaFilter;

public interface ISacOcorrenciaServiceLocal {

	public SacOcorrencia pesquisar(int id) throws SacOcorrenciaNaoEncontradaException;

	public List<SacOcorrencia> pesquisar(Integer position, Integer max)
			throws SacOcorrenciaNaoEncontradaException;

	public void cadastrar(SacOcorrencia sacOcorrencia);

	public void atualizar(SacOcorrencia sacOcorrencia) throws SacOcorrenciaNaoEncontradaException;

	public void remover(SacOcorrencia sacOcorrencia) throws SacOcorrenciaNaoEncontradaException;

	public List<SacOcorrencia> obter(SacOcorrenciaFilter filter) throws SacOcorrenciaNaoEncontradaException;
	
	public SacOcorrencia convertfromDTO (SacOcorrenciaDTO ocorrenciaDTO) throws ObjetoNaoEncontradoException;
	
	public boolean liberarVersao (Integer id, String numeroVersao);
	
	public Map<Integer, Integer> followUp(int id, int idUsuarioSupraMais, String mensagem);

}