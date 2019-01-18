package br.com.suprasync.persistencia.enumerate;

import java.util.Arrays;
import java.util.List;


public enum TempoEnum {

	ANO("ANO", "ano", 1), 
	MES("MES", "mês", 2), 
	DIA("DIA", "dia", 3), 
	HORA("HORA", "hora", 4),
	MINUTO("MINUTO", "minuto", 5),
	SEGUNDO("SEGUNDO", "Segundo", 6),
	MILISSEGUNDO("MILISSEGUNDO", "milissegundo", 7);

	private TempoEnum(String value, String descricao, int codigo) {
		this.value = value;
		this.descricao = descricao;
		this.codigo = codigo;
	}

	private String value;
	private String descricao;
	private int codigo;

	public int getCodigo() {
		return codigo;
	}

	public String getValue() {
		return value;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static String[] getValues() {

		String[] ids = new String[values().length];

		int i = 0;
		for (TempoEnum tipoEnum : values()) {
			ids[i] = (tipoEnum.descricao);
			i++;
		}
		return ids;
	}
	
	public static List<TempoEnum> getListEnum(){
		return Arrays.asList(values());
	}
	
	public static String findDescricao(int value){
		for (TempoEnum tipoEnum : values()) {
			if(tipoEnum.codigo == value){
				return tipoEnum.descricao; 
			}
		}
		return "";
	}
	
	public static TempoEnum findEnumCodigo(int value){
		for (TempoEnum tipoEnum : values()) {
			if(tipoEnum.codigo == value){
				return tipoEnum; 
			}
		}
		return null;
	}
	
	 @Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}

}
