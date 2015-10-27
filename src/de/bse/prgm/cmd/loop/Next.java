package de.bse.prgm.cmd.loop;

import de.bse.prgm.cmd.ICommand;
import de.bse.prgm.err.runtime.NoLoopHeadRuntimeError;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Next implements ICommand {

  private For head;
  private int jumpTo;

  
  public void execute(Machine machine, IConsole console) {
    if (head == null) {
      machine.getProgram().addError(new NoLoopHeadRuntimeError());
    } else if (!reached()) {
      head.getFrom().setValue(
          head.getFrom().getValue() + head.getStep().getValue());
      jumpTo = machine.getProgram().getCommands().indexOf(head);
      machine.setExecutionIndex(jumpTo);
    }

  }

  private boolean reached() {
    if (head.isNegative()) {
      return (head.getFrom().getValue() <= head.getTo().getValue());
    } else {
      return (head.getFrom().getValue() >= head.getTo().getValue());
    }
  }

  
  public String infoMsg() {
    return "[Info]Resume execution after this NEXT at Line "
        + String.valueOf(jumpTo);
  }

  
  public String toString() {
    return "NEXT";
  }

  public void setHead(For head) {
    this.head = head;
  }

}
