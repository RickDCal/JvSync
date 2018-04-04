package br.com.suprasync.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.dao.IGenericDAO;
import br.com.suprasync.persistencia.dao.ISacOcorrenciaDAO;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.suprasync.persistencia.dao.exception.SacOcorrenciaNaoEncontradaException;
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
	
}
