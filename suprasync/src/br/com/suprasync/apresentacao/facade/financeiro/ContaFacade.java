package br.com.suprasync.apresentacao.facade.financeiro;

import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.suprasync.negocio.IContaServiceLocal;
import br.com.suprasync.persistencia.Parcela;



//@Stateless
public class ContaFacade {

	private Properties p;
	private Context c;

	public IContaServiceLocal service;

	public ContaFacade() throws NamingException {

		p = new Properties();
		c = new InitialContext(p);
		service = (IContaServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/ContaService");

	}
	public List<Parcela> buscarParcelasEmAbertoPorParceiro(Long idParceiro) {
		return service.buscarParcelasEmAbertoPorParceiro(idParceiro);
	}

	public List<Parcela> buscarParcelasEmAtrasoPorParceiro(Long idParceiro) {
		return service.buscarParcelasEmAtrasoPorParceiro(idParceiro);
	}

}
