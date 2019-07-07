package br.com.suprasync.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.suprasync.negocio.exception.UsuarioInexistenteException;
import br.com.suprasync.persistencia.Funcionario;
import br.com.suprasync.persistencia.dao.IFuncionarioDAO;
import br.com.suprasync.persistencia.dao.exception.UsuarioNaoEncontradoException;

@Stateless
public class FuncionarioService implements IFuncionarioServiceLocal {

	@EJB
	private IFuncionarioDAO funcionarioDao;

	public FuncionarioService() {

	}	

	public Funcionario pesquisar (int id) throws  UsuarioInexistenteException {


		try {
			return funcionarioDao.obter(id);				
		}catch (UsuarioNaoEncontradoException e){
			throw new UsuarioInexistenteException();				
		}

	} 

	public List<Funcionario> pesquisar(Integer position, Integer max) throws UsuarioInexistenteException {

		try {
			return funcionarioDao.obterAtivos(position, max);
		} catch (UsuarioNaoEncontradoException e) {
			throw new UsuarioInexistenteException();
		}		

	}


}
