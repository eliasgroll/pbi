package de.bse.prgm.cmd.storage;

import de.bse.prgm.war.MemOverwriteWarning;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

/**
 * Writes a value to a location in EEPROM and stores it into a variable.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Write extends AccessCommand {

  public Write(String locationString, String varString) {
    super(locationString, varString);
  }

  @Override
  public void execute(Machine machine, IConsole console) {
    initVars(machine, console);
    try {
      if (machine.getEeprom().read(location.getValue()) == de.bse.vm.storage.EEPROM.CMD) {
        machine.getProgram().addWarning(new MemOverwriteWarning());
      }
      machine.getEeprom().write(location.getValue(), var.getValue());
    } catch (NullPointerException e) {
      // nothing to do here, machine already added an error
    }
  }

  @Override
  public String infoMsg() {
    return "[Info]Write at \"" + locationString + "\"  and store \"" + varString + "\" in EEPROM";
  }

}
