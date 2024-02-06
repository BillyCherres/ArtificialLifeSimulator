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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;




/**
 * Creates the initial population and initializes the update function.

 * @author cherresb
 *
 */
public class Population {
  private List<Organism> organisms;
  private Map<String, Integer> populationCounts;
  private Random random;

  /**
   * initializes population counts, organisms, and random.

   * @param counts is a map
   */
  public Population(Map<String, Integer> counts) {
    this.populationCounts = new HashMap<>();
    this.organisms = new ArrayList<>();
    this.random = new Random();

    for (Map.Entry<String, Integer> entry : counts.entrySet()) {
      String organismType = entry.getKey();
      int count = entry.getValue();
      if (count < 0) {
        throw new IllegalArgumentException("Organism count cannot be negative.");
      }
      for (int i = 0; i < count; i++) {
        Organism newOrganism = createOrganism(organismType);
        organisms.add(newOrganism);
        populationCounts.put(organismType, populationCounts.getOrDefault(organismType, 0) + 1);
      }
    }
  }
    
  /**
   * Helper function which creates an organism.

   * @param organismType is a String
 
  * @return Organism
  */
  private Organism createOrganism(String organismType) {
    if ("Cooperator".equals(organismType)) {
      return new Cooperator();
    } else if ("Defector".equals(organismType)) {
      return new Defector();
    } else if ("Partial Cooperator".equals(organismType)) {
      return new PartialCooperator();
    } else {
      throw new IllegalArgumentException("Invalid organism type: " + organismType);
    }
  }

  /**
   * Handles reproduction and cooperation for any organism.
   */
  public void update() {
    List<Organism> newOrganisms = new ArrayList<>();
    List<Organism> replacementOrganisms = new ArrayList<>();
    Iterator<Organism> iterator = organisms.iterator();
    
    while (iterator.hasNext()) {
      Organism o = iterator.next();
      o.update();
    
      if (o.cooperates()) {
    
        // Decrement the energy by 1 for cooperation
        o.decrementEnergy();
    
        // Give energy to 8 randomly selected organisms
        for (int i = 0; i < 8; i++) {
          Organism recipient = getRandomOrganismExcept(o);
          recipient.incrementEnergy();
        }
      }
    
      if (o.getEnergy() >= 10) {
        Organism offspring = o.reproduce();
        newOrganisms.add(offspring);
    
        // Randomly select an organism to remove and replace
        int randomIndex = random.nextInt(organisms.size());
        replacementOrganisms.add(organisms.get(randomIndex));
      }
    }
    
    // Replace offspring with random organisms
    if (!replacementOrganisms.isEmpty()) {
      for (int i = 0; i < newOrganisms.size() && i < replacementOrganisms.size(); i++) {
        Organism replacement = replacementOrganisms.get(i);
        int index = organisms.indexOf(replacement);
        if (index >= 0 && index < organisms.size()) {
          organisms.set(index, newOrganisms.get(i));
        }
      }
    }
    replacementOrganisms.clear();
    
    updatePopulationCounts();
  }


  /**
   * Helper function which returns a random organism except the parameter one.

   * @param excludedOrganism is type Organism
 
  * @return Organism
  */
  private Organism getRandomOrganismExcept(Organism excludedOrganism) {
    List<Organism> candidates = new ArrayList<>(organisms);
    candidates.remove(excludedOrganism);
    
    if (candidates.size() > 0) {
      int randomIndex = random.nextInt(candidates.size());
      return candidates.get(randomIndex);
    } else {
      System.out.println("Candidates is empty!!!");
      return null;
    }
  }

  /**
   * Updates the population counts.
   */
  private void updatePopulationCounts() {
    populationCounts.clear();
    for (Organism organism : organisms) {
      String type = organism.getType();
      populationCounts.put(type, populationCounts.getOrDefault(type, 0) + 1);
    }
  }


  /**
  * returns the cooperation mean.
  *   
  *
  * @return double representation of the cooperation mean.

  */
  public double calculateCooperationMean() {
    double totalCooperationProbability = 0;
    int totalOrganisms = organisms.size();

    for (Organism organism : organisms) {
      totalCooperationProbability += organism.getCooperationProbability();
    }

    return totalOrganisms == 0 ? 0 : totalCooperationProbability / totalOrganisms;
  }

  public Map<String, Integer> getPopulationCounts() {
    return new HashMap<>(populationCounts);
  }

  public List<Organism> getOrganisms() {
    return new ArrayList<>(organisms);
  }


}
