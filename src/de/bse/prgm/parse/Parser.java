package de.bse.prgm.parse;

import de.bse.prgm.cmd.Allocation;
import de.bse.prgm.cmd.Symbol;
import de.bse.prgm.cmd.control.Branch;
import de.bse.prgm.cmd.control.Gosub;
import de.bse.prgm.cmd.control.Goto;
import de.bse.prgm.cmd.control.If;
import de.bse.prgm.cmd.control.Label;
import de.bse.prgm.cmd.control.Return;
import de.bse.prgm.cmd.control.power.End;
import de.bse.prgm.cmd.control.power.Nap;
import de.bse.prgm.cmd.control.power.Sleep;
import de.bse.prgm.cmd.debug.ActivateAdditionalInformation;
import de.bse.prgm.cmd.debug.Assert;
import de.bse.prgm.cmd.debug.DeactivateAdditionalInformation;
import de.bse.prgm.cmd.debug.Debug;
import de.bse.prgm.cmd.debug.Instruct;
import de.bse.prgm.cmd.io.High;
import de.bse.prgm.cmd.io.Input;
import de.bse.prgm.cmd.io.Low;
import de.bse.prgm.cmd.io.Output;
import de.bse.prgm.cmd.io.Pot;
import de.bse.prgm.cmd.io.Pulsin;
import de.bse.prgm.cmd.io.Pulsout;
import de.bse.prgm.cmd.io.Pwm;
import de.bse.prgm.cmd.io.Reverse;
import de.bse.prgm.cmd.io.Serout;
import de.bse.prgm.cmd.io.Toggle;
import de.bse.prgm.cmd.io.sound.Sound;
import de.bse.prgm.cmd.loop.For;
import de.bse.prgm.cmd.loop.Next;
import de.bse.prgm.cmd.num.Lookdown;
import de.bse.prgm.cmd.num.Lookup;
import de.bse.prgm.cmd.num.Random;
import de.bse.prgm.cmd.storage.Eeprom;
import de.bse.prgm.cmd.storage.Read;
import de.bse.prgm.cmd.storage.Write;
import de.bse.prgm.cmd.time.Pause;
import de.bse.prgm.err.HardwareError;
import de.bse.prgm.err.IError;
import de.bse.prgm.err.InvalidAllocationError;
import de.bse.prgm.err.SyntaxError;
import de.bse.prgm.err.parsing.TooManySubroutineCallsError;
import de.bse.prgm.struct.ProgramInstance;
import de.bse.prgm.war.LabelWarning;
import de.bse.util.ParserException;

/**
 * Parser which parses each line and creates a respective ProgramInstance
 * 
 * @author Jonas Reichmann
 * @version 2.15
 */
public class Parser {

  /**
   * Parser cannot be instantiated, all methods are static.
   */
  private Parser() {
    // it is not possible to create a Parser-object
  }

