package br.com.suprasync.timer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.suprasync.negocio.IGenericServiceLocal;
import br.com.suprasync.negocio.ISacOcorrenciaServiceLocal;
import br.com.suprasync.negocio.IUsuarioServiceLocal;
import br.com.suprasync.persistencia.ParametroSistema;
import br.com.suprasync.persistencia.ParametroSlack;
import br.com.suprasync.persistencia.SacEtapa;
import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.Usuario;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.suprasync.persistencia.dao.exception.SacOcorrenciaNaoEncontradaException;
import br.com.suprasync.persistencia.enumerate.TempoEnum;
import br.com.suprasync.persistencia.filter.SacOcorrenciaFilter;
import br.com.suprasync.util.Utilities;

@Stateless
public class ServiceVerificaSacSuprasoft {


	private Properties p;
	private Context c;

	private IGenericServiceLocal genericService;
	private ISacOcorrenciaServiceLocal ocorrenciaService;
	private IUsuarioServiceLocal usuarioService;

	public ServiceVerificaSacSuprasoft() throws NamingException {

		p = new Properties();
		c = new InitialContext(p);
		genericService = (IGenericServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/GenericService");
		ocorrenciaService = (ISacOcorrenciaServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/SacOcorrenciaService");
		usuarioService = (IUsuarioServiceLocal)c.lookup("java:global/suprasyncEAR/suprasyncEJB/UsuarioService");

	}

	
	@Schedule(hour="08", minute="0,10,20,30,40,50", persistent=false) //@Schedule(hour = "*", minute="*/2", persistent=false) // testes
	public void avisaIniciarSac() {
		try {
			ParametroSistema parametroSistema = (ParametroSistema) genericService.obter(ParametroSistema.class, 0, 1).get(0);
			if (parametroSistema.getSlack() && Utilities.isDiaUtil(Calendar.getInstance())) {
				List<Integer> listCodigoUsuarios = ocorrenciaService.usuariosSemSacIniciadoNoDia();
				aviso(listCodigoUsuarios, "você ainda não possui tarefa iniciada no SAC. Verifique suas tarefas.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}

	@Schedule(hour="11,17,18", minute="55", persistent=false)	
	public void avisaPausarSac() {	
		try {
			ParametroSistema parametroSistema = (ParametroSistema) genericService.obter(ParametroSistema.class, 0, 1).get(0);
			if (parametroSistema.getSlack() && Utilities.isDiaUtil(Calendar.getInstance())) {
				List<Integer> listCodigoUsuarios = ocorrenciaService.usuariosComSacIniciadoNaoPausado();
				aviso(listCodigoUsuarios, "o próximo intervalo está chegando. Lembre-se de pausar sua tarefa no SAC.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}

	//@Schedule(hour = "*", minute="*/2", persistent=false) // testes
	@Schedule(hour="09-17", minute="0", persistent=false)	
	public void avisaIniciarSacExpediente() {
		try {
			avisaIniciarSac();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public void aviso (List<Integer> codUsuarios, String mensagem) throws IOException, ObjetoNaoEncontradoException, java.text.ParseException {		
		ParametroSlack parametroSlack = (ParametroSlack) genericService.obter(ParametroSlack.class, 0, 1).get(0);
		if (codUsuarios != null && !codUsuarios.isEmpty()) {
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
	public void finalizaSacNaoPausado () {
		try {
			ocorrenciaService.finalizaSacNaoPausado();
		} catch (Exception e) {
			System.out.println("Falha ao finalizar Sac não pausado.");
			e.printStackTrace();
		}
	}

	@Schedule(hour="07", minute="0", persistent=false)
	public void reativaMensagensFerias() {
		try {
			ocorrenciaService.reativaMensagensFerias();
		} catch (Exception e) {
			System.out.println("Falha ao ajustar banco para receber mensagens slack.");
			e.printStackTrace();
		}
	}

	@Schedule(hour="*", minute="30", persistent=false)
	public void lembrarFeedbacksVelhos() {
		try {
			ParametroSlack parametroSlack = (ParametroSlack) genericService.obter(ParametroSlack.class, 0, 1).get(0);			
			List<SacOcorrencia> ocorrencias = obterListaSacFeedback();	
			int i = 0;
			if (ocorrencias != null && !ocorrencias.isEmpty()) {
				for (SacOcorrencia sacOcorrencia : ocorrencias) {
					if (i < 2 && sacOcorrencia.getDataUltimoRedirecionamento() != null && sacOcorrencia.getDataUltimoRedirecionamento().before(Utilities.incrementaData(new Date(), TempoEnum.DIA, -7))) {
						enviaAvisoFeedback(sacOcorrencia, parametroSlack);
						i++;
					}
				}
			}
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
		}  
	}

	@Schedule(hour="9", minute="0", persistent=false)
	public void lembrarFeedbacksNovos() {
		try {
			ParametroSlack parametroSlack = (ParametroSlack) genericService.obter(ParametroSlack.class, 0, 1).get(0);			
			List<SacOcorrencia> ocorrencias = obterListaSacFeedback();	
			int i = 0;
			for (SacOcorrencia sacOcorrencia : ocorrencias) {
				if (i < 2 && sacOcorrencia.getDataUltimoRedirecionamento() != null && !sacOcorrencia.getDataUltimoRedirecionamento().before(Utilities.incrementaData(new Date(), TempoEnum.DIA, -7))) {
					enviaAvisoFeedback(sacOcorrencia, parametroSlack);
					i++;
				}
			}

		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
		}  
	}

	private List<SacOcorrencia> obterListaSacFeedback() {
		try {
			SacOcorrenciaFilter filter = new SacOcorrenciaFilter();
			List<Integer> etapas = new ArrayList<>();
			etapas.add(((SacEtapa) genericService.obter(SacEtapa.class, 10 )).getId());
			filter.setListIdEtapa(etapas);			
			filter.setUteis(true);
			return ocorrenciaService.obter(filter);
		} catch (ObjetoNaoEncontradoException | SacOcorrenciaNaoEncontradaException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	private void enviaAvisoFeedback (SacOcorrencia sacOcorrencia, ParametroSlack parametroSlack) {
		try {
			Usuario usuario = usuarioService.obterPorIdFuncionario(sacOcorrencia.getFuncionarioRedirecionamento().getId()).get(0);
			StringBuilder mensagem = new StringBuilder();
			mensagem.append(usuario.getNome()).append(", você possui feedback pendente para o cliente ")
			.append(sacOcorrencia.getCliente().getNome()).append(" referente ao SAC Nº ").append(sacOcorrencia.getId())
			.append(" Assunto: ").append(sacOcorrencia.getAssunto()).append(". Por favor, entre em contato o quanto antes.");				
			Utilities.enviaMensagemSlack(usuario.getUsuarioSlack(), mensagem.toString(), parametroSlack.getWebHookSlack());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}