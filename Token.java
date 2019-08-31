/*******************************************************************************
* CS380 Compilers - Group Final Project
* Tom Gruner
* Nicolas Urioste
* Jerry Reinoehl
*
* Token.java
*
* TODO Description
*******************************************************************************/

public class Token {
  
  private final TokenType tokenType;
  
  //****************************************************************************
  
  public Token(TokenType tokenType) {
    this.tokenType = tokenType;
  } // end constructor
  
  //****************************************************************************
  
  // TODO
  
  public TokenType getTokenType() {
    return tokenType;
  } // end getTokenType
  
} // end class Token
