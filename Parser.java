/*******************************************************************************
* CS380 Compilers - Group Final Project
* Tom Gruner
* Nicolas Urioste
* Jerry Reinoehl
*
* Parser.java
*
* TODO Description
*******************************************************************************/

import java.io.*;

public class Parser {

  private OutputStream out;
  private Lexer lexer;
  private Token token;
  
  //****************************************************************************
  
  public Parser(Lexer lexer, OutputStream out) {
    this.out = out;
    this.lexer = lexer;
  } // end constructor
  
  //****************************************************************************
  
  public void translate() throws IOException {   
    do {
      token = lexer.nextToken();
      if (token.getTokenType() == TokenType.INTEGER) {
        out.write(((IntegerToken)token).getValue().getBytes());
        out.write(".0 ".getBytes());
        out.write("\n".getBytes());
        System.out.println(token);
        token = lexer.nextToken();
        System.out.println(token);
        if (token.getTokenType() == TokenType.SENTINEL)
          break;
        else
          match(TokenType.SEPARATOR);
      }
    } while (token.getTokenType() != TokenType.SENTINEL);
    
    out.write("\n".getBytes());
    out.flush();
  } // end translate
  
  //****************************************************************************
  
  private void match(TokenType tokenType) throws IOException {
    if (token.getTokenType() == tokenType)
      token = lexer.nextToken();
    else
      System.err.println("Syntax error");
  } // end match
  
} // end class Parser
