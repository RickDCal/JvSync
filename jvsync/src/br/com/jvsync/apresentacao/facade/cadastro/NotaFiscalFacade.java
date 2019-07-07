//package br.com.jvsync.apresentacao.facade.cadastro;
//
//import java.util.List;
//import java.util.Properties;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//import br.com.jvsync.negocio.INotaFiscalServiceLocal;
//import br.com.jvsync.negocio.exception.ObjetoInexistenteException;
//import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
//
//public class NotaFiscalFacade {
//	
//	private Properties p;
//	private Context c;
//	
//	public INotaFiscalServiceLocal service;
//	
//	public NotaFiscalFacade() throws NamingException {
//		
//		p = new Properties();
//		c = new InitialContext(p);
//		service = (INotaFiscalServiceLocal)c.lookup("java:global/jvsyncEAR/jvsyncEJB/NotaFiscalService");
//		
//	}
//
//	public List<CabecalhoNotaFiscal> pesquisar(Integer position, Integer max) throws ObjetoInexistenteException {
//		return service.pesquisar(position, max);
//	}
//
//}
