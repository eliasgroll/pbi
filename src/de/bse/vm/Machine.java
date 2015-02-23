package de.bse.vm;

import de.bse.prgm.err.runtime.BadFormatRuntimeError;
import de.bse.prgm.err.runtime.ConstantReferenceRuntimeError;
import de.bse.prgm.err.runtime.VariableNotFoundRuntimeError;
import de.bse.prgm.struct.Program;
import de.bse.vm.storage.EEPROM;
import de.bse.vm.var.Bit;
import de.bse.vm.var.Byte;
import de.bse.vm.var.Clock;
import de.bse.vm.var.Constant;
import de.bse.vm.var.IVariable;
import de.bse.vm.var.Word;
import de.bse.vm.var.format.Formatter;

import java.util.HashMap;
import java.util.Map;

/**
 * The vm of the emulated microcontroller. Manages all variables.
 * 
 * @author Elias Groll, Jonas Reichmann
 * @version 2.15
 */
public class Machine {
  private final Program program;
  private int executionIndex = 0;
  private final EEPROM eeprom;
  private final Settings settings;

  /**
   * Stores all available Formatters for DEBUG as a regex group.
   */
  private final String allFormatters = "[%$@]";

  /**
   * Stores all available Formatters and the HashTag for DEBUG as a regex group.
   */
  private final String allFormattersAndHashTag = "[#%$@]";

  private Bit BIT0 = new Bit(0), BIT1 = new Bit(0), BIT2 = new Bit(0), BIT3 = new Bit(0),
      BIT4 = new Bit(0), BIT5 = new Bit(0), BIT6 = new Bit(0), BIT7 = new Bit(0),
      BIT8 = new Bit(0), BIT9 = new Bit(0), BIT10 = new Bit(0), BIT11 = new Bit(0),
      BIT12 = new Bit(0), BIT13 = new Bit(0), BIT14 = new Bit(0), BIT15 = new Bit(0),
      PIN0 = new Bit(0), PIN1 = new Bit(0), PIN2 = new Bit(0), PIN3 = new Bit(0),
      PIN4 = new Bit(0), PIN5 = new Bit(0), PIN6 = new Bit(0), PIN7 = new Bit(0),
      DIR0 = new Bit(0), DIR1 = new Bit(0), DIR2 = new Bit(0), DIR3 = new Bit(0),
      DIR4 = new Bit(0), DIR5 = new Bit(0), DIR6 = new Bit(0), DIR7 = new Bit(0);

  private Byte PINS = new Byte(PIN7, PIN6, PIN5, PIN3, PIN3, PIN2, PIN1, PIN0);
  private Byte DIRS = new Byte(DIR7, DIR6, DIR5, DIR4, DIR3, DIR2, DIR1, DIR0);

  private Byte B0 = new Byte(BIT7, BIT6, BIT5, BIT4, BIT3, BIT2, BIT1, BIT0);
  private Byte B1 = new Byte(BIT14, BIT13, BIT12, BIT11, BIT10, BIT9, BIT8, BIT15);
  private Byte B2 = new Byte(), B3 = new Byte(), B4 = new Byte(), B5 = new Byte(), B6 = new Byte(),
      B7 = new Byte(), B8 = new Byte(), B9 = new Byte(), B10 = new Byte(), B11 = new Byte(),
      B12 = new Byte(), B13 = new Byte();

  private Word PORT = new Word(DIRS, PINS), W0 = new Word(B1, B0), W1 = new Word(B3, B2),
      W2 = new Word(B5, B4), W3 = new Word(B7, B6), W4 = new Word(B9, B8), W5 = new Word(B11, B10),
      W6 = new Word(B13, B12);

  private Map<String, IVariable> allVariables = new HashMap<String, IVariable>();

