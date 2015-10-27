package de.bse.prgm.cmd.io;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Reverse extends IOCommand {

  public Reverse(String num) {
    super(num);
  }

  
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (dir != null) {
      dir.setValue(dir.getValue() == 1 ? 0 : 1);
    }
  }

  
  public String infoMsg() {
    return "[Info]Reversed direction DIR" + num;
  }

}
