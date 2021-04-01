///////////////////////////////////////////////////////////////////////////////
// Title: p4 graph
// Files:Graph.java, GraphTest.java, PackageManager.java,PackageManagerTest.java
// Course: CS 400 Spring 2020
// Lecture: 001
//
// Author: Mark Wu
// Email: hwu378@wisc.edu
// Lecturer's Name: Deb Deppeler
//Due date:4/9
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
//Bugs:N/A
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class GraphTest {
  Graph test;

  /**
   * Set up
   * 
   * @throws Exception the exception
   */

  @Before
  public void setUp() throws Exception {
    test = new Graph();
  }

  /**
   * Tear down.
   *
   * @throws Exception the exception
   */
  @After
  public void tearDown() throws Exception {
    test = null;
  }

  /**
   * Test if the right order or not
   */
  @Test
  void test001_add_a_vertex() {
    Graph test = new Graph();
    try {
      int order = 1;
      test.addVertex("V1");
      assertEquals(order, test.order());
    } catch (Exception e1) {
      fail("Unexpected exception occured in test001: " + e1);
    }
  }


  /**
   * Test if get the right order when adding null vertex
   */
  @Test
  public void test0002_insert_Null_Vertex() {
    Graph test = new Graph();
    try {
      int order = 0;
      test.addVertex(null);
      assertEquals(order, test.order());
    } catch (Exception e2) {
      fail("Unexpected exception occured in test002: " + e2);
    }
  }

  /**
   * Test if get the right order when adding many vertex
   */
  @Test
  void test003_add_many_vertex() {
    Graph test = new Graph();
    try {
      int order = 4;
      test.addVertex("V1");
      test.addVertex("V2");
      test.addVertex("V3");
      test.addVertex("V4");
      assertEquals(order, test.order());
    } catch (Exception e3) {
      fail("Unexpected exception occured in test003: " + e3);
    }
  }

  /**
   * Test the order when removing vertex
   */
  @Test
  void test004_remove_vertex() {
    Graph test = new Graph();
    try {
      int order = 2;
      test.addVertex("V1");
      test.addVertex("V2");
      test.addVertex("V3");
      test.removeVertex("V2");
      assertEquals(order, test.order());
    } catch (Exception e4) {
      fail("Unexpected exception occured in test004: " + e4);
    }
  }

  /**
   * Test the order when remove the null vertex
   */
  @Test
  public void test005_remove_Null_Vertex() {
    Graph test = new Graph();
    try {
      int order = 2;
      test.addVertex("V1");
      test.addVertex("V2");
      test.removeVertex(null);
      assertEquals(order, test.order());
    } catch (Exception e5) {
      fail("Vertex size should be 1!");
    }
  }

  /**
   * Test the right order and correct size when adding edges
   */
  @Test
  void test006_add_edges() {
    Graph test = new Graph();
    try {
      int order = 4;
      int size = 2;
      test.addVertex("V1");
      test.addVertex("V2");
      test.addVertex("V3");
      test.addVertex("V4");
      test.addEdge("V1", "V2");
      test.addEdge("V2", "V3");
      assertEquals(order, test.order());
      assertEquals(size, test.size());
    } catch (Exception e6) {
      fail("Unexpected exception occured in test006: " + e6);
    }
  }

  /**
   * Test the right order and correct size when removing the non-existing edges
   */
  @Test
  void test007_remove_nonexisted_edge() {
    Graph test = new Graph();
    try {
      int size = 3;
      int order = 5;
      test.addVertex("V1");
      test.addVertex("V2");
      test.addVertex("V3");
      test.addVertex("V4");
      test.addVertex("V5");
      test.addEdge("V1", "V2");
      test.addEdge("V2", "V3");
      test.addEdge("V3", "V5");
      test.removeEdge("V1", "V4");// non-existing doesn't count
      assertEquals(size, test.size());
      assertEquals(order, test.order());
    } catch (Exception e7) {
      fail("Unexpected exception occured in test007: " + e7);
    }
  }

  /**
   * Test the right order and correct size when removing edges
   */
  @Test
  void test008_add_and_remove_edges() {
    Graph test = new Graph();
    try {
      int size = 1;
      int order = 4;
      test.addVertex("V1");
      test.addVertex("V2");
      test.addVertex("V3");
      test.addVertex("V4");
      test.addEdge("V1", "V2");
      test.addEdge("V2", "V3");
      test.removeEdge("V2", "V3");
      assertEquals(size, test.size());
      assertEquals(order, test.order());
    } catch (Exception e8) {
      fail("Unexpected exception occured in test008: " + e8);
    }
  }

  /**
   * Test if the all the neighbors matched
   */
  @Test
  void test009_get_Adjacent_Vertices() {
    Graph test = new Graph();
    try {
      test.addVertex("V1");
      test.addVertex("V2");
      test.addVertex("V3");
      test.addVertex("V4");
      test.addEdge("V2", "V1");
      test.addEdge("V2", "V3");
      ArrayList<String> expected = new ArrayList<>();
      expected.add("V1");
      expected.add("V3");
      assertEquals(expected, test.getAdjacentVerticesOf("V2"));
    } catch (Exception e9) {
      fail("Unexpected exception occured in test009: " + e9);
    }
  }

  /**
   * Tests if getting the correct number of vertices
   */
  @Test
  public void test10_getAllVertices() {
    Graph test = new Graph();
    try {
      int size = 3;
      test.addVertex("V1");
      test.addVertex("V2");
      test.addVertex("V3");
      assertEquals(size, test.getAllVertices().size());
    } catch (Exception e10) {
      fail("Unexpected exception occured in test010: " + e10);
    }
  }

}
