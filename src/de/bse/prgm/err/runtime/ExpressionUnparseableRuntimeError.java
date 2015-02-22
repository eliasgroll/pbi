package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class ExpressionUnparseableRuntimeError implements IError {

  String expression;

  public ExpressionUnparseableRuntimeError(String expression) {
    super();
    this.expression = expression;
  }

  @Override
  public String errorMsg() {
    return "[Error, runtime]Expression \"" + expression.trim()
        + "\"is not valid";
  }

}
