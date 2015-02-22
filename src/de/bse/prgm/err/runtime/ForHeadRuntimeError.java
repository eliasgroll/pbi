package de.bse.prgm.err.runtime;

import de.bse.prgm.err.IError;

public class ForHeadRuntimeError implements IError {

  String data;

  public ForHeadRuntimeError(String data) {
    this.data = data;
  }

  @Override
  public String errorMsg() {
    if (data.equals("")) {
      return "[Error, runtime]Invalid Head of FOR-Command, Vars not found:"
          + data;
    }
    return "[Error, runtime]Invalid Head of FOR-Command";
  }
}
