package de.bse.prgm.cmd.io;

import de.bse.prgm.err.runtime.CannotChangeAConstantValueRuntimeError;
import de.bse.run.app.IConsole;
import de.bse.util.ParserException;
import de.bse.vm.Machine;
import de.bse.vm.var.IVariable;

/**
 * Simulates an analog Command using a pin on the BS1 and
 * prompts for a fake value when executed.
 * @author Elias Groll
 * @version 2.15
 */
public abstract class AnalogSimulationCommand extends IOCommand {
  /**
   * Constructor of the general analog simulation command
   * which uses 3 values to simulate an analog command on the BS1.
   * @param num the pin the command shall run on
   * @param varString of the variable the simulation shall save its result
   * @param scale of the analog simulation (0-255)
   */
  public AnalogSimulationCommand(String num, String varString, String scale) {
    super(num);
    this.varString = varString;
    this.scaleString = scale;
  }

  private String varString;
  private String scaleString;
  protected IVariable var;
  protected IVariable scale;
  boolean firstRun = true;
  boolean done = false;

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (var != null) {
      try {
        console.printLn("[Input]An arbitrary value for simulation");
        var.setValue(Integer.parseInt(console.readLn().trim()));
        done = true;
      } catch (NumberFormatException e) {
        console.printLn("[Input]invalid - using 0 \n");
        var.setValue(0);
      } catch (ParserException e) {
        machine.getProgram().addError(
            new CannotChangeAConstantValueRuntimeError(varString));
      }
    }
  }

  @Override
  public void init(Machine machine) {
    super.init(machine);
    scale = machine.parseIVariable(scaleString);
    var = machine.parseIVariable(varString);
  }
}
