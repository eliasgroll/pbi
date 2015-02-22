package de.bse.prgm.cmd.num;

import de.bse.prgm.cmd.storage.AccessCommand;

public abstract class SelectingCommand extends AccessCommand {

  public SelectingCommand(String locationString, String varString, long[] values) {
    super(locationString, varString);
    this.values = values;
  }

  protected final long[] values;

}
