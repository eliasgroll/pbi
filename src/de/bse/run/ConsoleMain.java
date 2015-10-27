package de.bse.run;

import de.bse.prgm.Interpreter;
import de.bse.prgm.parse.Lexer;
import de.bse.prgm.struct.Program;
import de.bse.run.app.Console;
import de.bse.run.app.IConsole;
import de.bse.util.Stopwatch;
import de.bse.vm.Machine;
import de.bse.vm.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsoleMain {

  private static IConsole console = new Console();
  private static Scanner scanner;

  /**
   * The console-application of the project.
   * 
   * @param args
   */
  public static void main(String[] args) {
    try {
      console.printLn("PiBasic VM [Version 2.15] \n by Elias Groll, Jonas Reichmann.\n");

      if (args.length == 0) {
        console.printLn("[Error, compile]No arguments - enter at least the path to your program");
        System.exit(1);
      }
      // default settings
      boolean printInfo = true;
      boolean emulate4Mhz = true;
      boolean printInternComposition = false;
      boolean ignoreWarnings = false;
      String path = null;
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-noinfo")) {
          printInfo = false;
        } else if (args[i].equals("-fast")) {
          emulate4Mhz = false;
        } else if (args[i].equals("-pic")) {
          printInternComposition = true;
        } else if (args[i].equals("-nowarn")) {
          ignoreWarnings = true;
        } else if (args[i].endsWith(".bs1")) {
          if (i != (args.length - 1)) {
            console.printLn("[Error, compile]The filepath has to be the last argument");
            System.exit(1);
          }
          path = args[i];

        } else {
          console.printLn("[Error, compile]Bad argument: \"" + args[i] + "\"");
          System.exit(1);
        }
      }
      if (path == null) {
        console.printLn("[Error, compile]Expected a filepath (*.bs1) as last argument");
        System.exit(1);
      }
      console.printLn("[Compile, " + path + "]");
      String code = null;
      try {
        code = getCodeFromFile(new File(path));
      } catch (FileNotFoundException e) {
        console.printLn("[Error, compile]File not found");
        System.exit(1);
      }
      Program program = Lexer.createProgramFromString(code);
      Settings settings = new Settings(printInfo, emulate4Mhz, printInternComposition,
          ignoreWarnings);
      Machine machine = new Machine(program, settings,null);
      Interpreter interpreter = new Interpreter(machine, console);
      String input = null;
      int execs;
      do {
        machine.setExecutionIndex(0);
        console.printLn("[Execute, " + path + "]");
        Stopwatch.start();
        execs = interpreter.run();
        console.printLn("[End of file, " + Stopwatch.stop() + " milliseconds, " + execs
            + " instructions]\n");
        do {
          console.print("Run again with given config?(y/n)");
          input = console.readLn();
        } while (!(input.equals("y") || input.equals("n")));
      } while (input.equals("y"));
    } catch (Exception e) {
      console.printLn("[Error, program]Sorry! Something went wrong");
    }
  }

  private static String getCodeFromFile(File file) throws FileNotFoundException {
    scanner = new Scanner(file);
    String retVal = "";
    while (scanner.hasNextLine()) {
      retVal += scanner.nextLine() + "\n";
    }
    return retVal;
  }

}
