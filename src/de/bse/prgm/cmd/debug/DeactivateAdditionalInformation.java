package de.bse.prgm.cmd.debug;

import de.bse.prgm.cmd.ICommand;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

/**
 * Activates the output of additional information about the commands at runtime.
 * This is not a build-in command of the bs1.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class DeactivateAdditionalInformation implements ICommand {

  @Override
  public void execute(Machine machine, IConsole console) {
    machine.getSettings().deactivateInfo();
    // no if because alloc is faster than check
  }

  @Override
  public String infoMsg() {
    return "[Info]Deactivating additional information";
  }

}
