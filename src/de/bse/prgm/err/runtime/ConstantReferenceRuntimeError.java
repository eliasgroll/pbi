package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class ConstantReferenceRuntimeError implements IError {

  public ConstantReferenceRuntimeError(String var) {
    super();
    this.var = var;
  }

  private String var;

  @Override
  public String errorMsg() {
    return "[Error, runtime]Ambigous name of reference \"" + var + "\"";
  }

}
