package de.bse.prgm.parse.arithmetical;

/**
 * Represents a binary AND Operator in the form of an Arithmetical Operator.
 * @author Jonas Reichmann
 * @version 2.15
 */
public class BAND extends ArithmeticalOperator {

  /**
   * Executes the calculation (&) and stores the result in retVal.
   */
  
  protected void exec() {
    retVal.setValue(leftSide.getValue() &  rightSide.getValue());
  }

  /**
   * Returns a new BAND instance.
   * @return new BAND instance
   */
  
  public ArithmeticalOperator create() {
    return new BAND();
  }
}
