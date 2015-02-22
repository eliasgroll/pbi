package de.bse.run.app;

import java.util.Scanner;

public class Console implements IConsole {

  @Override
  public void printLn(String s) {
    System.out.println(s);
  }

  @SuppressWarnings("resource")
  @Override
  public String readLn() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  @Override
  public void clearConsole() {
    try {
      final String os = System.getProperty("os.name");

      if (os.contains("Windows")) {
        Runtime.getRuntime().exec("cls");
      } else {
        Runtime.getRuntime().exec("clear");
      }
    } catch (Exception e) {
    }
  }

  @Override
  public void print(String s) {
    System.out.print(s);
  }

}
