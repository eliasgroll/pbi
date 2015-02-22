package de.bse.vm.var;

import de.bse.util.ParserException;

public class Clock implements IVariable {

  private DynamicVariable time = new DynamicVariable(16);

  @Override
  public long getValue() {
    time.setValue(System.currentTimeMillis());
    return time.getValue();
  }

  @Override
  public void setValue(long value) {
    throw new ParserException("Cannot change constant value");
  }

}
