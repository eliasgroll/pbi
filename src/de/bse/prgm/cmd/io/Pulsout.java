package de.bse.prgm.cmd.io;

import de.bse.prgm.cmd.ICommand;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Pulsout implements ICommand {

  
  public void execute(Machine machine, IConsole console) {
    // pulsout can not be supported by pbi
  }

  
  public String infoMsg() {
    return "[Info]Placeholder for \"PULSOUT\"";
  }

}
