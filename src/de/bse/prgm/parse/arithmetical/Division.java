package de.bse.prgm.parse.arithmetical;

/**
 * Represents a division in form of an arithmetical operator
 * @author Jonas Reichmann
 * @version 2.15
 */
public class Division extends ArithmeticalOperator {

  /**
   * Executes the calculation and stores the result in retVal.
   */
  @Override
  protected void exec() {
    retVal.setValue(leftSide.getValue() /  rightSide.getValue());
  }

  /**
   * Returns a new Division instance.
   * @return a new Division instance
   */
  @Override
  public ArithmeticalOperator create() {
    return new Division();
  }
}
