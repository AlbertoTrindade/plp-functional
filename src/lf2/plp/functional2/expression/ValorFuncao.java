package lf2.plp.functional2.expression;

import static lf2.plp.expressions1.util.ToStringProvider.listToString;

import java.util.ArrayList;
import java.util.List;

import lf2.plp.expressions2.expression.Expressao;
import lf2.plp.expressions2.expression.Id;
import lf2.plp.expressions2.expression.Valor;
import lf2.plp.expressions2.memory.AmbienteExecucao;
import lf2.plp.functional1.util.DefFuncao;

/**
 * @author S�rgio
 */
public class ValorFuncao extends DefFuncao implements ValorAbstrato {

	private Id id;

	public ValorFuncao(List<Arg> args, Expressao exp) {
		super(args, exp);
	}

	public Valor avaliar(AmbienteExecucao ambiente) {
		this.reduzir(ambiente);
		return this;
	}

	@Override
	public String toString() {
	
		return String.format("fn %s . %s", listToString(getListaArg(), " "),
				getExp());
	}
	
	public Id getId() {
		return this.id;
	}
	
	public void setId (Id id){
		this.id = id;
	}
	
	public Expressao reduzir(AmbienteExecucao ambiente) {
		ambiente.incrementa();

		if(this.id != null){
			ambiente.map(this.id, new ValorIrredutivel());
		}
		
		for(Arg arg : this.args){
			Id id = arg.getArgId();
			ambiente.map(id, new ValorIrredutivel());
			
			if (arg instanceof ArgOpcional) {
				ArgOpcional argOpcional = (ArgOpcional) arg;
				argOpcional.setValorPadrao(argOpcional.getValorPadrao().reduzir(ambiente));
			}
		}
		this.exp = exp.reduzir(ambiente);
		
		ambiente.restaura();
		
		return this;
	}
	
	public ValorFuncao clone() {
		ValorFuncao retorno;
		List<Arg> novaLista = new ArrayList<Arg>(this.args.size());
		
		for (Arg arg : this.args) {
			novaLista.add(arg.clone());
		}
		
		retorno = new ValorFuncao(novaLista, this.exp.clone());
		
		if (this.id != null)
			retorno.setId(this.id.clone());
		
		return retorno;
	}
}
