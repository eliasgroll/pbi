package de.bse.vm.var;

import de.bse.vm.var.format.Formatter;

/**
 * A variable which consists of individually accessible bits.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class ConglomerateVariable implements IVariable {
  /**
   * @param bits
   *          the bit[] which should be used to set a value.
   */
  public ConglomerateVariable(Bit[] bits) {
    this.bits = bits;
    for (int i = 0; i < bits.length; i++) {
      if (this.bits[i] == null) {
        this.bits[i] = new Bit(0);
      }
    }
    this.maximum = (long) 1 << this.bits.length;
  }

  protected Bit[] bits;
  protected long maximum;

  private String cutToByteLength(String byteAsString) {
    String retVal = "";
    if (byteAsString.length() > bits.length) {
      int index = byteAsString.length() - bits.length;
      retVal = byteAsString.substring(index);
    } else if (byteAsString.length() < bits.length) {
      int count = bits.length - byteAsString.length();
      retVal = byteAsString;
      for (int i = 0; i < count; i++) {
        retVal = "0" + retVal;
      }
    } else {
      retVal = byteAsString;
    }

    return retVal;
  }

  
  public String toString() {
    String value = Formatter.convertToHexString(getValue());
    return value;
  }

  /**
   * Get an individually accessible bit from the variable.
   * 
   * @param num
   *          the index of the bit in the variable
   * @return the bit at the specified index
   */
  public Bit getBit(int num) {
    if (num >= 0 && num < bits.length) {
      return bits[num];
    } else {
      return null;
    }
  }

  
  public long getValue() {
    long retVal = 0;
    long comp = 1;
    for (int i = bits.length - 1; i >= 0; i--) {
      if (bits[i].getValue() == 1) {
        retVal += comp;
      }
      comp *= 2;
    }

    return retVal;

  }

  
  public void setValue(long value) {
    long val = value;
    while (val < 0) {
      val = (maximum - (value * -1));
    }
    String byteAsString = "";
    byteAsString = Long.toBinaryString(val);
    byteAsString = cutToByteLength(byteAsString);
    for (int index = 0; index < bits.length; index++) {
      bits[index].setValue(Integer.parseInt(String.valueOf(byteAsString.charAt(index))));
    }

  }

  public Bit[] getBits() {
    return bits;
  }

  public long getMaximum() {
    return maximum;
  }

}
