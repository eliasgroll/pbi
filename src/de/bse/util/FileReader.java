package de.bse.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

  private static Scanner scanner;

  public static String read(File file) throws FileNotFoundException {
    String retVal = "";
    scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      retVal += scanner.nextLine() + "\n";
    }

    return retVal;
  }
}
