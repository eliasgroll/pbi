package de.bse.prgm.err;

public class HardwareError extends LineSpecificError {

  int pin;

  public HardwareError(int lineNumber, int pin) {
    super(lineNumber);
  }

  
  public String errorMsg() {
    return prefix() + "P" + pin + " is not located at the bs1";
  }

}
