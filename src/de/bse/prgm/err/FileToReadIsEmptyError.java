package de.bse.prgm.err;

public class FileToReadIsEmptyError implements IError {

  @Override
  public String errorMsg() {
    return "[Error, line 1]The file to read is empty";
  }

}
