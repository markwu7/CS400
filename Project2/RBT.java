///////////////////////////////////////////////////////////////////////////////
// Title: p2 Balance Search Tree
// Files:BST.java, RBT.java, CompareDS.java,TestBST.java,TestBRT.java
// Course: CS 400 Spring 2020
// Lecture: 001
//
// Author: Mark Wu
// Email: hwu378@wisc.edu
// Lecturer's Name: Deb Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a generic Red-Black tree extension of BST<K,V>.
 *
 * DO NOT CHANGE THE METHOD SIGNATURES OF PUBLIC METHODS DO NOT ADD ANY PACKAGE LEVEL OR PUBLIC
 * ACCESS METHODS OR FIELDS.
 * 
 * You are not required to override remove. If you do not override this operation, you may still
 * have the method in your type, and have the method throw new UnsupportedOperationException. See
 * https://docs.oracle.com/javase/7/docs/api/java/lang/UnsupportedOperationException.html
 * 
 * @param <K> is the generic type of key, must be a Comparable tyle
 * @param <V> is the generic type of value
 */
public class RBT<K extends Comparable<K>, V> implements STADT<K, V> {
  public static final int RED = 0;
  public static final int BLACK = 1;
  private Node<K, V> root;
  private int numKeys;

  /**
   * Inner class for RBT
   * 
   * @param <K> A Comparable type to be used as a key to an associated value.
   * @param <V> A value associated with the given key.
   */
  class Node<K, V> {
    K key;
    V value;
    Node<K, V> left;
    Node<K, V> right;
    Node<K, V> p;// parent
    int color;
    int bf;// balance factor
    int height;

    /**
     * 
     * @param key
     * @param value
     * @param leftChild
     * @param rightChild
     * @param parent
     */
    Node(K key, V value, Node<K, V> leftChild, Node<K, V> rightChild, Node<K, V> parent) {
      this.key = key;
      this.value = value;
      this.left = leftChild;
      this.right = rightChild;
      this.p = parent;
      this.height = 0;
      this.bf = 0;
    }

    Node(K key, V value) {
      this(key, value, null, null, null); // in the insert method assume every new key is red
      color = RED;
    }

    Node(K key, V value, Node<K, V> parent) {
      this(key, value, null, null, parent);
      color = RED;
    }
  }

  /**
   * The constructor with no parameter
   */
  public RBT() {

  }

  /**
   * Returns the color of the node that contains the specified key. Returns RBT.RED if the node is
   * red, and RBT.BLACK if the node is black. Returns -1 if the node is not found.
   * 
   * @param
   * @return either null or the node color
   */
  public int colorOf(K key) {
    Node<K, V> found = getNodeWith(root, key);
    return found == null ? -1 : found.color;
  }

  /**
   * The method is to get the node
   */
  private Node<K, V> getNodeWith(Node<K, V> node, K key) {
    if (node == null)
      return null;
    if (key.equals(node.key))// if found
      return node;
    else if (key.compareTo(node.key) < 0)
      return getNodeWith(node.left, key);
    else // if(key.compareTo(node.key) > 0)
      return getNodeWith(node.right, key);
  }

  /**
   * Returns true if the color of the root is black. If root is null, returns BLACK.
   * 
   * @return true if root is black, else returns false (for red)
   */
  public boolean rootIsBlack() {
    if (root == null)
      return true;
    if (colorOf(root.key) == BLACK)
      return true;
    return false;
  }

