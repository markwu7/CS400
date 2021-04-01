//////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////////////
//
// Title: P0_JavaI/O practice
// Files: Main.java
// Course: COMP SCI 400, Spring, 2020
//
// Author: Mark Wu
// Email: hwu378@wisc.edu
// Lecturer's Name: Debra Deppeler
// Description:
// In this program, my idea is that to calculate the efficient field goal for all NBA player last
//////////// season .
// The efficient field goal might not appear in the most NBA stat app, so I set this program to
//////////// calculate it.
// Also eFG is the advanced data to compare each player.
// In the first part, I let the user can calculate eFG% by their own data .
// In the second part, I find the data of, FGM,FGA,3PM from NBA Website and save it to the output.
///////////////////////////// CREDIT OUTSIDE HELP ///////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: N/A
// Online Sources:
// 1.https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java/2885224#2885224
// 2.https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is to do I/O program and calculate the eFG
 */
public class Main {
  private static final Scanner scan = new Scanner(System.in);
  private static final String title =
      "Name: Mark Wu \nwisc.edu mail address: hwu378@wisc.edu \nCS 400 lexture number:001";

  /**
   * Main method is to print my personal info and execute the driver
   * 
   * @param args
   * @throws IOException is thrown when can'y find the location of the file
   */
  public static void main(String[] args) throws IOException {
    System.out.println(title);
    driverApplication();
  }

  /**
   * The method of drive is to execute all the private method
   * 
   * @throws IOException when fails
   */
  public static void driverApplication() throws IOException {

    boolean quit = false;
    promptCalculateEFG();
    while (!quit) { // If the user wanna continue playing, the prompt asked for user
                    // input
      quit = yesno(scan);
    }
    promptFileInputEFG();
  }

  /**
   * This method is to scan the input file to print the eFG to the output file
   *
   */
  private static void promptFileInputEFG() {
    // prompt user for file name input
    System.out
        .println("\nCalculate The NBA player in 2018~ 2019 Effective Field Goal Percentage (EFG%)");
    System.out
        .println("--------------------------------------------------------------------------");
    String fileName = null;
    do {
      System.out.print("\nEnter file name from which to read player stats: ");
      try {
        fileName = scan.nextLine();
        if (fileName.trim().length() == 0) {
          fileName = null;
          throw new Exception("bad input");
        }
      } catch (Exception ex) {
        System.out.println("\nInvalid input.  Please try again.");
      }
    } while (fileName == null);

    String outputFileName = null;
    do {
      System.out.print("\nEnter file name to write player EFG's: ");
      try {
        outputFileName = scan.nextLine();
        if (outputFileName.trim().length() == 0) {
          outputFileName = null;
          throw new Exception("bad input");
        }
      } catch (Exception ex) {
        System.out.println("\nInvalid input.  Please try again.");
      }
    } while (outputFileName == null);

    // read file using Scanner
    List<String> fileLines = new ArrayList<>();
    Scanner fileScanner = null;
    try {
      fileScanner = new Scanner(new File(fileName));// scan the input
      while (fileScanner.hasNextLine()) {
        fileLines.add(fileScanner.nextLine());

      }
    } catch (Exception e) {
      e.printStackTrace();
      fileScanner.close();
      return;
    }
    fileScanner.close();

    // write final EFG for each player to output file
    List<String> efgLines = new ArrayList<>();

    for (String line : fileLines) {
      try {
        double efg = formulaEFG(Double.parseDouble(line.split(",")[4].trim()),
            Double.parseDouble(line.split(",")[2].trim()),
            Double.parseDouble(line.split(",")[3].trim()));
        efgLines.add(String.format("%s (%s): %.2f%%", line.split(",")[0].trim(),
            line.split(",")[1].trim(), efg)); // make the format clear in output
      } catch (Exception e1) {
        System.out.println(
            "\nError parsing input file data.  Please specify a valid file and try again.");
        return;
      }
    }

    // write lines to output file
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new File(outputFileName), "UTF-8");
    } catch (Exception e2) {
      System.out.println("\n Check your output file! Please specify a valid file and try again.");
    }
    for (String line : efgLines) {
      pw.println(line);
      pw.flush(); // print all the data
    }
    pw.close();
    System.out.println("\nOutput has been recorded in file: " + outputFileName);
  }

  /**
   * This method is to interact with the user and let them calculate their own data
   * 
   */
  private static void promptCalculateEFG() {
    // prompt user for input
    System.out.println("\nCalculate Effective Field Goal Percentage (EFG%)");
    System.out.println("================================================");
    int fga = 0;
    do {
      System.out.print("\nEnter total field goal attempts (FGA): ");
      try {
        fga = scan.nextInt();
        if (fga <= 0) {
          fga = 0;
          throw new Exception("bad input");
        }
        scan.nextLine();
      } catch (Exception ex) {
        scan.nextLine();
        System.out.println("\nInvalid input.  FGA must be greater than 0.  Please try again.");
      }
    } while (fga <= 0);

    int fgm = 0;
    do {
      System.out.print("\nEnter total field goals MADE (FGM): ");
      try {
        fgm = scan.nextInt();
        if (fgm <= 0 || fgm > fga) {
          fgm = 0;
          throw new Exception("bad input");
        }
        scan.nextLine();
      } catch (Exception ex) {
        scan.nextLine();
        System.out.println(
            "\nInvalid input.  FGM must be greater than 0 and not greater than FGA.  Please try again.");
      }
    } while (fgm <= 0);
    int threePM = 0;
    do {
      System.out.print("\nEnter total 3-point field goals MADE (ThreePM): ");
      try {
        threePM = scan.nextInt();
        if (threePM <= 0 || threePM > fga) {
          threePM = 0;
          throw new Exception("bad input");
        }
        scan.nextLine();
      } catch (Exception ex) {
        scan.nextLine();
        System.out.println(
            "\nInvalid input.  ThreePM must be greater than 0 and not greater than FGA.  Please try again.");
      }
    } while (threePM <= 0);
    // display EFG%
    System.out.println("EFG%:" + formulaEFG(fga, fgm, threePM) + "%");
  }

  /**
   * This method is to ask the user if they want to continue calculating
   * 
   * @param scan --It's used to receive input
   * @throws IOException--It's thrown when existing the I/O problem
   * @return quit when they don't want to continue calculating
   */
  private static boolean yesno(Scanner scan) throws IOException {
    boolean quit = false;
    System.out.println("Enter \"y\"or \"q\"; y for continue calculating, q for quiting.");
    String s = scan.nextLine().trim();
    if (s.equalsIgnoreCase("y")) {
      promptCalculateEFG();
    }
    if (s.equalsIgnoreCase("q")) {
      quit = true;
      return quit;
    } else {
      System.out.println("Please enter either \"y\" or \"q\"");
      return quit;
    }
  }

  /**
   * This method is the calculating formula of eFG.
   *
   * @param fga---Field Goal Attempt
   * @param fgm--Field  Goal Made
   * @param threePM
   */
  private static double formulaEFG(double fga, double fgm, double threePM) {
    double eFG = 100 * ((fgm + (threePM) * 0.5) / fga);// eFG formula
    return eFG;
  }
}
