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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Directed and unweighted graph implementation
 */
public class Graph implements GraphADT {
  private int size; // # of edges
  private int order; // # of vertices
  private HashMap<String, ArrayList<String>> mapList;

  /**
   * Default no-argument constructor
   */
  public Graph() {
    this.size = 0;
    this.order = 0;
    mapList = new HashMap<>();
  }

  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or throwing an
   * exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   * 
   * @param vertex the vertex to be added
   */
  @Override
  public void addVertex(String vertex) {
    if (vertex == null) {
      return;
    }

    if (vertex != null && !mapList.containsKey(vertex)) {// check if the vertex is null and if the
                                                         // hashmap already contains the vertex
      mapList.put(vertex, new ArrayList<String>());
      this.order++;
    }
  }

  /**
   * Remove a vertex and all associated edges from the graph.
   * 
   * If vertex is null or does not exist, method ends without removing a vertex, edges, or throwing
   * an exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   * 
   * @param vertex the vertex to be removed
   */
  @Override
  public void removeVertex(String vertex) {
    // check if the vertex is null or if the hashmap didn't contain the vertex
    if (vertex == null || mapList.containsKey(vertex) == false) {
      return;
    }
    if (mapList.containsKey(vertex) == true) {
      mapList.remove(vertex);
      order--;
      for (String key : getAllVertices()) {
        mapList.get(key).remove(vertex);// remove related edges
      }
    }
  }

  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted)
   * 
   * If either vertex does not exist, VERTEX IS ADDED and then edge is created. No exception is
   * thrown.
   *
   * If the edge exists in the graph, no edge is added and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge is not in the graph
   * 
   * @param vertex1 the first vertex (src)
   * @param vertex2 the second vertex (dst)
   */
  @Override
  public void addEdge(String vertex1, String vertex2) {
    // If either vertex does not exist, no edge is added and no exception is thrown.
    if (vertex1 == null || vertex2 == null) {
      return;
    }
    if (mapList.containsKey(vertex1)) {

      if (mapList.get(vertex1).contains(vertex2))
        return;
      else {
        mapList.get(vertex1).add(vertex2);
        this.size++;
      }
    }
    if (!mapList.containsKey(vertex1)) {
      addVertex(vertex1);
      mapList.get(vertex1).add(vertex2);
      this.size++;
    }

  }

  /**
   * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed and unweighted) If
   * either vertex does not exist, or if an edge from vertex1 to vertex2 does not exist, no edge is
   * removed and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge from vertex1 to vertex2 is in the graph
   * 
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   */
  @Override
  public void removeEdge(String vertex1, String vertex2) {
    if (vertex1 == null || vertex2 == null) {// If either vertex does not exist,or if an edge from
                                             // vertex1 to vertex2 does not exist.
      return;
    }

    if (mapList.containsKey(vertex1) && mapList.containsKey(vertex2)) {
      if (mapList.get(vertex1).contains(vertex2)) {
        mapList.get(vertex1).remove(vertex2);
        size--;
      } else {
        return; // no edge is removed and no exception is thrown.
      }
    }

  }

  /**
   * Returns a Set that contains all the vertices
   * 
   * @return a Set<String> which contains all the vertices in the graph
   */
  @Override
  public Set<String> getAllVertices() {
    Set<String> keySet = mapList.keySet();
    return keySet;
  }

  /**
   * Get all the neighbor (adjacent-dependencies) of a vertex
   * 
   * For the example graph, A->[B, C], D->[A, B] getAdjacentVerticesOf(A) should return [B, C].
   * 
   * In terms of packages, this list contains the immediate dependencies of A and depending on your
   * graph structure, this could be either the predecessors or successors of A.
   * 
   * @param vertex the specified vertex
   * @return an List<String> of all the adjacent vertices for specified vertex
   */
  @Override
  public List<String> getAdjacentVerticesOf(String vertex) {
    return mapList.get(vertex).subList(0, mapList.get(vertex).size());
  }

  /**
   * Returns the number of edges in this graph.
   * 
   * @return number of edges in the graph.
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Returns the number of vertices in this graph.
   * 
   * @return number of vertices in graph.
   */
  @Override
  public int order() {
    return this.order;
  }
}
