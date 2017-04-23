package lf2.plp.functional2.expression;

import lf2.plp.expressions2.expression.Id;

public class ArgRequerido extends Arg {
	
	public ArgRequerido(Id argId) {
		setArgId(argId);
	}
	
	@Override
	public String toString() {
		return getArgId().toString();
	}
}
