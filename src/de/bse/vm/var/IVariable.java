package de.bse.vm.var;

/**
 * The basic structure of a variable in the PiBasicInterpreter
 * 
 * @author Elias Groll, Jonas Reichmann
 * @version 2.15
 */
public interface IVariable {

  /**
   * Return the actual value of the variable. (unsigned)
   * 
   * @return value the value of the variable
   */
  public long getValue();

  /**
   * Set the value of the variable.
   * 
   * @param value
   *          the new value to be set
   */
  public void setValue(long value);

}
