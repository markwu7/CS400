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

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test if a Red-Black tree
// extension of BST is correct. Mostly check node color and position

// @SuppressWarnings("rawtypes")
public class TestRBT {

  protected RBT<Integer, String> rbt;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    rbt = new RBT<Integer, String>();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {}

  /**
   * CASE 123 Insert three values in sorted order and then check the root, left, and right keys to
   * see if RBT rebalancing occurred.
   * 
   */
  @Test
  void testRBT_001_insert_sorted_order_simple() {
    try {
      rbt.insert(10, "10");
      Assert.assertTrue(rbt.rootIsBlack());
      rbt.insert(20, "20");
      Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(20));
      Assert.assertEquals(rbt.colorOf(20), RBT.RED);
      rbt.insert(30, "30"); // SHOULD CAUSE REBALANCING
      Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));
      // Test if re-balance does work and in the right order
      Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
      Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
      Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));
      // rbt.print();
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception RBT Test" + " 001: " + e.getMessage());
    }
  }

  /**
   * CASE 321 Insert three values in reverse sorted order and then check the root, left, and right
   * keys to see if rebalancing occurred in the other direction.
   */
  @Test
  void testRBT_002_insert_reversed_sorted_order_simple() {
    try {
      rbt.insert(30, "10");
      if (!rbt.getKeyAtRoot().equals(30))
        fail("RBT insert at root does not work");
      rbt.insert(20, "20");
      if (!rbt.getKeyOfLeftChildOf(30).equals(20))
        fail("RBT insert to right child of root does not work");
      rbt.insert(10, "30");
      Integer k = rbt.getKeyAtRoot();
      if (!k.equals(20))
        fail("RBT rotate does not work");
      // Test if re-balance does work and in the right order
      Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
      Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
      Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception RBT test 002: " + e.getMessage());
    }

  }

  /**
   * CASE 132 Insert three values so that rebalancing requires new key to be the "new" root of the
   * rebalanced tree.
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred correctly.
   */
  @Test
  void testRBT_003_insert_smallest_largest_middle_order_simple() {
    try {
      rbt.insert(10, "10");
      if (!rbt.getKeyAtRoot().equals(10))
        fail("RBT  insert at root does not work");

      rbt.insert(30, "30");
      if (!rbt.getKeyOfRightChildOf(10).equals(30))
        fail("RBT insert to right child of root does not work");

      rbt.insert(20, "20");
      Integer k = rbt.getKeyAtRoot();
      if (!k.equals(20))
        fail("RBT  rotate does not work");
      // Test if re-balance does work and in the right order
      Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
      Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
      Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));
      rbt.print();

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception RBT test003: " + e.getMessage());
    }

  }

  /**
   * CASE 312 Insert three values so that rebalancing requires new key to be the "new" root of the
   * rebalanced tree.
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred correctly.
   */
  @Test
  void testRBT_004_insert_largest_smallest_middle_order_simple() {
    try {
      rbt.insert(30, "30");
      if (!rbt.getKeyAtRoot().equals(30))
        fail("RBT  insert at root does not work");
      rbt.insert(10, "10");
      if (!rbt.getKeyOfLeftChildOf(30).equals(10))
        fail("RBT insert to right child of root does not work");
      rbt.insert(20, "20");
      Integer k = rbt.getKeyAtRoot();
      if (!k.equals(20))
        fail("RBT  rotate does not work");
      // Test if re-balance does work and in the right order
      Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
      Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
      Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));
      rbt.print();
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception RBT test 004: " + e.getMessage());
    }

  }

  /**
   * Test if rbt multiple increasing order inserts re-balance Computes level order of the tree and
   * compares to expected level order
   */
  @Test
  void testRBT005_increasing_order_inserts() {
    try {
      rbt.insert(1, "");
      rbt.insert(2, "");
      rbt.insert(3, "");
      rbt.insert(4, "");
      rbt.insert(5, "");
      rbt.insert(6, "");
      rbt.insert(7, "");
      rbt.insert(8, "");
      rbt.insert(9, "");
      ArrayList<Integer> test = new ArrayList<>();
      test.add(4);
      test.add(2);
      test.add(6);
      test.add(1);
      test.add(3);
      test.add(5);
      test.add(8);
      test.add(7);
      test.add(9);
      List<Integer> levelOrder = rbt.getLevelOrderTraversal();
      for (int i = 0; i < levelOrder.size(); i++) {
        if (levelOrder.get(i) != test.get(i)) {
          fail();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception RBT test 005: " + e.getMessage());
    }
  }

  /**
   * Checks if the height is correct
   */
  @Test
  void testRBT006_check_height() {
    try {
      rbt.insert(30, "30");
      rbt.insert(10, "10");
      rbt.insert(20, "20");
      if (rbt.getHeight() != 2) {
        fail("Height is wrong!");
      }
      rbt.insert(33, "30");
      rbt.insert(44, "10");
      rbt.insert(55, "30");
      rbt.insert(66, "30");
      if (rbt.getHeight() != 3) {
        fail("Height is wrong!");
      }
      rbt.insert(52, "30");
      rbt.insert(56, "10");
      rbt.insert(13, "30");
      rbt.insert(14, "30");
      rbt.insert(17, "30");
      if (rbt.getHeight() != 4) {
        fail("Height is wrong!");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception RBT test 006: " + e.getMessage());
    }
  }

  /**
   * Test the contains method
   */
  @Test
  void testRBT007_test_conatins() {
    try {
      rbt.insert(30, "30");
      rbt.insert(10, "10");
      rbt.insert(20, "20");
      rbt.insert(35, "35");
      rbt.insert(77, "77");
      if (!rbt.contains(30) || !rbt.contains(10) || !rbt.contains(20) || !rbt.contains(35)
          || !rbt.contains(77)) {
        fail();
      }
      if (rbt.contains(100)) {
        fail();
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception RBT test 007: " + e.getMessage());
    }
  }
  
  /**
   * Test the InOrderTraversal method
   */
@Test
void testRBT008_test_InOrderTraversal() {
try {
          rbt.insert(15, "");
          rbt.insert(5, "");
          rbt.insert(40, "");
          rbt.insert(30, "");
          rbt.insert(45, "");
ArrayList<Integer> test = new ArrayList<>();
          test.add(5);
          test.add(15);
          test.add(30);
          test.add(40);
          test.add(45);
List<Integer> inOrder = rbt.getInOrderTraversal();
for (int i = 0; i < inOrder.size(); i++) {
if (inOrder.get(i) != test.get(i)) {
                  fail();
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
          fail("Unexpected exception RBT test 008: " + e.getMessage());
      }
}

/**
 * Test the preOrderTraversal method
 */
@Test
void testRBT009_test_preOrderTraversal() {
try {
        rbt.insert(15, "");
        rbt.insert(5, "");
        rbt.insert(40, "");
        rbt.insert(30, "");
        rbt.insert(45, "");
ArrayList<Integer> test = new ArrayList<>();
        test.add(15);
        test.add(5);
        test.add(40);
        test.add(30);
        test.add(45);
List<Integer> preOrder = rbt.getPreOrderTraversal();
for (int i = 0; i < preOrder.size(); i++) {
if (preOrder.get(i) != test.get(i)) {
                fail();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        fail("Unexpected exception RBT test 009: " + e.getMessage());
    }
}

/**
 * Test the postOrderTraversal method
 */
@Test
void testRBT010_test_postOrderTraversal() {
try {
        rbt.insert(15, "");
        rbt.insert(5, "");
        rbt.insert(40, "");
        rbt.insert(30, "");
        rbt.insert(45, "");
ArrayList<Integer> test = new ArrayList<>();
        test.add(5);
        test.add(30);
        test.add(45);
        test.add(40);
        test.add(15);
List<Integer> postOrder = rbt.getPostOrderTraversal();
for (int i = 0; i < postOrder.size(); i++) {
if (postOrder.get(i) != test.get(i)) {
                fail();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        fail("Unexpected exception RBT test 010: " + e.getMessage());
    }
}

/**
 * Test the get method
 */
@Test
void testRBT_011_get_check() {
    try {
        for(int i = 0; i < 9; i++) {
         String value = "" + i;
         rbt.insert(i, value); 
     }
        for(int i = 0; i < 9; i++) { // value should match with checking value 
            String s = "" + i;      
            if(!s.equals(rbt.get(i))) {
                fail("RBT should get " + i + "but it does not");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception RBT 011: "+e.getMessage() );
    }
}

/**
 * Test the numKeys method
 */
@Test
void testRBT_012_test_numKeys() {
    try {
        for(int i = 1; i < 9; i++) {
             String value = "" + i;
             rbt.insert(i, value);
             if(rbt.numKeys() != i) { // # of key should match with number of inserted node
                 fail("numKeys is wrong");
             }
        }
    }catch(Exception e){
        e.printStackTrace();
        fail( "Unexpected exception RBT 012: "+e.getMessage() );
    }
}    

/**
 * Test that methods properly throw  exceptions when needed, also that they throw the correct
 * exception
 */
@Test
void testBALST011_proper_exceptions_are_thrown() {
    try {
        rbt.remove(null);
        fail("IllegalNullException not thrown");
        rbt.remove(10);
    } catch (IllegalNullKeyException e) {
    } catch (UnsupportedOperationException e) {
    
    }
}
}
