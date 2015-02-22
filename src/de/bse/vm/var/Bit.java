package de.bse.vm.var;

public class Bit extends BoolVariable {

  public Bit(long value) {
    this.setValue(value);
  }

  @Override
  public String toString() {
    return high ? "1" : "0";
  }
}
