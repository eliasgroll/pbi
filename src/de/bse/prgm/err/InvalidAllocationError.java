package de.bse.prgm.err;

import de.bse.prgm.err.IError;

public class InvalidAllocationError implements IError {

  public InvalidAllocationError(String allocation, int lineNumber) {
    this.allocation = allocation;
    this.lineNumber = lineNumber;
  }

  int lineNumber;
  String allocation;

  @Override
  public String errorMsg() {
    return "[Error, line " + lineNumber + "]The allocation \"" + allocation
        + "\" is not executable";
  }

}
