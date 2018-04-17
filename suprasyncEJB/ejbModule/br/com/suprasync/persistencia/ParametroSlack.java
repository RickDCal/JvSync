package br.com.suprasync.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "parametro_slack")
public class ParametroSlack {
	
	private int id;
	private String webHookSlack;

	public ParametroSlack() {
		
	}	
	
	@Id
	@Column(name = "codigo")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "webhook_slack", columnDefinition="nvarchar")
	public String getWebHookSlack() {
		return webHookSlack;
	}

	public void setWebHookSlack(String webHookSlack) {
		this.webHookSlack = webHookSlack;
	}	

}
