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

/**
 * This class compares the performance of the implementation BST against RBT
 */
public class CompareDS {
  /**
   * The method is to print the title and switch all of different value
   * 
   * @param args
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  public static void main(String[] args)
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    BST<Integer, String> bst = new BST<Integer, String>();
    RBT<Integer, String> rbt = new RBT<Integer, String>();
    Integer[] size = new Integer[] {38, 18, 7, 60, 2, 5, 12, 75, 15};
    System.out.println("Compares work time for: BST and RBT");
    printBST(bst, size);
    printRBT(rbt, size);
  }

  /**
   *Prints the running time of bst
   * 
   * @param BST<Integer,String> bst
   * @param Integer[]size
   * @throws KeyNotFoundException
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  private static void printBST(BST<Integer, String> bst, Integer[] size)
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    System.out.println("BST is doing work of " + size.length + " values");
    System.out.println("It took " + bstTime(bst, size) + " ns to process "
        + String.valueOf(size.length) + " items");
  }

  /**
   * Prints the running time of rbt
   * @param BST<Integer,String> rbt
   * @param Integer[]size
   * @throws KeyNotFoundException
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  private static void printRBT(RBT<Integer, String> rbt, Integer[] size)
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    System.out.println("Red Black Tree is doing work of " + size.length + " values");
    System.out.println("It took " + rbtTime(rbt, size) + " ns to process "
        + String.valueOf(size.length) + " items\n");
  }

  /**
   * The running time of BST time
   * 
   * @param BST<Integer,String> bst
   * @param Integer[]size
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  private static String bstTime(BST<Integer, String> bst, Integer[] arr)
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    String processedTime = "";
    long startTime = System.nanoTime();
    for (int i = 0; i < arr.length; i++) {
      bst.insert(arr[i], "i");
    }
    for (int i = 0; i < arr.length; i++) {
      bst.contains(arr[i]);
    }
    for (int i = 0; i < arr.length; i++) {
      bst.get(arr[i]);
    }
    long endTime = System.nanoTime();
    processedTime = String.valueOf(endTime - startTime); // calculate time
    return processedTime;
  }

  /**
   *The running time of RBT time
   * 
   * @param BST<Integer,String> rbt
   * @param Integer[]size
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  private static String rbtTime(RBT<Integer, String> rbt, Integer[] arr)
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    String processedTime = ""; // store the processed time
    long startTime = System.nanoTime();
    for (int i = 0; i < arr.length; i++) {
      rbt.insert(arr[i], "i");
    }
    for (int i = 0; i < arr.length; i++) {
      rbt.contains(arr[i]);
    }
    for (int i = 0; i < arr.length; i++) {
      rbt.get(arr[i]);
    }
    long endTime = System.nanoTime();
    processedTime = String.valueOf(endTime - startTime); // calculate time
    return processedTime;
  }
}