  /**
   * Returns true if the node that contains this key is RED. If key is null, throws
   * IllegalNullKeyException. If key is not found, throws KeyNotFoundException.
   * 
   * @return true if the key is found and the node's color is RED, else return false if key is found
   *         and the node's color is BLACK.
   * 
   */
  public boolean isRed(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) {
      throw new KeyNotFoundException();
    }
    if (colorOf(key) != 0)
      return false;
    return true;
  }

  /**
   * Returns true if the node that contains this key is BLACK. If key is null, throws
   * IllegalNullKeyException. If key is not found, throws KeyNotFoundException.
   * 
   * @return true if the key is found and the node's color is BLACK, else return false if key is
   *         found and the node's color is RED.
   * @throws IllegalNullKeyException if null key
   * @throws KeyNotFoundException    if not found the key
   */
  public boolean isBlack(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) {
      throw new KeyNotFoundException();
    }
    if (colorOf(key) != 1)
      return false;
    return true;
  }

  /**
   * The method is to get the key at root
   */
  @Override
  public K getKeyAtRoot() {
    if (root != null) {
      return root.key;
    }
    return null;
  }

  /**
   * The method is to get the key of left children
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) { // if tree does not have key, throw KeyNotFoundException
      throw new KeyNotFoundException();
    }
    Node<K, V> temp = root;// search tree for node with given key
    temp = containsHelper(root, key);
    if (temp.left == null) // return left child key of node
      return null;
    return temp.left.key;
  }

  /**
   * The method is to get the key of right children
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) { // if key is not found in this RBT, throw KeyNotFoundException
      throw new KeyNotFoundException();
    }
    Node<K, V> temp = root;
    temp = containsHelper(root, key); // search tree for node with given key
    if (temp.right == null)// return right child key of node
      return null;
    return temp.right.key;
  }

  /**
   * The method is to get the height
   * */
  @Override
  public int getHeight() {
    return getHeightHelper(root);
  }

  /**
   * The private method is to help get the height
   * */
  private int getHeightHelper(Node<K, V> node) {
    if (node == null) { // if root is null, return 0
      return 0;
    }
    if (node.left == null & node.right == null) { // if root is leaf, return 1
      return 1;
    }
    return 1 + Math.max(getHeightHelper(node.left), getHeightHelper(node.right))
    ;//get the most far node from node
  }

  /**
   * The method is to get In Order Traversal
   * */
  @Override
  public List<K> getInOrderTraversal() {
    List<K> list = new ArrayList<K>(); // store key in the list
    return inOrder(root, list);
  }
  
  /**
   * private method to more detailed implement of in oder
   * @param Node<K, V> node
   * @param List<K> list
   * */
  private List<K> inOrder(Node<K, V> node, List<K> list) {
    if (node == null) {
      return list;
    }
    if (node.left != null) { // recursive with node's left child,if node has left child
      inOrder(node.left, list);
    }
    list.add(node.key); // add node's key to list
    if (node.right != null) {
      inOrder(node.right, list); // recursive with ndoe's right child,if node has right child
    }
    return list; // return list
  }

  /**
   * The method is to get pre Order Traversal
   * */
  @Override
  public List<K> getPreOrderTraversal() {
    List<K> list = new ArrayList<K>(); // store key in the list
    return preOrder(root, list);
  }

  /**
   * private method to more detailed implement of pre oder
   * @param Node<K, V> node
   * @param List<K> list
   * */
  private List<K> preOrder(Node<K, V> node, List<K> list) {
    if (node == null) {
      return list;
    }
    list.add(node.key);
    if (node.left != null) { // recursive with node's left child,if node has left child
      preOrder(node.left, list);
    }
    if (node.right != null) { // recursive with ndoe's right child,if node has right child
      preOrder(node.right, list);
    }
    return list; // return list
  }

  /**
   * The method is to get post Order Traversal
   * */
  @Override
  public List<K> getPostOrderTraversal() {
    List<K> list = new ArrayList<K>(); // store key in the list
    return postOrder(root, list);
  }

  /**
   * private method to more detailed implement of post oder
   * @param Node<K, V> node
   * @param List<K> list
   * */
  private List<K> postOrder(Node<K, V> node, List<K> list) {
    if (node == null) {
      return list;
    }
    if (node.left != null) { // recursive with node's left child,if node has left child
      postOrder(node.left, list);
    }
    if (node.right != null) { // recursive with node's right child,if node has right child
      postOrder(node.right, list);
    }
    list.add(node.key);
    return list;
  }

  /**
   * The method is to get level Order Traversal
   * */
  @Override
  public List<K> getLevelOrderTraversal() {
    List<K> list = new ArrayList<K>();// store key in the list
    if (root == null) {
      return list;
    }
    for (int i = 1; i <= getHeight(); i++) {
      levelOrderHelper(root, i, list);
    }
    return list;
  }

  /**
   * private method to more detailed implement of level oder
   * @param Node<K, V> node
   * @param List<K> list
   * */
  private void levelOrderHelper(Node<K, V> node, int level, List<K> list) {
    if (node == null) {
      return;
    }
    if (level == 1) {
      list.add(node.key);
    } else if (level > 1) {
      levelOrderHelper(node.left, level - 1, list);
      levelOrderHelper(node.right, level - 1, list);
    }
  }

