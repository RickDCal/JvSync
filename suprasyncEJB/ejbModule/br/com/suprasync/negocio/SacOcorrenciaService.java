package br.com.suprasync.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.suprasync.negocio.dto.SacOcorrenciaDTO;
import br.com.suprasync.persistencia.Cliente;
import br.com.suprasync.persistencia.SacEtapa;
import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.Funcionario;
import br.com.suprasync.persistencia.dao.IGenericDAO;
import br.com.suprasync.persistencia.dao.ISacOcorrenciaDAO;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.suprasync.persistencia.dao.exception.SacOcorrenciaNaoEncontradaException;
import br.com.suprasync.persistencia.enumerate.SACOcorrenciaEnum;
import br.com.suprasync.persistencia.filter.SacOcorrenciaFilter;

@Stateless
public class SacOcorrenciaService implements ISacOcorrenciaServiceLocal {

	@EJB
	private ISacOcorrenciaDAO sacOcorrenciaDao;

	@EJB
	private IGenericDAO genericDao;

	public SacOcorrenciaService() {

	}	



	public SacOcorrencia pesquisar (int id) throws  SacOcorrenciaNaoEncontradaException {

		try {
			return (SacOcorrencia) genericDao.obter(SacOcorrencia.class,id);
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
			throw new SacOcorrenciaNaoEncontradaException();
		}
	} 

	public List<SacOcorrencia> pesquisar(Integer position, Integer max) throws SacOcorrenciaNaoEncontradaException {

		try {
			return genericDao.obter(SacOcorrencia.class, position, max);
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
			throw new SacOcorrenciaNaoEncontradaException();

		}		

	}

	public SacOcorrencia convertfromDTO (SacOcorrenciaDTO ocorrenciaDTO) throws ObjetoNaoEncontradoException {

		SacOcorrencia ocorrencia = new SacOcorrencia();

		ocorrencia.setId(ocorrenciaDTO.getId());
		ocorrencia.setDataCadastro(ocorrenciaDTO.getDataCadastro());
		ocorrencia.setSituacaoOcorrencia(SACOcorrenciaEnum.getEnum(ocorrenciaDTO.getIdSituacao()));
		ocorrencia.setAssunto(ocorrenciaDTO.getAssunto());
		ocorrencia.setSolicitacao(ocorrenciaDTO.getSolicitacao());
		ocorrencia.setEstimativa(ocorrenciaDTO.getEstimativa());
		ocorrencia.setDescricaoDesenvolvimento(ocorrenciaDTO.getDescricaoDesenvolvimento());
		ocorrencia.setComentarioDesenvolvimento(ocorrenciaDTO.getComentarioDesenvolvimento());
		ocorrencia.setDataUltimoRedirecionamento(ocorrenciaDTO.getDataUltimoRedirecionamento());
		ocorrencia.setDataPrevisaoTermino(ocorrenciaDTO.getDataPrevisaoTermino());	
		if (ocorrenciaDTO.getIdCliente() != null) {
			ocorrencia.setCliente((Cliente) genericDao.obter(Cliente.class, ocorrenciaDTO.getIdCliente()));
		}
		if (ocorrenciaDTO.getIdEtapa() != null) {
			ocorrencia.setEtapa((SacEtapa) genericDao.obter(SacEtapa.class, ocorrenciaDTO.getIdEtapa()));
		}
		if (ocorrenciaDTO.getIdFuncionario() != null) {
			ocorrencia.setFuncionarioCadastro((Funcionario) genericDao.obter(Funcionario.class, ocorrenciaDTO.getIdFuncionario()));
		}
		return ocorrencia;
	}

	public void cadastrar (SacOcorrencia sacOcorrencia) {	

		genericDao.inserir(sacOcorrencia);		
	}  

	public void atualizar (SacOcorrencia sacOcorrencia) throws SacOcorrenciaNaoEncontradaException {

		genericDao.alterar(sacOcorrencia);		
	} 

	public void remover (SacOcorrencia sacOcorrencia) throws SacOcorrenciaNaoEncontradaException {

		genericDao.remover(sacOcorrencia);		
	} 

	public List<SacOcorrencia> obter (SacOcorrenciaFilter filter) throws SacOcorrenciaNaoEncontradaException {
		try {
			return sacOcorrenciaDao.obter(filter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SacOcorrenciaNaoEncontradaException();
		}
	}
	
	public boolean liberarVersao (Integer id, String numeroVersao) {
		return sacOcorrenciaDao.liberarVersao(id, numeroVersao);
	}
	
	public boolean followUp(int id, int idUsuarioSupraMais, String mensagem) {
		return sacOcorrenciaDao.followUp(id, idUsuarioSupraMais, mensagem);
	}

}
