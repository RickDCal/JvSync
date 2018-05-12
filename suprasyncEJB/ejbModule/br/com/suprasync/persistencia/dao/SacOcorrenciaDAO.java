package br.com.suprasync.persistencia.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.dao.exception.SacOcorrenciaNaoEncontradaException;
import br.com.suprasync.persistencia.enumerate.SACOcorrenciaEnum;
import br.com.suprasync.persistencia.filter.SacOcorrenciaFilter;

@Stateless
public class SacOcorrenciaDAO extends GenericDAO implements ISacOcorrenciaDAO {


	public SacOcorrenciaDAO() {

	}

	public List<SacOcorrencia> obter (SacOcorrenciaFilter filter) throws SacOcorrenciaNaoEncontradaException {

		Map<String, Object> parametros = new HashMap<String, Object>();

		if (filter == null) {
			filter = new SacOcorrenciaFilter();
		}

		StringBuilder consulta = new StringBuilder("select o from SacOcorrencia o where o.id is not null");

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
			consulta.append(" and (o.comentarioDesenvolvimento like :comentario )");
			parametros.put("comentario", "%" + filter.getComentarioDesenvolvimento() +"%");
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
			
		if (filter.isPrioridade()) {
			consulta.append(" and o.prioridade is not null ");
		}
		
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
	
	public List<Integer> followUp(int id, int idUsuarioSupraMais, String mensagem) {
		
		List<Integer> idIdUsuario = new ArrayList<Integer>();
		List<Integer> registrados = new ArrayList<Integer>();
		
		StringBuilder consultaInicial = new StringBuilder("select codigo from sac_ocorrencia where codigo = ")
				.append(id).append(" or sacocor_codigo_similar = ").append(id);
		Query queryInicial = entityManager.createNativeQuery(consultaInicial.toString());
		idIdUsuario = queryInicial.getResultList();
		
		if (idIdUsuario.size() > 0) {
			
			for (Integer codigo : idIdUsuario) {
			
				try {
					StringBuilder consulta = new StringBuilder("insert into sac_follow_up (ocor_codigo, codigo, data, usu_codigo, historico) ")
							.append("values(:id, isnull((select max(codigo) from sac_follow_up where ocor_codigo = :id),0)+1, ")
							.append("getdate(), :idUsuario, :mensagem)");
					Query query = entityManager.createNativeQuery(consulta.toString());
					query.setParameter("id", codigo);
					query.setParameter("idUsuario", idUsuarioSupraMais);
					query.setParameter("mensagem", mensagem);
					query.executeUpdate();
					registrados.add(codigo);
				} catch (Exception e) {
					e.printStackTrace();
					return registrados;
				}
			}
		}
		return registrados;		
	}
	
	public void consultaNativa (String consulta) {
		Query query = entityManager.createNativeQuery(consulta);
		query.executeUpdate();
	}
}
