package de.bse.prgm.cmd;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

/**
 * The "stamp" used to verify that we are parsing a bs1 program.
 * @author Elias Groll
 * @version 2.15
 */
public class Shebang implements ICommand {

  @Override
  public void execute(Machine machine, IConsole console) {
    // does nothing
  }

  @Override
  public String infoMsg() {
    return "[Info]Shebang succesfully detected";
  }

  @Override
  public String toString() {
    return "STAMP";
  }

}
