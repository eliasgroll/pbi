package de.bse.vm.storage;

import de.bse.vm.var.Byte;

public class EEPROM {

  public static int CMD = 204;
  private Byte b = new Byte();

  public EEPROM(int commands) {
    this.storage = new Byte[256];
    for (int i = 0; i < storage.length; i++) {
      storage[i] = new Byte();
    }
    for (int i = 255; i >= 255 - commands; i--) {
      storage[i].setValue(CMD);
    }
  }

  private Byte[] storage;

  public boolean write(long location, long value) {
    b.setValue(location);
    boolean retVal = (storage[(int) b.getValue()].getValue() == CMD);
    storage[(int) b.getValue()].setValue(value);
    return retVal;
  }

  public long read(long location) {
    b.setValue(location);
    return storage[(int) b.getValue()].getValue();
  }

  public String toString() {
    String memDump = "";
    
    for (int i = 0; i < storage.length; i++) {
      if (i % 16 == 0 && i != 0) {
        memDump += "\n";
      }
      memDump += ((storage[i].toString().length() == 1) ? ("0" + storage[i].toString()) : (storage[i].toString())) + " ";
      
    }
    
    return memDump;
  }
}
