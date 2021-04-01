///////////////////////////////////////////////////////////////////////////////
// Title: p1 Testing, Debugging and Performance
// Files:
// Course: CS 400 Spring 2020
// Lecture: 001
//
// Author: Mark Wu
// Email: hwu378@wisc.edu
// Lecturer's Name: Debra Deppeler
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

// TO TEST A DATA STRUCTURE CLASS:
//
// for each data structure class file you wish to test:
//     1. create a test class (like this one) 
//     2. edit the actual type being created (line 16)
//     3. run this test class 
//     4. OR, configure Eclipse project to run all tests
//        Eclipse: Run->Run Configurations->"Run All Tests..."

@SuppressWarnings("rawtypes")
public class TestDS_My extends DataStructureADTTest {

    // the return type must be the name of the data structure class you are testing
    @Override
    protected DataStructureADT createInstance() {
        return new DS_My();
    }

}
