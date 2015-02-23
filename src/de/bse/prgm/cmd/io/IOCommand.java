package de.bse.prgm.cmd.io;

import de.bse.prgm.cmd.HotspotCompiledCommand;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.IVariable;

/**
 * Input/Output Command on the BS1 which uses HotspotCompilation.
 * @author Elias Groll
 * @version 2.15
 */
public abstract class IOCommand extends HotspotCompiledCommand {

  protected final String num;

  protected IVariable pin;
  protected IVariable dir;
  private IVariable check;

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
  }

  @Override
  protected void init(Machine machine) {
    check = machine.parseIVariable(num);
    pin = machine.parseIVariable("PIN" + check.getValue());
    dir = machine.parseIVariable("DIR" + check.getValue());
  }

  public IOCommand(String num) {
    this.num = num;
  }

}
