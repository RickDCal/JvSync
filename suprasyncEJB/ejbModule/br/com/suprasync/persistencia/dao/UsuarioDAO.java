package br.com.suprasync.persistencia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.suprasync.persistencia.Usuario;
import br.com.suprasync.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.suprasync.persistencia.dao.exception.UsuarioNaoEncontradoException;

@Stateless
public class UsuarioDAO extends GenericDAO implements IUsuarioDAO {


	public UsuarioDAO() {

	}
	
	public Usuario inserir (Usuario usuario) {
		entityManager.persist(usuario);
		entityManager.flush(); // para inserir na mesma hora que gera na mem�ria
		return usuario;
	}

	public Usuario alterar (Usuario usuario) {
		entityManager.merge(usuario);
		entityManager.flush(); 
		return usuario;
	}

	public void remover (Usuario usuario) { 
		entityManager.remove(entityManager.contains(usuario)? usuario : entityManager.merge(usuario)); 
		entityManager.flush(); 		
	}

	public Usuario obter(String userName, String password) throws UsuarioNaoEncontradoException {

		Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.userName = :userName and u.password = :password");
		query.setParameter("userName", userName); 
		query.setParameter("password", password);

		Usuario usuario = null;
		List<Usuario> usuarios = query.getResultList();

		if (usuarios.size() > 0) {
			usuario = (Usuario)query.getSingleResult();
		}			

		return usuario;
	}	

	public Usuario obter (int id) throws UsuarioNaoEncontradoException {
		//		Usuario usuario = entityManager.find(Usuario.class, id);
		//			return usuario;
		Usuario usuario = null;
		try {
			usuario =  (Usuario) this.obter(Usuario.class, id);
		} catch (ObjetoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}

	public List<Usuario> obter(Integer position, Integer max) {

		String consulta =  "select u from Usuario u where u.id is not null and u.dataExclusao is null";		
		Query query = entityManager.createQuery(consulta);

		if (position !=null) {
			query.setFirstResult(position);
		}
		if (max != null) {
			query.setMaxResults(max);
		}

		List<Usuario> usuarios = query.getResultList();

		return usuarios;		

	}	


}
