package de.bse.prgm.err;

public abstract class LineSpecificError implements IError {
  protected int lineNumber;

  public LineSpecificError(int lineNumber) {
    this.lineNumber = lineNumber;
  }

}
