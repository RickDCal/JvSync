package br.com.suprasync.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "parametro_sistema")
public class ParametroSistema {
	
	private int id;
	private Boolean slack = Boolean.FALSE;

	public ParametroSistema() {
		
	}	
	
	@Id
	@Column(name = "codigo")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="ind_slack", columnDefinition="smallint")
	public Boolean getSlack() {
		return slack;
	}
	public void setSlack(Boolean slack) {
		this.slack = slack;
	}

	

}
