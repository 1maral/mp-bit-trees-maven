package edu.grinnell.csc207.util;

/**
 * A leaf node in a binary tree, representing an edge node with a specific value,
 * and no children.
 */
public class BitTreeLeaf implements BitTreeNode {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * The value associated with this leaf node.
   */
  String value;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Constructs a new BitTreeLeaf with the given value.
   *
   * @param value the value to associate with this leaf node.
   */
  public BitTreeLeaf(String value) {
    this.value = value;
  } // BitTreeLeaf

  // +---------+----------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Returns the value associated with this leaf node.
   *
   * @return the value of this leaf node.
   */
  public String value() {
    return this.value;
  } // value()
} // class BitTreeLeaf
