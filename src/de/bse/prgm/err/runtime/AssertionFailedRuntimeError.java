package de.bse.prgm.err.runtime;

import de.bse.prgm.err.LineSpecificError;

public class AssertionFailedRuntimeError extends LineSpecificError {

  public AssertionFailedRuntimeError(int lineNumber, String expression) {
    super(lineNumber);
    this.expression = expression;
  }

  String expression;

  
  public String errorMsg() {
    return "[Error, runime]Assertion \"" + expression + "\" failed in line "
        + lineNumber;
  }

}
