package lf2.plp.functional2.excecao;

import lf2.plp.functional2.expression.Arg;

public class ArgumentoRequeridoAposOpcionalException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArgumentoRequeridoAposOpcionalException(Arg arg){
		super("N�o � poss�vel especificar o par�metro requerido " + arg + " ap�s a especifica��o de par�metros opcionais.");
	}
}
