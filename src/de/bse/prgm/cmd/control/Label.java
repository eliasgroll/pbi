package de.bse.prgm.cmd.control;

import de.bse.prgm.cmd.ICommand;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Label implements ICommand {

  private final String name;

  public Label(String name) {
    this.name = name;
  }

  
  public void execute(Machine machine, IConsole console) {
    // a label is called, but not executed
  }

  
  public String infoMsg() {
    return "[Info]Execution reached a label called \"" + getName() + "\"";
  }

  
  public String toString() {
    return "LABEL";
  }

  public String getName() {
    return name;
  }
}
