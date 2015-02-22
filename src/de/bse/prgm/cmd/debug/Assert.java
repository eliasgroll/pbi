package de.bse.prgm.cmd.debug;

import de.bse.prgm.cmd.HotspotCompiledCommand;
import de.bse.prgm.err.runtime.AssertionFailedRuntimeError;
import de.bse.prgm.parse.relational.RelationParser;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.IVariable;

/**
 * Throws an error at runtime if the evaluated expression is false. This is not
 * a build-in command of the bs1.
 * 
 * @author Elias Groll
 * @version 2.15
 * 
 */
public class Assert extends HotspotCompiledCommand {

  /**
   * Creates a new Assertion.
   * 
   * @param expressionString
   *          the evaluated expression
   * @param lineNumber
   *          used to display the line, in which the assertion failed.
   */
  public Assert(String expressionString, int lineNumber) {
    this.expressionString = expressionString;
    this.lineNumber = lineNumber;
  }

  private final String expressionString;
  private IVariable expression;
  private final int lineNumber;

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (expression == null || expression.getValue() == 0) {
      machine.getProgram().addError(
          new AssertionFailedRuntimeError(lineNumber, expressionString));
    }
  }

  @Override
  public String infoMsg() {
    return "[Info]Assert \"" + expressionString + "\"";
  }

  @Override
  protected void init(Machine machine) {
    RelationParser parser = new RelationParser();
    try {
      expression = parser.parseRelationalOperation(expressionString, machine);
    } catch (Exception e) {
      expression = null;
    }
  }

}
