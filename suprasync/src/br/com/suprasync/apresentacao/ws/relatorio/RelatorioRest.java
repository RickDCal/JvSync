package br.com.suprasync.apresentacao.ws.relatorio;

import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.suprasync.apresentacao.facade.GenericFacade;
import br.com.suprasync.persistencia.filter.SacOcorrenciaFilter;
import br.com.suprasync.util.Utilities;

@Path("/relatorio")
public class RelatorioRest {

	private JsonObject retorno = new JsonObject();
	private JsonArray jdados = new JsonArray();	
	private boolean success = false;
	private JsonParser parser = new JsonParser();
	private String mensagemRetorno = null;

	@PUT
	@Path("/graficoOcorrencias2")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String obterOcorrenciasCadastradasSolucionadasGraf(String filtro) throws NamingException {		

		GenericFacade genericFacade = new GenericFacade();
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select data as coluna1, sum(cadastradas) as coluna2, sum(solucionadas) as coluna3")
		.append(" from")
		.append(" (	select datepart(year, data_cadastro) as ano , substring(convert(varchar, data_cadastro, 3), 4,5) as data,")
		.append(" 1 as cadastradas, 0 as solucionadas, func_codigo_solicitacao, clifor_codigo")
		.append(" from sac_ocorrencia where data_cadastro > '01/01/2014'")
		.append(" Union ALL")
		.append(" select datepart(year, data_solucao) as ano, substring(convert(varchar, data_solucao, 3), 4,5) as data,")
		.append(" 0 as cadastradas, 1 as solucionadas, func_codigo_solicitacao, clifor_codigo")
		.append(" from sac_ocorrencia where data_solucao is not null and id_situacao = 2 and sac_ocorrencia.data_cadastro >= '01/01/2014'")
		.append(" ) todos")
		.append(" join cliente_fornecedor cf on cf.codigo =  todos.clifor_codigo")
		.append(" where data is not null");			

		SacOcorrenciaFilter filter = new SacOcorrenciaFilter();
		if (filtro != null) {
			Gson gson = new Gson();
			filter = gson.fromJson(filtro, SacOcorrenciaFilter.class);
		}			

		if (filter != null && filter.getDataInicioSolucao() != null) {
			nativeQuery.append(" and convert(date, '01/' + data) >= '").append(Utilities.dataDDIMMIYYYY(filter.getDataInicioSolucao())).append("'");
		}

		if (filter != null && filter.getDataFimSolucao() != null) {
			nativeQuery.append(" and convert(date, '01/' + data) <= '").append(Utilities.dataDDIMMIYYYY(filter.getDataFimSolucao())).append("'");
		}
		
		if (filter != null && filter.getListIdFuncionario() != null && filter.getListIdFuncionario().size() > 0) {
			nativeQuery.append(" and func_codigo_solicitacao in (");
			for (int i = 0; i < filter.getListIdFuncionario().size(); i++) {
				nativeQuery.append(filter.getListIdFuncionario().get(i));				
			}
			nativeQuery.append(")");
		}
		
		if (filter != null && filter.getNomeCliente() != null) {
			nativeQuery.append(" and cf.nome like '%").append(filter.getNomeCliente()).append("%'");
		}
		
		nativeQuery.append(" group by ano, data")		
		.append(" order by ano, data ");			

		List<Object> resultList = genericFacade.obter(nativeQuery.toString());

		for (Iterator<Object> iterator = resultList.iterator(); iterator.hasNext();) {
			Object[] result = (Object[]) iterator.next();

			JsonObject jo = new JsonObject();
			jo.addProperty("mes", (String) result[0]);
			jo.addProperty("cadastradas", (Integer) result[1]);
			jo.addProperty("solucionadas", (Integer) result[2]);
			jdados.add(jo);
		}		
		setSuccess(true);
		return montaResposta();
	}	

	//---------------------------------------------------------------------------//

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String montaResposta() {
		retorno.addProperty("success", success);
		if (mensagemRetorno != null) {
			retorno.addProperty("mensagemRetorno", mensagemRetorno);
		}
		retorno.add("data", jdados);
		return retorno.toString();
	}

}
