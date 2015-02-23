package de.bse.vm.var;

import de.bse.util.ParserException;

/**
 * Wrapper for the system time. Not accessible at the bs1, but useful if you want tos do a
 * speed-test.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Clock implements IVariable {

  @Override
  public long getValue() {
    return System.currentTimeMillis();
  }

  @Override
  public void setValue(long value) {
    throw new ParserException("Cannot change constant value");
  }

}
