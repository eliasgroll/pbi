package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class NullBytesVariableRuntimeError implements IError {

  
  public String errorMsg() {
    return "[Error, runtime]A variable without bytes can't be created";
  }

}
