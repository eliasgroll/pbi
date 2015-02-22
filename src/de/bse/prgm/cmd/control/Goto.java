package de.bse.prgm.cmd.control;

import de.bse.prgm.cmd.HotspotCompiledCommand;
import de.bse.prgm.err.runtime.AmbigousLabelRuntimeError;
import de.bse.prgm.err.runtime.LabelDoesNotExistRuntimeError;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

/**
 * Goes to a label in the program.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Goto extends HotspotCompiledCommand {
  private final String reference;
  private int jumpTo = 0;
  private int ambigous = 0;

  /**
   * Contructs a new Goto.
   * 
   * @param reference
   *          name of the label
   */
  public Goto(String reference) {
    this.reference = reference;
  }

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    machine.setExecutionIndex(jumpTo - 1);
  }

  @Override
  public String infoMsg() {
    return "[Info]Resume execution after \"" + getReference() + "\"";
  }

  @Override
  public String toString() {
    return "GOTO";
  }

  /**
   * Searches for the referenced label in the program.
   */
  @Override
  protected void init(Machine machine) {
    for (int i = 0; i < machine.getProgram().getCommands().size(); i++) {
      if (machine.getProgram().getCommands().get(i) instanceof Label) {
        Label label = (Label) machine.getProgram().getCommands().get(i);
        if (label.getName().equals(getReference())) {
          jumpTo = i;
          ambigous++;
        }
      }
    }
    if (ambigous > 1) {
      machine.getProgram().addError(
          new AmbigousLabelRuntimeError(getReference()));
    } else if (jumpTo == 0) {
      machine.getProgram().addError(
          new LabelDoesNotExistRuntimeError(getReference()));
    }
  }

  public String getReference() {
    return reference;
  }

}
