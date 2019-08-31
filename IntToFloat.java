/*******************************************************************************
* CS380 Compilers - Group Final Project
* Tom Gruner
* Nicolas Urioste
* Jerry Reinoehl
*
* IntToFloat.java
*
* TODO Description
*******************************************************************************/

// TODO PACKAGE

import java.io.*;

public class IntToFloat {

  private static final String USAGE = "IntToFloat [-i <inputfile>] [-o <outputfile>]";
  
  private InputStream in = System.in;    // set default input stream to stdin
  private OutputStream out = System.out; // set default output stream to stdout
  private char separator = ',';          // set default separator char
  
  //****************************************************************************
  
  // TODO
  
  public IntToFloat(String[] args) {
    Lexer lexer;
    Parser parser;
    
    processArguments(args); // sets in, out, and separator
    
    lexer = new Lexer(in);
    parser = new Parser(lexer, out);
    
    try {
      parser.translate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  } // end constructor
  
  //****************************************************************************
  
  // Iterates through arguments and sets member variables.
  
  private void processArguments(String[] args) {
    String arg;
    for (int i = 0; i<args.length; i++) {
      arg = args[i];
      
      if (isInputFileSwitch(arg)) {      // input file switch
        setInputStream(args, i);
        i++;
      }
      else if (isOutputFileSwitch(arg)) { // output file switch
        setOutputStream(args, i);
        i++;
      }
    }
  } // processArguments
  
  //****************************************************************************
  
  // Returns true if given string represents an input file switch.
  
  private boolean isInputFileSwitch(String str) {
    return str.equals("-i") || str.equals("/i");
  } // end isInputFileSwitch
  
  //****************************************************************************
  
  // Returns true if given string represents an output file switch.
  
  private boolean isOutputFileSwitch(String str) {
    return str.equals("-o") || str.equals("/o");
  } // end isOutputFileSwitch
  
  //****************************************************************************
  
  // Sets InputStream in to FileInputStream determined by file argument.
  // If file argument is not present or file cannot be opened, displays
  // error and exits.
  
  private void setInputStream(String[] args, int index) {
    // ensure the file was passed with switch
    if (index == args.length-1) {
      System.err.println("Input file not supplied");
      printUsage();
      System.exit(1);
    }
    // attempt to open input stream
    try {
      in = new FileInputStream(args[index+1]);
    }
    catch (FileNotFoundException e) {
      System.err.println("Input file \"" + args[index+1] + "\" not found");
      System.exit(1);
    }
  } // end setInputStream
  
  //****************************************************************************
  
  // Sets OutputStream out to FileOutputStream determined by file argument.
  // If file argument is not present or file cannot be opened, displays
  // error and exits.
  
  private void setOutputStream(String[] args, int index) {
    // ensure the file was passed with switch
    if (index == args.length-1) {
      System.err.println("Output file not supplied");
      printUsage();
      System.exit(1);
    }
    // attempt to open output stream
    try {
      out = new FileOutputStream(args[index+1]);
    }
    catch (FileNotFoundException e) {
      System.err.println("Output file \"" + args[index+1] + "\" not found");
      System.exit(1);
    }
  } // end setOutputStream
  
  //****************************************************************************
  
  // Display usage string.
  
  private void printUsage() {
    System.err.format("usage: %s\n", USAGE);
  } // end printUsage
  
  //****************************************************************************
  
  public static void main(String[] args) {
    new IntToFloat(args);
  } // end main
  
} // end class IntToFloat
