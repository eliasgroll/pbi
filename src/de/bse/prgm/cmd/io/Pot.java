package de.bse.prgm.cmd.io;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Pot extends AnalogSimulationCommand {

  private final Input input;

  public Pot(String pin, String varString, String scale) {
    super(pin, varString, scale);
    input = new Input(pin);
  }

  
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    input.execute(machine, console);
  }

  
  public String infoMsg() {
    return "[Info]Simulate a potential-measurement on PIN" + pin.getValue();
  }

}
