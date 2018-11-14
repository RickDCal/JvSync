package br.com.suprasync.apresentacao.ws.relatorio;

import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

	
	@GET
	@Path("/graficoOcorrencias2")
	@Produces(MediaType.APPLICATION_JSON)
	public String obterOcorrenciasCadastradasSolucionadasGraf(@DefaultValue("") 
	@QueryParam("filter") String filtro) throws NamingException {	
		
		GenericFacade genericFacade = new GenericFacade();
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select data as coluna1, sum(cadastradas) as coluna2, sum(solucionadas) as coluna3")
		.append(" from")
		.append(" (	select datepart(year, data_cadastro) as ano , substring(convert(varchar, data_cadastro, 3), 4,5) as data,")
		.append(" 1 as cadastradas, 0 as solucionadas")
		.append(" from sac_ocorrencia")
		.append(" Union ALL")
		.append(" select datepart(year, data_solucao) as ano, substring(convert(varchar, data_solucao, 3), 4,5) as data,")
		.append(" 0 as cadastradas, 1 as solucionadas")
		.append(" from sac_ocorrencia where data_solucao is not null")
		.append(" ) todos")
		.append(" where data is not null");
			
		
		SacOcorrenciaFilter filter = new SacOcorrenciaFilter();
		if (filtro != null) {
			Gson gson = new Gson();
			filter = gson.fromJson(filtro, SacOcorrenciaFilter.class);
		}

		if (filter != null && filter.getDataInicioCadastro() != null) {
			nativeQuery.append(" and data >= '").append(Utilities.dataDDIMMIYYYY(filter.getDataInicioCadastro())).append("'");
		}
		
		if (filter != null && filter.getDataFimCadastro() != null) {
			nativeQuery.append(" and data <= '").append(Utilities.dataDDIMMIYYYY(filter.getDataFimCadastro())).append("'");
		}
		
		nativeQuery.append(" group by ano, data")		
		.append(" order by data ");
			
		
		List resultList = genericFacade.obter(nativeQuery.toString());
		
		for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
			Object[] result = (Object[]) iterator.next();
            String codigo = (String) result[0];
            Integer contexto = (Integer) result[1];
            Integer tipoLigacao = (Integer) result[2];
            System.out.println(codigo + contexto + tipoLigacao);
            
            JsonObject jo = new JsonObject();
			jo.addProperty("mes", (String) result[0]);
			jo.addProperty("cadastradas", (Integer) result[1]);
			jo.addProperty("solucionadas", (Integer) result[2]);
			jdados.add(jo);
		}
		
		
		
		
		
		
		
		
//		try {
//			SacOcorrenciaFilter sacFilter = trataFilterGraficoOcorrencias1(filter, 0,5000);	
//			SacOcorrenciaFacade ocorrenciaFacade = new SacOcorrenciaFacade();
//			List<SacOcorrencia> sacs = ocorrenciaFacade.converteListOcorrenciaDTOemListSacOcorrencia(ocorrenciaFacade.buscarOcorrenciasDTOSupra(sacFilter));
//			Map<String, Integer> recordMap  = new HashMap<String, Integer>();
//			UtilFacade utilFacade = new UtilFacade();
//			//List<SacOcorrencia> sacs = ocorrenciaFacade.obter(sacFilter);
//			for (Iterator<SacOcorrencia> iterator = sacs.iterator(); iterator.hasNext();) {
//				SacOcorrencia sac = (SacOcorrencia) iterator.next();
//				String mes = utilFacade.dataMMIYYYY(sac.getDataSolucao());
//				//System.out.println("sac " + sac.getId() + " " + sac.getDataSolucao() + " " + mes);
//				if (null == recordMap.get(mes)) {
//					recordMap.put(mes, 1);
//				} else {
//					Integer saldo = recordMap.get(mes);
//					recordMap.replace(mes, 1 + saldo);
//				}
//			}
			
//			int i = 5;
//			
//			for (int j = 1; j < i; j++) {
//				JsonObject jo = new JsonObject();
//				jo.addProperty("mes", "mes" + j);
//				jo.addProperty("cadastradas", i+j);
//				jo.addProperty("solucionadas", j*2);
//				jdados.add(jo);
//				
//			}
			
			
//			
//			for (Map.Entry<String, Integer> entry : recordMap.entrySet()) {
//				JsonObject jo = new JsonObject();
//				jo.addProperty("mes", entry.getKey());
//				jo.addProperty("valor", entry.getValue());
//				
//				jdados.add(jo);
//			}	
			setSuccess(true);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FalhaAoProcessarRequisicaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ObjetoNaoEncontradoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
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
