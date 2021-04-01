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
 * The DS_My is using an array to store Data object
 */
public class DS_My implements DataStructureADT<String, String> {
  private int size;
  private int maxCapacity;
  private pair[] array;

  /**
   * An inner class for storing key and value as a pair
   */
  private class pair {
    private String key;
    private String value;
    private pair(String key, String value) {
      this.key = key;
      this.value = value;
    }
    /**
     * The getter
     * @return the key
     * */
    private String getKey() {
      return this.key;
    }

    /**
     * The getter
     * @return the value
     * */
    private String getValue() {
      return this.value;
    }
  }

  /**
   * Get the index of the key which is stored in the array
   * 
   * @param key - the key we find
   * @return the index of the key or-1 when not found
   */
  private int indexOfKey(String key) {
    int index = -1;
    if (key != null) {
      for (int i = 0; i < size; i++)
        if (array[i].getKey().equals(key))
          index = i;
    }
    return index;
  }

  /**
   * The constructor of this class. It initializes the capacity and size
   */
  public DS_My() {
    this.maxCapacity = 1000;// default capacity
    this.size = 0;
    this.array = new pair[maxCapacity];
  }

  /**
   * The method is to insert the pair of the key,and value to the data structure and increases size.
   * 
   * @param key   - the key of data
   * @param value - the value that associated with the key
   * @throws IllegalArgumentException - if it's null key
   * @throws RuntimeException         - if the key already in the array
   */
  @Override
  public void insert(String key, String value) {
    if (key == null)
      throw new IllegalArgumentException("It's null key!");
    if (contains(key))
      throw new RuntimeException("It's duplicate key");
    // if size is 0, add pair to array
    else if (this.size == 0) {
      array[this.size] = new pair(key, value);
      this.size++;
      return;
    } else if (maxCapacity == size) {
      pair[] shadowList = new pair[maxCapacity * 2];
      for (int i = 0; i < this.size; i++) {
        if (array[i].key.equals(key)) {
          throw new RuntimeException("duplicate key");
        }
        shadowList[i] = array[i]; // Change value in array
      }
      array = shadowList;// change to another list
      array[size] = new pair(key, value);
      maxCapacity = maxCapacity * 2;// Double capacity
      size++;
      return;
    }
    array[size] = new pair(key, value);// Add pair to array when not filled
    size++;
  }

  /**
   * The method is to remove the key and decrease the size when the key is found
   * 
   * @param Key
   * @return - true when this method is successfully; false when we're unable to remove
   * @throws IllegalArgumentException - if it's null key
   */
  @Override
  public boolean remove(String key) {
    if (key == null)
      throw new IllegalArgumentException("It's null key!");
    if (size == 0)
      return false;// when no key to remove return false
    pair[] shadow = new pair[maxCapacity];// create temporary array to store the data

    // when the key is found
    if (contains(key)) {
      for (int i = indexOfKey(key); i < size - 1; i++) { // loop begins by the index of the key
        array[i] = array[i + 1];
        shadow[i] = shadow[i + 1];
      }
      array[size - 1] = null;
      shadow[size - 1] = null;
      size--;
      return true;
    } else
      return false;
  }

  /**
   * The method is to get the value associated with the key
   *
   * @return the value of the key when the key is found
   * @throws IllegalArgumentException - if it's null key
   */
  @Override
  public String get(String key) {
    if (key == null)
      throw new IllegalArgumentException("It's null key!");
    if (size == 0)
      return null;
    if (contains(key))// if found the key
      for (int i = 0; i < size; i++) {
        if (key.compareTo(array[i].getKey()) == 0)
          return array[i].getValue();
      }
    return null;
  }

  /**
   * The method is to if the elements in the data structure
   * 
   * @return - Returns true when the key is found in the data structure or false when not found or
   *         no elements
   */
  @Override
  public boolean contains(String key) {
    if (size == 0)
      return false;// no element so return false
    for (int i = 0; i < size; i++) {
      if (key.compareTo(array[i].getKey()) == 0)
        return true;// if found the key return true
    }
    return false;
  }

  /**
   * The method is to get size Returns how many element in the data structure
   * 
   * @return size
   */
  @Override
  public int size() {
    return this.size;
  }

}

