
import java.util.TreeMap;

public class MyProfiler<K extends Comparable<K>, V> {
  HashTableADT<K, V> hashtable;
  TreeMap<K, V> treemap;

  public MyProfiler() {
    hashtable = new HashTable<>();
    treemap = new TreeMap<>();
  }

  public void insert(K key, V value) throws IllegalNullKeyException {
    hashtable.insert(key, value);
    treemap.put(key, value);
  }

  public void retrieve(K key) throws IllegalNullKeyException, KeyNotFoundException {
    hashtable.get(key); // gets the value of an entry with the given key from hash table
    treemap.get(key); // gets the value of an entry with the given key from hash table
  }

  public static void main(String[] args) {
    try {
      int numElements = Integer.parseInt(args[0]);
      MyProfiler<Integer, Integer> profile = new MyProfiler<Integer, Integer>();
      for (int i = 0; i < numElements; i++) {
        profile.insert(i, i);
      }
      for (int i = 0; i < numElements; i++) {
        profile.retrieve(i); // Retrieve
      }
      String msg = String.format("Inserted and retreived %d (key,value) pairs", numElements);
      System.out.println(msg);
    } catch (Exception e) {
      System.out.println("Usage: java MyProfiler <number_of_elements>");
      System.exit(1);
    }
  }
}
