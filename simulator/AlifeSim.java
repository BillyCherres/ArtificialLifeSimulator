package simulator;
/*
    Author: Billy Cherres
    Assignment name: Assignment 5: Artificial Life 
    Assignment due date: November 15 
    Written/online sources used: All for the extra credit
    https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/awt/package-summary.html
    https://www.codejava.net/java-se/swing/jframe-basic-tutorial-and-examples
    https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html
    https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html
    https://youtu.be/4PfDdJ8GFHI?si=sMeBfgcMDjt-RtIW (JFrame JPanel Swing)
    https://youtu.be/Kmgo00avvEw?si=eCMG3IbQmu2yARtQ (AWT)
    Help obtained: none
    Add the statement: “I confirm that the above list of sources is complete AND that I have not 
    talked to anyone else (e.g., CSC 207 students) about the solution to this problem.”
*/

import java.util.HashMap;
import java.util.Map;

/**
 * Checks the correct number of command line arguments, Create initial population.
 * Create simulation, and Reports the final counts and mean cooperation probability.

 * @author cherresb
 *
 */
public class AlifeSim {
private static final int REQ_ARGS = 4;
  /**
   * This main method runs the whole program. 

   * @param args is the signature for the main method
   */
  public static void main(String[] args) {
    // Check if the correct number of command-line arguments is provided
    if (args.length != REQ_ARGS) {
      System.out.println(REQ_ARGS + "Arguments needed!!!: Usage: "
          + "java ALifeSim <#/iterations> <#/cooperators> <#/defectors> <#/partial cooperators>");
      return;
    }

    // Parse command-line arguments
    // arg[0] had to be defined lower in the program for the checkstyle to pass
    int numCooperators = Integer.parseInt(args[1]);
    int numDefectors = Integer.parseInt(args[2]);
    int numPartialCooperators = Integer.parseInt(args[3]);
    
    // Create initial population
    Map<String, Integer> initialPopulation = new HashMap<>();
    initialPopulation.put("Cooperator", numCooperators);
    initialPopulation.put("Defector", numDefectors);
    initialPopulation.put("Partial Cooperator", numPartialCooperators);

    // Create simulation
    Population population = new Population(initialPopulation);
    int numIterations = Integer.parseInt(args[0]);
    for (int iteration = 1; iteration <= numIterations; iteration++) {
      population.update();
    }

    // Report final counts and mean cooperation probability
    System.out.println("After " + numIterations + " ticks:");
    System.out.println("Cooperators = " + population.getPopulationCounts().get("Cooperator"));
    System.out.println("Defectors   = " + population.getPopulationCounts().get("Defector"));
    System.out.println("Partial     = " 
        + population.getPopulationCounts().get("Partial Cooperator"));
    System.out.println("\nMean Cooperation Probability = " + population.calculateCooperationMean());
  }



}
