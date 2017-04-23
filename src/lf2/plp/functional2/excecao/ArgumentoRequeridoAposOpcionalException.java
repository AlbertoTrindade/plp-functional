package lf2.plp.functional2.excecao;

import lf2.plp.functional2.expression.Arg;

public class ArgumentoRequeridoAposOpcionalException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArgumentoRequeridoAposOpcionalException(Arg arg){
		super("Não é possível especificar o parâmetro requerido " + arg + " após a especificação de parâmetros opcionais.");
	}
}
