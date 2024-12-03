package edu.grinnell.csc207.util;

/**
 * An interior node of a binary tree, contains references to its left and right child nodes.
 * It does not hold any value.
 */
public class BitTreeInteriorNode implements BitTreeNode {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * Reference to the left child node of this interior node.
   */
  BitTreeNode left;

  /**
   * Reference to the right child node of this interior node.
   */
  BitTreeNode right;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Constructs an interior node with no children to start.
   */
  public BitTreeInteriorNode() {
    this.left = null;
    this.right = null;
  } // BitTreeInteriorNode

  // +---------+----------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Returns the left child node of this interior node.
   * 
   * @return the left child node, or null if there is no left child.
   */
  public BitTreeNode left() {
    return this.left;
  } // left()

  /**
   * Returns the right child node of this interior node.
   * 
   * @return the right child node, or null if there is no right child.
   */
  public BitTreeNode right() {
    return this.right;
  } // right()
} // class BitTreeInteriorNode
