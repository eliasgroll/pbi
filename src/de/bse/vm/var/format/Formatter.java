package de.bse.vm.var.format;

/**
 * Wrapper class for Java-Formatters.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Formatter {

  public static String convertToFormattedBinaryString(long value) {
    return "%" + convertToBinaryString(value);
  }

  public static String convertToBinaryString(long value) {
    return Long.toBinaryString(value);
  }

  public static long parseBinaryString(String string) {
    return Long.parseLong(string, 2);
  }

  public static String convertToFormattedHexString(long value) {
    return "$" + convertToHexString(value);
  }

  public static String convertToHexString(long value) {
    return Long.toHexString(value);
  }

  public static long parseHexString(String string) {
    return Long.parseLong(string, 16);
  }

  public static String convertToFormattedAsciiString(long value) {
    return "@" + convertToAsciiString(value);
  }

  public static String convertToAsciiString(long value) {
    char tmp = (char) (value % 256);
    return String.valueOf(tmp);
  }

  /**
   * Takes an ASCII string with length 1 and converts it to a long.
   * Will throw NumberFormatException when used with Strings longer than 1 character
   * @param string in ASCII which shall be converted
   * @return long representation of the ASCII character
   */
  public static long parseAsciiString(String string) {
    if (string.length() > 1) {
      throw new NumberFormatException();
    }
    return (long) string.charAt(0);
  }
}
