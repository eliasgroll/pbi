package de.bse.prgm.parse.arithmetical;

/**
 * Represents a MAX operator in form of an arithmetical operator
 * @author Jonas Reichmann
 * @version 2.15
 */
public class Max extends ArithmeticalOperator {

  /**
   * Executes the calculation and stores the result in retVal.
   */
  @Override
  protected void exec() {
    short left = (short) leftSide.getValue();
    short right = (short) rightSide.getValue();
        
    if (left > right) {
      retVal.setValue(left);
    } else {
      retVal.setValue(right);
    }
  }

  /**
   * Returns a new Max instance.
   * @return a new Max instance
   */
  @Override
  public ArithmeticalOperator create() {
    return new Max();
  }
}
