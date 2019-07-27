package br.com.jvsync.timer;

import javax.ejb.Schedule;

public class TimerAtualizaBancoDados {
	
	@Schedule(hour="0", minute="45", persistent=false) //@Schedule(hour = "*", minute="*/2", persistent=false) // testes
	public void atualizaBancoDadosTimer() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}				
	}

}
