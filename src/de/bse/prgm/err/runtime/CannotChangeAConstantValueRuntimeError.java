package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class CannotChangeAConstantValueRuntimeError implements IError {

  public CannotChangeAConstantValueRuntimeError(String var) {
    this.var = var;
  }

  String var;

  @Override
  public String errorMsg() {
    return "[Error, runtime]" + var + " is a constant and can not be changed";
  }

}
