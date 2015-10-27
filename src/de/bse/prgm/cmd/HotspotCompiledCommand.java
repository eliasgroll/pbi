package de.bse.prgm.cmd;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

/**
 * A special ICommand which is generating an executable data-structure during
 * the first run.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public abstract class HotspotCompiledCommand implements ICommand {
  private boolean firstRun = true;

  
  public void execute(Machine machine, IConsole console) {
    if (firstRun) {
      init(machine);
      firstRun = false;
    }
  }

  /**
   * Initialise variables and create the executable structure.
   * 
   * @param machine
   *          needed to create the struct
   */
  protected abstract void init(Machine machine);

}