/**
 * The insert method
 *  
 * @param K key
 * @param V value
 * @throws IllegalNullKeyException if key is null
 * @throws DuplicateKeyException if duplicate key
 * */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    if (key == null) { // if key is null, throw IllegalNullKeyException
      throw new IllegalNullKeyException();
    }
    if (contains(key)) {
      throw new DuplicateKeyException();
    }
    // insert node to root with Helper method
    root = insertHelper(root, key, value);
    root.color = BLACK;//root is null
  }

  /**
   * private method to more detailed implement of insert
   * 
   * @param Node<K, V> current
   * @param K key
   * @param V value
   * */
  private Node<K, V> insertHelper(Node<K, V> current, K key, V value)
      throws DuplicateKeyException, IllegalNullKeyException {
    if (current == null) {
      numKeys++;// Increment
      return new Node<K, V>(key, value);// set node to default RED
    }
    if (key.compareTo(current.key) < 0) {
      current.left = insertHelper(current.left, key, value);//recursively left to see where to insert
    } else if (key.compareTo(current.key) > 0) {
      current.right = insertHelper(current.right, key, value);//recursively right to see where to insert
    } else {
      current.key = key;
    }
    int heightBalance = reBalance(current); // get balance to check balance error
    if (heightBalance > 1 && key.compareTo(current.left.key) < 0) { // left left return
      return rightRotate(current); // right rotate
    }
    if (heightBalance < -1 && key.compareTo(current.right.key) > 0) { // right right
      return leftRotate(current); // left rotate
    }
    if (heightBalance > 1 && key.compareTo(current.left.key) > 0) { // left right
      current.left = leftRotate(current.left); // left rotate
      return rightRotate(current); // right rotate
    }
    if (heightBalance < -1 && key.compareTo(current.right.key) < 0) { // right left
      current.right = rightRotate(current.right); // right rotate
      return leftRotate(current); // left rotate
    }
    if (heightBalance == 0) {
      return flipColor(current);//if balance re color
    }
    return current;
  }

  /**
   * private method to more detailed implement of left rotate
   * 
   * @param Node<K, V> node
   * */
  private Node<K, V> leftRotate(Node<K, V> node) {
    // store node's right child
    Node<K, V> swap = node.right;
    // save node's right child's left child
    node.right = swap.left;
    swap.left = node;
    swap.color = node.color;
    node.color = RED;
    return swap;
  }

  /**
   * private method to more detailed implement of left rotate
   * 
   * @param Node<K, V> node
   * */
  private Node<K, V> rightRotate(Node<K, V> node) {
    // store node's left child
    Node<K, V> swap = node.left;
    // save node's left child's right child
    node.left = swap.right;
    swap.right = node;
    swap.color = node.color;
    node.color = RED;
    return swap;
  }


  /**
   * Finds balance factor of given node
   * 
   * @param find height balance of this node
   * @return the height balance of the node
   */
  private int reBalance(Node<K, V> node) {
    if (node == null) { // if node is null, return 0
      return 0;
    }
    return getHeightHelper(node.left) - getHeightHelper(node.right);
    // return left sub tree height - right sub tree height
  }

/**
 * private method to more detailed implement of flipcolor
 * 
 * @param Node<K, V> node
 * */
  private Node<K, V> flipColor(Node<K, V> node) {
    node.color = RED;
    node.left.color = BLACK;
    node.right.color = BLACK;
    return node;
  }

/**
 * The method to get the key
 * */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    return getHelper(root, key).value;
  }

/**
 * The private method to more detailed implement of get
 * */
  private Node<K, V> getHelper(Node<K, V> node, K key) throws KeyNotFoundException {
    if (node == null) {
      throw new KeyNotFoundException();
    }
    if (node.key.compareTo(key) == 0) { // key is the same return node
      return node;
    } else if (key.compareTo(node.key) < 0) {//to left 
      return getHelper(node.left, key);
    } else if (key.compareTo(node.key) > 0) {//to right
      return getHelper(node.right, key);
    }
    throw new KeyNotFoundException();
  }

/**
 * The method to check if contains a key
 * 
 * @param K key
 * */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (containsHelper(root, key) == null) {    // call contains helper
      return false;
    }
    return true;
  }

  /**
   * The private method to more detailed implement of contains
   * 
   * @param Node<K, V> node, K key
   * */
  private Node<K, V> containsHelper(Node<K, V> node, K key) {
    if (node == null) {// when node is null return null
      return null;
    }
    if (node.key.compareTo(key) == 0) {
      return node;
    } else if (key.compareTo(node.key) < 0) {
      // to left child
      return containsHelper(node.left, key);
    } else {
      return containsHelper(node.right, key);
    }
  }

  /**
   * The method is to get number of key
   * */
  @Override
  public int numKeys() {
    return this.numKeys;
  }

  /**
   * The print method
   * */
  @Override
  public void print() {
    if (root == null) {
      return;
    }
    printHelper(root, 0, numKeys);// Call to recursive print method
    System.out.println("\n");

  }

  /**
   * Helper method to recursively print the keys and make the tree
   */
  private void printHelper(Node<K, V> node, int space, int count) {
    if (node == null) {
      return;
    }
    space = space + count;
    printHelper(node.right, space, count);// Print right keys first
    for (int i = count; i < space; i++) {
      // For loop to print spaces
      System.out.print(" ");
    }
    System.out.println(node.key);
    printHelper(node.left, space, count);// Then print left keys
  }

  /**
   * The remove method
   * */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, UnsupportedOperationException {
    if (key == null) {
      
      throw new UnsupportedOperationException();
    }
    if(contains(key)) {
      throw new IllegalNullKeyException();
    }
    return true;
  }
}
