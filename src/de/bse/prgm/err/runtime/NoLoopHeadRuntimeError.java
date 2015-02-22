package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class NoLoopHeadRuntimeError implements IError {

  @Override
  public String errorMsg() {
    return "[Error, runtime]There is no loop to resume";
  }

}
