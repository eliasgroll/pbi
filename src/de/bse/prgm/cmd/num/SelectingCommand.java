package de.bse.prgm.cmd.num;

import de.bse.prgm.cmd.storage.AccessCommand;

/**
 * Used to minimize code duplicates in commands, which do something with a location, a variable and
 * a list.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public abstract class SelectingCommand extends AccessCommand {

  public SelectingCommand(String locationString, String varString, long[] values) {
    super(locationString, varString);
    this.values = values;
  }

  protected final long[] values;

}
