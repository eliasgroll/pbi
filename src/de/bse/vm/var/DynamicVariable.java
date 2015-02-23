package de.bse.vm.var;

/**
 * A variable with a specified amount of bits.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class DynamicVariable extends ConglomerateVariable {

  public DynamicVariable(int numberOfBits) {
    super(new Bit[numberOfBits]);
  }

  public DynamicVariable(Bit[] bits) {
    super(bits);
  }

}
