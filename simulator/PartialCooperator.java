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
import java.util.Random;
/**
 * Initializes the Partial Cooperator organism and overrides specific Organism methods.

 * @author cherresb
 *
 */
class PartialCooperator extends Organism {
  private final Random random;
  private double cooperationProbability;
  private static final int REPRODUCTION_COST = 10;

  /**
   * Constructor that initializes random and probability.
   */
  public PartialCooperator() {
    random = new Random();
    cooperationProbability = 0.5; // Start with 50% cooperation probability
  }


  /**
   *  returns the type of this Organism as a string.
   *  
   *
   * @return String representation of the type of organism
 
   */
  @Override
    String getType() {
    return "Partial Cooperator";
  }

  /**
   * called by update when the organism can reproduce. Creates an offspring organism and returns it.
   *  
   *
   * @return Organism representation of the offspring 
 
   */
  @Override
  Organism reproduce() {
    for (int i = 0; i <= REPRODUCTION_COST; i++) {
      // Deduct one energy point for reproduction 10 times (use up all of the energy)
      decrementEnergy();
    }
    Organism offspring = new PartialCooperator(); 
    return offspring;
  }

  /**
   * returns the cooperation probability of this organism.
   *  
   *
   * @return double representation of the Cooperation Probability
 
   */
  @Override
  double getCooperationProbability() {
    return cooperationProbability;
    
  }
  
  
  /**
   * returns whether or not the organism cooperates.
   *  
   *
   * @return boolean representation if the organism cooparates
 
   */
  @Override
  boolean cooperates() {
    return random.nextDouble() < cooperationProbability;
  }
}

