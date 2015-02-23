package de.bse.prgm.cmd.num;

import de.bse.prgm.cmd.HotspotCompiledCommand;
import de.bse.prgm.err.runtime.CannotChangeAConstantValueRuntimeError;
import de.bse.run.app.IConsole;
import de.bse.util.ParserException;
import de.bse.vm.Machine;
import de.bse.vm.var.IVariable;

/**
 * Generates a pseudo-random value based on the value of a variable and stores the generated value
 * in the variable.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Random extends HotspotCompiledCommand {

  public Random(String varString) {
    super();
    this.varString = varString;
  }

  String varString;
  IVariable var;
  java.util.Random random;

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (var != null) {
      try {
        random.setSeed(var.getValue());
        var.setValue((short) random.nextInt());
      } catch (ParserException e) {
        machine.getProgram().addError(new CannotChangeAConstantValueRuntimeError(varString));
      }
    }
  }

  @Override
  public String infoMsg() {
    return "[Info]Generate a pseudo-random number on " + varString;
  }

  @Override
  public void init(Machine machine) {
    var = machine.parseIVariable(varString);
    random = new java.util.Random();
  }

}
