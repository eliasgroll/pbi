package de.bse.prgm.err.runtime;

public class LabelDoesNotExistRuntimeError extends AmbigousLabelRuntimeError {

  public LabelDoesNotExistRuntimeError(String name) {
    super(name);

  }

  
  public String errorMsg() {
    return "[Error, runtime]Couldn't jump to missing label \"" + name + "\"";
  }
}
