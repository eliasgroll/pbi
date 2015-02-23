package de.bse.vm.var;

/**
 * Represents a bit in the BS1, can only have the values 0 or 1.
 * Extends BoolVariable and can be used as one.
 * @author Elias Groll
 * @version 2.15
 */
public class Bit extends BoolVariable {

  /**
   * Constructs a new Bit with a preset value.
   * @param value of the new Bit
   */
  public Bit(long value) {
    this.setValue(value);
  }

  /**
   * Returns the state of the Bit in a string.
   * @return state of the bit (1=HIGH or 0 = LOW)
   */
  @Override
  public String toString() {
    return high ? "1" : "0";
  }
}
