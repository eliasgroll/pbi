package de.bse.prgm.parse.arithmetical;

/**
 * Represents the upper 16 bits of a product
 * in form of an arithmetical operator
 * @author Jonas Reichmann, Elias Groll
 * @version 2.15
 */
public class HighProduct extends ArithmeticalOperator {

  /**
   * Executes the calculation (**) and stores the result in retVal
   */
  @Override
  protected void exec() {
    retVal.setValue((leftSide.getValue() * rightSide.getValue()) >> 16);
  }

  /**
   * Returns a new HighProduct instance
   * @return a new HighProduct instance
   */
  @Override
  public ArithmeticalOperator create() {
    return new HighProduct();
  }
}
