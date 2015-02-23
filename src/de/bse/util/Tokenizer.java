package de.bse.util;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
  private class TokenInfo {
    public final Pattern regex;
    public final int token;

    public TokenInfo(Pattern regex, int token) {
      super();
      this.regex = regex;
      this.token = token;
    }
  }

  public class Token {

    public final int token;
    public final String sequence;

    /**
     * Constructs a new Token.
     * @param token value of the token
     * @param sequence of the token
     */
    public Token(int token, String sequence) {
      super();
      this.token = token;
      this.sequence = sequence;
    }

  }

  private LinkedList<TokenInfo> tokenInfos;
  private LinkedList<Token> tokens;

  public Tokenizer() {
    tokenInfos = new LinkedList<TokenInfo>();
    tokens = new LinkedList<Token>();
  }

  public void add(String regex, int token) {
    tokenInfos
      .add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
  }

  /**
   * Tokenizes a given String.
   * @param str to be tokenized
   */
  public void tokenize(String str) {
    String string = str.trim();
    tokens.clear();
    while (!string.equals("")) {
      boolean match = false;
      for (TokenInfo info : tokenInfos) {
        Matcher matcher = info.regex.matcher(string);
        if (matcher.find()) {
          match = true;
          String tok = matcher.group().trim();
          string = matcher.replaceFirst("").trim();
          tokens.add(new Token(info.token, tok));
          break;
        }
      }
      if (!match) {
        throw new ParserException("Unexpected symbol found");
      }
    }
  }

  public LinkedList<Token> getTokens() {
    return tokens;
  }

}