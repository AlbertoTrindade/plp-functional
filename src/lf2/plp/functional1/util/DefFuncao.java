package lf2.plp.functional1.util;

import java.util.ArrayList;
import java.util.List;

import lf2.plp.expressions1.util.Tipo;
import lf2.plp.expressions2.expression.Expressao;
import lf2.plp.expressions2.expression.Id;
import lf2.plp.expressions2.memory.AmbienteCompilacao;
import lf2.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf2.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf2.plp.functional2.expression.Arg;
import lf2.plp.functional2.expression.ArgOpcional;

public class DefFuncao {

	protected List<Arg> args;

	protected Expressao exp;

	public DefFuncao(List<Arg> args, Expressao exp) {
		this.args = args;
		this.exp = exp;
	}

	public List<Arg> getListaArg() {
		return args;
	}

	public Expressao getExp() {
		return exp;
	}

	/**
	 * Retorna a aridade desta funcao.
	 * 
	 * @return a aridade desta funcao.
	 */
	public int getAridade() {
		return args.size();
	}

	/**
	 * Realiza a verificacao de tipos desta declara��o.
	 * 
	 * @param amb
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *         <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador declarado mais de uma vez no
	 *                mesmo bloco do ambiente.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		ambiente.incrementa();
		
		boolean temArgumentosOpcionais = false;
		boolean argumentosOpcionaisDireita = true;

		// Usa uma inst�ncia de TipoQualquer para cada par�metro formal.
		// Essa inst�ncia ser� inferida durante o getTipo de exp.
		for (Arg arg : args) {
			Id id = arg.getArgId();
			ambiente.map(id, new TipoPolimorfico());
			
			if (arg instanceof ArgOpcional) {
				temArgumentosOpcionais = true;
			}
			else if (temArgumentosOpcionais) {
				argumentosOpcionaisDireita = false;
			}
		}
		
		if (!argumentosOpcionaisDireita) {
			ambiente.restaura();
			
			return false;
		}

		// Chama o checa tipo da express�o para veririficar se o corpo da
		// fun��o est� correto. Isto ir� inferir o tipo dos par�metros.
		boolean result = exp.checaTipo(ambiente);

		ambiente.restaura();

		return result;
	}

	/**
	 * Retorna os tipos possiveis desta fun��o.
	 * 
	 * @param amb
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            tipos.
	 * @return os tipos possiveis desta declara��o.
	 * @exception VariavelNaoDeclaradaException
	 *                se houver uma vari&aacute;vel n&atilde;o declarada no
	 *                ambiente.
	 * @exception VariavelJaDeclaradaException
	 *                se houver uma mesma vari&aacute;vel declarada duas vezes
	 *                no mesmo bloco do ambiente.
	 * @precondition exp.checaTipo();
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		ambiente.incrementa();

		for (Arg arg : args) {
			Id id = arg.getArgId();
			ambiente.map(id, new TipoPolimorfico());
		}

		// Usa o checaTipo apenas para inferir o tipo dos par�metros.
		// Pois o getTipo da express�o pode simplismente retornar o
		// tipo, por exemplo, no caso de uma express�o bin�ria ou un�ria
		// os tipos sempre s�o bem definidos (Booleano, Inteiro ou String).
		exp.checaTipo(ambiente);

		// Comp�e o tipo desta fun��o do resultado para o primeiro par�metro.
		Tipo result = exp.getTipo(ambiente);

		// Obt�m o tipo inferido de cada par�metro.
		List<Tipo> params = new ArrayList<Tipo>(getAridade());
		Tipo argTipo;
		for (int i = 0; i < getAridade(); i++) {
			argTipo = ((TipoPolimorfico) ambiente.get(args.get(i).getArgId())).inferir();
			params.add(argTipo);
		}
		result = new TipoFuncao(params, result);

		ambiente.restaura();

		return result;
	}
	
	public DefFuncao clone() {
		List<Arg> novaLista = new ArrayList<Arg>(this.args.size());
		
		for (Arg arg : this.args){
			novaLista.add(arg.clone());
		}
		
		return new DefFuncao(novaLista, this.exp.clone());
	}
}
