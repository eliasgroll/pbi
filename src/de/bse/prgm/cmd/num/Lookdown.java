package de.bse.prgm.cmd.num;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Lookdown extends SelectingCommand {

  public Lookdown(String locationString, long[] values, String varString) {
    super(locationString, varString, values);
  }


  @Override
  public void execute(Machine machine, IConsole console) {
    initVars(machine, console);
    for (int i = 0; i < values.length; i++) {
      if (location.getValue() == i) {
        var.setValue(i);
      }
    }
  }

  @Override
  public String infoMsg() {
    String infoMsg = "Searching for " + locationString
        + " in a list containing the values ";
    for (int i = 0; i < values.length; i++) {
      infoMsg += String.valueOf(values[i]) + " ";
    }
    return infoMsg;
  }

}
