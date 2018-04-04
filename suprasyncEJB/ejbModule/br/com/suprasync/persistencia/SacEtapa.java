package br.com.suprasync.persistencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.suprasync.persistencia.enumerate.SACOcorrenciaEnum;

@Entity  
@Table(name="sac_etapa_atendimento")// o nome da tabela correspondente quando for entity
public class SacEtapa {

	private int id;	
	private String nome;	
	private SACOcorrenciaEnum situacaoInicial;
	private Date dataExclusao; 
			
	public SacEtapa() {
		
	}
	
	@Id //indica chave prim�ria
	@Column(name="codigo")//nome da coluna no bd
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="nome", columnDefinition="nvarchar")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "id_situacao_inicio", columnDefinition= "int")
	public SACOcorrenciaEnum getSituacaoInicial() {
		return situacaoInicial;
	}

	public void setSituacaoInicial(SACOcorrenciaEnum situacaoInicial) {
		this.situacaoInicial = situacaoInicial;
	}
	
	@Column(name="data_exclusao", columnDefinition="datetime")
	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	

	@Override
	public String toString() {
		return "Etapa [id=" + id +  ", Nome="
				+ nome + "]";
	}	
	
}
