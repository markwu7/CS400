///////////////////////////////////////////////////////////////////////////////
// Title: p1 Testing, Debugging and Performance
// Files:
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

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {
  private T ds;

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {
    ds = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    ds = null;
  }

  /**
   * The test is to test the empty ds size
   */
  @Test
  void test00_empty_ds_size() {
    if (ds.size() != 0)
      fail("Data structure should be empty, with size=0, but size=" + ds.size());
  }

  /**
   * The test is to insert one key value pair into the data structure and then confirm that size()
   * is 1
   */
  @Test
  void test01_insert_one() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assert (ds.size() == 1);
    if (ds.size() != 1)
      fail("Data structure should be size 1, but size=" + ds.size());
  }

  /**
   * The test is to insert one key,value pair and remove it, then confirm size is 0
   */
  @Test
  void test02_insert_remove_one_size_0() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assert (ds.remove(key)); // remove the key
    if (ds.size() != 0)
      fail("Data structure should be empty, with size=0, but size=" + ds.size());
  }

  /**
   * The test is to- insert a few key,value pairs such that one of them has the same key as an
   * earlier one
   */
  @Test
  void test03_duplicate_exception_thrown() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    ds.insert("2", "two");
    ds.insert("10", "ten");
    try {
      ds.insert(key, value);
      fail("duplicate exception not thrown");
    } catch (RuntimeException re) {
    }
    if (ds.size() != 3)
      fail("Data structure should be size 3, but size=" + ds.size());
  }

  /**
   * The test is to insert some key,value pairs, then try removing a key that was not inserted
   */
  @Test
  void test04_remove_returns_false_when_key_not_present() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assert (!ds.remove("2")); // remove non-existent key is false
    assert (ds.remove(key)); // remove existing key is true
    if (ds.get(key) != null)
      fail("get(" + key + ") returned " + ds.get(key) + " which should have been removed");
  }

  /**
   * Inserts one item and fails if unable to remove it
   */
  @Test
  void test05_insert_remove_one() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assert (!ds.remove("2")); // remove non-existent key is false
    ds.remove(key); // remove existing key is true
    if (ds.size() != 0)
      fail("Data structure should be empty, with size=0, but size=" + ds.size());
    if (ds.get(key) != null)// check if the key has been removed
      fail("get(" + key + ") returned " + ds.get(key) + " which should have been removed");
  }

  /**
   * Inserts many items and fails if size is not correct
   */
  @Test
  void test06_insert_many_size() {
    String key = " ";
    String value = " ";
    for (int i = 0; i < 1000; i++) {
      key = ("Key" + Integer.toString(i));
      value = ("Value" + Integer.toString(i));
      ds.insert(key, value);
    }
    if (ds.size() != 1000)
      fail("Data structure should be size 1000,but size=" + ds.size());
  }

  /**
   * Inserts two pairs with different keys, but same values; fails if second doesn't insert
   */
  @Test
  void test07_duplicate_values() {
    ds.insert("2", " two");
    ds.insert("5", " two");
    try {
      ds.insert("2", " two");
      fail("duplicate exception not thrown");
    } catch (RuntimeException re) {
    }
    if (ds.size() != 2)
      fail("Data structure should be size 2, but size=" + ds.size());
    if (ds.contains("5") == false)
      fail("The second key doesn't insert");
  }

  /**
   * Test remove null key
   */
  @Test
  void test08_test_remove_null_key() {
    try {
      ds.insert("2", "two");
      ds.insert("5", "five");
      ds.remove(null);
      fail("The exception is thrown because you remove the null key!");
    } catch (IllegalArgumentException e) {
    }
  }

  /**
   * Test when the ds is empty
   */
  @Test
  void test09_test_when_ds_is_empty() {
    if (ds.remove("1"))
      fail("Tried to remove ds, should return false");
  }

  /**
   * Test the insert null key
   */
  @Test
  void test10_test_insert_null_key() {
    boolean error = false;
    try {
      ds.insert(null, "two");
    } catch (IllegalArgumentException e) {
      error = true;
    }
    if (!error) {
      fail("Data structure should not be inserted the null key!");
    }
  }

  /**
   * Test the get null key
   */
  @Test
  void test11_test_get_null_key() {
    ds.insert("2", "two");
    ds.insert("5", "five");
    String key = null;
    try {
      ds.get(key);
      fail("The key should not be full!");
    } catch (IllegalArgumentException e) {
    }
  }

  /**
   * The test is to test if the return value it's correct
   */
  @Test
  void test12_test_return_value() {
    ds.insert("2", "two");
    ds.insert("5", "five");
    String key1 = "5";
    String key2 = "50";
    if (ds.get(key2) != null)
      fail("The method should return null as data not found, but the return value is "
          + ds.get(key2));
    if (!ds.get(key1).equals("five"))
      fail("The value should be five, but the retuen value is " + ds.get(key1));
    if (ds.size() != 2)
      fail("Data structure should be size 2, but size=" + ds.size());
  }

  /**
   * The test is to test the insert removed key
   */
  @Test
  void test13_throws_RuntimeException_when_insert_removed_key() {
    try {
      ds.insert("2", "two");
      ds.remove("2");
      ds.insert("2", "five");// insert the key that was removed
    } catch (RuntimeException re) {
      fail("You can not insert the key that was removed, so the exception was thrown "
          + ds.get("2"));
    }
  }
}
