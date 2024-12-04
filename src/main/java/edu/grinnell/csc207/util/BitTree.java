package edu.grinnell.csc207.util;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Trees intended to be used in storing mappings between fixed-length 
 * sequences of bits and corresponding values.
 *
 * @author Maral Bat-Erdene
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * The fixed length of the bit sequences stored in the tree.
   */
  int length;

  /**
   * The root of the tree.
   */
  BitTreeNode root;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructs a new BitTree with the specified bit length.
   * 
   * @param n the fixed length of bit sequences for this tree.
   * @throws IllegalArgumentException if given n is less than or equal to 0.
   */
  public BitTree(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    } // if
    this.length = n;
    this.root = null;
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+
  /**
   * Checks if the given bit sequence is valid for this tree.
   * A valid bit sequence must have the correct length and contain 0 or 1.
   * 
   * @param bits the bit sequence to validate.
   * @return true if the sequence is valid, or false if not.
   */
  private boolean isValid(String bits) {
    if (bits.length() != this.length) {
      return false;
    } // if

    for (int i = 0; i < this.length; i++) {
      char bit = bits.charAt(i);
      if (bit < '0' || bit > '1') {
        return false;
      } // if
    } // for

    return true;
  } // isValid(String)

  /**
   * Helper method for setting a value in the tree.
   * 
   * @param node  the current node.
   * @param bits  the bit sequence showing the path.
   * @param value the value to store at the specified path.
   * @param index the current bits' index.
   * @return the updated node.
   */
  private BitTreeNode setHelper(BitTreeNode node, String bits, String value, int index) {
    // Found the correct position, then store the value
    if (index == this.length) {
      return new BitTreeLeaf(value);
    } // if

    // The root or current node is empty, thus create new branches
    if (node == null) {
      node = new BitTreeInteriorNode();
    } // if
    
    // Otherwise, create new branches
    BitTreeInteriorNode branch = (BitTreeInteriorNode) node;
    char bit = bits.charAt(index);

    if (bit == '0') {
      branch.left = setHelper(branch.left, bits, value, index + 1);
    } else{
      branch.right = setHelper(branch.right, bits, value, index + 1);
    } // if/else

    return branch;
  } // setHelper(BitTreeNode, String, String, int)
  
  /**
   * Helper method for getting a value from the tree.
   * 
   * @param node  the current node.
   * @param bits  the bit sequence showing the path.
   * @param index the current bit index.
   * @return the value at the specified path.
   * @throws IndexOutOfBoundsException if the path is invalid or no value exists at the path.
   */
  private String getHelper(BitTreeNode node, String bits, int index) {
    // If node not found
    if (node == null) {
      throw new IndexOutOfBoundsException("Invalid node.");
    } // if

    // If at the end of of the branch
    if (index == this.length) {
      if (node instanceof BitTreeLeaf bitTreeLeaf) {
        return bitTreeLeaf.value();
      } else {
        throw new IndexOutOfBoundsException("Trouble translating because no corresponding value.");
      } // if/else
    } // if

    BitTreeInteriorNode branch = (BitTreeInteriorNode) node;
    char bit = bits.charAt(index);

    if (bit == '0') {
      return getHelper(branch.left, bits, index + 1);
    } else { // bit == '1'
      return getHelper(branch.right, bits, index + 1);
    } // if/else
  } // getHelper(BitTreeNode, String, int)

  /**
   * Helper method for dumping the tree's contents in CSV format.
   * 
   * @param pen   the PrintWriter used to output the data.
   * @param node  the current node.
   * @param index the current path represented as a string of bits.
   */
  void dumpHelper(PrintWriter pen, BitTreeNode node, String index) {
    if (node == null) {
      return;
    } // if

    if (node instanceof BitTreeLeaf bitTreeLeaf) {
      pen.println(index + "," + bitTreeLeaf.value());
    } else if (node instanceof BitTreeInteriorNode bitTreeInteriorNode) {
      dumpHelper(pen, bitTreeInteriorNode.left(), index + "0");
      dumpHelper(pen, bitTreeInteriorNode.right(), index + "1");
    } // if/else
  } // dumpHelper
  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Adds or replaces the value at the specified bit path in the tree.
   * 
   * @param bits  the bit sequence showing the path.
   * @param value the value to store.
   * @throws IndexOutOfBoundsException if the bit sequence is invalid.
   */
  public void set(String bits, String value) {
    if (!isValid(bits)) {
      throw new IndexOutOfBoundsException("Invalid bits given.");
    } // if

    this.root = setHelper(this.root, bits, value, 0);
  } // set(String, String)

  /**
   * Returns the value at the specified bit path in the tree.
   * 
   * @param bits the bit sequence showing the path.
   * @return the value at the specified path.
   * @throws IndexOutOfBoundsException if the bit sequence is invalid or no value exists.
   */
  public String get(String bits) {
    if (!isValid(bits)) {
      throw new IndexOutOfBoundsException("Invalid bits given.");
    } // if

    return getHelper(this.root, bits, 0);
  } // get(String, String)

  /**
   * Outputs the contents of the tree in CSV format to the given PrintWriter.
   * 
   * @param pen the PrintWriter to write to.
   */
  public void dump(PrintWriter pen) {
    dumpHelper(pen, this.root, "");
  } // dump(PrintWriter)

  /**
   * Reads mappings from an InputStream and stores them in the tree if valid.
   * Each line in the stream must follow the format "bits,value".
   * 
   * @param source the InputStream to read from.
   * @throws IllegalArgumentException if the InputStream is null or if reading fails.
   */
  public void load(InputStream source) {
    if (source == null) {
      throw new IllegalArgumentException("Source cannot be null.");
    } // if

    try (Scanner scanner = new Scanner(source)) {
      while (scanner.hasNextLine()) {
        String[] line = scanner.nextLine().split(",", 2);
        if (line.length == 2) {
          String bits = line[0];
          String value = line[1];
          set(bits, value);
        } // if
      } // while
    } catch (Exception ex) {
      throw new IllegalArgumentException("Scanner failed to read source.");
    } // try/catch
  } // load(InputStream)
} // class BitTree
