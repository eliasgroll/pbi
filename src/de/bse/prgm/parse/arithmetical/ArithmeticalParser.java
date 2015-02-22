package de.bse.prgm.parse.arithmetical;

import de.bse.util.ParserException;
import de.bse.util.Tokenizer;
import de.bse.util.Tokenizer.Token;
import de.bse.vm.Machine;
import de.bse.vm.var.DynamicVariable;
import de.bse.vm.var.IVariable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * Arithmetical Parser which can parse arithmetical terms
 * @author Elias Groll
 * @version 2.15
 */
public class ArithmeticalParser {

  private static final int EPSILON = 0;
  private static final int OPERATOR = 1;
  private static final int VARIABLE = 2;
  private static final int NUMBER = 3;

  private static Tokenizer tokenizer = null;

  LinkedList<Token> tokens;
  Token lookahead;
  private static Map<String, ArithmeticalOperator> knownOperators = null;

  private void nextToken() {
    if (!tokens.isEmpty()) {
      lookahead = tokens.removeLast();
    } else {
      lookahead = tokenizer.new Token(EPSILON, null);
    }
  }

  /**
   * Parses an arithmetical expression depending on a machine supplied.
   * @param calc (string) to be calculated
   * @param machine used to lookup variables
   * @return IVariable containing the result of the calculation
   */
  public IVariable parseArithmeticalOperation(String calc, Machine machine) {
    if (tokenizer == null) {
      String allFormattersAndHashTag = machine.getAllFormattersAndHashTag();
      tokenizer = new Tokenizer();
      tokenizer
          .add(
              "\\+|-|/(?!/)|//|\\*(?!\\*)|\\*\\*|&(?!/)|\\^(?!/)|\\|(?!/)|MAX|MIN|&/|\\|/|\\^/",
              OPERATOR);
      tokenizer.add(allFormattersAndHashTag + "*\\w[\\w\\d]*", VARIABLE);
      tokenizer.add("\\d+", NUMBER);
      knownOperators = new HashMap<String, ArithmeticalOperator>();
      knownOperators.put("+", new Addition());
      knownOperators.put("-", new Subtraction());
      knownOperators.put("*", new Product());
      knownOperators.put("**", new HighProduct());
      knownOperators.put("/", new Division());
      knownOperators.put("&", new BAND());
      knownOperators.put("|", new BOR());
      knownOperators.put("^", new BXOR());
      knownOperators.put("|/", new BNOR());
      knownOperators.put("&/", new BNAND());
      knownOperators.put("^/", new BNXOR());
      knownOperators.put("//", new Modulo());
      knownOperators.put("MIN", new Min());
      knownOperators.put("MAX", new Max());

    }
    tokenizer.tokenize(calc);
    tokens = tokenizer.getTokens();
    IVariable retVal = parseTerm(machine);
    if (lookahead.token != EPSILON) {
      throw new ParserException("Unexpected symbol found");
    }
    return retVal;
  }

  /**
   * Parses a term on the machine.
   * @param machine used to parse on
   * @return IVariable with the result (relies on parseLeftTerm())
   */
  private IVariable parseTerm(Machine machine) {
    boolean allowUnary = (lookahead != null)
        && (lookahead.sequence.equals("-"));
    nextToken();
    if (lookahead.token == VARIABLE || lookahead.token == NUMBER) {
      IVariable val = parseValue(lookahead.sequence, machine);
      return parseLeftTerm(val, machine);
    } else if (allowUnary && lookahead.token == EPSILON) {
      IVariable val = new DynamicVariable(16);
      val.setValue(0);
      return parseLeftTerm(val, machine);
    } else if (lookahead.token == EPSILON) {
      throw new ParserException("Nothing to parse");
    } else {
      throw new ParserException("Unexpected symbol found");
    }
  }

  /**
   * Parses the left side of the term depending on a given machine.
   * @param val to be parsed
   * @param machine to parse on
   * @return operator instance found in the left term
   */
  private IVariable parseLeftTerm(IVariable val, Machine machine) {
    nextToken();
    if (lookahead.token == OPERATOR) {
      ArithmeticalOperator operator = knownOperators.get(lookahead.sequence)
          .create();
      operator.init(parseTerm(machine), val);
      return operator;
    } else if (lookahead.token == EPSILON) {
      return val;
    } else {
      throw new ParserException("Unexpected symbol found");
    }
  }

  /**
   * Parses a value depending on the machine.
   * @param val to be parsed
   * @param machine to be parsed on
   * @return IVariable with value on machine
   */
  private IVariable parseValue(String val, Machine machine) {
    IVariable retVal = machine == null ? null : machine.parseIVariable(val);
    if (retVal == null) {
      retVal = new DynamicVariable(16);
      try {
        retVal.setValue(Integer.parseInt(val));
      } catch (NumberFormatException e) {
        throw new ParserException("Unexpected symbol found");
      }
    }
    return retVal;
  }

//   public static void main(String args[]) {
//   ArithmeticalParser psr = new ArithmeticalParser();
//   Machine machine = new Machine(null, null);
//   machine.B0.setValue(10);
//   IVariable op = psr.parseArithmeticalOperation("-3 MAX 2 + B0 * 2",
//   machine);
//   IVariable op2 = psr.parseArithmeticalOperation("B0-5", machine);
//   System.out.println(op.getValue());
//   machine.B0.setValue(11);
//   System.out.println(op.getValue());
//   }

}
