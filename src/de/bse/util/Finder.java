package de.bse.util;

/**
 * Finder which finds depending on the
 * begin- and endIndex.
 * @author Elias Groll
 * @version 2.15
 */
public class Finder {
  public int beginIndex = 0;
  public int endIndex = 0;

  /**
   * Constructs a new Finder.
   * @param beginIndex of the finder
   * @param endIndex of the finder
   */
  public Finder(int beginIndex, int endIndex) {
    super();
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }
}
