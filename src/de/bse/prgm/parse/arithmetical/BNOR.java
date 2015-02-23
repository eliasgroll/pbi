package de.bse.prgm.parse.arithmetical;

/**
 * Represents a binary NOR operator in form of an arithmetical operator
 * @author Jonas Reichmann
 * @version 2.15
 */
public class BNOR extends ArithmeticalOperator {

  /**
   * Executes the calculation (|/) and stores the result in retVal.
   */
  @Override
  protected void exec() {
    retVal.setValue(leftSide.getValue() |  ~rightSide.getValue());
  }

  /**
   * Returns a new BNOR instance.
   * @return new BNOR instance
   */
  @Override
  public ArithmeticalOperator create() {
    return new BNOR();
  }
}
