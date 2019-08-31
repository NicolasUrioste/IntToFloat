/*******************************************************************************
* CS380 Compilers - Group Final Project
* Tom Gruner
* Nicolas Urioste
* Jerry Reinoehl
*
* Lexer.java
*
* TODO Description
*******************************************************************************/

import java.io.*;

public class Lexer {
  
  private static final char SENTINEL = ';';
  private static final int BUFFER_SIZE = 4096;
  
  private InputStream in;
  private TokenType tokenType;
  private int line = 0, col = 0;
  private StringBuffer tokenBuffer = new StringBuffer();
  private StringBuffer lineBuffer = new StringBuffer();
  
  private byte[] buffer = new byte[BUFFER_SIZE];
  private int p = 0; // pointer in buffer
  private char c;
  private int charsRead;

  //****************************************************************************

  public Lexer(InputStream in) {
    this.in = in;
    buffer[p] = 0; // force next call to next() to call read()
  } // end constructor
  
  //****************************************************************************
  
  public Token nextToken() throws IOException {
    skipWhitespace();
    
    if (Character.isDigit(c)) {
      return nextIntegerToken();
    }
    else if (c == SENTINEL) {
      return new Token(TokenType.SENTINEL);
    }
    else {
      System.err.println("Syntax Error encountered in next()");
      return new Token(TokenType.SENTINEL);
    }
  } // end next
  
  //****************************************************************************
  
  private IntegerToken nextIntegerToken() throws IOException {
    tokenBuffer.setLength(0); // clear tokenBuffer
    
    // continue collecting characters as long as they are digits
    for (; Character.isDigit(c); c = nextChar())
      tokenBuffer.append(c);
    
    // if non-whitespace char terminates integer throw SyntaxError
    if (!Character.isWhitespace(c)) {
      System.err.println("Found non-whitespace terminating integer");
    }
    
    return new IntegerToken(tokenBuffer.toString());
  }
  
  //****************************************************************************
  
  private void skipWhitespace() throws IOException {
    while (true) {
      c = nextChar();
      
      switch (c) {
        case ' ':
          col++;
          break;
        case '\t':
          col++; // align col
          break;
        case '\n':
          line++;
          col = 0;
          break;
        case 0:
          c = SENTINEL;
        default:
          return;
      }
    }
  } // end skipWhitespace
  
  //****************************************************************************
  
  private char nextChar() throws IOException {
    if (buffer[p] == 0) {
      if ((charsRead = in.read(buffer, 0, BUFFER_SIZE-1)) == -1)
        return 0;
        
      p = 0;
      buffer[charsRead] = 0;
    }
    
    return (char)buffer[p++];
  } // end nextChar
  
} // end class Lexer