  /**
   * Constructor of the Machine class, expects a program and settings.
   * 
   * @param program
   *          which the machine shall run
   * @param settings
   *          with which the machine shall run
   */
  public Machine(Program program, Settings settings) {
    this.program = program;
    allVariables.put("B0", B0);
    allVariables.put("B1", B1);
    allVariables.put("B2", B2);
    allVariables.put("B3", B3);
    allVariables.put("B4", B4);
    allVariables.put("B5", B5);
    allVariables.put("B6", B6);
    allVariables.put("B7", B7);
    allVariables.put("B8", B8);
    allVariables.put("B9", B9);
    allVariables.put("B10", B10);
    allVariables.put("B11", B11);
    allVariables.put("B12", B12);
    allVariables.put("B13", B13);
    allVariables.put("DIRS", DIRS);
    allVariables.put("PINS", PINS);
    allVariables.put("PIN0", PIN0);
    allVariables.put("PIN1", PIN1);
    allVariables.put("PIN2", PIN2);
    allVariables.put("PIN3", PIN3);
    allVariables.put("PIN4", PIN4);
    allVariables.put("PIN5", PIN5);
    allVariables.put("PIN6", PIN6);
    allVariables.put("PIN7", PIN7);
    allVariables.put("DIR0", DIR0);
    allVariables.put("DIR1", DIR1);
    allVariables.put("DIR2", DIR2);
    allVariables.put("DIR3", DIR3);
    allVariables.put("DIR4", DIR4);
    allVariables.put("DIR5", DIR5);
    allVariables.put("DIR6", DIR6);
    allVariables.put("DIR7", DIR7);
    allVariables.put("BIT0", BIT0);
    allVariables.put("BIT1", BIT1);
    allVariables.put("BIT2", BIT2);
    allVariables.put("BIT3", BIT3);
    allVariables.put("BIT4", BIT4);
    allVariables.put("BIT5", BIT5);
    allVariables.put("BIT6", BIT6);
    allVariables.put("BIT7", BIT7);
    allVariables.put("BIT8", BIT8);
    allVariables.put("BIT9", BIT9);
    allVariables.put("BIT10", BIT10);
    allVariables.put("BIT11", BIT11);
    allVariables.put("BIT12", BIT12);
    allVariables.put("BIT13", BIT13);
    allVariables.put("BIT14", BIT14);
    allVariables.put("BIT15", BIT15);
    allVariables.put("W0", W0);
    allVariables.put("W1", W1);
    allVariables.put("W2", W2);
    allVariables.put("W3", W3);
    allVariables.put("W4", W4);
    allVariables.put("W5", W5);
    allVariables.put("W6", W6);
    allVariables.put("PORT", PORT);
    allVariables.put("SYSCLOCK", new Clock());
    this.settings = settings;
    this.eeprom = new EEPROM(program.getCommands().size());
  }

  /**
   * Returns an IVariable in the machine by its name.
   * 
   * @param name
   *          of the IVariable
   * @return IVariable in the machine with this name or constant if name is a number
   */
  public IVariable parseIVariable(String name) {
    String tmp = name;
    try {
      if (tmp.startsWith("@")) {
        tmp = tmp.substring(1);
        tmp = String.valueOf(Formatter.parseAsciiString(tmp));
      } else if (tmp.startsWith("$")) {
        tmp = tmp.substring(1);
        tmp = tmp.toLowerCase();
        tmp = String.valueOf(Formatter.parseHexString(tmp));
      } else if (tmp.startsWith("%")) {
        tmp = tmp.substring(1);
        tmp = String.valueOf(Formatter.parseBinaryString(tmp));
      }
    } catch (NumberFormatException e) {
      program.addError(new BadFormatRuntimeError(tmp));
    }
    IVariable retVal = allVariables.get(tmp);
    if (retVal == null) {
      try {
        retVal = new Constant(Integer.parseInt(tmp));
      } catch (NumberFormatException e) {
        program.addError(new VariableNotFoundRuntimeError(tmp));
      }
    }
    return retVal;
  }

  /**
   * Creates a new IVariable in the machine using a name and a IVariable to be put into the machine.
   * 
   * @param name
   *          of the new IVariable
   * @param var
   *          to be inserted into the machine
   */
  public void addIVariable(String name, IVariable var) {
    if (!allVariables.containsKey(name)) {
      allVariables.put(name, var);
    } else {
      program.addError(new ConstantReferenceRuntimeError(name));
    }
  }

  public Program getProgram() {
    return program;
  }

  public int getExecutionIndex() {
    return executionIndex;
  }

  public void setExecutionIndex(int executionIndex) {
    this.executionIndex = executionIndex;
  }

  public EEPROM getEeprom() {
    return eeprom;
  }

  public Settings getSettings() {
    return settings;
  }

  public String getAllFormatters() {
    return allFormatters;
  }

  public String getAllFormattersAndHashTag() {
    return allFormattersAndHashTag;
  }

}
