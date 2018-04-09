package br.com.suprasync.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.suprasync.persistencia.Funcionario;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.suprasync.persistencia.dao.exception.UsuarioNaoEncontradoException;

@Stateless
public class FuncionarioDAO extends GenericDAO implements IFuncionarioDAO {


	public FuncionarioDAO() {

	}	

	public Funcionario obter (int id) throws UsuarioNaoEncontradoException {
		Funcionario usuario = null;
		try {
			usuario =  (Funcionario) this.obter(Funcionario.class, id);
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public List<Funcionario> obterAtivos(Integer position, Integer max) {

		String consulta =  "select f from Funcionario f where f.id is not null and f.dataExclusao is null ";		
		Query query = entityManager.createQuery(consulta);

		if (position !=null) {
			query.setFirstResult(position);
		}
		if (max != null) {
			query.setMaxResults(max);
		}

		List<Funcionario> usuarios = query.getResultList();

		return usuarios;		

	}	


}
