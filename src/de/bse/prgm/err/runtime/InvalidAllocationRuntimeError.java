package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class InvalidAllocationRuntimeError implements IError {

  public InvalidAllocationRuntimeError(String allocation) {
    this.allocation = allocation;
  }

  String allocation;

  
  public String errorMsg() {
    return "[Error, runtime]The allocation \"" + allocation
        + "\" is not executable";
  }

}
