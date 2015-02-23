package de.bse.run.app;

/**
 * Interface for all consoles the BSE can use to
 * print data to and read data from.
 * @author Elias Groll
 * @version 2.15
 */
public interface IConsole {
  /**
   * Prints a given line to the console and adds a newline.
   * @param line to be printed to the console
   */
  public void printLn(String line);

  /**
   * Prints a given line to the console,
   * but does not add a newline.
   * @param line to be printed to the console
   */
  public void print(String line);
  
  /**
   * Reads a line from the console.
   * @return line from the console which was entered by the user
   */
  public String readLn();
  
  /**
   * Clears the console.
   */
  public void clearConsole();
  
}
