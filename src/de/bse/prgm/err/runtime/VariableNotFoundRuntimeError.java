package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class VariableNotFoundRuntimeError implements IError {

  public VariableNotFoundRuntimeError(String var) {
    super();
    this.var = var;
  }

  String var;

  
  public String errorMsg() {
    return "[Error, runtime]Unknown variable \"" + var + "\"";
  }

}
