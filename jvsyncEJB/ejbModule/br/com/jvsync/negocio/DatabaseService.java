package br.com.jvsync.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import com.google.gson.Gson;

import br.com.jvsync.persistencia.CabecalhoNotaFiscal;
import br.com.jvsync.persistencia.ItemNotaFiscal;
import br.com.jvsync.persistencia.MSCabecalhoNotaFiscal;
import br.com.jvsync.persistencia.MSItemNotaFiscal;
import br.com.jvsync.persistencia.MSParceiro;
import br.com.jvsync.persistencia.MSProduto;
import br.com.jvsync.persistencia.MSTipoVenda;
import br.com.jvsync.persistencia.MSTipoVolume;
import br.com.jvsync.persistencia.MSVendedor;
import br.com.jvsync.persistencia.Parceiro;
import br.com.jvsync.persistencia.Produto;
import br.com.jvsync.persistencia.TipoVenda;
import br.com.jvsync.persistencia.TipoVolume;
import br.com.jvsync.persistencia.Vendedor;
import br.com.jvsync.persistencia.dao.ICadastroDAO;
import br.com.jvsync.persistencia.dao.IGenericDAO;
import br.com.jvsync.persistencia.dao.exception.ObjetoNaoEncontradoException;

@Stateless
public class DatabaseService {
	
	@EJB
	private IGenericDAO genericDao;

	public DatabaseService() {

	}	
	
	@Schedule(hour="01", minute="40", persistent=false) //@Schedule(hour = "*", minute="*/2", persistent=false) // testes
	public void atualizaBancoDadosTimer() {
		try {
			System.out.println(atualizaBancoDados());
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	public String atualizaBancoDados() {
		
		StringBuilder stb = new StringBuilder();
		try {
			stb.append(atualizaDados(CabecalhoNotaFiscal.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(ItemNotaFiscal.class));
			stb.append(System.lineSeparator());	
			stb.append(atualizaDados(Parceiro.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(Produto.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(TipoVenda.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(TipoVolume.class));
			stb.append(System.lineSeparator());
			stb.append(atualizaDados(Vendedor.class));
			stb.append(System.lineSeparator());
		} catch (ObjetoNaoEncontradoException | NamingException e) {
			stb.append("ocorreu uma falha na atualização dos dados");
			e.printStackTrace();
		}
		return stb.toString();		
	}
	
	@SuppressWarnings("unchecked")
	public <T>String atualizaDados (Class<T> classe) throws ObjetoNaoEncontradoException, NamingException {

		StringBuilder stb = new StringBuilder("Atualizar ").append(classe.getSimpleName()).append(". Total registros de origem: ");		

		int x = genericDao.totalRegistros(classe).intValue();
		int i = 0;
		stb.append(x);

		System.out.println(stb.toString());
		List<Object> entidadesOrigem;		
		while (i < x) {
			try {
				if (i > 0){ System.out.println("" + i + " linhas atualizadas");}
				entidadesOrigem = genericDao.obter(classe, i, 500);			
				List<Object> entidadesPersistir = new ArrayList<>();
				String tipo = classe.getSimpleName().toLowerCase();				
				Class classePersistir = null;	
				Gson gson = new Gson();

				switch (tipo) {
				case "cabecalhonotafiscal": classePersistir = MSCabecalhoNotaFiscal.class; break;
				case "itemnotafiscal": classePersistir = MSItemNotaFiscal.class; break;
				case "parceiro": classePersistir = MSParceiro.class; break;
				case "produto": classePersistir = MSProduto.class; break;
				case "tipovenda": classePersistir = MSTipoVenda.class; break;
				case "tipovolume": classePersistir = MSTipoVolume.class; break;
				case "vendedor": classePersistir = MSVendedor.class; break;
				default: break;
				}					
				
				if (classePersistir == null) {
					return "Não foi possível determinar o tipo de entidade a ser persistida. Classe de origem:" + classe.getSimpleName();
				}

				for (Object object : entidadesOrigem) {
					Object entidadePersistir = gson.fromJson(gson.toJson(object), classePersistir); 
					if (entidadePersistir != null) {
						entidadesPersistir.add(entidadePersistir);
					}						
				}				
				genericDao.atualizaDados(entidadesPersistir);
				i = i + 500;
				
			} catch (Exception e) {
				System.out.println("Houve uma falha ao atualizar registros. Registros atualizados até o momento: " + i);
				e.printStackTrace();
			}			
		}

		
		stb.append(" Registros atualizados: ").append(i > x ? x : i);
		System.out.println(stb.toString());
		return stb.toString();
	}
	

}
