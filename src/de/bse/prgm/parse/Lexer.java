package de.bse.prgm.parse;

import de.bse.prgm.cmd.Shebang;
import de.bse.prgm.err.FileToReadIsEmptyError;
import de.bse.prgm.err.IError;
import de.bse.prgm.err.ShebangMissingError;
import de.bse.prgm.err.SyntaxError;
import de.bse.prgm.struct.Program;
import de.bse.prgm.war.LineNumberWarning;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The Lexer verifies every line of code, and sends verified lines to the
 * parser.
 * 
 * @author Elias Groll
 * @version 2.15
 */
public class Lexer {

  /**
   * Lexer cannot be instantiated.
   */
  private Lexer() {
    // it is not possible to create a Lexer-object
  }

  /**
   * All tokens the Lexer can understand/verify.
   */
  private static final String[] knownCommands = new String[] { "HIGH", "LOW",
      "INSTRUCT", "OUTPUT", "PAUSE", "GOTO", "IF", "END", "SOUND", "GOSUB",
      "RETURN", "FOR", "NEXT", "DEBUG", "BREAKPOINT", "SYMBOL", "LOOKUP",
      "LOOKDOWN", "SEROUT", "SERIN", "PWM", "POT", "PULSIN", "PULSOUT", "LET",
      "INPUT", "ASSERT", "ACTIVATEINFO", "DEACTIVATEINFO", "BRANCH", "NAP",
      "SLEEP", "EEPROM", "RANDOM", "READ", "WRITE", "TOGGLE", "REVERSE" };

  private static Scanner prgmScanner;

  /**
   * Checks whether the given line is an accepted token.
   * 
   * @param line
   *          to be checked if it's a known command
   */
  private static boolean isAKnownCommand(String line) {
    boolean retVal = false;

    for (String cmd : knownCommands) {
      if ((line.startsWith(cmd) ^ line.endsWith(":")) ^ line.contains("=")) {
        // excluding or -> a line cannot be a label and a command or allocation
        retVal = true;
      }
    }

    return retVal;
  }

  /**
   * Checks whether the given line is a comment / blank line or not.
   * 
   * @param line
   *          to be checked
   * @return true/false if the line is a comment or blank line
   */
  private static boolean isACommentOrABlankLine(String line) {
    boolean retVal = false;
    if (line.startsWith("'") || line.isEmpty()) {
      retVal = true;
    }

    return retVal;

  }

  /**
   * Checks whether the given line is a BS1-Shebang or not.
   * 
   * @param line
   *          to be checked if it is a shebang or not
   * @return true/false if the line is a BS1-Shebang
   */
  private static boolean isAShebang(String line) {
    boolean retVal = false;
    if (line.matches("'\\s*\\{\\$STAMP\\s+BS1\\}")) {
      retVal = true;
    }
    return retVal;

  }

  /**
   * Creates an executable Program from a given String.
   * 
   * @param prgm
   *          (String) out of which the program will be created
   * @return Program createdProgam
   */
  public static Program createProgramFromString(String prgm) {
    Program retVal = new Program();
    prgmScanner = new Scanner(prgm);
    int lineNumber = 1;
    try {
      String actualLine = prgmScanner.nextLine();
      actualLine = actualLine.trim();
      if (!isAShebang(actualLine)) {
        retVal.addError(new ShebangMissingError());
      }
      retVal.addCommand(new Shebang());

      while (prgmScanner.hasNextLine()) {
        lineNumber++;
        actualLine = prgmScanner.nextLine();
        actualLine = cutInlineComments(actualLine);
        actualLine = actualLine.trim();
        if (isACommentOrABlankLine(actualLine)) {
          continue;
        } else if (isAKnownCommand(actualLine)
            && !(isACommentOrABlankLine(actualLine))) {
          retVal.addInstance(Parser.parseLine(actualLine, lineNumber));
        } else {
          retVal.addError(new SyntaxError(lineNumber));
        }
      }
    } catch (NoSuchElementException e) {
      retVal.addError(new FileToReadIsEmptyError());
    } catch (Exception e) {
      retVal.addError(new IError() {

        @Override
        public String errorMsg() {
          return "[Error, internal]Parser error";
        }
      });
    }
    if (lineNumber > 128) {
      retVal.addWarning(new LineNumberWarning());
    }
    return retVal;
  }

  /**
   * Returns an array of strings containing all tokens.
   * 
   * @return array of strings containing all commands known to the Lexer
   */
  public static String[] getKnownCommands() {
    return knownCommands;
  }

  /**
   * Cuts everything after the comment-tag ("'").
   * 
   * @param line
   *          to be checked for inline-comments
   * @return line without inline-comments
   */
  private static String cutInlineComments(String line) {
    if (line.contains("'")) {
      return line.substring(0, line.indexOf("'"));
    } else {
      return line;
    }
  }

}
