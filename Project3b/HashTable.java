///////////////////////////////////////////////////////////////////////////////
// Title: p3b hashtable
// Files:HashTable.java, HashTableTest.java, MyProfiler.java
// Course: CS 400 Spring 2020
// Lecture: 001
//
// Author: Mark Wu
// Email: hwu378@wisc.edu
// Lecturer's Name: Deb Deppeler
//Due date:3/26
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

/**
 * This class represents a hash table extends comparable objects and
 * implements HashTableADT class. It has capacity, loadFactor, loadFactorThreshold,
 * numKeys and an array table which is the hash table created. And using the linked list.
 *
 * @param <K>  key
 * @param <V> value
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
  private int numKeys;// how many keys in the table
  private int capacity;// the size
  private double loadFactor;// current load factor
  private double LFThreshold;// the threshold
  private Node<K,V>[] table;// the hash table array

  /**
   * Inner class for Listnode
   * 
   * @param <K> is the key of the node
   * @param <V> is the value of the node
   */
  class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;
    Node(K key, V value) { // node for hashTable
      this.key = key;
      this.value = value;
      this.next = null;
    }
    Node(V value) { // node for hashTable
      this.value = value;
    }
  }

  /**
   * Default constructor
   */
  public HashTable() {
    this(10, 0.75);
    loadFactor = 0;
    table = new Node[capacity];
  }

  /**
   * Constructor different parameter
   * 
   * @param initialCapacity
   * @param loadFactorThreshold
   */
  public HashTable(int initialCapacity, double loadFactorThreshold) {
    this.capacity = initialCapacity;
    this.LFThreshold = loadFactorThreshold;
    table = new Node[capacity];
  }

  /**
   * private helper method for calculating hash code
   * 
   * @param key
   * 
   * @return hashIndex
   */
  private int hashIndex(K key) {
    int hashIndex = key.hashCode();
    hashIndex = Math.abs(hashIndex);
    return hashIndex % capacity;
  }

  /**
   * private helper method for resize
   * @throws IllegalNullKeyException 
   */
  private void resize() {
    this.capacity = (capacity * 2 + 1);
    Node<K, V>[] newtable = new Node[capacity];
    for (int i = 0; i < table.length; i++) {
      Node<K, V> list = table[i]; // For traversing linked list number i.
      while (list != null) {// list can not be null
        Node<K, V> next = list.next;// to the next node
        int hashIndex = (Math.abs(list.key.hashCode())) % newtable.length;
        list.next = newtable[hashIndex];
        newtable[hashIndex] = list;
        list =next;
      }
    }
    table = newtable;
    capacity = newtable.length;
  }

  /**
   * The method of insert of hashtable
   * 
   * @param The key
   * @param The value
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    int bucket = hashIndex(key);
    Node<K, V> list = table[bucket];
    while (list != null) {
      if (list.key.equals(key)) {//if the key exist replaced the newest value
      list.value = value;
      return ;
    } 
      list = list.next;
    }
    Node<K, V> newNode = new Node<K, V>(key, value);// add new nodes at the beginning of each list
    newNode.next = table[bucket];
    table[bucket] = newNode;
    numKeys++;
    
    loadFactor = (double)numKeys/capacity;
    if (loadFactor >= LFThreshold) {// when almost full resize
      resize();
    }
  }

  /**
   * The remove method
   * 
   * @param The given key
   * @throws IllegalNullKeyException when key is null
   * @return True as the key is found, otherwise false.
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    int bucket = hashIndex(key);
    if (table[bucket] == null) {
      return false;
    }
    if (table[bucket].key.equals(key)) {
      table[bucket] = table[bucket].next;
      numKeys--;// delete
      return true;
    }
    Node<K, V> previous = table[bucket];
    Node<K, V> current = previous.next;
    while (current != null && !current.key.equals(key)) {
      current = current.next;
      previous = current;
    }
    if (current != null) {
      previous.next = current.next;
      numKeys--;
      return true;
    }
    return false;// if key not in table
  }

  /**
   * The method of get
   * 
   * @return the value key
   * @throws IllegalNullKeyException when key is null
   * @throws KeyNotFoundException    when key is not found
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    int bucket = hashIndex(key);
    Node<K, V> list = table[bucket]; // traversing list
    if (contains(key) == false) // check if it exists, if not throw exception
      throw new KeyNotFoundException();
    while (list != null) {// if found
      if (list.key.equals(key))
        return list.value;
      else {
        list = list.next;
      }
    }
    return null;

  }

  /**
   * Checks if key in the hash table
   * 
   * @param key
   * @return true when exists
   */
  private boolean contains(K key) { // Checks if key in the hashTable
    int bucket = hashIndex(key);
    Node<K, V> List = table[bucket]; // traversing the list.
    while (List != null) {
      if (List.key.equals(key)) // return true when key found
        return true;
      List = List.next;
    }
    return false;
  }

  /**
   * Get the number of keys
   * 
   * @return the number of keys
   */
  @Override
  public int numKeys() {
    return numKeys;
  }

  /**
   * Get the Load Factor Threshold
   * 
   * @return the Load Factor Threshold
   */
  @Override
  public double getLoadFactorThreshold() {
    return LFThreshold;
  }

  /**
   * Get the Load Factor
   * 
   * @return the Load Factor
   */
  @Override
  public double getLoadFactor() {
    return ((double) (numKeys)) / capacity;
  }

  /**
   * Get the capacity
   * 
   * @return the capacity
   */
  @Override
  public int getCapacity() {
    return capacity;
  }

  /**
   * Get the Collision Resolution
   * 
   * @return the integer of Collision Resolution
   */
  @Override
  public int getCollisionResolution() {
    return 4;
  }
}