package de.bse.prgm.cmd.debug;

import de.bse.prgm.cmd.ICommand;
import de.bse.prgm.cmd.control.Label;
import de.bse.prgm.cmd.control.power.End;
import de.bse.prgm.parse.Parser;
import de.bse.prgm.struct.ProgramInstance;
import de.bse.prgm.war.IWarning;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

/**
 * Loops an evaluation of user-input. Reads a line and tries to execute is as command. The user can
 * quit the evaluation by the input of an empty line. Instruct works as a built-in Breakpoint. This
 * is not a build-in command of the bs1.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Instruct implements ICommand {

  public Instruct() {
    this.lineNumber = -1;
  }

  public Instruct(int lineNumber) {
    this.lineNumber = lineNumber;
  }

  private final int lineNumber;

  @Override
  public void execute(Machine machine, IConsole console) {
    String line;
    do {
      console.printLn("[Breakpoint" + (lineNumber != -1 ? ", line " + lineNumber : "") + "]");
      line = console.readLn().trim();
      ProgramInstance instance = Parser.parseLine(line, -1);
      if (instance != null && instance.getErrors().isEmpty()) {
        ICommand command = instance.getCommand();

        console.printLn("[Input]accepted: " + command.infoMsg() + "\n");
        command.execute(machine, console);
        if (command instanceof Label) {
          instance.addWarning(new IWarning() {

            @Override
            public String warningMsg() {
              return "[Warn]You cannot goto a dynamically created label";
            }

          });
        } else if (command instanceof End) {
          break;
        }
        for (IWarning warn : instance.getWarnings()) {
          console.printLn(warn.warningMsg() + "\n");
          machine.getProgram().addWarning(warn);
        }
      } else if (!line.isEmpty()) {
        console.printLn("[Input]invalid \n");
      } else {
        console.printLn("[Input]continue.. \n");
        break;
      }
    } while (!line.isEmpty());
  }

  @Override
  public String infoMsg() {
    return "[Info]Input a valid command to be evaluated and executed seperately";
  }

  @Override
  public String toString() {
    return "BREAKPOINT";
  }
}
