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
  
  //****************************************************************************
  
  public Parser(Lexer lexer, OutputStream out) {
    this.out = out;
    this.lexer = lexer;
  } // end constructor
  
  //****************************************************************************
  
  public void translate() throws IOException {
    Token token;
    
    do {
      token = lexer.nextToken();
      if (token.getTokenType() == TokenType.INTEGER) {
        out.write(((IntegerToken)token).getValue().getBytes());
        out.write(".0 ".getBytes());
      }
    } while (token.getTokenType() != TokenType.SENTINEL);
    
    out.write("\n".getBytes());
    out.flush();
  } // end translate
  
} // end class Parser
