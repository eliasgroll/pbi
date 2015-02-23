package de.bse.prgm.cmd;

import de.bse.prgm.err.runtime.NullBytesVariableRuntimeError;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.DynamicVariable;
import de.bse.vm.var.IVariable;
import de.bse.vm.var.Reference;

/**
 * Reference on a given or new variable
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Symbol implements ICommand {

  public Symbol(String name, String referencedObject) {
    this.name = name;
    this.referencedObject = referencedObject;
  }

  public Symbol(String name, int newObject) {
    this.name = name;
    this.newObject = newObject;
  }

  private String name;
  private String referencedObject;
  private int newObject = 0;

  @Override
  public void execute(Machine machine, IConsole console) {
    IVariable referenced;
    if (referencedObject == null) { // referencedObject is the String
      referenced = new DynamicVariable(newObject);
    } else {
      referenced = machine.parseIVariable(referencedObject);
    }
    IVariable reference = new Reference(referenced);
    machine.addIVariable(name, reference);
    if (newObject == 0 && referencedObject == null) {
      machine.getProgram().addError(new NullBytesVariableRuntimeError());
    }
  }

  @Override
  public String infoMsg() {
    return "[Info]Referencing " + referencedObject + " with " + name;
  }

  @Override
  public String toString() {
    return "SYMBOL";
  }

}
