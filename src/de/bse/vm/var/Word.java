package de.bse.vm.var;

/**
 * Represents a word inside the BS1 architecture.
 * @author Elias Groll
 * @version 2.15
 */
public class Word extends ConglomerateVariable {

  /**
   * Creates a new Word.
   * @param byte0 first part of the word
   * @param byte1 second part of the word
   */
  public Word(Byte byte0, Byte byte1) {
    super(createBitArrayFromTwoBitArrays(byte0.getBits(), byte1.getBits()));

  }

  /**
   * Creates a 16 Bit value from two 8 Bit values.
   * @param bitArray0 first part of the new value
   * @param bitArray1 second part of the new value
   * @return 16 Bit value consisting out of the two Bytes
   */
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