  /**
   * Parses a given line and returns a ProgramInstance containing the command and / or the errors
   * created by the line.
   * 
   * @param line
   * @param lineNumber
   * @return
   */
  public static ProgramInstance parseLine(String line, int lineNumber) {
    if (line.startsWith("PAUSE")) {
      return parsePause(line, lineNumber);
    } else if (line.startsWith("HIGH")) {
      return parseHigh(line, lineNumber);
    } else if (line.startsWith("LOW")) {
      return parseLow(line, lineNumber);
    } else if (line.endsWith(":")) {
      return parseLabel(line, lineNumber);
    } else if (line.startsWith("FOR")) {
      return parseFor(line, lineNumber);
    } else if (line.startsWith("NEXT")) {
      return parseNext(line, lineNumber);
    } else if (line.startsWith("GOTO")) {
      return parseGoto(line, lineNumber);
    } else if (line.equals("END")) {
      return parseEnd();
    } else if (line.startsWith("INPUT")) {
      return parseInput(line, lineNumber);
    } else if (line.startsWith("DEBUG")) {
      return parseDebug(line, lineNumber);
    } else if (line.startsWith("SYMBOL")) {
      return parseSymbol(line, lineNumber);
    } else if (line.startsWith("SOUND")) {
      return parseSound(line, lineNumber);
    } else if (line.startsWith("TOGGLE")) {
      return parseToggle(line, lineNumber);
    } else if (line.startsWith("OUTPUT")) {
      return parseOutput(line, lineNumber);
    } else if (line.equals("INSTRUCT") || line.equals("BREAKPOINT") || line.equals("BP")) {
      return parseInstruct(line, lineNumber);
    } else if (line.startsWith("IF")) {
      return parseIf(line, lineNumber);
    } else if (line.startsWith("ASSERT")) {
      return parseAssert(line.replaceAll("ASSERT\\s*", "").trim(), lineNumber);
    } else if (line.startsWith("LET") && line.contains("=")) {
      return parseAllocation(line.replaceAll("LET\\s*", "").trim(), lineNumber);
    } else if (line.contains("=")) {
      return parseAllocation(line, lineNumber);
    } else if (line.startsWith("GOSUB")) {
      return parseGosub(line, lineNumber);
    } else if (line.equals("RETURN")) {
      return parseReturn();
    } else if (line.startsWith("LOOKUP")) {
      return parseLookup(line, lineNumber);
    } else if (line.startsWith("LOOKDOWN")) {
      return parseLookdown(line, lineNumber);
    } else if (line.startsWith("SEROUT")) {
      return parseSerout(line, lineNumber);
    } else if (line.startsWith("POT")) {
      return parsePot(line, lineNumber);
    } else if (line.startsWith("PWM")) {
      return parsePwm(line, lineNumber);
    } else if (line.startsWith("PULSIN")) {
      return parsePulsin(line, lineNumber);
    } else if (line.startsWith("PULSOUT")) {
      return parsePulsout(line, lineNumber);
    } else if (line.startsWith("NAP")) {
      return parseNap(line, lineNumber);
    } else if (line.startsWith("SLEEP")) {
      return parseSleep(line, lineNumber);
    } else if (line.startsWith("BRANCH")) {
      return parseBranch(line, lineNumber);
    } else if (line.startsWith("READ")) {
      return parseRead(line, lineNumber);
    } else if (line.startsWith("WRITE")) {
      return parseWrite(line, lineNumber);
    } else if (line.startsWith("EEPROM")) {
      return parseEeprom(line, lineNumber);
    } else if (line.startsWith("RANDOM")) {
      return parseRandom(line, lineNumber);
    } else if (line.startsWith("ACTIVATEINFO")) {
      return parseActivateInfo(line, lineNumber);
    } else if (line.startsWith("DISACTIVATEINFO")) {
      return parseDeactivateInfo(line, lineNumber);
    } else if (line.startsWith("REVERSE")) {
      return parseReverse(line, lineNumber);
    }

    ProgramInstance retVal = new ProgramInstance();
    retVal.addError(new IError() {
      @Override
      public String errorMsg() {
        return "[Error, internal]parse failed";
      }
    });
    return retVal;
  }

  /**
   * Parses a RETURN command.
   * 
   * @return ProgramInstance containing the RETURN command and possible errors
   */
  private static ProgramInstance parseReturn() {
    ProgramInstance retVal = new ProgramInstance();
    retVal.setCommand(new Return());
    return retVal;
  }

  /**
   * Parses an Allocation.
   * 
   * @param line
   *          with the allocation
   * @param lineNumber
   *          in which the allocation stands
   * @return ProgramInstance returning the Allocation and possible errors
   */
  public static ProgramInstance parseAllocation(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    String[] trimmed = line.split("=");
    if (trimmed.length > 2) {
      retVal.addError(new InvalidAllocationError(line, lineNumber));
    }
    retVal.setCommand(new Allocation(trimmed[0].trim(), trimmed[1].trim()));
    return retVal;
  }

  /**
   * Parses an INSTRUCT command.
   * 
   * @param command
   *          to be instructed
   * @param lineNumber
   *          in which the INSTRUCT command stands in
   * @return ProgramInstance containing the INSTRUCT and possible errors
   */
  private static ProgramInstance parseInstruct(String command, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    retVal.setCommand(command.equals("BREAKPOINT") ? new Instruct(lineNumber) : new Instruct());
    return retVal;
  }

