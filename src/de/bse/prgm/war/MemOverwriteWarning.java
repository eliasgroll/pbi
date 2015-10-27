package de.bse.prgm.war;

public class MemOverwriteWarning implements IWarning {

  
  public String warningMsg() {
    return "[Warning]You could have overwritten a command in eeprom";
  }

}
