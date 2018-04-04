package br.com.suprasync.persistencia.filter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;

import br.com.suprasync.negocio.exception.FalhaAoConverterDataException;
import br.com.suprasync.persistencia.enumerate.SACOcorrenciaEnum;
import br.com.suprasync.util.Utilities;

public class SacOcorrenciaFilter {

	private Integer id;	
	private Integer idCliente;
	private String nomeCliente;
	private Date dataInicioCadastro; // TODO: adicionar 23:59
	private Date dataFimCadastro;	
//	private List<Integer> ListIdSituacao;
	private List<SACOcorrenciaEnum> ListSituacaoEnum;
	private String assunto;
	private List<Integer> ListIdEtapa;
	private String solicitacao;
	private Integer estimativa;
	private String descricaoDesenvolvimento;
	private String comentarioDesenvolvimento;
	private List<Integer> ListIdUsuario;
	private Date dataInicioPrevisaoTermino;
	private Date dataFimPrevisaoTermino;
	private Integer position;
	private Integer max;
	private boolean uteis;

	public SacOcorrenciaFilter() {

	}

	public SacOcorrenciaFilter(JsonArray filterJson) throws ParseException, FalhaAoConverterDataException {
		montaFilterJson(filterJson);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Date getDataInicioCadastro() {
		return dataInicioCadastro;
	}

	public void setDataInicioCadastro(Date dataInicioCadastro) {
		this.dataInicioCadastro = dataInicioCadastro;
	}

	public Date getDataFimCadastro() {
		return dataFimCadastro;
	}

	public void setDataFimCadastro(Date dataFimCadastro) {
		this.dataFimCadastro = dataFimCadastro;
	}

	public List<SACOcorrenciaEnum> getListSituacaoEnum() {
		return ListSituacaoEnum;
	}

	public void setListSituacaoEnum(List<SACOcorrenciaEnum> listSituacaoEnum) {
		ListSituacaoEnum = listSituacaoEnum;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public List<Integer> getListIdEtapa() {
		return ListIdEtapa;
	}

	public void setListIdEtapa(List<Integer> listIdEtapa) {
		ListIdEtapa = listIdEtapa;
	}

	public String getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Integer getEstimativa() {
		return estimativa;
	}

	public void setEstimativa(Integer estimativa) {
		this.estimativa = estimativa;
	}

	public String getDescricaoDesenvolvimento() {
		return descricaoDesenvolvimento;
	}

	public void setDescricaoDesenvolvimento(String descricaoDesenvolvimento) {
		this.descricaoDesenvolvimento = descricaoDesenvolvimento;
	}

	public String getComentarioDesenvolvimento() {
		return comentarioDesenvolvimento;
	}

	public void setComentarioDesenvolvimento(String comentarioDesenvolvimento) {
		this.comentarioDesenvolvimento = comentarioDesenvolvimento;
	}

	public List<Integer> getListIdUsuario() {
		return ListIdUsuario;
	}

	public void setListIdUsuario(List<Integer> listIdUsuario) {
		ListIdUsuario = listIdUsuario;
	}

	public Date getDataInicioPrevisaoTermino() {
		return dataInicioPrevisaoTermino;
	}

	public void setDataInicioPrevisaoTermino(Date dataInicioPrevisaoTermino) {
		this.dataInicioPrevisaoTermino = dataInicioPrevisaoTermino;
	}

	public Date getDataFimPrevisaoTermino() {
		return dataFimPrevisaoTermino;
	}

	public void setDataFimPrevisaoTermino(Date dataFimPrevisaoTermino) {
		this.dataFimPrevisaoTermino = dataFimPrevisaoTermino;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public boolean isUteis() {
		return uteis;
	}

	public void setUteis(boolean uteis) {
		this.uteis = uteis;
	}

	private void montaFilterJson(JsonArray filterJson) throws ParseException, FalhaAoConverterDataException {

		System.out.println(this.getClass().getDeclaredFields().toString());

		Utilities util = new Utilities();
		for (int i = 0; i < filterJson.size(); i++) {		

			String parametro = filterJson.get(i).getAsJsonObject().get("property").getAsString();			
			JsonArray valores = null;			
			String valor = null;
			
			try {
				valor = filterJson.get(i).getAsJsonObject().get("value").getAsString();
			} catch (Exception e1) {
				valores = filterJson.get(i).getAsJsonObject().get("value").getAsJsonArray();
			}			

			if (parametro != null && !parametro.isEmpty() && (valor != null || valores != null)) {				

				switch (parametro.toLowerCase()) {
				case "id": setId(Integer.parseInt(valor)); break;
				case "idcliente" : setIdCliente(idCliente);break;
				case "nomecliente" : setNomeCliente(valor);break;
				case "datainiciocadastro" : setDataInicioCadastro(util.dataDDIMMIYYYY(valor));break; // TODO: adicionar 23:59
				case "datafimcadastro" : setDataFimCadastro(util.dataDDIMMIYYYY(valor));break;	
				case "assunto" : setAssunto(valor);break;
				case "solicitacao" : setSolicitacao(valor);break;
				case "estimativa" : setEstimativa(Integer.parseInt(valor));break;
				case "descricaodesenvolvimento" : setDescricaoDesenvolvimento(valor);break;
				case "comentariodesenvolvimento" : setComentarioDesenvolvimento(valor);break;
				case "datainicioprevisaopermino" : setDataInicioPrevisaoTermino(util.dataDDIMMIYYYY(valor));break;
				case "datafimprevisaotermino" : setDataFimPrevisaoTermino(util.dataDDIMMIYYYY(valor));break;
				case "position" : setPosition(Integer.parseInt(valor));break;
				case "max" : setMax(Integer.parseInt(valor));break;
				
				case "idsituacao" : ListSituacaoEnum = new ArrayList<SACOcorrenciaEnum>();				
				if (valores != null) {
					try {
						for (int j = 0; j < valores.size(); j++) {
							ListSituacaoEnum.add(SACOcorrenciaEnum.getEnum(valores.get(j).getAsInt()));						
						}
					} catch (Exception e) {						
						ListSituacaoEnum.add(SACOcorrenciaEnum.getEnum(Integer.parseInt(valor)));
					}
				} else {
					ListSituacaoEnum.add(SACOcorrenciaEnum.getEnum(Integer.parseInt(valor)));
				}
					
				break;
				
				case "idetapa" : ListIdEtapa = new ArrayList<Integer>();
				if(valores != null) {
					try {
						for (int j = 0; j < valores.size(); j++) {
							ListIdEtapa.add(valores.get(j).getAsInt());						
						}
					} catch (Exception e) {						
						ListIdEtapa.add(Integer.parseInt(valor));
					}
				} else {
					ListIdEtapa.add(Integer.parseInt(valor));
				}
				
				break;
				
				case "idusuario" : ListIdUsuario = new ArrayList<Integer>();
				if(valores != null) {
					try {
						for (int j = 0; j < valores.size(); j++) {
							ListIdUsuario.add(valores.get(j).getAsInt());						
						}
					} catch (Exception e) {						
						ListIdUsuario.add(Integer.parseInt(valor));
					}
				} else {
					ListIdEtapa.add(Integer.parseInt(valor));
				}
				
				break;	

				default:
				break;
				}

			}

		}

	}

}
