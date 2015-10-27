package de.bse.prgm.cmd.control;

import de.bse.prgm.cmd.ICommand;
import de.bse.run.app.IConsole;
import de.bse.util.ParserException;
import de.bse.vm.Machine;
import de.bse.vm.var.Bit;
import de.bse.vm.var.ConglomerateVariable;

/**
 * Goes to a subroutine.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Gosub implements ICommand {

  private static int numberOfGosubs = 0;

  private final int id;
  private final String reference;
  private final Goto jmp;

  /**
   * Constructs a new Gosub (subroutine-call).
   * 
   * @param reference
   *          the label which should be used as subroutine
   * @throws ParserException
   *           thrown if a program has too many subroutine-calls
   */
  public Gosub(String reference) throws ParserException {
    this.reference = reference;
    jmp = new Goto(reference);
    numberOfGosubs++;
    if (numberOfGosubs >= 16) {
      throw new ParserException("too many subroutines");
    }
    id = numberOfGosubs - 1;
  }

  
  public void execute(Machine machine, IConsole console) {
    Bit[] gosubs = ((ConglomerateVariable) machine.parseIVariable("W6"))
        .getBits();
    gosubs[getId()].setValue(1);
    jmp.execute(machine, console);
  }

  
  public String infoMsg() {
    return "[Info]Call subroutine \"" + reference + "\"";
  }

  
  public String toString() {
    return "GOSUB";
  }

  /**
   * The id is used to return to this subroutine-call.
   * 
   * @return id of this Gosub
   */
  public int getId() {
    return id;
  }

}
