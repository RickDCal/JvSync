package br.com.suprasync.timer;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.http.ParseException;

import br.com.suprasync.negocio.IGenericServiceLocal;
import br.com.suprasync.negocio.ISacOcorrenciaServiceLocal;
import br.com.suprasync.persistencia.ParametroSistema;
import br.com.suprasync.persistencia.ParametroSlack;
import br.com.suprasync.persistencia.Usuario;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.suprasync.util.Utilities;

@Stateless
public class ServiceVerificaSacSuprasoft {
	
	
	private Properties p;
	private Context c;
	
	private IGenericServiceLocal genericService;
	private ISacOcorrenciaServiceLocal ocorrenciaService;
	
	public ServiceVerificaSacSuprasoft() throws NamingException {
		
		p = new Properties();
		c = new InitialContext(p);
		genericService = (IGenericServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/GenericService");
		ocorrenciaService = (ISacOcorrenciaServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/SacOcorrenciaService");
		
	}

	//@Schedule(hour = "*", minute="*/2", persistent=false) // testes
	@Schedule(hour="08", minute="0,10,20,30,40,50", persistent=false)
	public void avisaIniciarSac() throws RemoteException, Exception{
		try {
			ParametroSistema parametroSistema = (ParametroSistema) genericService.obter(ParametroSistema.class, 0, 1).get(0);
			if (parametroSistema.getSlack() && Utilities.isDiaUtil(Calendar.getInstance())) {
				List<Integer> listCodigoUsuarios = ocorrenciaService.usuariosSemSacIniciadoNoDia();
				//aviso(listCodigoUsuarios, Utilities.encodeMensagemUtfToIso("você ainda não possui tarefa iniciada no SAC. Verifique suas tarefas."));
				aviso(listCodigoUsuarios, "você ainda não possui tarefa iniciada no SAC. Verifique suas tarefas.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	//@Schedule(hour = "*", minute="*/2", persistent=false) // testes
	@Schedule(hour="11,17,18", minute="55", persistent=false)	
	public void avisaPausarSac() throws RemoteException, Exception{	
		try {
			ParametroSistema parametroSistema = (ParametroSistema) genericService.obter(ParametroSistema.class, 0, 1).get(0);
			if (parametroSistema.getSlack() && Utilities.isDiaUtil(Calendar.getInstance())) {
				List<Integer> listCodigoUsuarios = ocorrenciaService.usuariosComSacIniciadoNaoPausado();
				//aviso(listCodigoUsuarios,  Utilities.encodeMensagemUtfToIso("o próximo intervalo está chegando. Lembre-se de pausar sua tarefa no SAC."));
				aviso(listCodigoUsuarios, "o próximo intervalo está chegando. Lembre-se de pausar sua tarefa no SAC.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	//@Schedule(hour = "*", minute="*/2", persistent=false) // testes
	@Schedule(hour="09-17", minute="0", persistent=false)	
	public void avisaIniciarSacExpediente() throws RemoteException, Exception {
		try {
			avisaIniciarSac();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void aviso (List<Integer> codUsuarios, String mensagem) throws ParseException, IOException, ObjetoNaoEncontradoException, java.text.ParseException {		
		ParametroSlack parametroSlack = (ParametroSlack) genericService.obter(ParametroSlack.class, 0, 1).get(0);
		if (codUsuarios != null && codUsuarios.size() >0 ) {
			for (int i = 0; i < codUsuarios.size(); i++) {
				Usuario usuario = (Usuario) genericService.obter(Usuario.class, codUsuarios.get(i));
				if (usuario.getUsuarioSlack() != null) {
					Utilities.enviaMensagemSlack(usuario.getUsuarioSlack(), usuario.getNome() + ", " + mensagem, parametroSlack.getWebHookSlack());
				}				
			}
		}
	}
	
	//@Schedule(hour = "*", minute="*/2", persistent=false) // testes
	@Schedule(hour="07", minute="0", persistent=false)
	public void finalizaSacNaoPausado () throws RemoteException, Exception {
		try {
			ocorrenciaService.finalizaSacNaoPausado();
		} catch (Exception e) {
			System.out.println("Falha ao finalizar Sac não pausado.");
			e.printStackTrace();
		}
	}	
}