package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 * This program converts between Braille, ASCII, and Unicode representations using
 * two terminal-line inputs.
 * 
 * @author Maral Bat-Erdene
 */
public class BrailleASCII {
  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   * Converts ASCII characters to Braille bit strings in a given string.
   * @param input The ASCII string to convert.
   * @return A string representing the Braille translation.
   */
  private static String convertToBraille(String input) {
    String brailleString = "";
    for (int i = 0; i < input.length(); i++) {
      brailleString += BrailleAsciiTables.toBraille(input.charAt(i));
    } // for
    return brailleString;
  } // convertToBraille(String)

  /**
   * Converts Braille bit strings to ASCII characters in a given string.
   * @param input The Braille bit string to convert.
   * @return The corresponding ASCII string.
   */
  private static String convertToAscii(String input) {
    String asciiString = "";
    // Check if the Braille bit string is valid
    if (!isBrailleBits(input)) {
      System.err.println("Invalid bit string.");
    } // if

    for (int i = 0; i < input.length(); i += 6) {
      asciiString += BrailleAsciiTables.toAscii(input.substring(i, i + 6));
    } // for
    return asciiString;
  } // convertToAscii(String)

  /**
   * Converts either ASCII characters or Braille bits to Unicode Braille characters.
   * @param input The string to convert.
   * @return The corresponding Unicode Braille translation.
   */
  private static String convertToUnicode(String input) {
    String unicodeString = "";
    // If the input is ASCII, convert it to Braille first
    if (!isBrailleBits(input)) {
      input = convertToBraille(input);
    } // if

    // Check if the Braille bit string has a valid length
    if ((input.length() % 6) != 0) {
      System.err.println("Invalid length of bit string.");
    } // if

    for (int i = 0; i < input.length(); i += 6) {
      unicodeString += BrailleAsciiTables.toUnicode(input.substring(i, i + 6));
    } // for
    return unicodeString;
  } // convertToUnicode(String)

  /**
   * Helper method to check if the input string is in correct Braille format.
   * @param input The string to check.
   * @return true if the string is in Braille bit format, false otherwise.
   */
  private static boolean isBrailleBits(String input) {
    // Check if the input is null or empty
    if (input == null) {
      return false;
    } // if

    // Check if the length of the input is a multiple of 6
    if (input.length() % 6 != 0) {
      return false;
    } // if

    // Check if all characters in the string are 0 or 1
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (c != '0' && c != '1') {
        return false;
      } // if
    } // for
    return true;
  } // isBrailleBits(String)

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Main method that processes command-line arguments for translation.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
  
    // Validate input arguments
    if (args.length != 2) {
      pen.println("Invalid input. Try again with a target character set and the message.");
      return;
    } // if
  
    String finalString;
    // Convert the target character set to lowercase for case-insensitive comparison
    String toType = args[0].toLowerCase();
    String input = args[1];

    // Determine the correct translation table and perform the conversion
    if (toType.equals("braille")) {
      finalString = convertToBraille(input);
    } else if (toType.equals("ascii")) {
      finalString = convertToAscii(input);
    } else if (toType.equals("unicode")) {
      finalString = convertToUnicode(input);
    } else {
      pen.println("Invalid target character set. Use braille, ascii, or unicode.");
      return;
    } // if/else

    pen.println(finalString);
    pen.close();
  } // main(String[])
} // class BrailleASCII
