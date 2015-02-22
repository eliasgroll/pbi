package de.bse.prgm.cmd.num;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Lookup extends SelectingCommand {

  public Lookup(String locationString, String varString, long[] values) {
    super(locationString, varString, values);
  }

  @Override
  public void execute(Machine machine, IConsole console) {
    initVars(machine, console);
    if (location.getValue() < values.length) {
      var.setValue(values[(int) location.getValue()]);
    }
  }

  @Override
  public String infoMsg() {
    String infoMsg = "Searching for the value at position " + locationString
        + " in a list containing the values ";
    for (int i = 0; i < values.length; i++) {
      infoMsg += String.valueOf(values[i]) + " ";
    }
    return infoMsg;
  }

}
