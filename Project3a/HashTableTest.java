///////////////////////////////////////////////////////////////////////////////
// Title: p3a hashtable
// Files:HashTable.java, HashTableTest.java
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


import static org.junit.jupiter.api.Assertions.fail;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The JUnit test for the hashtable
 */
public class HashTableTest {
  HashTableADT a = new HashTable<Integer, String>();

  private HashTableADT createInstance() {
    return new HashTable<Integer, String>();
  }

  @Before
  public void setUp() throws Exception {
    a = createInstance();
  }

  @After
  public void tearDown() throws Exception {
    a = null;
  }

  /**
   * Tests that a HashTable returns an integer code indicating which collision resolution strategy
   * is used. REFER TO HashTableADT for valid collision scheme codes.
   */
  @Test
  public void test000_collision_scheme() {
    HashTableADT htIntegerKey = new HashTable<Integer, String>();
    int scheme = htIntegerKey.getCollisionResolution();
    if (scheme < 1 || scheme > 9)
      fail("collision resolution must be indicated with 1-9");
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that insert(null,null) throws IllegalNullKeyException
   */
  @Test
  public void test001_IllegalNullKey() {
    try {
      HashTableADT htIntegerKey = new HashTable<Integer, String>();
      htIntegerKey.insert(null, null);
      fail("should not be able to insert null key");
    } catch (IllegalNullKeyException e) {
      /* expected */ } catch (Exception e) {
      fail("insert null key should not throw exception " + e.getClass().getName());
    }
  }

  /**
   * test size
   */
  @Test
  public void test002_insert_one_size_is_one() {
    try {
      HashTableADT htIntegerKey = new HashTable<Integer, String>();
      htIntegerKey.insert(4, "four");
      if (htIntegerKey.numKeys() != 1) {
        fail("The number of keys should be 1 ");
      }
    } catch (Exception e) {
    }
  }

  /**
   * test003 for the remove method and size
   */
  @Test
  public void test003_insert_one_remove_one_size_should_be0() {
    try {
      a.insert(4, "four");
      a.remove("four");
      if (a.numKeys() == 0) {
        fail("The number of keys should be 1");
      }
    } catch (Exception e) {
    }
  }

  /**
   * test004 for get method
   */
  @Test
  public void test004_insert_one_and_get_one() {
    try {
      a.insert(4, "four");
      if (a.get(4) != "four") {
        fail("The number of keys should be 1!");
      }
    } catch (Exception e) {
    }
  }

  /**
   * Test 005 to get null key
   */
  @Test
  public void test005_get_null_key() {
    try {
      a.get(null);
      fail("Should throw IllegalNullKeyException here ");
    } catch (IllegalNullKeyException INKE) {
    } catch (Exception e) {
      fail("Unexpected exception: " + e.getClass().getName());
    }
  }

   /**
   *Test if the duplicate value is the newest one
   */
   @Test
   public void test006_test_insert_duplicate() {
   try {
   a.insert(1, "1");
   a.insert(1, "3");
   Assert.assertEquals("3", a.get(1));
   } catch (Exception e) {
   fail("Unexpected exception: " + e.getClass().getName());
   }
   }

  /**
   * Check the size after insert many elements
   * 
   */
  @Test
  public void test007_test_insert_many_size() {
    try {
      for (int i = 0; i < 100; i++) {
        a.insert(i, Integer.toString(i));
      }
      Assert.assertEquals(100, a.numKeys());
    } catch (Exception e) {
      fail("Unexpected exception: " + e.getClass().getName());
    }
  }


  /**
   * Test if the exception is thrown when the element has been removed
   */
  @Test
  public void test008_test_removed_element() {
    try {
      a.insert(1, "1");
      Assert.assertEquals("1", a.get(1));
      Assert.assertTrue(a.remove(1));
      a.get(1);
      fail("Should KeyNotFoundException");
    } catch (KeyNotFoundException KNFE) {
    } catch (Exception e) {
      fail("Unexpected exception: " + e.getClass().getName());
    }
  }

  /**
   * Test remove the element doesn't exist
   */
  @Test
  public void test009_test_remove_non_exist() {
    try {
      Assert.assertFalse(a.remove(1));
    } catch (Exception e) {
      fail("Unexpected exception: " + e.getClass().getName());
    }
  }

  /**
   * Test the collision
   */
  @Test
  public void test010_collision_2_elements() {
    try {
      int key1 = 1;
      int key2 = 1 + a.getCapacity();
      a.insert(key1, "10");
      a.insert(key2, "15");
      Assert.assertEquals("10", a.get(key1));
      Assert.assertEquals("15", a.get(key2));
    } catch (Exception e) {
      fail("Unexpected exception: " + e.getClass().getName());
    }
  }
}
