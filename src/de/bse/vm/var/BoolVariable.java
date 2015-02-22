package de.bse.vm.var;

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
