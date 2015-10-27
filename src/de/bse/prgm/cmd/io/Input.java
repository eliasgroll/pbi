package de.bse.prgm.cmd.io;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Input extends IOCommand {

  public Input(String num) {
    super(num);
  }

  
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (dir != null) {
      dir.setValue(0);
    }
  }

  
  public String infoMsg() {
    return "[Info]DIR" + num + " is now set to 0(INPUT)";
  }

  
  public String toString() {
    return "INPUT";
  }

}
