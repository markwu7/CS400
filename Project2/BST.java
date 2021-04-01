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
 * Defines the operations required of student's BST class.
 *
 * NOTE: There are many methods in this interface that are required solely to support gray-box
 * testing of the internal tree structure. They must be implemented as described to pass all grading
 * tests.
 * 
 * @param <K> A Comparable type to be used as a key to an associated value.
 * @param <V> A value associated with the given key.
 */
public class BST<K extends Comparable<K>, V> implements STADT<K, V> {
  private BSTNode<K, V> root;
  private int numKeys;
  
  /**
   * Inner class for BST
   * 
   *  @param <K> A Comparable type to be used as a key to an associated value.
   *  @param <V> A value associated with the given key.
   * */
  class BSTNode<K, V> {
    private K key;
    private V value;
    private BSTNode<K, V> left, right;
    public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
      this.key = key;
      this.value = value;
      this.left = left;
      this.right = right;
    }
  }

  /**
   * Returns the key that is in the root node of this ST. If root is null, returns null.
   * 
   * @return key found at root node, or null
   */
  public K getKeyAtRoot() {
    if (root != null) {
      return root.key;
    }
    return null;
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the returns the key that is in the left child. If the left child of the found node is
   * null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the left child of the found key
   * 
   * @throws IllegalNullKeyException if key argument is null
   * @throws KeyNotFoundException    if key is not found in this BST
   */
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) { // if tree does not have key, throw KeyNotFoundException
      throw new KeyNotFoundException();
    }
    BSTNode<K, V> tempNode = root;
    // search tree for node with given key
    tempNode = containsHelper(root, key);
    // return left child of node
    return tempNode.left.key;
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the returns the key that is in the right child. If the right child of the found node is
   * null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the right child of the found key
   * 
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException    if key is not found in this BST
   */
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) { // if key is not found in this BST, throw KeyNotFoundException
      throw new KeyNotFoundException();
    }
    BSTNode<K, V> temp = root;
    // search tree for node with given key
    temp = containsHelper(root, key);
    // return left child of node
    return temp.right.key;
  }


  /**
   * Returns the height of this BST. H is defined as the number of levels in the tree.
   * 
   * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max( height(root.left),
   * height(root.right) )
   * 
   * Examples: A BST with no keys, has a height of zero (0). A BST with one key, has a height of one
   * (1). A BST with two keys, has a height of two (2). A BST with three keys, can be balanced with
   * a height of two(2) or it may be linear with a height of three (3) ... and so on for tree with
   * other heights
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   */
  public int getHeight() {
    return getHeightHelper(root);
  }

 /**
  * The private method to help implement to get height
  * */
  private int getHeightHelper(BSTNode<K, V> node) {
    if (node == null) { // if root is null, return 0
      return 0;
    }
    if (node.left == null & node.right == null) { // if root is leaf, return 1
      return 1;
    }
    return 1 + Math.max(getHeightHelper(node.left), getHeightHelper(node.right));
  }

  /**
   * Returns the keys of the data structure in sorted order. In the case of binary search trees, the
   * visit order is: L V R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   */
  public List<K> getInOrderTraversal() {
    List<K> list = new ArrayList<K>(); // store key in the list
    return inOrder(root, list);
  }
  
  /**
   * private method to more detailed implement of in oder
   * @param Node<K, V> node
   * @param List<K> list
   * */
  private List<K> inOrder(BSTNode<K, V> node, List<K> list) {
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
   * Returns the keys of the data structure in pre-order traversal order. In the case of binary
   * search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  public List<K> getPreOrderTraversal() {
    List<K> list = new ArrayList<K>(); // store key in the list
    return preOrder(root, list);
  }

  /**
   * private method to more detailed implement of pre oder
   * @param Node<K, V> node
   * @param List<K> list
   * */
  private List<K> preOrder(BSTNode<K, V> node, List<K> list) {
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
   * Returns the keys of the data structure in post-order traversal order. In the case of binary
   * search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  public List<K> getPostOrderTraversal() {
    List<K> list = new ArrayList<K>(); // store key in the list
    return postOrder(root, list);
  }

  /**
   * private method to more detailed implement of post oder
   * @param Node<K, V> node
   * @param List<K> list
   * */
  private List<K> postOrder(BSTNode<K, V> node, List<K> list) {
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
   * Returns the keys of the data structure in level-order traversal order.
   * 
   * The root is first in the list, then the keys found in the next level down, and so on.
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
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
  private void levelOrderHelper(BSTNode<K, V> node, int level, List<K> list) {
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
   * Add the key,value pair to the data structure and increase the number of keys. If key is null,
   * throw IllegalNullKeyException; If key is already in data structure, throw
   * DuplicateKeyException(); Do not increase the num of keys in the structure, if key,value pair is
   * not added.
   */
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    if (key == null) { // if key is null, throw IllegalNullKeyException
      throw new IllegalNullKeyException();
    }
   if(contains(key)) {
     throw new DuplicateKeyException();
   }
    root = insertHelper(root, key, value); // insert helper method
    numKeys++;
  }

  /**
   * private method to more detailed implement of insert
   * 
   * @param BSTNode<K, V> node
   * @param K key
   * @param V value
   * */
  private BSTNode<K, V> insertHelper(BSTNode<K, V> node, K key, V value) throws DuplicateKeyException {
    if (node == null) {
      node = new BSTNode<K, V>(key, value, null, null);
    }
    if (key.compareTo(node.key) < 0) {
      node.left = insertHelper(node.left, key, value);
    } else if (key.compareTo(node.key) > 0) {
      node.right = insertHelper(node.right, key, value);
    }
    return node;
  }

  /**
   * If key is found, remove the key,value pair from the data structure and decrease num keys, and
   * return true. If key is not found, do not decrease the number of keys in the data structure,
   * return false. If key is null, throw IllegalNullKeyException
   *
   */
  public boolean remove(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    root = removeHelper(root, key);
    numKeys--;
    if (contains(key) == true)
      return false;
    else
      return true;
  }

  /**
   * private method to more detailed implement of remove
   * 
   * @param BSTNode<K, V> node
   * @param K key
   * @param V value
   * */
  private BSTNode<K, V> removeHelper(BSTNode<K, V> node, K key) {
    if (node == null) {
      return null;
    }
    if (key.compareTo(node.key) < 0) {
      node.left = removeHelper(node.left, key);
    } else if (key.compareTo(node.key) > 0) {
      node.right = removeHelper(node.right, key);
    } else {
      if (node.left == null && node.right == null) // If it's leaf return null
        return null;
      if (node.left == null) // If no left child return right child
        return node.right;
      if (node.right == null) // if no right child return left child
        return node.left;
      node.key = min(node.right).key;
      node.value = min(node.right).value;
      node.right = removeHelper(node.right, node.key);
    }
    return node;
  }

  /**
   * This method recursively traverses left down the tree to find the smallest value given a node
   * 
   * @param startNode is the starting place to traverse down
   * @return the smallest node given the param
   */
  private BSTNode<K, V> min(BSTNode<K, V> x) {
    if (x.left == null) {
      return x;
    }
    return min(x.left);
  }

  /**
   * Returns the value associated with the specified key.
   *
   * Does not remove key or decrease number of keys If key is null, throw IllegalNullKeyException If
   * key is not found, throw KeyNotFoundException().
   */
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    return getHelper(root, key).value;
  }

  /**
   * The private method to more detailed implement of get
   * */
  private BSTNode<K, V> getHelper(BSTNode<K, V> node, K key) throws KeyNotFoundException {
    if (node == null) {
      throw new KeyNotFoundException();
    }
    if (node.key.compareTo(key) == 0) {    // key is the same return node
      return node;
    } else if (key.compareTo(node.key) < 0) {//to left
      return getHelper(node.left, key);
    } else if (key.compareTo(node.key) >0) { //  to right
      return getHelper(node.right, key);
    }
    throw new KeyNotFoundException();
  }
  
 /**
  * The method is to check if contains the key
  * */ 
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }  
    if (containsHelper(root, key) == null) {
      return false;
    }
    return true;
    
  }
  
  /**
   * The private method to more detailed implement of contains
   * */
  private  BSTNode<K, V> containsHelper(BSTNode<K, V> node, K key) {
    if (node == null) {//when node is null return null
      return null;
    }
    if (node.key.compareTo(key) == 0) {
      return node;
    } else if (key.compareTo(node.key) <0) {
      return containsHelper(node.left, key);
    } else {
      return containsHelper(node.right, key);
    }
  }

  /**
   * Returns the number of key,value pairs in the data structure
   */
  public int numKeys() {
    return this.numKeys;
  }

  /**
   * Print the tree.
   *
   * For our testing purposes of your print method: all keys that we insert in the tree will have a
   * string length of exactly 2 characters. example: numbers 10-99, or strings aa - zz, or AA to ZZ
   *
   * This makes it easier for you to not worry about spacing issues.
   *
   * You can display a binary search in any of a variety of ways, but we must see a tree that we can
   * identify left and right children of each node
   *
   * For example:
   * 
   * 30 /\ / \ 20 40 / /\ / / \ 10 35 50
   * 
   * Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
   * 
   * Or, you can display a tree of this kind.
   * 
   * | |-------50 |-------40 | |-------35 30 |-------20 | |-------10
   * 
   * Or, you can come up with your own orientation pattern, like this.
   * 
   * 10 20 30 35 40 50
   * 
   * The connecting lines are not required if we can interpret your tree.
   * 
   */
  public void print() {
    if (root == null) {
      return;
  }
  printHelper(root, 0, numKeys);// Call to recursive print method
  System.out.println("\n");

  }

  /**
   * Helper method to recursively  print the keys and make the tree
   */
  private void printHelper(BSTNode<K, V> node, int space, int count) {
      if (node == null) {
          return;
      }
      space =space+ count;
      printHelper(node.right, space, count);// Print right keys first
      for (int i = count; i < space; i++) {
          // For loop to print spaces
          System.out.print(" ");
      }
      System.out.println(node.key);
      printHelper(node.left, space, count);// Then print left keys
  }
  
}
