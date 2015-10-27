package de.bse.prgm.err.parsing;

import de.bse.prgm.err.IError;

public class TooManySubroutineCallsError implements IError {

  
  public String errorMsg() {
    return "[Error, parse]There are only 16 calls of \"GOSUB\" allowed";
  }

}
