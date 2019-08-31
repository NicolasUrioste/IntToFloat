/*******************************************************************************
* CS380 Compilers - Group Final Project
* Tom Gruner
* Nicolas Urioste
* Jerry Reinoehl
*
* IntegerToken.java
*
* TODO Description
*******************************************************************************/

public class IntegerToken extends Token {

  private final String value;
  
  //****************************************************************************

  public IntegerToken(String value) {
    super(TokenType.INTEGER);
    this.value = value;
  } // end constructor
  
  //****************************************************************************
  
  public String getValue() {
    return value;
  } // end getValue
  
} // end class IntegerToken
