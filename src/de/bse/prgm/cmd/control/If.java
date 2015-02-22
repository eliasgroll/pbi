package de.bse.prgm.cmd.control;

import de.bse.prgm.cmd.HotspotCompiledCommand;
import de.bse.prgm.parse.relational.RelationParser;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.IVariable;

/**
 * Evaluates an expression and goes to a label if the expression is true.
 * @author Elias Groll
 * @version 2.15
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

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (relation.getValue() != 0) {
      jump.execute(machine, console);
    }

  }

  @Override
  public String infoMsg() {
    return "[Info]If " + expression + " then resume after \"" + jump.getReference()
        + "\"";
  }

  @Override
  public String toString() {
    return "IF";
  }

  @Override
  protected void init(Machine machine) {
    relation = rp.parseRelationalOperation(expression, machine);
  }
}
