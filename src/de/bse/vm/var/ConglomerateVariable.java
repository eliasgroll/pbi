package de.bse.vm.var;

import de.bse.vm.var.format.Formatter;

public class ConglomerateVariable implements IVariable {

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

  private String cutToByteLength(String s) {
    String retVal = "";
    if (s.length() > bits.length) {
      int index = s.length() - bits.length;
      retVal = s.substring(index);
    } else if (s.length() < bits.length) {
      int count = bits.length - s.length();
      retVal = s;
      for (int i = 0; i < count; i++) {
        retVal = "0" + retVal;
      }
    } else {
      retVal = s;
    }

    return retVal;
  }

  @Override
  public String toString() {
    String value = Formatter.convertToHexString(getValue());
    return value;
  }

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

  public void setValue(long x) {
    long val = x;
    while (val < 0) {
      val = (maximum - (x * -1));
    }
    String byteAsString = "";
    byteAsString = Long.toBinaryString(val);
    byteAsString = cutToByteLength(byteAsString);
    for (int index = 0; index < bits.length; index++) {
      bits[index].setValue(Integer.parseInt(String.valueOf(byteAsString
          .charAt(index))));
    }

  }

  public Bit[] getBits() {
    return bits;
  }

  public long getMaximum() {
    return maximum;
  }

  // public static void main(String[] args) {
  // Byte byte1 = new Byte(new Bit(0), new Bit(0), new Bit(0), new Bit(0),
  // new Bit(0), new Bit(0), new Bit(0), new Bit(0));
  // Byte byte2 = new Byte(new Bit(0), new Bit(0), new Bit(0), new Bit(0),
  // new Bit(0), new Bit(0), new Bit(0), new Bit(0));
  // Word word1 = new Word(byte1, byte2);
  // @SuppressWarnings("resource")
  // Scanner scanner = new Scanner(System.in);
  // while (true) {
  // int test = scanner.nextInt();
  // word1.setValue(test);
  // System.out.println("word1 " + word1.getValue() + "  " +
  // word1.toString());
  // System.out.println("byte1 " + byte1.getValue() + "  " +
  // byte1.toString());
  // System.out.println("byte2 " + byte2.getValue() + "  " +
  // byte2.toString());
  // }
  // }

}
