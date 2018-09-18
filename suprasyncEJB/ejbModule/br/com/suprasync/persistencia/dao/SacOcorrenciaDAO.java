package br.com.suprasync.persistencia.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.suprasync.persistencia.SacDesenvolvimento;
import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.SacOcorrenciaArquivo;
import br.com.suprasync.persistencia.SacOcorrenciaFollowUp;
import br.com.suprasync.persistencia.dao.exception.SacOcorrenciaNaoEncontradaException;
import br.com.suprasync.persistencia.enumerate.SACOcorrenciaEnum;
import br.com.suprasync.persistencia.filter.SacOcorrenciaFilter;

@Stateless
public class SacOcorrenciaDAO extends GenericDAO implements ISacOcorrenciaDAO {

	private Map<String, Object> parametros = new HashMap<String, Object>();

	public SacOcorrenciaDAO() {

	}

	public List<SacOcorrencia> obter (SacOcorrenciaFilter filter) throws SacOcorrenciaNaoEncontradaException {

		//Map<String, Object> parametros = new HashMap<String, Object>();

		if (filter == null) {
			filter = new SacOcorrenciaFilter();
		}

		StringBuilder consulta = new StringBuilder("select o from SacOcorrencia o where o.id is not null");

		consulta = colocaFiltrosConsulta(filter, consulta);


		if (filter.isPrioridade()) {
			consulta.append(" order by o.prioridade asc");
		} else {
			consulta.append(" order by o.id desc ");
		}

		/** seta os parâmetros na query */
		Query query = entityManager.createQuery(consulta.toString());
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}

		if (filter.getPosition() != null) {
			query.setFirstResult(filter.getPosition());
		}

		if (filter.getMax() != null) {
			query.setMaxResults(filter.getMax());
		}	

