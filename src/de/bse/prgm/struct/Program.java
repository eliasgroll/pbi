package de.bse.prgm.struct;

import de.bse.prgm.cmd.ICommand;
import de.bse.prgm.err.IError;
import de.bse.prgm.war.IWarning;

import java.util.ArrayList;
import java.util.List;

public class Program {
  private List<IError> errors = new ArrayList<IError>(); // only for a sorted output
  private List<ICommand> commands = new ArrayList<ICommand>();
  private List<IWarning> warnings = new ArrayList<IWarning>();

  public List<IError> getErrors() {
    return errors;
  }

  public List<ICommand> getCommands() {
    return commands;
  }

  public List<IWarning> getWarnings() {
    return warnings;
  }

  public void addError(IError error) {
    errors.add(error);
  }

  public void addCommand(ICommand command) {
    commands.add(command);
  }

  public void addWarning(IWarning warning) {
    warnings.add(warning);
  }

  public void setCommands(List<ICommand> commands) {
    this.commands = commands;
  }

  /**
   * Adds an ProgramInstance to the Program.
   * @param instance to be added
   */
  public void addInstance(final ProgramInstance instance) {
    if (instance.getErrors() != null) {
      errors.addAll(instance.getErrors());
    }
    if (instance.getWarnings() != null) {
      warnings.addAll(instance.getWarnings());
    }

    if (instance.getCommand() == null) {
      warnings.add(new IWarning() {

        @Override
        public String warningMsg() {
          return "[Warning]A parsed instance didn't return a initialized command";
        }
      });
    }
    addCommand(instance.getCommand());
  }

  @Override
  public String toString() {
    String retVal = "commands: " + commands.size() + ";" + " warnings: "
        + warnings.size() + ";" + " errors: " + (errors.size()) + "\n\n";
    int cycle = 0;
    for (ICommand cmd : commands) {
      cycle++;
      retVal += "[" + cycle + "]" + (cmd == null ? "null" : cmd.toString()) + "\n";
    }
    return retVal;

  }

}
