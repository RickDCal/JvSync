package br.com.jvsync.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="jvsync_relacionamento_produto")
public class RelacionamentoProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "int")
	private int id;
	
	@Column(name="codigo_jiva", columnDefinition = "nvarchar")
	private String codigoJiva;
	
	@Column(name="codigo_aliar", columnDefinition = "nvarchar")
	private String codigoAliar;
	
	@OneToOne
	@JoinColumn(name="tipmov_jiva", columnDefinition = "nvarchar")
	private TipoMovimentoJiva tipoMovimentoJiva;
	
	public RelacionamentoProduto() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoJiva() {
		return codigoJiva;
	}

	public void setCodigoJiva(String codigoJiva) {
		this.codigoJiva = codigoJiva;
	}

	public String getCodigoAliar() {
		return codigoAliar;
	}

	public void setCodigoAliar(String codigoAliar) {
		this.codigoAliar = codigoAliar;
	}

	public TipoMovimentoJiva getTipoMovimentoJiva() {
		return tipoMovimentoJiva;
	}

	public void setTipoMovimentoJiva(TipoMovimentoJiva tipoMovimentoJiva) {
		this.tipoMovimentoJiva = tipoMovimentoJiva;
	}

	@Override
	public String toString() {
		return "RelacionamentoProduto [id=" + id + ", codigoJiva=" + codigoJiva + ", codigoAliar=" + codigoAliar + "]";
	}
	

}
