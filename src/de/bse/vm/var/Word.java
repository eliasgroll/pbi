package de.bse.vm.var;

public class Word extends ConglomerateVariable {

  public Word(Byte byte0, Byte byte1) {
    super(createBitArrayFromTwoBitArrays(byte0.getBits(), byte1.getBits()));

  }

  private static Bit[] createBitArrayFromTwoBitArrays(Bit[] bitArray0,
      Bit[] bitArray1) {
    Bit[] retVal = new Bit[bitArray0.length + bitArray1.length];
    for (int index = 0; index < bitArray0.length; index++) {
      retVal[index] = bitArray0[index];
    }
    for (int index = 0; index < bitArray1.length; index++) {
      retVal[bitArray0.length + index] = bitArray1[index];
    }
    return retVal;
  }

}
