package br.com.suprasync.persistencia.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.suprasync.persistencia.SacOcorrencia;
import br.com.suprasync.persistencia.dao.exception.SacOcorrenciaNaoEncontradaException;
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
		
		if (filter.getEstimativa() != null) {
			consulta.append(" and o.estimativa = :estimativa ");
			parametros.put("estimativa", filter.getEstimativa());
		}
		
		if (filter.getDescricaoDesenvolvimento() != null) {
			consulta.append(" and o.descricaoDesenvolvimento like descricao ");
			parametros.put("descricao", "%" + filter.getDescricaoDesenvolvimento() +"%");
		}
		
		if (filter.getComentarioDesenvolvimento() != null) {
			consulta.append(" and o.comentarioDesenvolvimento like descricao ");
			parametros.put("comentario", "%" + filter.getComentarioDesenvolvimento() +"%");
		}
		
		if (filter.getListIdUsuario() != null) {
			consulta.append(" and o.usuario.id in :listEtapa ");
			parametros.put("listUsuario", filter.getListIdUsuario());
		}
		
		if (filter.getDataInicioPrevisaoTermino() != null) {
			consulta.append(" and o.dataPrevisaoTermino >= :dataInicioPrevisao ");
			parametros.put("dataInicioPrevisao", filter.getDataInicioPrevisaoTermino());
		}
		
		if (filter.getDataFimPrevisaoTermino() != null) {
			consulta.append(" and o.dataPrevisaoTermino <= :dataFimPrevisao ");
			parametros.put("dataFimPrevisao", filter.getDataFimPrevisaoTermino());
		}
			
		consulta.append(" order by o.id ");

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

}
