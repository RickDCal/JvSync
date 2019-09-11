package br.com.jvsync.negocio;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import br.com.jvsync.persistencia.dao.IGenericDAO;

@Stateless
public class DatabaseService {

	@EJB
	private IGenericDAO genericDao;

	public DatabaseService() {

	}	

	@Schedule(hour="01", minute="40", persistent=false)
	public void atualizaBancoDadosTimer() {
		try {
			System.out.println("inicio da atualização automática.");
			String url = "http://localhost:8080/jvsync/rest/database/atualizarDados?tabela=all";
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(url);			
			Response response = target.request().get();

			final String retorno; 
			if (response != null) {
				retorno = response.readEntity(String.class);
			} else {
				retorno = null;
			}
			System.out.println(retorno);
		} catch (Exception e) {
			e.printStackTrace();
		}				

	}

}
