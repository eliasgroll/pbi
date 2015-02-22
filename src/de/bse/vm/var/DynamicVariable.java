package de.bse.vm.var;

public class DynamicVariable extends ConglomerateVariable {

  public DynamicVariable(int numberOfBits) {
    super(new Bit[numberOfBits]);
  }
  
  public DynamicVariable(Bit[] bits) {
    super(bits);
  }

}
