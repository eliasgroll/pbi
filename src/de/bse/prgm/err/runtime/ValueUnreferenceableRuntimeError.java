package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class ValueUnreferenceableRuntimeError implements IError {

  public ValueUnreferenceableRuntimeError(String value) {
    super();
    this.value = value;
  }

  String value;

  
  public String errorMsg() {
    return "[Error, runtime]\"" + value + "\" is not a constant or variable";
  }

}
