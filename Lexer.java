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
  private char separator = ',';
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
    buffer[p] = 0; // force next call to nextChar() to fill buffer
  } // end constructor
  
  //****************************************************************************
  
  public Token nextToken() throws IOException {
    skipWhitespace();
    
    if (Character.isDigit(c) || c == '-') {
      return nextIntegerToken();
    }
    else if (c == SENTINEL) {
      return new Token(TokenType.SENTINEL);
    }
    else if (c == separator) {
      return new Token(TokenType.SEPARATOR);
    }
    else {
      System.err.println("Syntax Error encountered in next():" + line + ", " + col);
      return new Token(TokenType.SENTINEL);
    }
  } // end next
  
  //****************************************************************************
  
  private IntegerToken nextIntegerToken() throws IOException {
    tokenBuffer.setLength(0); // clear tokenBuffer
    
    if (c == '-')             // collect optional minus sign
      tokenBuffer.append(c);
    
    // continue collecting characters as long as they are digits
    for (; Character.isDigit(c); c = nextChar())
      tokenBuffer.append(c);
    
    putBackChar();            // replace last non-digit read
    return new IntegerToken(tokenBuffer.toString());
  }
  
  //****************************************************************************
  
  private void skipWhitespace() throws IOException {
    while (Character.isWhitespace(c = nextChar()));
    
      /*switch (c) {
        case ' ':
          col++;
          break;
        case '\t':
          col += 8 - (col % 8); // align col to tab width
          break;
        case '\n':
          line++;
          col = 0;
          lineBuffer.setLength(0);
          break;
        case 0:
          c = SENTINEL;
        default:
          return;
      }*/
    
  } // end skipWhitespace
  
  //****************************************************************************
  
  private char nextChar() throws IOException {
    if (buffer[p] == 0) {
      if ((charsRead = in.read(buffer, 0, BUFFER_SIZE-1)) == -1)
        return 0;
        
      p = 0;
      buffer[charsRead] = 0;
    }
    System.out.println("buffer[p]= " + (char)buffer[p] + " col= " + col);
    switch (buffer[p]) {
      case '\t':
        col += 8 - (col % 8); // align col to tab width
        break;
      case '\n':
        line++;
        col = 0;
        lineBuffer.setLength(0);
        break;
      default:
        col++;
    }
    
    lineBuffer.append(buffer[p]);
    return (char)buffer[p++];
  } // end nextChar
  
  //****************************************************************************
  
  private void putBackChar() {
    p--;
    col--;
  } // end putbackChar
  
  //****************************************************************************
  
  public boolean setSeparator(char separator) {
    if (Character.isDigit(separator) ||
        Character.isWhitespace(separator) ||
        separator == '-')
      return false;
    
    this.separator = separator;
    return true;
  } // end setSeparator
  
} // end class Lexer
