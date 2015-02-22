package de.bse.prgm.cmd.debug;

import de.bse.prgm.cmd.ICommand;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.format.Formatter;

/**
 * Formats and prints its arguments to the console.
 * 
 * @author Jonas Reichmann
 * @version 2.15
 */
public class Debug implements ICommand {

  private final String[] args;
  private String allArgs = "";

  /**
   * Create a new Debug.
   * 
   * @param args
   *          elements can be string, a variable or a constant. Variables and
   *          constants can be formatted as ASCII, binary number or hex number.
   */
  public Debug(String[] args) {
    this.args = args;
    for (String arg : args) {
      allArgs += arg + " ";
    }
  }

  @Override
  public void execute(Machine machine, IConsole console) {
    String result = "";
    for (String string : args) {
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
          machine
              .parseIVariable(string.replaceAll(machine.getAllFormattersAndHashTag(), ""))
              .getValue(),
          machine);
    }
  }

  private String createVarInfo(String string, long num, Machine machine) {
    String retString = "";

//    Formatter formatter = null;

    if (!(string.contains("#"))) {
      retString += string.replaceAll(machine.getAllFormattersAndHashTag(), "") + " = ";
    } else {
      string = string.replaceAll("#", "").trim();
    }
    if (string.contains("@")) {
//      formatter = new AsciiFormatter(String.valueOf(num));
      retString += Formatter.convertToFormattedAsciiString(num);
    } else if (string.contains("$")) {
//      formatter = new HexFormatter(String.valueOf(num));
      retString += Formatter.convertToFormattedHexString(num);
    } else if (string.contains("%")) {
//      formatter = new BinFormatter(String.valueOf(num));
      retString += Formatter.convertToBinaryString(num);
    } else {
      retString += String.valueOf(num);
    }
//    try {
//      retString += formatter.convertToString();
//    } catch (NullPointerException e) {
//      retString += String.valueOf(num);
//    }

    return retString;
  }

  @Override
  public String infoMsg() {
    return "[Info]DEBUG-Message has the format: " + allArgs;
  }

  @Override
  public String toString() {
    return "DEBUG";
  }
}
