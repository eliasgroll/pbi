package de.bse.prgm.cmd.time;

import de.bse.prgm.cmd.HotspotCompiledCommand;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.IVariable;

/**
 * Pauses the execution (time in [ms]).
 * 
 * @author Jonas Reichmann
 * @version 2.15
 */
public class Pause extends HotspotCompiledCommand {

  public Pause(String time2) {
    this.timeString = time2;
    this.multiplier = 1;
  }

  public Pause(String time2, int multiplier) {
    this.timeString = time2;
    this.multiplier = multiplier;
  }

  protected long time;
  private IVariable timeVar;
  private final String timeString;
  private final long multiplier;

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    time = timeVar.getValue() * multiplier;
    try {
      Thread.sleep((long) time);
    } catch (InterruptedException e) {
      // a failed pause is not important for the program execution - do nothing
    }

  }

  @Override
  public String infoMsg() {
    return "[Info]Paused the execution for " + time + " milliseconds";
  }

  @Override
  public String toString() {
    return "PAUSE";
  }

  @Override
  protected void init(Machine machine) {
    timeVar = machine.parseIVariable(timeString);
  }

}
