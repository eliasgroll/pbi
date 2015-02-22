package de.bse.prgm.cmd.io;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Output extends IOCommand {

  public Output(String num) {
    super(num);
  }

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (dir != null) {
      dir.setValue(1);
    }
  }

  @Override
  public String infoMsg() {
    return "[Info]DIR" + num + " is now set to 1(OUTPUT)";
  }

  @Override
  public String toString() {
    return "OUTPUT";
  }

}
