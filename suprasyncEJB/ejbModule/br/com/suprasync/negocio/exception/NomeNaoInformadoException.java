package br.com.suprasync.negocio.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NomeNaoInformadoException extends Exception {

		public NomeNaoInformadoException() {
		}
}
