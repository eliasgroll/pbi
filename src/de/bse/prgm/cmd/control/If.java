package de.bse.prgm.cmd.control;

import de.bse.prgm.cmd.HotspotCompiledCommand;
import de.bse.prgm.err.runtime.ExpressionUnparseableRuntimeError;
import de.bse.prgm.parse.relational.RelationParser;
import de.bse.run.app.IConsole;
import de.bse.util.ParserException;
import de.bse.vm.Machine;
import de.bse.vm.var.IVariable;

/**
 * Evaluates an expression and goes to a label if the expression is true.
 * 
 * @author Elias Groll
 * @version 10.15
 */
public class If extends HotspotCompiledCommand {
	String expression;
	Goto jump;
	IVariable relation;
	RelationParser rp = new RelationParser();

	public If(String expression, String label) {
		this.expression = expression;
		this.jump = new Goto(label);
	}

	public void execute(Machine machine, IConsole console) {
		super.execute(machine, console);
		if (relation.getValue() != 0) {
			jump.execute(machine, console);
		}

	}

	public String infoMsg() {
		return "[Info]If " + expression + " then resume after \""
				+ jump.getReference() + "\"";
	}

	public String toString() {
		return "IF";
	}

	protected void init(Machine machine) {
		try {
			relation = rp.parseRelationalOperation(expression, machine);
		} catch (ParserException e) {
			machine.getProgram().addError(
					new ExpressionUnparseableRuntimeError(expression));
		}
	}
}
