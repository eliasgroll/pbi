package de.bse.prgm.cmd.io;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Toggle extends IOCommand {

  public Toggle(String num) {
    super(num);
  }

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (pin != null && dir != null) {
      pin.setValue(pin.getValue() == 1 ? 0 : 1);
      dir.setValue(1);
    }
  }

  @Override
  public String infoMsg() {
    return "[Info]PIN" + num + " changed it's state and DIR" + num
        + " is now set to 1(OUTPUT)";
  }

  @Override
  public String toString() {
    return "TOGGLE";
  }
}
