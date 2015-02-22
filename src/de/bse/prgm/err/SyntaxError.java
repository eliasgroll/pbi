package de.bse.prgm.err;

public class SyntaxError extends LineSpecificError {

  public SyntaxError(int lineNumber) {
    super(lineNumber);

  }

  @Override
  public String errorMsg() {
    return "[Error, line " + lineNumber + "]Incorrect syntax";
  }

}
