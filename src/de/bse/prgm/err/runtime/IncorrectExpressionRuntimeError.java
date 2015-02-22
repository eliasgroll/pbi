package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class IncorrectExpressionRuntimeError implements IError {

  public IncorrectExpressionRuntimeError(String expression) {
    this.expression = expression;
  }

  String expression;

  @Override
  public String errorMsg() {
    return "[Error, runtime]The expression \"" + expression + "\" is not valid";
  }

}
