package br.com.suprasync.persistencia.dao;

import javax.ejb.Stateless;

@Stateless
public class BaseDAO extends GenericDAO implements IGenericDAO {
/*CRIEI ESTA CLASSE APENAS PARA QUE A CLASSE GenericDAO N�O TENHA QUE IMPLEMETAR DIRETAMENTE A INTERFACE IGenericDAO*/

	public BaseDAO() {
		
	}	

}
