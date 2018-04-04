package br.com.suprasync.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name="funcionario")// o nome da tabela correspondente quando for entity
public class Usuario {

	private int id;	
			
	public Usuario() {
		
	}
	
	@Id //indica chave prim�ria
	@Column(name="codigo")/*este valor corresponde ao func_codigo do usuario do supra*/
	//@GeneratedValue(strategy = GenerationType.IDENTITY) //auto incermento na chave
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
