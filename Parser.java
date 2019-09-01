/*******************************************************************************
* CS380 Compilers - Group Final Project
* Tom Gruner
* Nicolas Urioste
* Jerry Reinoehl
*
* Parser.java
*
* Grammar for IntToFloat:
*   stmt -> ints ;
*   ints -> int , ints | int
*   int  -> [0-9]+ { print(int.value) }
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
      if (token.tokenType == TokenType.INTEGER) {
        out.write(((IntegerToken)token).getValue().getBytes());
        out.write(".0 ".getBytes());
        out.write("\n".getBytes());
        System.out.println(token);
        token = lexer.nextToken();
        System.out.println(token);
        if (token.tokenType == TokenType.SENTINEL)
          break;
        else
          match(TokenType.SEPARATOR);
      }
    } while (token.tokenType != TokenType.SENTINEL);
    
    out.write("\n".getBytes());
    out.flush();
  } // end translate
  
  //****************************************************************************
  
  public void stmt() throws IOException {
    token = lexer.nextToken();
    System.out.println("read token: " + token);
    ints();
    //match(TokenType.SENTINEL);
  } // end stmt
  
  //****************************************************************************
  
  private void ints() throws IOException {
    integer();
    if (token.tokenType == TokenType.SEPARATOR) {
      match(TokenType.SEPARATOR);
      ints();
    }
  } // end ints
  
  //****************************************************************************
  
  private void integer() throws IOException {
    System.out.println(((IntegerToken)token).getValue() + ".0");
    match(TokenType.INTEGER);
  } // end integer
  
  //****************************************************************************
  
  private void match(TokenType tokenType) throws IOException {
    if (token.tokenType == tokenType) {
      token = lexer.nextToken();
      System.out.println("read token: " + token);
    }
    else
      System.err.println("Syntax error");
  } // end match
  
} // end class Parser
