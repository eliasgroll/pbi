package de.bse.prgm.cmd.io;

import de.bse.prgm.cmd.ICommand;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.format.Formatter;

/**
 * Constructor of the SEROUT command which gives out serial data
 * in the syntax of the DEBUG command using a pin and a baudRate.
 * @author Jonas Reichmann, Elias Groll
 * @version 2.15
 */
public class Serout implements ICommand {

  private final int pin;
  private final String baudmode;
  private final String[] data;
  private String dataString = "";
  /**
   * Stores all available Formatters for DEBUG as a regex group.
   */
  private String allFormatters = "[#%$@]";

  /**
   * Simulate serial out.
   * 
   * @param pin
   * @param baudmode
   * @param data
   */
  /**
   * Constructor of the SEROUT command which gives out serial data
   * in the syntax of the DEBUG command using a pin and a baudRate.
   * @param pin on which the serial data has to be sent
   * @param baudmode of the data to be sent
   * @param data to be transmitted, uses DEBUG syntax
   */
  public Serout(int pin, String baudmode, String[] data) {
    this.pin = pin;
    this.baudmode = baudmode;
    this.data = data;
    for (String datum : data) {
      dataString += datum + " ";
    }
  }

  
  public void execute(Machine machine, IConsole console) {
    String result = "[SEROUT] ";
    for (String string : data) {
      result += evaluate(string, machine, console);
    }
    console.printLn(result);
  }

  private String evaluate(String string, Machine machine, IConsole console) {
    string = string.trim();
    if (string.startsWith("\"") && string.endsWith("\"")) {
      string = string.substring(1, string.length());
      string = string.substring(0, string.length() - 1);
      return string;
    } else if (string.equals("CR")) {
      return System.lineSeparator();
    } else if (string.equals("EEPROM")) {
      return machine.getEeprom().toString();
    } else if (string.equals("CLS")) {
      console.clearConsole();
      return "";
    } else {
      return createVarInfo(string,
          machine.parseIVariable(string.replaceAll(allFormatters, "")).getValue(),
          machine);
    }
  }

  private String createVarInfo(String string, long num, Machine machine) {
    String retString = "";

    if (!(string.contains("#"))) {
      retString += string.replaceAll(machine.getAllFormattersAndHashTag(), "") + " = ";
    } else {
      string = string.replaceAll("#", "").trim();
    }
    if (string.contains("@")) {
      retString += Formatter.convertToFormattedAsciiString(num);
    } else if (string.contains("$")) {
      retString += Formatter.convertToFormattedHexString(num);
    } else if (string.contains("%")) {
      retString += Formatter.convertToBinaryString(num);
    } else {
      retString += String.valueOf(num);
    }
    
    return retString;
  }
  
  
  public String infoMsg() {
    String infoMsg = "Sending the following data on pin " + String.valueOf(pin)
        + " (with baud-rate " + baudmode + "): " + dataString;

    return infoMsg;
  }

}
