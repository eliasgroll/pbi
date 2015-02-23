package de.bse.prgm.cmd.loop;

import de.bse.prgm.cmd.ICommand;
import de.bse.prgm.err.runtime.InvalidAllocationRuntimeError;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.var.Constant;
import de.bse.vm.var.IVariable;

/**
 * For command which loops through the following code block until it finds a next
 * whilst increasing the iterator by stepSize until it hits is goal.
 * @author Elias Groll, Jonas Reichmann
 * @version 2.15
 */
public class For implements ICommand {

  private final String fromInit;
  private final String goal;
  private final String stepSize;
  private boolean negative = false;

  private IVariable from;
  private IVariable to;
  private IVariable step;

  /**
   * Constructor of the For class which loops through the following code block
   * until it hits a valid Next command or an END command.
   * @param fromInit value to begin from
   * @param goal value to reach
   * @param stepSize how much the iterator shall increase with each cycle, can be negative
   */
  public For(String fromInit, String goal, String stepSize) {
    this.fromInit = fromInit;
    this.goal = goal;
    this.stepSize = stepSize;
  }

  @Override
  public void execute(Machine machine, IConsole console) {
    initVars(machine, console);
    initNext(machine, console);
  }

  @Override
  public String infoMsg() {
    return "[Info]Iterating from "
        + String.valueOf(fromInit.replace(".*=\\s*", "").trim()) + " to "
        + goal + " with step-size=" + stepSize + " on "
        + fromInit.replace("\\s*=.*", "").trim();
  }

  private void initVars(Machine machine, IConsole console) {
    String[] alloc = fromInit.split("=");
    if (alloc.length != 2) {
      machine.getProgram()
          .addError(new InvalidAllocationRuntimeError(fromInit));
    }
    try {
      setFrom(machine.parseIVariable(alloc[0].trim()));
      long initialisingValue = Long.parseLong(alloc[1].trim());

      getFrom().setValue(initialisingValue);
      to = machine.parseIVariable(goal);
      if (stepSize != null) {
        if (stepSize.startsWith("-")) {
          negative = true;
        }
        step = machine.parseIVariable(stepSize);
      } else {
        step = new Constant(1);
      }
    } catch (NullPointerException e) {
      machine.getProgram()
          .addError(new InvalidAllocationRuntimeError(fromInit));

    } catch (NumberFormatException e) {
      machine.getProgram()
          .addError(new InvalidAllocationRuntimeError(fromInit));

    }
  }

  private void initNext(Machine machine, IConsole console) {
    int searchCounter = 0;
    for (int i = machine.getProgram().getCommands().indexOf(this) + 1; i < machine
        .getProgram().getCommands().size(); i++) {
      ICommand cmd = machine.getProgram().getCommands().get(i);
      if (cmd instanceof For) {
        searchCounter++;
      } else if (cmd instanceof Next) {
        if (searchCounter == 0) {
          Next next = (Next) cmd;
          next.setHead(this);
          break;
        } else {
          searchCounter--;
        }
      }
    }
  }

  @Override
  public String toString() {
    return "FOR";
  }

  public IVariable getStep() {
    return step;
  }

  public IVariable getTo() {
    return to;
  }

  public IVariable getFrom() {
    return from;
  }

  public void setFrom(IVariable from) {
    this.from = from;
  }

  public boolean isNegative() {
    return negative;
  }

}
