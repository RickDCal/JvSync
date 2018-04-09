package br.com.suprasync.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.suprasync.persistencia.Funcionario;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.suprasync.persistencia.dao.exception.UsuarioNaoEncontradoException;

@Stateless
public class BaseDAO extends GenericDAO implements IGenericDAO {
/*CRIEI ESTA CLASSE APENAS PARA QUE A CLASSE GenericDAO NÃO TENHA QUE IMPLEMETAR DIRETAMENTE A INTERFACE IGenericDAO*/

	public BaseDAO() {
		
	}	

}
