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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;

/**
 * 
 * PackageManager is used to process json package dependency files and provide function that make
 * that information available to other users.
 * 
 * Each package that depends upon other packages has its own entry in the json file.
 * 
 * Package dependencies are important when building software, as you must install packages in an
 * order such that each package is installed after all of the packages that it depends on have been
 * installed.
 * 
 * For example: package A depends upon package B, then package B must be installed before package A.
 * 
 * This program will read package information and provide information about the packages that must
 * be installed before any given package can be installed. all of the packages in
 * 
 * You may add a main method, but we will test all methods with our own Test classes.
 */

public class PackageManager {
  private Graph graph;

  /**
   * Package Manager default no-argument constructor.
   */
  public PackageManager() {
    graph = new Graph();
  }

  /**
   * Takes in a file path for a json file and builds the package dependency graph from it.
   * 
   * @param jsonFilepath the name of json data file with package dependency information
   * @throws FileNotFoundException if file path is incorrect
   * @throws IOException           if the give file cannot be read
   * @throws ParseException        if the given json cannot be parsed
   */
  public void constructGraph(String jsonFilepath)
      throws FileNotFoundException, IOException, ParseException {
    // create JSON object, parsing file
    Object object = new JSONParser().parse(new FileReader(jsonFilepath));
    JSONObject jObject = (JSONObject) object;// typecast
    // create JSON array, get package
    JSONArray jArray = (JSONArray) jObject.get("packages");

    // loop through JSON array and add key/value pairs to graph
    for (int i = 0; i < jArray.size(); i++) {
      JSONObject obj = (JSONObject) jArray.get(i);
      String name = (String) obj.get("name");// get each package's name
      graph.addVertex(name);// add name be a vertex

      JSONArray dependenciesArray = (JSONArray) obj.get("dependencies");// get each dependencies
      for (int j = 0; j < dependenciesArray.size(); j++) {
        graph.addEdge((String) obj.get("name"), (String) dependenciesArray.get(j));
      }
    }
  }

  /**
   * Helper method to get all packages in the graph.
   * 
   * @return Set<String> of all the packages
   */
  public Set<String> getAllPackages() {
    // getter method
    return graph.getAllVertices();
  }

  /**
   * Given a package name, returns a list of packages in a valid installation order.
   * 
   * Valid installation order means that each package is listed before any packages that depend upon
   * that package.
   * 
   * @return List<String>, order in which the packages have to be installed
   * 
   * @throws CycleException           if you encounter a cycle in the graph while finding the
   *                                  installation order for a particular package. Tip: Cycles in
   *                                  some other part of the graph that do not affect the
   *                                  installation order for the specified package, should not throw
   *                                  this exception.
   * 
   * @throws PackageNotFoundException if the package passed does not exist in the dependency graph.
   */
  public List<String> getInstallationOrder(String pkg)
      throws CycleException, PackageNotFoundException {
    Set<String> packageList = getAllPackages();
    if (pkg != null && packageList.contains(pkg)) {
      ArrayList<String> visit = new ArrayList<String>();
      return getInstallationOrderHelper(pkg, visit);
    } else {
      throw new PackageNotFoundException();
    }
  }

  /**
   * The  private helper method getInstallationOrder
   * 
   * @param pkg     - the pkg
   * @param visited -
   * @return List<String> order in which the packages have to be installed
   * @throws CycleException -when the cycle happened
   */
  private List<String> getInstallationOrderHelper(String pkg, ArrayList<String> visited)
      throws CycleException {
    List<String> output = new ArrayList<String>(); // the output list I return
    // the vertex should not be visited
    if (visited.contains(pkg))
      throw new CycleException();
    visited.add(pkg); // mark current as visited

    // list of adjacent vertices
    List<String> adj = graph.getAdjacentVerticesOf(pkg);

    for (int i = 0; i < adj.size(); i++) { // loop adjacent vertices
      List<String> installOrder = getInstallationOrderHelper(adj.get(i), visited);
      for (int j = 0; j < output.size(); j++) {
        installOrder.remove(output.get(j)); // remove visited vertices
      }
      output.addAll(installOrder); // add rest of vertices
    }
    visited.remove(pkg);
    output.add(pkg);
    return output;
  }

