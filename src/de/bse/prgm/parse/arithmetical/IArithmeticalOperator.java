package de.bse.prgm.parse.arithmetical;

import de.bse.vm.var.IVariable;

/**
 * Interface of all Arithmetical Operators.
 * @author Elias Groll
 * @version 2.15
 */
public interface IArithmeticalOperator {
  /**
   * Calculates the result of val1 operator val2
   * and returns the result as 16-bit IVariable.
   * @param val1 Value 1 of the calculation
   * @param val2 Value 2 of the calculation
   * @return result (IVariable) of the calculation
   */
  public IVariable calculate(IVariable val1, IVariable val2);
}
