package de.bse.prgm.war;


public class LineNumberWarning implements IWarning {

  @Override
  public String warningMsg() {
    return "[Warn]The program is very long";
  }

}