  /**
   * Parses an IF command.
   * 
   * @param line
   *          with the IF command an parameters
   * @param lineNumber
   *          in which the IF command stands
   * @return ProgramInstance containing the IF command and possible errors
   */
  private static ProgramInstance parseIf(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    if (!line.contains("THEN")) {
      retVal.addError(new SyntaxError(lineNumber));
    } else {
      String expression = line.substring(2, line.indexOf("THEN") - 1);
      String label = line.substring(line.indexOf("THEN") + 4, line.length()).trim();
      retVal.setCommand(new If(expression, label));
    }

    return retVal;

  }

  /**
   * Parses a PAUSE command and its parameters.
   * 
   * @param line
   *          with the PAUSE command and parameters
   * @param lineNumber
   *          in which the PAUSE command is in
   * @return ProgramInstance with the PAUSE command and possible errors
   */
  private static ProgramInstance parsePause(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Pause pause = null;
    try {
      String time = line.replaceAll("\\s*PAUSE\\s*", "").trim();
      pause = new Pause(time);
    } catch (NumberFormatException e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    retVal.setCommand(pause);
    return retVal;
  }

  /**
   * Parses a HIGH command and its parameters.
   * 
   * @param line
   *          with the HIGH command and its parameters
   * @param lineNumber
   *          in which the HIGH commands is in
   * @return ProgramInstance containing the HIGH command and possible errors
   */
  private static ProgramInstance parseHigh(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    High high = null;
    try {
      String pin = line.replaceAll("\\s*HIGH\\s*", "").trim();
      high = new High(pin);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    retVal.setCommand(high);
    return retVal;
  }

  /**
   * Parses a SOUND command and its parameters.
   * 
   * @param line
   *          containing the SOUND command and its parameters
   * @param lineNumber
   *          in which the SOUND command is in
   * @return ProgramInstance containing the SOUND command and possible errors
   */
  private static ProgramInstance parseSound(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Sound sound = null;
    try {
      line = line.replaceAll("\\s*SOUND\\s*", "");
      String[] res = line.split(",");

      if (res[2].matches(".*\\)\\S+")) {
        retVal.addError(new SyntaxError(lineNumber));
      }

      String pin = res[0].replaceAll("\\s*", "").trim();
      String note = res[1].replaceAll("\\s*\\(\\s*", "").trim();
      String duration = res[2].replaceAll("\\s*\\)\\s*", "").trim();

      sound = new Sound(pin, note, duration);
      retVal.setCommand(sound);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses a DEBUG command and its parameters.
   * 
   * @param line
   *          containing the DEBUG command and its parameters
   * @param lineNumber
   *          in which the DEBUG command is in
   * @return ProgramInstance containing the DEBUG command and possible errors
   */
  private static ProgramInstance parseDebug(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Debug debug = null;
    try {
      line = line.replaceAll("\\s*DEBUG\\s*", "");
      String[] params = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
      debug = new Debug(params);
      retVal.setCommand(debug);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses a SEROUT command and its parameters.
   * 
   * @param line
   *          containing the SEROUT command and its parameters
   * @param lineNumber
   *          in which the SEROUT command is in
   * @return ProgramInstance containing the SEROUT command
   */
  private static ProgramInstance parseSerout(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Serout serout = null;
    try {
      line = line.replaceAll("\\s*SEROUT\\s*", "");
      String rest = line.substring(line.indexOf(",") + 1, line.length()).trim();
      int pin = Integer.valueOf(line.substring(0, line.indexOf(",")).trim());
      String baudRate = rest.substring(0, rest.indexOf(",")).trim();
      if (pin < 0 || pin > 7) {
        retVal.addError(new HardwareError(lineNumber, pin));
      }
      String rawData = rest.substring(rest.indexOf("(") + 1, rest.lastIndexOf(")"));
      String[] data = rawData.split(",");
      serout = new Serout(pin, baudRate, data);
      retVal.setCommand(serout);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses a LOW command and its parameters.
   * 
   * @param line
   *          containing the LOW command and its parameters
   * @param lineNumber
   *          in which the LOW command is in
   * @return ProgramInstance containing the LOW command and possible errors
   */
  private static ProgramInstance parseLow(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Low low = null;
    try {
      String pin = line.replaceAll("\\s*LOW\\s*", "").trim();
      low = new Low(pin);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    retVal.setCommand(low);
    return retVal;
  }

  /**
   * Parses a TOGGLE command and its parameters.
   * 
   * @param line
   *          containing the TOGGLE command and its parameters
   * @param lineNumber
   *          in which the TOGGLE command is in
   * @return ProgramInstance containing the TOGGLE command and possible errors
   */
  private static ProgramInstance parseToggle(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Toggle toggle = null;
    try {
      String pin = line.replaceAll("\\s*TOGGLE\\s*", "").trim();
      toggle = new Toggle(String.valueOf(pin));
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    retVal.setCommand(toggle);
    return retVal;
  }

  /**
   * Parses a REVERSE command and its parameters.
   * 
   * @param line
   *          containing the REVERSE command and its parameters
   * @param lineNumber
   *          in which the REVERSE command is in
   * @return ProgramInstance containing the REVERSE command and possible errors
   */
  private static ProgramInstance parseReverse(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Reverse reverse = null;
    try {
      String pin = line.replaceAll("\\s*REVERSE\\s*", "").trim();
      reverse = new Reverse(String.valueOf(pin));
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    retVal.setCommand(reverse);
    return retVal;
  }

  /**
   * Parses an OUTPUT command and its parameters.
   * 
   * @param line
   *          containing the OUTPUT command and its parameters
   * @param lineNumber
   *          in which the OUPUT command is in
   * @return ProgramInstance containing the OUTPUT command and possible errosr
   */
  private static ProgramInstance parseOutput(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Output output = null;
    try {
      String dir = line.replaceAll("\\s*OUTPUT\\s*", "").trim();
      output = new Output(dir);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    retVal.setCommand(output);
    return retVal;
  }

  /**
   * Parses an INPUT command and its parameters.
   * 
   * @param line
   *          containing the INPUT command and its parameters
   * @param lineNumber
   *          in which the INPUT command is in
   * @return ProgramInstance containing the INPUT command and possible errors
   */
  private static ProgramInstance parseInput(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Input input = null;
    try {
      String dir = line.replaceAll("\\s*INPUT\\s*", "").trim();
      input = new Input(dir);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    retVal.setCommand(input);
    return retVal;
  }

  /**
   * Parses a label.
   * 
   * @param line
   *          containing the Label
   * @param lineNumber
   *          in which the label is in
   * @return ProgramInstance containing the label and possible errors
   */
  private static ProgramInstance parseLabel(String line, int lineNumber) {
    String name = line.substring(0, line.length() - 1).trim();
    Label label = new Label(name);
    ProgramInstance retVal = new ProgramInstance();
    retVal.setCommand(label);
    retVal.addWarning(new LabelWarning(lineNumber, name));
    return retVal;

  }

  /**
   * Parses a GOTO command and its parameters.
   * 
   * @param line
   *          containing the GOTO command and its parameters
   * @param lineNumber
   *          in which the GOTO command is in
   * @return ProgramInstance containing the GOTO command and possible errors
   */
  private static ProgramInstance parseGoto(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    String name = line.substring("GOTO".length()).trim();
    Goto myGoto = new Goto(name);
    retVal.setCommand(myGoto);
    return retVal;
  }

  /**
   * Parses a GOSUB command and its parameters.
   * 
   * @param line
   *          containing the GOSUB command and its parameters
   * @param lineNumber
   *          in which the GOSUB command is in
   * @return ProgramInstance containing the GOSUB command and possible errors
   */
  private static ProgramInstance parseGosub(String line, int lineNumber) throws ParserException {
    ProgramInstance retVal = new ProgramInstance();
    String name = line.substring("GOSUB".length()).trim();
    try {
      Gosub myGosub = new Gosub(name);
      retVal.setCommand(myGosub);
    } catch (ParserException e) {
      retVal.addError(new TooManySubroutineCallsError());
    }
    return retVal;
  }

  /**
   * Parses a FOR command and its parameters.
   * 
   * @param line
   *          containing the FOR command and its parameters
   * @param lineNumber
   *          in which the FOR command is in
   * @return ProgramInstance containing the FOR command and possible errors
   */
  private static ProgramInstance parseFor(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    For myFor = null;
    String init = "";
    String to = "";
    String step = "";
    try {
      line = line.replaceAll("\\s*FOR\\s*", "");
      init = line.replaceAll("\\s*", "").replaceAll("\\s*TO.*", "").trim();
      to = line.replaceAll(".*TO\\s*", "").replaceAll("\\s+(STEP)?.*", "").trim();
      if (line.contains("STEP")) {
        step = line.replaceAll(".*STEP\\s*", "").trim();
      } else {
        step = "1";
      }
      myFor = new For(init, to, step);

      retVal.setCommand(myFor);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses a NEXT command and its parameters.
   * 
   * @param line
   *          containing the NEXT command and its parameters
   * @param lineNumber
   *          in which the NEXT command is in
   * @return ProgramInstance containing the NEXT command and possible errors
   */
  private static ProgramInstance parseNext(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Next next = null;

    try {
      next = new Next();

      retVal.setCommand(next);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses an END command.
   * 
   * @param line
   *          containing the END
   * @param lineNumber
   *          in which the END is in
   * @return ProgramInstance containing the END command and possible errors
   */
  private static ProgramInstance parseEnd() {
    ProgramInstance retVal = new ProgramInstance();
    retVal.setCommand(new End());
    return retVal;
  }

  /**
   * Parses a SYMBOL command and its parameters.
   * 
   * @param line
   *          containing the SYMBOL command and its parameters
   * @param lineNumber
   *          in which the SYMBOL command is in
   * @return ProgramInstance containing the SYMBOL command and possible errors
   */
  private static ProgramInstance parseSymbol(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Symbol symbol = null;

    try {
      line = line.replaceAll("\\s*SYMBOL\\s*", "");
      String name = line.replaceAll("\\s*=.*", "").trim();

      if (line.contains("NEW")) {
        int size = Integer.valueOf(line.replaceAll(".*=\\s*NEW\\s*", "").trim());

        symbol = new Symbol(name, size);
      } else {
        String referencedVariable = line.replaceAll(".*=\\s*", "").trim();

        symbol = new Symbol(name, referencedVariable);
      }
      retVal.setCommand(symbol);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses a LOOKUP command and its parameters.
   * 
   * @param line
   *          containing the LOOKUP command and its parameters
   * @param lineNumber
   *          in which the LOOKUP command is in
   * @return ProgramInstance containing the LOOKUP command and possible errors
   */
  private static ProgramInstance parseLookup(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Lookup lookup = null;

    try {
      String data = line.replaceAll("\\s*LOOKUP\\s*", "").trim();
      String[] args = data.split("[\\(\\)]");
      String target = args[0].replaceAll(",", "").trim();
      String rawValues = args[1].trim();
      String result = args[2].replaceAll(",", "").trim();
      long[] values;

      if (rawValues.startsWith("\"") && rawValues.startsWith("\"")) {
        rawValues = rawValues.substring(1, rawValues.length() - 1);
        values = new long[rawValues.length()];
        for (int i = 0; i < rawValues.length(); i++) {
          values[i] = (long) rawValues.charAt(i);
        }
      } else {
        String[] someValues = rawValues.split(",");
        values = new long[someValues.length];
        for (int i = 0; i < someValues.length; i++) {
          values[i] = (long) Integer.valueOf(someValues[i]);
        }
      }

      lookup = new Lookup(target, result, values);

      retVal.setCommand(lookup);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses a LOOKDOWN command and its parameters.
   * 
   * @param line
   *          containing the LOOKDOWN command and its parameters
   * @param lineNumber
   *          in which the LOOKDOWN command is in
   * @return ProgramInstance containing the LOOKDOWN command and possible errors
   */
  private static ProgramInstance parseLookdown(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Lookdown lookdown = null;

    try {
      String data = line.replaceAll("\\s*LOOKDOWN\\s*", "").trim();
      String[] args = data.split("[\\(\\)]");
      String target = args[0].replaceAll(",", "").trim();
      String rawValues = args[1].trim();
      String result = args[2].replaceAll(",", "").trim();
      long[] values;

      if (rawValues.startsWith("\"") && rawValues.startsWith("\"")) {
        rawValues = rawValues.substring(1, rawValues.length() - 1);
        values = new long[rawValues.length()];
        for (int i = 0; i < rawValues.length(); i++) {
          values[i] = (long) rawValues.charAt(i);
        }
      } else {
        String[] someValues = rawValues.split(",");
        values = new long[someValues.length];
        for (int i = 0; i < someValues.length; i++) {
          values[i] = (long) Integer.valueOf(someValues[i]);
        }
      }

      lookdown = new Lookdown(target, values, result);

      retVal.setCommand(lookdown);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses a PWM command and its parameters.
   * 
   * @param line
   *          containing the PWM command and its parameters
   * @param lineNumber
   *          in which the PWM command is in
   * @return ProgramInstance containing the PWM command and possible errors
   */
  private static ProgramInstance parsePwm(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Pwm pwm = null;

    try {
      String[] data = line.split(",");
      int pin = Integer.valueOf(data[0].trim());
      int duty = Integer.valueOf(data[1].trim());
      int duration = Integer.valueOf(data[2].trim());
      if (duty < 0 || duty > 255) {
        retVal.addError(new SyntaxError(lineNumber));
      }
      if (duration < 0 || duration > 255) {
        retVal.addError(new SyntaxError(lineNumber));
      }
      pwm = new Pwm(String.valueOf(pin), duty, duration);
      retVal.setCommand(pwm);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }

    return retVal;
  }

  /**
   * Parses a POT command and its parameters.
   * 
   * @param line
   *          containing the POT command and its parameters
   * @param lineNumber
   *          in which the POT command is in
   * @return ProgramInstance containing the POT command and possible errors
   */
  private static ProgramInstance parsePot(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Pot pot = null;

    try {
      String[] data = line.replaceAll("\\s*POT\\s*", "").trim().split(",");
      String pin = data[0].trim();
      String scale = data[1].trim();
      String var = data[2].trim();
      if (Integer.valueOf(scale) > 255 || Integer.valueOf(scale) < 0) {
        retVal.addError(new SyntaxError(lineNumber));
      }
      pot = new Pot(pin, var, scale);
      retVal.setCommand(pot);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }

    return retVal;
  }

  /**
   * Parses a PULSIN command and its parameters.
   * 
   * @param line
   *          containing the PULSIN command and its parameters
   * @param lineNumber
   *          in which the PULSIN command is in
   * @return ProgramInstance containing the PULSIN command and possible errors
   */
  private static ProgramInstance parsePulsin(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Pulsin pulsin = null;

    try {
      String[] data = line.replaceAll("\\s*PULSIN\\s*", "").trim().split(",");
      String pin = String.valueOf(data[0]);
      String state = String.valueOf(data[1]);
      String variable = String.valueOf(data[2]);
      pulsin = new Pulsin(pin, state, variable);
      retVal.setCommand(pulsin);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }

    return retVal;
  }

  /**
   * Parses a PULSOUT command and its parameters.
   * 
   * @param line
   *          containing the PULSOUT command and its parameters
   * @param lineNumber
   *          in which the PULSOUT command is in
   * @return ProgramInstance containing the PULSOUT command and possible errors
   */
  private static ProgramInstance parsePulsout(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Pulsout pulsout = null;

    try {
      pulsout = new Pulsout();
      retVal.setCommand(pulsout);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }

    return retVal;
  }

  /**
   * Parses an ASSERT command and its parameters.
   * 
   * @param line
   *          containing the ASSERT command and its parameters
   * @param lineNumber
   *          in which the ASSERT command is in
   * @return ProgramInstance containing the ASSERT command and possible errors
   */
  private static ProgramInstance parseAssert(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Assert check = null;

    try {
      check = new Assert(line, lineNumber);
      retVal.setCommand(check);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }

    return retVal;
  }

  /**
   * Parses a NAP command and its parameters.
   * 
   * @param line
   *          containing the NAP command and its parameters
   * @param lineNumber
   *          in which the NAP command is in
   * @return ProgramInstance containing the NAP command and possible errors
   */
  private static ProgramInstance parseNap(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Nap nap = null;

    try {
      nap = new Nap(line.replaceAll("NAP\\s*", "").trim());
      retVal.setCommand(nap);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }

    return retVal;
  }

  /**
   * Parses a SLEEP command and its parameters.
   * 
   * @param line
   *          containing the SLEEP command and its parameters
   * @param lineNumber
   *          in which the SLEEP command is in
   * @return ProgramInstance containing the SLEEP command and possible errors
   */
  private static ProgramInstance parseSleep(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Sleep sleep = null;

    try {
      sleep = new Sleep(line.replaceAll("SLEEP\\s*", ""));
      retVal.setCommand(sleep);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }

    return retVal;
  }

  /**
   * Parses a RANDOM command and its parameters.
   * 
   * @param line
   *          containing the RANDOM command and its parameters
   * @param lineNumber
   *          in which the RANDOM command is in
   * @return ProgramInstance containing the RANDOM command and possible errors
   */
  private static ProgramInstance parseRandom(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Random random = null;

    try {
      random = new Random(line.replaceAll("RANDOM\\s*", "").trim());
      retVal.setCommand(random);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses an EEPROM command and its parameters.
   * 
   * @param line
   *          containing the EEPROM command and its parameters
   * @param lineNumber
   *          in which the EEPROM command is in
   * @return ProgramInstance containing the EEPROM command and possible errors
   */
  private static ProgramInstance parseEeprom(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Eeprom eeprom = null;

    try {
      String location = "0";
      long[] values;

      line = line.replaceAll("EEPROM\\s*", "").trim();
      if (line.matches("^[\\d\\w]*,\\s*\\(.*")) {
        location = line.substring(0, line.indexOf("(")).replaceAll(",", "").trim();
      }
      String rest = line.substring(line.indexOf("(") + 1, line.lastIndexOf(")"));
      String[] rawValues = rest.split(",");
      values = new long[rawValues.length];
      for (int i = 0; i < rawValues.length; i++) {
        values[i] = (long) Integer.valueOf(rawValues[i]);
      }

      eeprom = new Eeprom(location, values);
      retVal.setCommand(eeprom);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses a READ command and its parameters.
   * 
   * @param line
   *          containing the READ command and its parameters
   * @param lineNumber
   *          in which the READ command is in
   * @return ProgramInstance containing the READ command and possible errors
   */
  private static ProgramInstance parseRead(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Read read = null;

    try {
      line = line.replaceAll("READ\\s*", "").trim();
      String[] data = line.split(",");
      String location = data[0].trim();
      String var = data[1].trim();
      read = new Read(location, var);
      retVal.setCommand(read);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses a WRITE command and its parameters.
   * 
   * @param line
   *          containing the WRITE command and its parameters
   * @param lineNumber
   *          in which the WRITE command is in
   * @return ProgramInstance containing the WRITE command and possible errors
   */
  private static ProgramInstance parseWrite(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Write write = null;

    try {
      line = line.replaceAll("WRITE\\s*", "").trim();
      String[] data = line.split(",");
      String location = data[0].trim();
      String value = data[1].trim();
      write = new Write(location, value);
      retVal.setCommand(write);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }

  /**
   * Parses a DEACTIVATEINFO command and its parameters.
   * 
   * @param line
   *          containing the DEACTIVATEINFO command and its parameters
   * @param lineNumber
   *          in which the DEACTIVATEINFO command is in
   * @return ProgramInstance containing the DEACTIVATEINFO command and possible errors
   */
  private static ProgramInstance parseDeactivateInfo(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    retVal.setCommand(new DeactivateAdditionalInformation());
    return retVal;
  }

  /**
   * Parses an ACTIVATEINFO command and its parameters.
   * 
   * @param line
   *          containing the ACTIVATEINFO command and its parameters
   * @param lineNumber
   *          in which the ACTIVATEINFO command is in
   * @return ProgramInstance containing the ACTIVATEINFO command and possible errors
   */
  private static ProgramInstance parseActivateInfo(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    retVal.setCommand(new ActivateAdditionalInformation());
    return retVal;
  }

  /**
   * Parses a BRANCH command and its parameters.
   * 
   * @param line
   *          containing the BRANCH command and its parameters
   * @param lineNumber
   *          in which the BRANCH command is in
   * @return ProgramInstance containing the BRANCH command and possible errors
   */
  private static ProgramInstance parseBranch(String line, int lineNumber) {
    ProgramInstance retVal = new ProgramInstance();
    Branch branch = null;

    try {
      line = line.replaceAll("BRANCH\\s*", "").trim();
      String offset = line.substring(0, line.indexOf(","));
      line = line.substring(line.indexOf(","), line.length());
      line = line.substring(line.indexOf("(") + 1, line.lastIndexOf(")"));
      String[] addresses = line.split(",");

      branch = new Branch(offset, addresses);
      retVal.setCommand(branch);
    } catch (Exception e) {
      retVal.addError(new SyntaxError(lineNumber));
    }
    return retVal;
  }
}
