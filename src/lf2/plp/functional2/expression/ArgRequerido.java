package lf2.plp.functional2.expression;

import lf2.plp.expressions1.util.Tipo;
import lf2.plp.expressions2.expression.Expressao;
import lf2.plp.expressions2.expression.Id;
import lf2.plp.expressions2.expression.Valor;
import lf2.plp.expressions2.memory.AmbienteCompilacao;
import lf2.plp.expressions2.memory.AmbienteExecucao;
import lf2.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf2.plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ArgRequerido extends Arg {
	
	public ArgRequerido(Id argId) {
		setArgId(argId);
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expressao reduzir(AmbienteExecucao ambiente) {
		// TODO Auto-generated method stub
		return null;
	}
}
