package de.bse.prgm.parse.arithmetical;

/**
 * Represents a Substraction operator in form of an arithmetical operator.
 * @author Jonas Reichmann, Elias Groll
 * @version 2.15
 */
public class Subtraction extends ArithmeticalOperator {

  /**
   * Executes the calculation (-) and stores the result in retVal.
   */
  
  protected void exec() {
    retVal.setValue(leftSide.getValue() -  rightSide.getValue());
  }

  /**
   * Returns a new Substraction instance.
   * @return a new Substraction instance
   */
  
  public ArithmeticalOperator create() {
    return new Subtraction();
  }
}
