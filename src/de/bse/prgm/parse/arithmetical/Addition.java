package de.bse.prgm.parse.arithmetical;

/**
 * Represents an Addition / Sum in the format of Arithmetical Operator
 * @author Jonas Reichmann, Elias Groll
 * @version 2.15
 */
public class Addition extends ArithmeticalOperator {

  /**
   * Executes the calculation (+) and stores the value in retVal.
   */
  @Override
  protected void exec() {
    retVal.setValue(leftSide.getValue() +  rightSide.getValue());
  }

  /**
   * Returns a new Addition instance.
   * @return new Addition instance
   */
  @Override
  public ArithmeticalOperator create() {
    return new Addition();
  }
}
