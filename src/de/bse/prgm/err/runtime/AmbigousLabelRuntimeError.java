package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class AmbigousLabelRuntimeError implements IError {

  String name;

  public AmbigousLabelRuntimeError(String name) {
    this.name = name;
  }

  
  public String errorMsg() {
    return "[Error, runtime]Couldn't jump to ambigous label \"" + name + "\"";
  }

}
