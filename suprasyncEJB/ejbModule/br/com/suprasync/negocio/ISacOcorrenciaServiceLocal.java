package br.com.suprasync.negocio;

import java.util.List;

import br.com.suprasync.negocio.dto.SacOcorrenciaDTO;
import br.com.suprasync.persistencia.SacDesenvolvimento;
import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.SacOcorrenciaArquivo;
import br.com.suprasync.persistencia.SacOcorrenciaFollowUp;
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
	
	public List<Integer> followUpComSimilares(int id, int idUsuarioSupraMais, String mensagem);
	
	public void consultaNativa (String consulta);
	
	public List <SacOcorrenciaArquivo> obterAnexosSac(int idOcorrencia, Integer codigo, String nomeArquivo);
	
	public List<Integer> usuariosSemSacIniciadoNoDia ();

	public List<Integer> usuariosComSacIniciadoNaoPausado ();
	
	public void finalizaSacNaoPausado ();
	
	public List<SacDesenvolvimento> obterSacDesenvolvimento(SacOcorrenciaFilter filter);

	public List<SacOcorrencia> obterSacToDo(SacOcorrenciaFilter filter);
	
	public List<SacOcorrencia> obterSacDoing(SacOcorrenciaFilter filter);
	
	public List<SacOcorrencia> obterSacDone(SacOcorrenciaFilter filter);
	
	public Integer TotalRegistros(SacOcorrenciaFilter filter);
	
	public List<SacOcorrenciaFollowUp> obterFollowUp(SacOcorrenciaFilter filter);
	
	public boolean insereFollowUp(int id, int idUsuarioSupraMais, String mensagem);
	
	public List<SacDesenvolvimento> obterUltimosSacDesenvolvedores(int quantidadeSacs);
	
	public List<SacDesenvolvimento> obterUltimosSacDesenvolvedor(int idUsuario, int quantidadeSacs);
	
	public SacOcorrencia redirecionarOcorrencia(SacOcorrenciaDTO ocorrenciaDto) throws ObjetoNaoEncontradoException;
	
	public void reativaMensagensFerias();
	
}