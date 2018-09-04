package br.com.suprasync.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.suprasync.persistencia.SacDesenvolvimento;
import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.SacOcorrenciaArquivo;
import br.com.suprasync.persistencia.SacOcorrenciaFollowUp;
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

}