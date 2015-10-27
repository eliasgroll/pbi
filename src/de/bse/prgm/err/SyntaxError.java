package de.bse.prgm.err;

public class SyntaxError extends LineSpecificError {

  public SyntaxError(int lineNumber) {
    super(lineNumber);

  }

  
  public String errorMsg() {
    return prefix() + "Incorrect syntax";
  }

}
