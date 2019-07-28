package br.com.jvsync.timer;

import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@Stateless
public class TimerAtualizaBancoDadosSqlServer {

	@Schedule(hour="18", minute="40", persistent=false) //@Schedule(hour = "*", minute="*/2", persistent=false) // testes
	public void atualizaBancoSQL() {
		try {

			StringBuilder url = new StringBuilder();
			url.append("http://localhost:8080/jvsync/rest/database/atualizarDados?tabela=all");
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(url.toString());
			Response resp =  target.request().get(); 
			System.out.println("jvcaller execução finalizada às " + new Date().toString() + " Status de requisição: " + resp.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}