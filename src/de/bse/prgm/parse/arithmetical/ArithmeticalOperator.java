package de.bse.prgm.parse.arithmetical;

import de.bse.util.ParserException;
import de.bse.vm.var.DynamicVariable;
import de.bse.vm.var.IVariable;

/**
 * Represents an arithmetical operator in a term on the BS1
 * @author Elias Groll
 * @version 2.15
 */
public abstract class ArithmeticalOperator implements IVariable {

  /**
   * IVariable containing the left side of the term.
   */
  protected IVariable leftSide;
  /**
   * IVariable containing the right side of the term.
   */
  protected IVariable rightSide;
  /**
   * result in 16 bit in IVariable format.
   */
  protected IVariable retVal = new DynamicVariable(16);

  /**
   * Calculates left and right side and returns the result as a 16-bit IVariable.
   * @param leftSide left side of the term
   * @param rightSide right side of the term
   * @return result (16-bit IVariable)
   */
  public IVariable calculate(IVariable leftSide, IVariable rightSide) {
    init(this.leftSide, this.rightSide);
    exec();
    return retVal;
  }

  /**
   * Initializes the left and right side of the term.
   * @param leftSide of the term
   * @param rightSide right side of the term
   */
  void init(IVariable leftSide, IVariable rightSide) {
    this.leftSide = leftSide;
    this.rightSide = rightSide;
  }

  /**
   * Executes the calculation.
   */
  protected abstract void exec();

  /**
   * Returns the result of the calculation.
   * [executes calculate(leftSide, rightSide).getValue();]
   * @return value of the calculation
   */
  
  public long getValue() {
    return calculate(leftSide, rightSide).getValue();
  }

  /**
   * Attemps to set a value, but since ArithmeticalOperator is a
   * constant it will always throw a ParserException.
   */
  
  public void setValue(long value) {
    throw new ParserException("Cannot change a constant");
  }

  /**
   * Creates a new ArithmeticalOperator. - class intern factory
   * @return ArithmeticalOperator being created
   */
  public abstract ArithmeticalOperator create();

}
