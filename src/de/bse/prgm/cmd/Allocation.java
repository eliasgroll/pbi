package de.bse.prgm.cmd;

import de.bse.prgm.err.runtime.CannotChangeAConstantValueRuntimeError;
import de.bse.prgm.err.runtime.InvalidAllocationRuntimeError;
import de.bse.prgm.parse.arithmetical.ArithmeticalParser;
import de.bse.run.app.IConsole;
import de.bse.util.ParserException;
import de.bse.vm.Machine;
import de.bse.vm.var.IVariable;

/**
 * Allocates the value of an expression to a variable.
 * @author Elias Groll
 * @version 2.15
 */

public class Allocation extends HotspotCompiledCommand {

  public Allocation(String varString, String calcString) {
    this.calcString = calcString;
    this.varString = varString;
  }

  private String varString;
  private String calcString;
  private IVariable var;
  private IVariable calc;
  private ArithmeticalParser parser = new ArithmeticalParser();

  
  public void execute(Machine machine, IConsole console) {
    super.execute(machine,console);
    if (var != null && calc != null) {
      try {
   
        var.setValue(calc.getValue());
      } catch (ParserException e) {
        machine.getProgram().addError(new CannotChangeAConstantValueRuntimeError(
            varString));
      }
    }
  }

  public String getVarAsString() {
    return varString;
  }

  
  public String infoMsg() {
    return "[Info]Allocating \"" + calcString + "\" to \"" + varString + "\"";
  }

  
  public String toString() {
    return "ALLOCATION";
  }

  public IVariable getVar() {
    return var;
  }

  
  protected void init(Machine machine) {
    try {
      var = machine.parseIVariable(varString);
      calc = parser.parseArithmeticalOperation(calcString, machine);
    } catch (ParserException e) {
      machine.getProgram().addError(new InvalidAllocationRuntimeError(varString
          + " = " + calcString));
    }    
  }

}
