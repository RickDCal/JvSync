package br.com.suprasync.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.suprasync.persistencia.Parcela;
import br.com.suprasync.persistencia.dao.IContaDAO;

@Stateless
public class ContaService implements IContaServiceLocal {

	@EJB
	private IContaDAO contaDao;

	public ContaService() {

	}	

	public List<Parcela> buscarParcelasEmAbertoPorParceiro(Long idParceiro) {
		return contaDao.buscarParcelasEmAbertoPorParceiro(idParceiro);
	}

	public List<Parcela> buscarParcelasEmAtrasoPorParceiro(Long idParceiro) {
		return contaDao.buscarParcelasEmAtrasoPorParceiro(idParceiro);
	}


}