		return query.getResultList();	

	}

	public boolean liberarVersao (Integer id, String numeroVersao) {
		try {
			StringBuilder consulta = new StringBuilder("update sac_ocorrencia set versao_atualizador = :numeroVersao ")
					.append(" , func_codigo_redirecionamento = func_codigo_solicitacao, id_situacao = 5")
					.append(" , sacetapa_codigo = isnull((select top(1) codigo from sac_etapa_atendimento where id_situacao_inicio = 5), sacetapa_codigo)")
					.append(" where codigo = :id")
					.append(" or sacocor_codigo_similar = :id ");
			Query query = entityManager.createNativeQuery(consulta.toString());
			query.setParameter("numeroVersao", numeroVersao);
			query.setParameter("id", id);			
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void consultaNativa (String consulta) {
		Query query = entityManager.createNativeQuery(consulta);
		query.executeUpdate();
	}

	public List <SacOcorrenciaArquivo> obterAnexosSac(int idOcorrencia, Integer codigo, String nomeArquivo) {

		//Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idOcorrencia", idOcorrencia);

		StringBuilder sb = new StringBuilder("select a from SacOcorrenciaArquivo a where a.idSacOcorrencia = :idOcorrencia ");

		if (codigo != null) {
			sb.append(" and a.codigo = :codigo");
			parametros.put("codigo", codigo);
		}

		if (nomeArquivo != null && !nomeArquivo.isEmpty()) {
			sb.append(" and a.nomeArquivo = :nomeArquivo");
			parametros.put("nomeArquivo", nomeArquivo);
		}

		Query query = entityManager.createQuery(sb.toString());

		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}		

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}		


	}

	@SuppressWarnings("unchecked")
	public List<Integer> usuariosSemSacIniciadoNoDia (){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct usuario.codigo from usuario")
		.append(" join sac_ocorrencia_desenvolvimento sod on sod.usu_codigo = usuario.codigo")
		.append(" where not exists (select 1 from sac_ocorrencia_desenvolvimento sod2 where sod2.usu_codigo = sod.usu_codigo")
		.append(" and convert(date,data_hora_inicio) = convert(date,getdate())) and usuario.data_exclusao is null");

		Query query = entityManager.createNativeQuery(sb.toString());

		return query.getResultList();	

	}

	@SuppressWarnings("unchecked")
	public List<Integer> usuariosComSacIniciadoNaoPausado (){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct usuario.codigo from usuario")
		.append(" join sac_ocorrencia_desenvolvimento sod on sod.usu_codigo = usuario.codigo")
		.append(" where")
		.append(" usuario.data_exclusao is null and data_hora_fim is null");

		Query query = entityManager.createNativeQuery(sb.toString());

		return query.getResultList();	

	}

	public void finalizaSacNaoPausado (){
		StringBuilder sb = new StringBuilder();
		sb.append("if exists (select 1 from sac_ocorrencia_desenvolvimento where")
		.append(" convert(date, sac_ocorrencia_desenvolvimento.data_hora_inicio) < convert(date,getdate())")
		.append(" and sac_ocorrencia_desenvolvimento.data_hora_fim is null)")		
		.append(" update sac_ocorrencia_desenvolvimento")
		.append(" set sac_ocorrencia_desenvolvimento.data_hora_fim = dateadd(hour,18,convert(datetime,convert(date, data_hora_inicio)))")
		.append(" , sac_ocorrencia_desenvolvimento.tempo_gasto =")
		.append(" datediff(minute, sac_ocorrencia_desenvolvimento.data_hora_inicio, dateadd(hour,18,convert(datetime,convert(date, data_hora_inicio))))")
		.append(" where")
		.append(" convert(date, sac_ocorrencia_desenvolvimento.data_hora_inicio) < convert(date,getdate())")
		.append(" and sac_ocorrencia_desenvolvimento.data_hora_fim is null");		

		Query query = entityManager.createNativeQuery(sb.toString());

		System.out.println("Ocorrencias esquecidas finalizadas automaticamente: " + query.executeUpdate());
		//return query.getResultList();		
	}

	public List<SacDesenvolvimento> obterSacDesenvolvimento(SacOcorrenciaFilter filter) {

		//Map<String, Object> parametros = new HashMap<String, Object>();

		StringBuilder consulta = new StringBuilder();
		consulta.append("select d from SacDesenvolvimento d where d.id is not null");

		if (filter.getDataInicioCadastro() != null) {
			consulta.append(" and d.dataInicio >= :dataInicio");
			parametros.put("dataInicio", filter.getDataInicioCadastro());
		}

		if (filter.getDataFimCadastro() != null) {
			consulta.append(" and d.dataFim >= :dataFim");
			parametros.put("dataFim", filter.getDataFimCadastro());
		}

		if (filter.getId() != null) {
			consulta.append(" and d.sacOcorrencia.id = :id");
			parametros.put("id", filter.getId());
		}

		if (filter.getListIdFuncionario() != null) {
			consulta.append(" and d.funcionario.id in :listFuncionario ");
			parametros.put("listFuncionario", filter.getListIdFuncionario());
		}

		if (filter.isReady()) {
			consulta.append(" and d.sacOcorrencia.ready = 1 ");
		}

		Query query = entityManager.createQuery(consulta.toString());

		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}

		if (filter.getPosition() != null) {
			query.setFirstResult(filter.getPosition());
		}

		if (filter.getMax() != null) {
			query.setMaxResults(filter.getMax());
		}	

		return query.getResultList();

	}

	public List<SacOcorrencia> obterSacToDo(SacOcorrenciaFilter filter) {

		//String codigosFuncionariosDesenvolvedores = "9, 31, 119, 113, 57, 68";
		String codigosFuncaoDesenvolvedores = "7,10,15,16,17,18,19,20,21,22,23";



		StringBuilder consulta = new StringBuilder();
		consulta.append("select o from SacOcorrencia o where o.id is not null");

		consulta.append(" and (o.etapa.id  = 15 or (o.id.etapa = 16 and o.funcionarioRedirecionamento.funcao in ( ")
		.append(codigosFuncaoDesenvolvedores).append(")))")
		.append(" and o.etapa.id <> 21 "); //etapa 21 = quarentena

		consulta.append(" and o.situacaoOcorrencia not in :listSituacaoInuteis ");
		List<SACOcorrenciaEnum> listSituacoes = new ArrayList<SACOcorrenciaEnum>();
		listSituacoes.add(SACOcorrenciaEnum.AGRUPADA);
		//listSituacoes.add(SACOcorrenciaEnum.FEEDBACK);
		listSituacoes.add(SACOcorrenciaEnum.SOLUCIONADA);
		listSituacoes.add(SACOcorrenciaEnum.REMOVIDA);
		parametros.put("listSituacaoInuteis", listSituacoes);

		if (filter.getId() != null) {
			consulta.append(" and o.id = :id");
			parametros.put("id", filter.getId());
		}

		if (filter.getIdCliente() != null) {
			consulta.append(" and o.cliente.id = :idCliente ");
			parametros.put("idCliente", filter.getIdCliente());
		}

		if (filter.getListIdFuncionario() != null) {
			consulta.append(" and COALESCE(o.funcionarioRedirecionamento.id,o.funcionarioCadastro.id) in :listFuncionario ");
			parametros.put("listFuncionario", filter.getListIdFuncionario());
		}

		if (filter.getListIdEtapa() != null) {
			consulta.append(" and o.etapa.id in :listEtapa ");
			parametros.put("listEtapa", filter.getListIdEtapa());
		}

		if (filter.isPrioridade()) {
			consulta.append(" and o.prioridade is not null ");
		}

		//		if (filter.isReady()) {
		//			consulta.append(" and o.ready = 1 ");
		//		}

		consulta.append(" order by COALESCE(o.prioridade, 999) asc ");

		/** seta os parâmetros na query */
		Query query = entityManager.createQuery(consulta.toString());
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}

		if (filter.getPosition() != null) {
			query.setFirstResult(filter.getPosition());
		}

		if (filter.getMax() != null) {
			query.setMaxResults(filter.getMax());
		}	

		return query.getResultList();	

	}

	public List<SacOcorrencia> obterSacDoing(SacOcorrenciaFilter filter) {

		//String codigosFuncionariosDesenvolvedores = "9, 31, 119, 113, 57, 68";
		String codigosFuncaoDesenvolvedores = "7,10,15,16,17,18,19,20,21,22,23";

		//Map<String, Object> parametros = new HashMap<String, Object>();

		StringBuilder consulta = new StringBuilder();

		consulta.append(" select o from SacOcorrencia o where o.id is not null")
		.append(" and o.id in (select d.sacOcorrencia.id from SacDesenvolvimento d")
		.append(" where d.funcionario.funcao.id in (").append(codigosFuncaoDesenvolvedores).append("))")
		.append(" and o.etapa.id <> 15")
		.append(" and o.situacaoOcorrencia not in :listSituacaoInuteis ")
		.append(" and o.etapa.id <> 21 "); //etapa 21 = quarentena

		List<SACOcorrenciaEnum> listSituacoes = new ArrayList<SACOcorrenciaEnum>();
		//listSituacoes.add(SACOcorrenciaEnum.AGRUPADA);  agrupadas neste caso aparecem
		listSituacoes.add(SACOcorrenciaEnum.FEEDBACK);
		listSituacoes.add(SACOcorrenciaEnum.SOLUCIONADA);
		listSituacoes.add(SACOcorrenciaEnum.REMOVIDA);
		parametros.put("listSituacaoInuteis", listSituacoes);

		if (filter.getId() != null) {
			consulta.append(" and o.id = :id");
			parametros.put("id", filter.getId());
		}

		if (filter.getIdCliente() != null) {
			consulta.append(" and o.cliente.id = :idCliente ");
			parametros.put("idCliente", filter.getIdCliente());
		}

		if (filter.getListIdFuncionario() != null) {
			consulta.append(" and COALESCE(o.funcionarioRedirecionamento.id,o.funcionarioCadastro.id) in :listFuncionario ");
			parametros.put("listFuncionario", filter.getListIdFuncionario());
		}

		if (filter.getListIdEtapa() != null) {
			consulta.append(" and o.etapa.id in :listEtapa ");
			parametros.put("listEtapa", filter.getListIdEtapa());
		}

		if (filter.isPrioridade()) {
			consulta.append(" and o.prioridade is not null ");
		}

		//		if (filter.isReady()) {
		//			consulta.append(" and o.ready = 1 ");
		//		}

		consulta.append(" order by o.funcionarioRedirecionamento.nome, COALESCE(o.prioridade, 999), o.id asc ");

		/** seta os parâmetros na query */
		Query query = entityManager.createQuery(consulta.toString());
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}

		if (filter.getPosition() != null) {
			query.setFirstResult(filter.getPosition());
		}

		if (filter.getMax() != null) {
			query.setMaxResults(filter.getMax());
		}	

		return query.getResultList();	

	}


	public List<SacOcorrencia> obterSacDone(SacOcorrenciaFilter filter) {

		//String codigosFuncionariosDesenvolvedores = "9, 31, 119, 113, 57, 68";
		String codigosFuncaoDesenvolvedores = "7,10,15,16,17,18,19,20,21,22,23";

		//Map<String, Object> parametros = new HashMap<String, Object>();

		StringBuilder consulta = new StringBuilder();		

		consulta.append("select o from SacOcorrencia o")
		.append(" where o.id in (")
		.append(" select d.sacOcorrencia.id from SacDesenvolvimento d") 
		.append(" where d.funcionario.funcao.id in (").append(codigosFuncaoDesenvolvedores).append(")")
		.append(" and d.dataFim >= dateadd(MONTH, -1, getdate()))")
		.append(" and o.situacaoOcorrencia in (2,5)")
		.append(" and o.etapa.id <> 21 ");


		if (filter.getId() != null) {
			consulta.append(" and o.id = :id");
			parametros.put("id", filter.getId());
		}

		if (filter.getIdCliente() != null) {
			consulta.append(" and o.cliente.id = :idCliente ");
			parametros.put("idCliente", filter.getIdCliente());
		}

		if (filter.getListIdFuncionario() != null) {
			consulta.append(" and COALESCE(o.funcionarioRedirecionamento.id,o.funcionarioCadastro.id) in :listFuncionario ");
			parametros.put("listFuncionario", filter.getListIdFuncionario());
		}

		if (filter.getListIdEtapa() != null) {
			consulta.append(" and o.etapa.id in :listEtapa ");
			parametros.put("listEtapa", filter.getListIdEtapa());
		}

		if (filter.isPrioridade()) {
			consulta.append(" and o.prioridade is not null ");
		}


		consulta.append(" order by o.funcionarioRedirecionamento.nome, COALESCE(o.prioridade, 999), o.id asc ");

		/** seta os parâmetros na query */
		Query query = entityManager.createQuery(consulta.toString());
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}

		if (filter.getPosition() != null) {
			query.setFirstResult(filter.getPosition());
		}

		if (filter.getMax() != null) {
			query.setMaxResults(filter.getMax());
		}	

		return query.getResultList();	

	}

	public Integer TotalRegistros(SacOcorrenciaFilter filter) {
		StringBuilder consulta = new StringBuilder();		

		consulta.append("select count (o.id) from SacOcorrencia o where o.id is not null");
		consulta = colocaFiltrosConsulta(filter, consulta);
		
		Query query = entityManager.createQuery(consulta.toString());
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}	
		
		Long resultado =  (Long) query.getSingleResult();
		return resultado.intValue();
	}

	private StringBuilder colocaFiltrosConsulta(SacOcorrenciaFilter filter, StringBuilder consulta) {

		if (filter.getId() != null) {
			consulta.append(" and o.id = :id ");
			parametros.put("id", filter.getId());
		}

		if (filter.getIdCliente() != null) {
			consulta.append(" and o.cliente.id = :idCliente ");
			parametros.put("idCliente", filter.getIdCliente());
		}

		if (filter.getNomeCliente() != null) {
			consulta.append(" and o.cliente.nome like :nomeCliente ");
			parametros.put("nomeCliente", "%" + filter.getNomeCliente() +"%");
		}

		if (filter.getDataInicioCadastro() != null) {
			consulta.append(" and o.dataCadastro>= :dataInicioCadastro ");
			parametros.put("dataInicioCadastro", filter.getDataInicioCadastro());
		}

		if (filter.getDataFimCadastro() != null) {
			consulta.append(" and o.dataCadastro <= :dataFimCadastro ");
			parametros.put("dataFimCadastro", filter.getDataFimCadastro());
		}
		
		if (filter.getDataInicioSolucao() != null) {
			consulta.append(" and o.dataSolucao >= :dataInicioSolucao ");
			parametros.put("dataInicioSolucao", filter.getDataInicioSolucao());
		}

		if (filter.getDataFimSolucao() != null) {
			consulta.append(" and o.dataSolucao <= :dataFimSolucao ");
			parametros.put("dataFimSolucao", filter.getDataFimSolucao());
		}

		if (filter.getListSituacaoEnum() != null) {
			consulta.append(" and o.situacaoOcorrencia in :listSituacao ");
			parametros.put("listSituacao", filter.getListSituacaoEnum());
		}

		if (filter.getAssunto() != null) {
			consulta.append(" and o.assunto like :assunto ");
			parametros.put("assunto", "%" + filter.getAssunto() +"%");
		}

		if (filter.getListIdEtapa() != null) {
			consulta.append(" and o.etapa.id in :listEtapa ");
			parametros.put("listEtapa", filter.getListIdEtapa());
		}

		if (filter.getSolicitacao() != null) {
			consulta.append(" and o.solicitacao like :solicitacao ");
			parametros.put("solicitacao", "%" + filter.getSolicitacao() +"%");
		}		

		if (filter.getSolucao() != null) {
			consulta.append(" and o.solucao like :solucao ");
			parametros.put("solucao", "%" + filter.getSolucao() +"%");
		}		

		if (filter.getDescricaoDesenvolvimento() != null) {
			consulta.append(" and (o.descricaoDesenvolvimento like :descricao )");
			parametros.put("descricao", "%" + filter.getDescricaoDesenvolvimento() +"%");
		}

		if (filter.getComentarioDesenvolvimento() != null) {
			consulta.append(" and (o.comentarioDesenvolvimento like :comentarioDes )");
			parametros.put("comentarioDes", "%" + filter.getComentarioDesenvolvimento() +"%");
		}

		if (filter.getComentario() != null) {
			consulta.append(" and (o.comentario like :comentario  or o.solucao like :comentario or o.descricaoDesenvolvimento like :comentario or o.comentarioDesenvolvimento like :comentario) ");
			parametros.put("comentario", "%" + filter.getComentario() +"%");
		}	

		if (filter.getEstimativa() != null) {
			consulta.append(" and o.estimativa = :estimativa ");
			parametros.put("estimativa", filter.getEstimativa());
		}

		if (filter.getListIdFuncionario() != null) {
			consulta.append(" and COALESCE(o.funcionarioRedirecionamento.id,o.funcionarioCadastro.id) in :listFuncionario ");
			parametros.put("listFuncionario", filter.getListIdFuncionario());
		}

		if (filter.getDataInicioPrevisaoTermino() != null) {
			consulta.append(" and o.dataPrevisaoTermino >= :dataInicioPrevisao ");
			parametros.put("dataInicioPrevisao", filter.getDataInicioPrevisaoTermino());
		}

		if (filter.getDataFimPrevisaoTermino() != null) {
			consulta.append(" and o.dataPrevisaoTermino <= :dataFimPrevisao ");
			parametros.put("dataFimPrevisao", filter.getDataFimPrevisaoTermino());
		}

		if (filter.isUteis()) {
			consulta.append(" and o.situacaoOcorrencia not in :listSituacaoInuteis ");
			List<SACOcorrenciaEnum> listSituacoes = new ArrayList<SACOcorrenciaEnum>();
			listSituacoes.add(SACOcorrenciaEnum.AGRUPADA);
			//listSituacoes.add(SACOcorrenciaEnum.FEEDBACK);
			listSituacoes.add(SACOcorrenciaEnum.SOLUCIONADA);
			listSituacoes.add(SACOcorrenciaEnum.REMOVIDA);

			parametros.put("listSituacaoInuteis", listSituacoes);
		}

		if (filter.isReady()) {
			consulta.append(" and o.ready = 1 ");
		}

		if (filter.isPrioridade()) {
			consulta.append(" and o.prioridade is not null ");
		}
		
		if (filter.isPassouDesenvolvimento()) {
			consulta.append(" and o.id in (select d.sacOcorrencia.id from SacDesenvolvimento d) ");
		}

		return consulta;

	}
	
	public List<SacOcorrenciaFollowUp> obterFollowUp(SacOcorrenciaFilter filter) {
		StringBuilder consulta = new StringBuilder("select f from SacOcorrenciaFollowUp f where f.idSacOcorrencia is not null ");
		
		if (filter.getId() != null) {
			consulta.append(" and f.idSacOcorrencia = :id");
			parametros.put("id", filter.getId());
		}
		
		consulta.append(" order by f.sequencia desc ");
		
		Query query = entityManager.createQuery(consulta.toString());
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}		
		return query.getResultList();		
	}
	
	public List<Integer> followUpComSimilares(int id, int idUsuarioSupraMais, String mensagem) {

		List<Integer> ids = new ArrayList<Integer>();
		List<Integer> registrados = new ArrayList<Integer>();

		StringBuilder consultaInicial = new StringBuilder("select codigo from sac_ocorrencia where codigo = ")
				.append(id).append(" or sacocor_codigo_similar = ").append(id);
		Query queryInicial = entityManager.createNativeQuery(consultaInicial.toString());
		ids = queryInicial.getResultList();

		if (ids.size() > 0) {
			for (Integer codigo : ids) {
				try {
					if (insereFollowUp(id, idUsuarioSupraMais, mensagem)) {
						registrados.add(id);
					};
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return registrados;		
	}
	
	public boolean insereFollowUp(int id, int idUsuarioSupraMais, String mensagem) {
		//existe outro mpetodo nesta classe que insere follow up também nas ocorrencias vinculadas / agrupadas
		List<Integer> registrados = new ArrayList<Integer>();
		
		try {
			StringBuilder consulta = new StringBuilder("insert into sac_follow_up (ocor_codigo, codigo, data, usu_codigo, historico) ")
					.append("values(:id, isnull((select max(codigo) from sac_follow_up where ocor_codigo = :id),0)+1, ")
					.append("getdate(), :idUsuario, :mensagem)");
			Query query = entityManager.createNativeQuery(consulta.toString());
			query.setParameter("id", id);
			query.setParameter("idUsuario", idUsuarioSupraMais);
			query.setParameter("mensagem", mensagem);
			query.executeUpdate();
			registrados.add(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	public List<SacDesenvolvimento> obterUltimosSacDesenvolvedores(int quantidadeSacs) {
		StringBuilder consulta = new StringBuilder("select distinct d.funcionario.id from SacDesenvolvimento d ");
		Query query = entityManager.createQuery(consulta.toString());
		List<Integer> idFucionarios = new ArrayList<Integer>();
		
		idFucionarios = query.getResultList();
		List<SacDesenvolvimento> ocorrencias = new ArrayList<SacDesenvolvimento>();
		
		for (Integer id : idFucionarios) {
			ocorrencias.addAll(obterUltimosSacDesenvolvedor(id, quantidadeSacs));
		}
		return ocorrencias;			
	}	
	
	public List<SacDesenvolvimento> obterUltimosSacDesenvolvedor(int idUsuario, int quantidadeSacs) {
		
		
		//Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder consulta = new StringBuilder();
		consulta.append("select top(").append(quantidadeSacs).append(") maxCodigo from")
		.append(" (select distinct func_codigo, sacocor_codigo, max(codigo) as maxCodigo from sac_ocorrencia_desenvolvimento")
		.append(" where func_codigo = ").append(idUsuario).append(" group by func_codigo, sacocor_codigo ) base  order by maxCodigo desc");
		
		List<Integer> listaSacDesenvolvimento =  new ArrayList<>();
		Query query = entityManager.createNativeQuery(consulta.toString());
		listaSacDesenvolvimento = query.getResultList();
		
		StringBuilder ids = new StringBuilder();
		
		for (Integer idSacDesenvolvimento : listaSacDesenvolvimento) {
			ids.append(" ").append(idSacDesenvolvimento).append(", ");
		}
		ids.append(" 0 ");
		
		consulta = new StringBuilder();		

		consulta.append("select d from SacDesenvolvimento d where d.id in ( ").append(ids.toString()).append(")")
		.append(" order by d.dataInicio desc ");
		
		Query query2 = entityManager.createQuery(consulta.toString());
		
		return query2.getResultList();

	}

}
