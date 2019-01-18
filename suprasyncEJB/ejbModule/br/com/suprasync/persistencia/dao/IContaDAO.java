package br.com.suprasync.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.suprasync.persistencia.Parcela;

@Local
public interface IContaDAO {

	public List<Parcela> buscarParcelasEmAbertoPorParceiro(Long idParceiro);
	
	public List<Parcela> buscarParcelasEmAtrasoPorParceiro(Long idParceiro);
		

}