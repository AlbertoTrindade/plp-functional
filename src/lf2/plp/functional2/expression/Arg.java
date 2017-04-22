package lf2.plp.functional2.expression;

import lf2.plp.expressions2.expression.Expressao;
import lf2.plp.expressions2.expression.Id;

public abstract class Arg implements Expressao {
	
	private Id argId;
	
	public Id getArgId() {
		return argId;
	}

	public void setArgId(Id argId) {
		this.argId = argId;
	}	

	@Override
	public Expressao clone() {
		return this;
	}

}
