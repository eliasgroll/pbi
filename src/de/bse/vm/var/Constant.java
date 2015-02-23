package de.bse.vm.var;

import de.bse.util.ParserException;

/**
 * An unchangeable 16 bit- variable.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Constant extends DynamicVariable {

  public Constant(int value) {
    super(16);
    super.setValue(value);
  }

  @Override
  public long getValue() {
    return super.getValue();
  }

  @Override
  public void setValue(long value) {
    throw new ParserException("Cannot change constant value");
  }

}
