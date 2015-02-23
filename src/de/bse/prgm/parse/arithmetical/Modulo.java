package de.bse.prgm.parse.arithmetical;

/**
 * Represents a Modulo Operator in form of an arithmetical operator
 * @author Jonas Reichmann
 * @version 2.15
 */
public class Modulo extends ArithmeticalOperator {

  /**
   * Executes the calculation (//) and stores the result in retVal.
   */
  @Override
  protected void exec() {
    retVal.setValue(leftSide.getValue() %  rightSide.getValue());
  }

  /**
   * Returns a new Modulo instance.
   * @return a new Modulo instance
   */
  @Override
  public ArithmeticalOperator create() {
    return new Modulo();
  }
}
