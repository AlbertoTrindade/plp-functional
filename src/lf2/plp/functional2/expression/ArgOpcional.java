package lf2.plp.functional2.expression;

import lf2.plp.expressions2.expression.Expressao;
import lf2.plp.expressions2.expression.Id;

public class ArgOpcional extends Arg {
	
	private Expressao valorPadrao;

	public ArgOpcional(Id argId, Expressao valorPadrao) {
		setArgId(argId);
		this.valorPadrao = valorPadrao;
	}
	
	public Expressao getValorPadrao() {
		return valorPadrao;
	}

	public void setValorPadrao(Expressao valorPadrao) {
		this.valorPadrao = valorPadrao;
	}
	
	@Override
	public String toString() {
		return getArgId() + "?(" + valorPadrao + ")";
	}
}
