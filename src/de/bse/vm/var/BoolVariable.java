package de.bse.vm.var;

/**
 * Wrapper for the primitive type boolean in java, to match IVariable.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public abstract class BoolVariable implements IVariable {
  protected boolean high;

  @Override
  public long getValue() {
    return high ? 1 : 0; // true = 1; false = 0
  }

  @Override
  public void setValue(long value) {
    this.high = value != 0 ? true : false; // if high != 0 -> true

  }
}
