package de.bse.prgm.cmd.io;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class High extends IOCommand {

  public High(String num) {
    super(num);
  }

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (pin != null && dir != null) {
      pin.setValue(1);
      dir.setValue(1);
    }
  }

  @Override
  public String infoMsg() {
    return "[Info]PIN" + num + " and DIR" + num
        + " are now set to 1(HIGH) and 1(OUPUT)";
  }

  @Override
  public String toString() {
    return "HIGH";
  }
}
