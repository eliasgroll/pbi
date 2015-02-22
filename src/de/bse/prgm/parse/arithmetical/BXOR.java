package de.bse.prgm.parse.arithmetical;

/**
 * Represents a binary XOR operation in form of an arithmetical operator
 * @author Jonas Reichmann
 * @version 2.15
 */
public class BXOR extends ArithmeticalOperator {

  /**
   * Executes the calculation (^) and stores the result in retVal
   */
  @Override
  protected void exec() {
    retVal.setValue(leftSide.getValue() ^  rightSide.getValue());
  }

  /**
   * Returns a new BXOR instance
   * @return a new BXOR instance
   */
  @Override
  public ArithmeticalOperator create() {
    return new BXOR();
  }
}
