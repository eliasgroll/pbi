package de.bse.prgm.cmd.control;

import de.bse.prgm.cmd.ICommand;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.Bit;
import de.bse.vm.var.ConglomerateVariable;

public class Return implements ICommand {

  
  public void execute(Machine machine, IConsole console) {
    Bit[] gosubs = ((ConglomerateVariable) machine.parseIVariable("W6"))
        .getBits();
    int jumpTo = machine.getProgram().getCommands().size() - 1;
    search: for (int id = gosubs.length - 1; id >= 0; id--) {
      if (gosubs[id].getValue() == 1) {
        gosubs[id].setValue(0);
        for (int i = 0; i < machine.getProgram().getCommands().size(); i++) {
          ICommand cmd = machine.getProgram().getCommands().get(i);
          if ((cmd instanceof Gosub) && (((Gosub) cmd).getId() == id)) {
            jumpTo = i;
            break search;
          }
        }
      }
    }
    machine.setExecutionIndex(jumpTo);
  }

  
  public String infoMsg() {
    return "[Info]Return to last executed gosub or to end if there is none";
  }

  
  public String toString() {
    return "RETURN";
  }
}
