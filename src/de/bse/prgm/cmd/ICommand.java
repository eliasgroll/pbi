package de.bse.prgm.cmd;

import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

/**
 * The basic struct of a command in the PiBasicInterpreter
 * 
 * @author Elias Groll, Jonas Reichmann
 * @version 2.15
 */
public interface ICommand {

  /**
   * Execution of a single cmd.
   * 
   * @param machine
   *          the machine on which the cmd acts
   * @param console
   *          the console which is used by the cmd to interact with the user
   */
  public void execute(Machine machine, IConsole console);

  /**
   * Explanation of the actual- executed cmd.
   * 
   * @return explanation as String with the format "[Info]*"
   */
  public String infoMsg();
}
