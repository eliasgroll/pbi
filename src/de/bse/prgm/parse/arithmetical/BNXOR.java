package de.bse.prgm.parse.arithmetical;

/**
 * Represents a binary NXOR operator in form of an arithmetical operator
 * @author Jonas Reichmann
 * @version 2.15
 */
public class BNXOR extends ArithmeticalOperator {

  /**
   * Executes the calculation (^/) and stores the result in retVal
   */
  @Override
  protected void exec() {
    retVal.setValue(leftSide.getValue() ^  ~rightSide.getValue());
  }

  /**
   * Returns a new BNXOR instance
   * @return new BNXOR instance
   */
  @Override
  public ArithmeticalOperator create() {
    return new BNXOR();
  }
}
