package de.bse.prgm.err;

public class HardwareError extends LineSpecificError {

  int pin;

  public HardwareError(int lineNumber, int pin) {
    super(lineNumber);
  }

  @Override
  public String errorMsg() {
    return "[Error, line " + lineNumber + "]P" + pin + " is not located at the bs1";
  }

}
