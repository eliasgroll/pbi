package de.bse.prgm.parse.arithmetical;

/**
 * Represents a MIN operator in form of an arithmetical operator
 * @author Jonas Reichmann, Elias Groll
 * @version 2.15
 */
public class Min extends ArithmeticalOperator {

  /**
   * Executes the calculation and stores the result in retVal
   */
  @Override
  protected void exec() {
    short left = (short) leftSide.getValue();
    short right = (short) rightSide.getValue();
        
    if (left < right) {
      retVal.setValue(left);
    } else {
      retVal.setValue(right);
    }
  }
  
  /**
   * Returns a new Min instance
   * @return a new Min instance
   */
  @Override
  public ArithmeticalOperator create() {
    return new Min();
  }

}
