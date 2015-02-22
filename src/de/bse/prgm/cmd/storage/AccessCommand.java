package de.bse.prgm.cmd.storage;

import de.bse.prgm.cmd.HotspotCompiledCommand;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.IVariable;

public abstract class AccessCommand extends HotspotCompiledCommand {

  public AccessCommand(String locationString, String varString) {
    this.locationString = locationString;
    this.varString = varString;
  }

  protected final String locationString;
  protected final String varString;
  protected IVariable location;
  protected IVariable var;

  protected void initVars(Machine machine, IConsole console) {
    super.execute(machine, console);
  }

  @Override
  public void init(Machine machine) {
    var = machine.parseIVariable(varString);
    location = machine.parseIVariable(locationString);
  }

}
