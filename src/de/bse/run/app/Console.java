package de.bse.run.app;

import java.util.Scanner;

public class Console implements IConsole {

  public void printLn(String line) {
    System.out.println(line);
  }

  @SuppressWarnings("resource")
  
  public String readLn() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  
  public void clearConsole() {
    try {
      final String os = System.getProperty("os.name");

      if (os.contains("Windows")) {
        Runtime.getRuntime().exec("cls");
      } else {
        Runtime.getRuntime().exec("clear");
      }
    } catch (Exception e) {
    	//do nothing
    }
  }

  
  public void print(String line) {
    System.out.print(line);
  }

}
