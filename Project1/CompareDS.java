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

/**
 * This class compares the performance of the implementation DS_My against DS_Brian
 */
public class CompareDS {
  private static DS_My my = new DS_My();
  private static DS_Brian brian = new DS_Brian();
  private static int[] size = new int[] {100, 1000, 10000, 100000};

  /**
   * The method is to print the title and switch all of different value
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("CompareDS.main Compares work time for: DS_My and DS_Brian");
    for (int i = 0; i < size.length; i++) {
      int size1 = size[i];
      for (int j = 0; j < 5; j++) {
        switch (j) {
          case 0:
            insert_many_items(size1);
            break;
          case 1:
            get(size1);
            break;
          case 2:
            contains(size1);
            break;
          case 3:
            size(size1);
            break;
          case 4:
            remove_many_items(size1);
            break;
        }
      }
      System.out.println("----------------------------------------------------------------------------------------");
    }
  }

  /**
   * Test the time of the insert method, and show how long does it take
   * @param size
   */
  private static void insert_many_items(int size) {
    System.out.println("Description:Test insert " + size + " items \n");
    // DS_My
    long startTime1 = System.nanoTime();
    for (int i = 0; i < size; i++) {
      my.insert(String.valueOf(i), null);
    }
    long endTime1 = System.nanoTime();
    System.out.println("DS_My is doing work for " + size + " values\n" + "It took "
        + (endTime1 - startTime1) + " ns to process " + size + " items");
    // DS_Brian
    long startTime2 = System.nanoTime();
    for (int i = 0; i < size; i++) {
      brian.insert(String.valueOf(i), null);
    }
    long endTime2 = System.nanoTime();
    System.out.println("DS_Brian is doing work for " + size + " values\n" + "It took "
        + (endTime2 - startTime2) + " ns to process " + size + " items\n");
  }

  /**
   * Test the time of the size method, and show how long does it take
   * @param size
   */
  private static void size(int size) {
    System.out.println("Description: Test size method\n");
    // DS_My
    long startTime1 = System.nanoTime();
    my.size();
    long endTime1 = System.nanoTime();
    System.out.println("DS_My is doing work for " + size + " values\n" + "It took "
        + (endTime1 - startTime1) + " ns to process " + size + " items");
    // DS_Brian
    long startTime2 = System.nanoTime();
    brian.size();
    long endTime2 = System.nanoTime();
    System.out.println("DS_Brian is doing work for " + size + " values\n" + "It took "
        + (endTime2 - startTime2) + " ns to process " + size + " items\n");
  }

  /**
   * Test the time of the get method, and show how long does it take
   * @param size
   */
  private static void get(int size) {
    System.out.println("Description: Test get method\n");
    String key = String.valueOf(size/2);
    // DS_My
    long startTime1 = System.nanoTime();
    my.get(key);
    long endTime1 = System.nanoTime();
    System.out.println("DS_My is doing work for " + size + " values\n" + "It took "
        + (endTime1 - startTime1) + " ns to process " + size + " items");
    // DS_Brian
    long startTime2 = System.nanoTime();
    brian.get(key);
    long endTime2 = System.nanoTime();
    System.out.println("DS_Brian is doing work for " + size + " values\n" + "It took "
        + (endTime2 - startTime2) + " ns to process " + size + " items\n");
  }

  /**
   * Test the time of the contains method, and show how long does it take
   * @param size
   */
  private static void contains(int size) {
    System.out.println("Description: Test contains method\n");
    String key = String.valueOf(size/2);
    // DS_My
    long startTime1 = System.nanoTime();
    my.contains(key);
    long endTime1 = System.nanoTime();
    System.out.println("DS_My is doing work for " + size + " values\n" + "It took "
        + (endTime1 - startTime1) + " ns to process " + size + " items");

    // DS_Brian
    long startTime2 = System.nanoTime();
    brian.contains(key);
    long endTime2 = System.nanoTime();
    System.out.println("DS_Brian is doing work for " + size + " values\n" + "It took "
        + (endTime2 - startTime2) + " ns to process " + size + " items\n");
  }

  /**
   * Test the time of the remove method, and show how long does it take
   * @param size
   */
  private static void remove_many_items(int size) {
    System.out.println("Description:Test remove " + size + " items \n");
    // DS_My
    long startTime1 = System.nanoTime();
    for (int i = 0; i < size; i++) {
      my.remove(String.valueOf(i));
    }
    long endTime1 = System.nanoTime();
    System.out.println("DS_My is doing work for " + size + " values\n" + "It took "
        + (endTime1 - startTime1) + " ns to process " + size + " items");
    // DS_Brian
    long startTime2 = System.nanoTime();
    for (int i = 0; i < size; i++) {
      brian.remove(String.valueOf(i));
    }
    long endTime2 = System.nanoTime();
    System.out.println("DS_Brian is doing work for " + size + " values\n" + "It took "
        + (endTime2 - startTime2) + " ns to process " + size + " items\n");
  }
}
