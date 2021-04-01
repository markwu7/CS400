///////////////////////////////////////////////////////////////////////////////
// Title: p4 graph
// Files:Graph.java, GraphTest.java, Package.java,PackageManagerTest.java
// Course: CS 400 Spring 2020
// Lecture: 001
//
// Author: Mark Wu
// Email: hwu378@wisc.edu
// Lecturer's Name: Deb Deppeler
// Due date:4/9
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
// Bugs:N/A
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.json.simple.parser.ParseException;

public class PackageManagerTest {
 public  PackageManager pack;

  /**
   * Set up
   * 
   * @throws Exception the exception
   */

  @Before
  public void setUp() throws Exception {
    pack = new PackageManager();
  }

  /**
   * Tear down.
   *
   * @throws Exception the exception
   */
  @After
  public void tearDown() throws Exception {
    pack = null;
  }

  /**
   * Testing if the construct graph method is correctly implemented without throwing any errors
   */
  @Test
  void test001_ConstructGraph() {
     PackageManager pack= new PackageManager();
     try {
       pack.constructGraph("valid.json");
     } catch (FileNotFoundException e1) {
        e1.printStackTrace();
        fail("Unexpected exception occured in test001: " + e1);
     } catch (ParseException e1) {
       e1.printStackTrace();
       fail("Unexpected exception occured in test001: " + e1);
    }
     catch (IOException e1) {
        e1.printStackTrace();
        fail("Unexpected exception occured in test001: " + e1);
     } 
  }
  
  
}
