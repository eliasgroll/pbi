package de.bse.prgm.cmd.control;

import de.bse.prgm.cmd.HotspotCompiledCommand;
import de.bse.prgm.war.IWarning;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.IVariable;

import java.util.Arrays;

/**
 * Goes to the adress specified by offset.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Branch extends HotspotCompiledCommand {

  /**
   * Constructs a new Branch.
   * 
   * @param varString
   *          index for label-selection
   * @param labels
   *          selection of labels
   */
  public Branch(String varString, String[] labels) {
    this.varString = varString;
    this.labels = labels;
  }

  private String varString;
  private String[] labels;
  private Goto[] branch;
  private IVariable var;

  
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    if (var != null && branch != null) {
      try {
        branch[(int) var.getValue()].execute(machine, console);
      } catch (ArrayIndexOutOfBoundsException e) {
        // we'd normally throw an error but the bs1 doesn't
        machine.getProgram().addWarning(new IWarning() {

          
          public String warningMsg() {
            return "[Warn]Branch-variable does not match a label";
          }
        });
      }
    }
  }

  
  public String infoMsg() {
    return "[Info]Switch " + varString + " to match " + Arrays.toString(labels);
  }

  
  public String toString() {
    return "BRANCH";
  }

  
  protected void init(Machine machine) {
    var = machine.parseIVariable(varString);
    branch = new Goto[labels.length];
    for (int i = 0; i < labels.length; i++) {
      branch[i] = new Goto(labels[i]);
    }
  }

}
