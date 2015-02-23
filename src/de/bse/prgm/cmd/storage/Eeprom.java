package de.bse.prgm.cmd.storage;

import de.bse.prgm.cmd.HotspotCompiledCommand;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.IVariable;

/**
 * Write data to the EEPROM.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Eeprom extends HotspotCompiledCommand {

  public Eeprom(String locationString, long[] values) {
    this.locationString = locationString;
    this.values = values;
  }

  private final String locationString;
  private IVariable location;
  private final long[] values;

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (location != null) {
      long tmp = location.getValue();
      for (int i = 0; i < values.length; i++) {
        machine.getEeprom().write(tmp + i, values[i]);
      }
    }
  }

  @Override
  public String infoMsg() {
    return "[Info]Writing " + values.toString() + " at \"" + locationString + "\" to EEPROM";
  }

  @Override
  public void init(Machine machine) {
    location = machine.parseIVariable(locationString);
  }

}
