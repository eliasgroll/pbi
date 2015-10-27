package de.bse.prgm.cmd.io;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Low extends IOCommand {

  public Low(String num) {
    super(num);
  }

  
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (pin != null && dir != null) {
      pin.setValue(0);
      dir.setValue(1);
    }
  }

  
  public String infoMsg() {
    return "[Info]PIN" + num + " and DIR" + num
        + " are now set to 0(LOW) and 1(OUTPUT)";
  }

  
  public String toString() {
    return "LOW";
  }
}
