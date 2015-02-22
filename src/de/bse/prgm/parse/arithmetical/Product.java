package de.bse.prgm.parse.arithmetical;

/**
 * Represents a product in form of an arithmetical operator
 * @author Jonas Reichmann
 * @version 2.15
 */
public class Product extends ArithmeticalOperator {

  /**
   * Executes the calculation and stores the result in retVal
   */
  @Override
  protected void exec() {
    retVal.setValue(leftSide.getValue() *  rightSide.getValue());
  }

  /**
   * Returns a new Product instance
   * @return a new Product instance
   */
  @Override
  public ArithmeticalOperator create() {
    return new Product();
  }
}