  /**
   * Given two packages - one to be installed and the other installed, return a List of the packages
   * that need to be newly installed.
   * 
   * For example, refer to shared_dependecies.json - toInstall("A","B") If package A needs to be
   * installed and packageB is already installed, return the list ["A", "C"] since D will have been
   * installed when B was previously installed.
   * 
   * @return List<String>, packages that need to be newly installed.
   * 
   * @throws CycleException           if you encounter a cycle in the graph while finding the
   *                                  dependencies of the given packages. If there is a cycle in
   *                                  some other part of the graph that doesn't affect the parsing
   *                                  of these dependencies, cycle exception should not be thrown.
   * 
   * @throws PackageNotFoundException if any of the packages passed do not exist in the dependency
   *                                  graph.
   */
  public List<String> toInstall(String newPkg, String installedPkg)
      throws CycleException, PackageNotFoundException {
    Set<String> packageList = getAllPackages();
    List<String> allPkg=null;
    List<String> already=null;
    if (!packageList.contains(newPkg) || !packageList.contains(installedPkg)) {
      throw new PackageNotFoundException();
    }
    try {
      allPkg = this.getInstallationOrder(newPkg);
      already = this.getInstallationOrder(installedPkg);
      for (int i = 0; i < allPkg.size(); i++) {
       String currPack = allPkg.get(i);
        if (already.contains(currPack)) {// check if already contained
          allPkg.remove(i); // remove the already existing package
          i--;
        }
      }
    } catch (CycleException e) {
      throw new CycleException();
    }
    return allPkg;
  }

  /**
   * Return a valid global installation order of all the packages in the dependency graph.
   * 
   * assumes: no package has been installed and you are required to install all the packages
   * 
   * returns a valid installation order that will not violate any dependencies
   * 
   * @return List<String>, order in which all the packages have to be installed
   * @throws CycleException if you encounter a cycle in the graph
   */
  public List<String> getInstallationOrderForAllPackages() throws CycleException {
    List<String> result = new ArrayList<>();//list to return
    for(String vertice: graph.getAllVertices()) {
        if (!result.contains(vertice)) {//don't  visit the package again
            List<String> list;
            try {
              list = getInstallationOrder(vertice);
              for (String s : list) {
                if (!result.contains(s)) {
                    result.add(s);
                }
            }
            } catch (PackageNotFoundException e) {
            }
        }
    }
    return result;
  }

  /**
   * Find and return the name of the package with the maximum number of dependencies.
   * 
   * Tip: it's not just the number of dependencies given in the json file. The number of
   * dependencies includes the dependencies of its dependencies. But, if a package is listed in
   * multiple places, it is only counted once.
   * 
   * Example: if A depends on B and C, and B depends on C, and C depends on D. Then, A has 3
   * dependencies - B,C and D.
   * 
   * @return String, name of the package with most dependencies.
   * @throws CycleException if you encounter a cycle in the graph
   */
  public String getPackageWithMaxDependencies() throws CycleException {
    ArrayList<String> packages = new ArrayList<String>();
    Set<String> allPackages = this.graph.getAllVertices();
    packages.addAll(allPackages);
    int max = 0;
    String pkgMax = "";
    for (String pkg : packages) {//track all packages 
        try {
          int  temp=this.getInstallationOrder(pkg).size();
            if (temp> max) {//compare here
                max = temp;
                pkgMax = pkg;//swap
            }
        } catch (PackageNotFoundException e){}
    }
    return pkgMax;
}
  
  /**
   *Drive method for PackManager
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("PackageManager.main()");
  }

}
