package de.bse.prgm;

import de.bse.prgm.cmd.ICommand;
import de.bse.prgm.err.IError;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

/**
 * Interpreter which will run the compiled program
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Interpreter {

  /**
   * Console on which the Interpreter will run.
   */
  private IConsole console;
  /**
   * Machine which the Interpreter will use.
   */
  private Machine machine;

  /**
   * used to count the number of executed commands.
   */
  private int instructions = 0;

  /**
   * Constructor for the interpreter.
   * 
   * @param machine
   *          that will be used as data during the execution
   * @param console
   *          that will be used as io-stream during the execution
   */
  public Interpreter(Machine machine, IConsole console) {
    this.machine = machine;
    this.console = console;
  }

  /**
   * Run executes the program.
   */
  public int run() {
    instructions = 0;
    try {
      executeCommands();
      if (machine.getSettings().getPrintInternComposition()) {
        console.printLn("\n" + machine.getProgram().toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
      console.printLn("[Warn]Unexpected end of exececution");
    }
    return instructions;
  }

  private void executeCommands() {
    int printedWarnings = 0;
    while (machine.getExecutionIndex() < machine.getProgram().getCommands().size()) {
      if (!machine.getSettings().getIngoreWarnings()
          && printedWarnings != machine.getProgram().getWarnings().size()) {
        for (int i = printedWarnings; i < machine.getProgram().getWarnings().size(); i++) {
          printedWarnings = machine.getProgram().getWarnings().size();
          console.printLn(machine.getProgram().getWarnings().get(i).warningMsg());
        }
      }
      if (!noErrors()) {
        break;
      }
      ICommand cmd = machine.getProgram().getCommands().get(machine.getExecutionIndex());
      if (machine.getSettings().getPrintInfo()) {
        console.printLn(cmd.infoMsg());
      }
      if (machine.getSettings().getEmulate4Mhz()) {
        try {
          Thread.sleep(20);
        } catch (InterruptedException e) {
          // do nothing
        }
      }
      cmd.execute(machine, console);
      machine.setExecutionIndex(machine.getExecutionIndex() + 1);
      instructions++;
    }
    if (!noErrors()) { // if got and error at the last command
      for (IError err : machine.getProgram().getErrors()) {
        console.printLn(err.errorMsg());
      }
    }
  }

  /**
   * Determines wether the program executed has created any errors or not
   * 
   * @return true/false depending on if the program has created any errors.
   */
  private boolean noErrors() {
    return machine.getProgram().getErrors().isEmpty();
  }

}
