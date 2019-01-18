package br.com.suprasync.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.suprasync.persistencia.Parcela;

@Local
public interface IContaServiceLocal {
	
	public List<Parcela> buscarParcelasEmAbertoPorParceiro(Long idParceiro);

	public List<Parcela> buscarParcelasEmAtrasoPorParceiro(Long idParceiro);

}
