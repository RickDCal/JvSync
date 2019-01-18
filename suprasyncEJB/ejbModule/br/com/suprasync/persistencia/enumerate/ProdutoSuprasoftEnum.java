/**
 * 
 */
package br.com.suprasync.persistencia.enumerate;

/**
 * @author Ricardo Dias
 *
 */
public enum ProdutoSuprasoftEnum {
	N_(0, "Nenhum"),
	SUPRA_MAIS(1, "SupraMAIS"),
	SUPRA_WEB(2, "SupraWEB"),
	SUPRA_MOBILE(3, "SupraMobile"),
	SUPRA_PCP(4, "SupraPCP");
	
	private ProdutoSuprasoftEnum(Integer value, String descricao) {
		this.value = value;
		this.descricao = descricao;
	}

	private Integer value;
	private String descricao;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}
}
