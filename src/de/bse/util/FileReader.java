package de.bse.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * FileReader which uses a Scanner to read a file and give
 * back its entire content in a string using .read(File file).
 * @author Elias Groll
 * @version 2.15
 */
public class FileReader {

  /**
   * Scanner used to read the file.
   */
  private static Scanner scanner;

  /**
   * Reads from a given file, may throw a FileNotFoundException.
   * @param file to be read
   * @return a string containing the entire file
   * @throws FileNotFoundException
   */
  public static String read(File file) throws FileNotFoundException {
    String retVal = "";
    scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      retVal += scanner.nextLine() + "\n";
    }

    return retVal;
  }
}
