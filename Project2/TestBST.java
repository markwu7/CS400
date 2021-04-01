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

import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// @SuppressWarnings("rawtypes")
public class TestBST {

  protected STADT<Integer, String> bst;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    bst = new BST<Integer, String>();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {}

  /**
   * CASE 123 Insert three values in sorted order and then check the root, left, and right keys to
   * see if insert worked correctly.
   */
  @Test
  void testBST_001_insert_sorted_order_simple() {
    try {
      bst.insert(10, "10");
      if (!bst.getKeyAtRoot().equals(10))
        fail("insert at root does not work");

      bst.insert(20, "20");
      if (!bst.getKeyOfRightChildOf(10).equals(20))
        fail("insert to right child of root does not work");

      bst.insert(30, "30");
      if (!bst.getKeyAtRoot().equals(10))
        fail("inserting 30 changed root");

      if (!bst.getKeyOfRightChildOf(20).equals(30))
        fail("inserting 30 as right child of 20");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
      Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));
      Assert.assertEquals(bst.getKeyOfRightChildOf(20), Integer.valueOf(30));

      bst.print();

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception BST001: " + e.getMessage());
    }
  }

  /**
   * CASE 321 Insert three values in reverse sorted order and then check the root, left, and right
   * keys to see if insert worked in the other direction.
   */
  @Test
  void testBST_002_insert_reversed_sorted_order_simple() {
    try {
      bst.insert(30, "30");
      if (!bst.getKeyAtRoot().equals(30))
        fail("insert at root does not work");

      bst.insert(20, "20");
      if (!bst.getKeyOfLeftChildOf(30).equals(20))
        fail("insert to left child of root does not work");

      bst.insert(10, "10");
      if (!bst.getKeyAtRoot().equals(30))
        fail("inserting 10 changed root");

      if (!bst.getKeyOfLeftChildOf(20).equals(10))
        fail("inserting 10 as left child of 20");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
      Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));
      Assert.assertEquals(bst.getKeyOfLeftChildOf(20), Integer.valueOf(10));

      bst.print();

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception BST 002: " + e.getMessage());
    }
  }

  /**
   * CASE 132 Insert three values so that rebalancing requires new key to be the "new" root of the
   * rebalanced tree.
   * 
   * Then check the root, left, and right keys to see if insert occurred correctly.
   */
  @Test
  void testBST_003_insert_smallest_largest_middle_order_simple() {
    try {
      bst.insert(10, "10");
      if (!bst.getKeyAtRoot().equals(10))
        fail("insert at root does not work");

      bst.insert(30, "30");
      if (!bst.getKeyOfRightChildOf(10).equals(30))
        fail("insert to right child of root does not work");
      Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));

      bst.insert(20, "20");
      if (!bst.getKeyAtRoot().equals(10))
        fail("inserting 20 changed root");

      if (!bst.getKeyOfLeftChildOf(30).equals(20))
        fail("inserting 20 as left child of 30");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
      Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));
      Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));

      bst.print();

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception BST 003: " + e.getMessage());
    }
  }

  /**
   * CASE 312 Insert three values so that rebalancing requires new key to be the "new" root of the
   * rebalanced tree.
   * 
   * Then check the root, left, and right keys to see if insert occurred correctly.
   */
  @Test
  void testBST_004_insert_largest_smallest_middle_order_simple() {
    try {
      bst.insert(30, "30");
      if (!bst.getKeyAtRoot().equals(30))
        fail("insert at root does not work");

      bst.insert(10, "10");
      if (!bst.getKeyOfLeftChildOf(30).equals(10))
        fail("insert to left child of root does not work");

      bst.insert(20, "20");
      if (!bst.getKeyAtRoot().equals(30))
        fail("inserting 10 changed root");

      if (!bst.getKeyOfRightChildOf(10).equals(20))
        fail("inserting 20 as right child of 10");

      // the tree should have 30 at the root
      // and 10 as its left child and 20 as 10's right child

      Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
      Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(10));
      Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));

      bst.print();

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 001: " + e.getMessage());
    }
  }

  /*
   * Test if bst multiple increasing order inserts re-balance Computes level order of the tree and
   * compares to expected level order
   */
  @Test
  void testbst005_increasing_order_inserts() {
    try {
      bst.insert(1, "");
      bst.insert(2, "");
      bst.insert(3, "");
      bst.insert(4, "");
      bst.insert(5, "");
      bst.insert(6, "");
      bst.insert(7, "");
      bst.insert(8, "");
      bst.insert(9, "");
      ArrayList<Integer> test = new ArrayList<>();
      test.add(1);
      test.add(2);
      test.add(3);
      test.add(4);
      test.add(5);
      test.add(6);
      test.add(7);
      test.add(8);
      test.add(9);
      List<Integer> levelOrder = bst.getLevelOrderTraversal();
      for (int i = 0; i < levelOrder.size(); i++) {
        if (levelOrder.get(i) != test.get(i)) {
          fail();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception bst test 005: " + e.getMessage());
    }
  }

  /*
   * Checks if the height is correct
   */
  @Test
  void testbst006_check_height() {
    try {
      bst.insert(30, "30");
      bst.insert(10, "10");
      bst.insert(20, "20");
      if (bst.getHeight() != 3) {
        fail("Height is wrong!");
      }
      bst.insert(33, "30");
      bst.insert(44, "10");
      bst.insert(55, "30");
      bst.insert(66, "30");
      if (bst.getHeight() != 5) {
        fail("Height is wrong!");
      }
      bst.insert(52, "30");
      bst.insert(56, "10");
      if (bst.getHeight() != 6) {
        fail("Height is wrong!");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception bst test 006: " + e.getMessage());
    }
  }

  /*
   * Test the contains method
   */
  @Test
  void testbst007_test_conatins() {
    try {
      bst.insert(30, "30");
      bst.insert(10, "10");
      bst.insert(20, "20");
      bst.insert(35, "35");
      bst.insert(77, "77");
      if (!bst.contains(30) || !bst.contains(10) || !bst.contains(20) || !bst.contains(35)
          || !bst.contains(77)) {
        fail();
      }
      if (bst.contains(100)) {
        fail();
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception bst test 007: " + e.getMessage());
    }
  }

  /*
   * Test the InOrderTraversal method
   */
  @Test
  void testbst008_test_InOrderTraversal() {
    try {
      bst.insert(15, "");
      bst.insert(5, "");
      bst.insert(40, "");
      bst.insert(30, "");
      bst.insert(45, "");
      ArrayList<Integer> test = new ArrayList<>();
      test.add(5);
      test.add(15);
      test.add(30);
      test.add(40);
      test.add(45);
      List<Integer> inOrder = bst.getInOrderTraversal();
      for (int i = 0; i < inOrder.size(); i++) {
        if (inOrder.get(i) != test.get(i)) {
          fail();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception bst test 008: " + e.getMessage());
    }
  }

  /*
   * Test the preOrderTraversal method
   */
  @Test
  void testbst009_test_preOrderTraversal() {
    try {
      bst.insert(15, "");
      bst.insert(5, "");
      bst.insert(40, "");
      bst.insert(30, "");
      bst.insert(45, "");
      ArrayList<Integer> test = new ArrayList<>();
      test.add(15);
      test.add(5);
      test.add(40);
      test.add(30);
      test.add(45);
      List<Integer> preOrder = bst.getPreOrderTraversal();
      for (int i = 0; i < preOrder.size(); i++) {
        if (preOrder.get(i) != test.get(i)) {
          fail();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception bst test 009: " + e.getMessage());
    }
  }

  /*
   * Test the postOrderTraversal method
   */
  @Test
  void testbst010_test_postOrderTraversal() {
    try {
      bst.insert(15, "");
      bst.insert(5, "");
      bst.insert(40, "");
      bst.insert(30, "");
      bst.insert(45, "");
      ArrayList<Integer> test = new ArrayList<>();
      test.add(5);
      test.add(30);
      test.add(45);
      test.add(40);
      test.add(15);
      List<Integer> postOrder = bst.getPostOrderTraversal();
      for (int i = 0; i < postOrder.size(); i++) {
        if (postOrder.get(i) != test.get(i)) {
          fail();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception bst test 010: " + e.getMessage());
    }
  }

  /*
   * Test the get method
   */
  @Test
  void testbst_011_get_check() {
    try {
      for (int i = 0; i < 9; i++) {
        String value = "" + i;
        bst.insert(i, value);
      }
      for (int i = 0; i < 9; i++) { // value should match with checking value
        String s = "" + i;
        if (!s.equals(bst.get(i))) {
          fail("bst should get " + i + "but it does not");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception bst 011: " + e.getMessage());
    }
  }

  /*
   * Test the numKeys method
   */
  @Test
  void testbst_012_test_numKeys() {
    try {
      for (int i = 1; i < 9; i++) {
        String value = "" + i;
        bst.insert(i, value);
        if (bst.numKeys() != i) { // # of key should match with number of inserted node
          fail("numKeys is wrong");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception bst 012: " + e.getMessage());
    }
  }

  /*
   * Test the remove method and insert method in the same operation
   */
  @Test
  void testBST013_test_insert_delete_and_insert_keys() {
    try {
      bst.insert(55, "55");
      bst.insert(78, "78");
      bst.insert(11, "11");
      bst.insert(14, "14");
      bst.insert(43, "43");
      bst.insert(12, "12");
      bst.insert(15, "15");
      bst.insert(18, "18");
      bst.remove(43);
      bst.remove(12);
      if (bst.contains(43) || bst.contains(12)) {
        fail();
      }
      if (!bst.contains(55) || !bst.contains(78) || !bst.contains(11) || !bst.contains(14)
          || !bst.contains(15) || !bst.contains(18)) {
        fail();
      }
      bst.insert(43, "");
      bst.insert(50, "");
      if (!bst.contains(43) || !bst.contains(50) || !bst.contains(55) || !bst.contains(78) || !bst.contains(11)
          || !bst.contains(14) || !bst.contains(15) || !bst.contains(18)) {
        fail();
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception BST 013" + e.getMessage());
    }
  }
}
