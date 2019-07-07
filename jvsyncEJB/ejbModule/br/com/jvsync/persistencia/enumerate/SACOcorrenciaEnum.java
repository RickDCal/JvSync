package br.com.jvsync.persistencia.enumerate;

public enum SACOcorrenciaEnum {
	
 NENHUM(0), CADASTRADA(1), SOLUCIONADA(2), ANALISE(3), AGUARDA_SOLUCAO(4), 
 FEEDBACK(5), EXECUCAO(6), AGRUPADA(7), REMOVIDA(8);
 
 private int code;
 private SACOcorrenciaEnum (int c) {
	 code = c;
 }
 
 public int getCode() {
	 return code;
 }

public static SACOcorrenciaEnum getEnum(int code) {
	switch (code) {
	case 1: return SACOcorrenciaEnum.CADASTRADA;
	case 2: return SACOcorrenciaEnum.SOLUCIONADA;
	case 3: return SACOcorrenciaEnum.ANALISE;
	case 4: return SACOcorrenciaEnum.AGUARDA_SOLUCAO;
	case 5: return SACOcorrenciaEnum.FEEDBACK;
	case 6: return SACOcorrenciaEnum.EXECUCAO;
	case 7: return SACOcorrenciaEnum.AGRUPADA;
	case 8: return SACOcorrenciaEnum.REMOVIDA;	
	default: return null;
	}
} 

}
