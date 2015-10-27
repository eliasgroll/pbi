package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class BadFormatRuntimeError implements IError {

  public BadFormatRuntimeError(String varString) {
    this.varString = varString;
  }

  String varString;

  
  public String errorMsg() {
    return "[Error, runtime]\"" + varString + "\" has a bad format";
  }

}
