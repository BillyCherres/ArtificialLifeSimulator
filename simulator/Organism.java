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
/**
 * Blueprint for the other organisms.

 * @author cherresb
 *
 */
abstract class Organism {

  private int energy;
  
  /**
   * Initializing the organism-specific state of this object.
   *  By default, an organism starts with zero energy.
   */
  public Organism() {
    energy = 0;
  }
  
  /**
   * updates the given organism. By default, an organism gains one new energy point.
   */
  public void update() {
    incrementEnergy();
  }
  
  /**
  * returns the current energy of this organism.
  *  
  *
  * @return int represents the current energy level of an organism
 
  */
  public int getEnergy()  {
    return this.energy;
  }
  
  
  /**
   * increments this organism’s energy by 1.
   */
  public void incrementEnergy() {
    energy++;
  }

  /**
   * decrements this organism’s energy by 1. An organism’s energy cannot be decremented below 0.
   */
  public void decrementEnergy() {
    energy = Math.max(0, energy - 1);
  }

  
  /**
  * returns the type of this Organism as a string.
  *  
  *
  * @return String representation of the type of arganism
 
  */
  abstract String getType();
  
  /**
  * called by update when the organism can reproduce. Creates an offspring organism and returns it.
  *  
  *
  * @return Organism representation of the offspring 
 
  */
  abstract Organism reproduce();

  
  /**
  *  returns the cooperation probability of this organism.
  *  
  *
  * @return double representation of the Cooperation Probability
 
  */
  abstract double getCooperationProbability();


  /**
  * returns whether or not the organism cooperates.
  *  
  *
  * @return boolean representation if the organism cooparates
 
  */
  abstract boolean cooperates();
 
}