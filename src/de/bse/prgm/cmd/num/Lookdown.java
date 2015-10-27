package de.bse.prgm.cmd.num;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

/**
 * Retrieves the index of an item's value in the list.
 * 
 * @author Jonas Reichmann
 * @version 2.15
 */
public class Lookdown extends SelectingCommand {

  public Lookdown(String locationString, long[] values, String varString) {
    super(locationString, varString, values);
  }

  
  public void execute(Machine machine, IConsole console) {
    initVars(machine, console);
    for (int i = 0; i < values.length; i++) {
      if (location.getValue() == i) {
        var.setValue(i);
      }
    }
  }

  
  public String infoMsg() {
    String infoMsg = "Searching for " + locationString + " in a list containing the values ";
    for (int i = 0; i < values.length; i++) {
      infoMsg += String.valueOf(values[i]) + " ";
    }
    return infoMsg;
  }

}
