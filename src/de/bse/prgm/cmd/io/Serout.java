package de.bse.prgm.cmd.io;

import de.bse.prgm.cmd.ICommand;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.format.Formatter;

public class Serout implements ICommand {

  private final int pin;
  private final String baudmode;
  private final String[] data;
  private String dataString = "";
  /**
   * Stores all available Formatters for DEBUG as a regex group.
   */
  private String allFormatters = "[#%$@]";
  
  public Serout(int pin, String baudmode, String[] data) {
    this.pin = pin;
    this.baudmode = baudmode;
    this.data = data;
    for (String datum : data) {
      dataString += datum + " ";
    }
  }
  
  @Override
  public void execute(Machine machine, IConsole console) {
    String result = "[SEROUT] ";
    for (String string : data) {
      result += evaluate(string, machine,console);
    }
    console.printLn(result);
  }

  private String evaluate(String string, Machine machine,IConsole console) {    
    string = string.trim();
    if (string.startsWith("\"") && string.endsWith("\"")) {
      string = string.substring(1, string.length());
      string = string.substring(0, string.length()- 1);
      return string;
    } else if (string.equals("CR")) {
      return System.lineSeparator();
    } else if (string.equals("EEPROM")) {
      return machine.getEeprom().toString();
    }else if(string.equals("CLS")){
      console.clearConsole();
      return "";
    }
    else {
      return createVarInfo(string, machine.parseIVariable(string.replaceAll(allFormatters, "")).getValue(), machine);
    }
  }

  private String createVarInfo(String string, long num, Machine machine) {
    String retString = "";

//  Formatter formatter = null;

  if (!(string.contains("#"))) {
    retString += string.replaceAll(machine.getAllFormattersAndHashTag(), "") + " = ";
  } else {
    string = string.replaceAll("#", "").trim();
  }
  if (string.contains("@")) {
//    formatter = new AsciiFormatter(String.valueOf(num));
    retString += Formatter.convertToFormattedAsciiString(num);
  } else if (string.contains("$")) {
//    formatter = new HexFormatter(String.valueOf(num));
    retString += Formatter.convertToFormattedHexString(num);
  } else if (string.contains("%")) {
//    formatter = new BinFormatter(String.valueOf(num));
    retString += Formatter.convertToBinaryString(num);
  } else {
    retString += String.valueOf(num);
  }
//  try {
//    retString += formatter.convertToString();
//  } catch (NullPointerException e) {
//    retString += String.valueOf(num);
//  }

  return retString;
  }
  
  @Override
  public String infoMsg() {
    String infoMsg = "Sending the following data on pin " + String.valueOf(pin)
        + " (with baud-rate " + baudmode + "): " + dataString;
    
    return infoMsg;
  }

}
