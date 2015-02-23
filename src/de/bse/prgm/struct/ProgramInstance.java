package de.bse.prgm.struct;

import de.bse.prgm.cmd.ICommand;
import de.bse.prgm.err.IError;
import de.bse.prgm.war.IWarning;

import java.util.HashSet;
import java.util.Set;

public class ProgramInstance {

  private Set<IWarning> warnings = new HashSet<IWarning>();
  private Set<IError> errors = new HashSet<IError>();
  private ICommand command;

  public Set<IWarning> getWarnings() {
    return warnings;
  }

  public Set<IError> getErrors() {
    return errors;
  }

  public ICommand getCommand() {
    return command;
  }

  public void addError(IError error) {
    errors.add(error);
  }

  public void setCommand(ICommand command) {
    this.command = command;
  }

  public void addWarning(IWarning warning) {
    warnings.add(warning);
  }

  @Override
  public String toString() {
    String retVal = "";
    if (command != null) {
      retVal += "<<VIRTUAL>>" + command.infoMsg() + "\n";
    } else {
      retVal += "null \n";
    }

    for (IError error : errors) {
      retVal += error.errorMsg() + " ";
    }
    retVal += "\n";

    for (IWarning warning : warnings) {
      retVal += warning.warningMsg() + " ";
    }
    retVal += "\n";

    return retVal;

  }

}
