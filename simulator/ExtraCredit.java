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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Uses a combination of JavaFrame and Jpanel to successfuly.
 * create a canvas where I can display our population.
 * Timer is imported to update the population every number of seconds. 

 * @author cherresb
 *
 */ 
public class ExtraCredit extends JFrame {
  private static final long serialVersionUID = 1L;
  private Population population;
  
  /**
   * One argument constructor that creates the JFrame and JPanel to create the canvas.
   * The Timer is also created to update the population and rerender the canvas.

   * @param population represents the initial population
   */
  public ExtraCredit(Population population) {
    this.population = population;

    //set up the JFrame (canvas)
    setTitle("Artificial Life Simulator");
    setSize(1000, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Creating the JPanel to render the simulation
    SimulationPanel simulationPanel = new SimulationPanel();
    add(simulationPanel);
    
    //Creating timer to update simulation at regular intervals
    Timer timer = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        population.update();
        simulationPanel.repaint();
      }
    });
    timer.start();
  }
  
  /**
   * Inner class that creates the visuals for every organism in the population.
   * This class has methods that puts the data on the canvas after every update

   * @author cherresb
   *
   */ 
  private class SimulationPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Map<String, List<Point>> organismPositions;
    
    /**
     * zero argument constructor that initializes organismPositions.
     */
    public SimulationPanel() {
      organismPositions = new HashMap<>();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      // Show the population statistics
      Map<String, Integer> populationCounts = population.getPopulationCounts();
      
      int yoffset = 20;
      for (Map.Entry<String, Integer> entry : populationCounts.entrySet()) {
    
        String organismType = entry.getKey();
        int count = entry.getValue();
        g.drawString(organismType + ": " + count, 20, yoffset);
        yoffset += 20;
      }
      //Draw the individual organism with their respective colors
      List<Organism> organisms = population.getOrganisms();
      for (Organism organism : organisms) {
        String type = organism.getType();
        Point position = getRandomPositionForType(type);
        g.setColor(getColorForType(type));
        g.fillOval(position.x, position.y, 30, 30);
        g.setColor(Color.BLACK);
        g.drawString(type.substring(0, 1), position.x + 12, position.y + 18);
      }
    }
    

    
    
    /**
     * Creates a random position for the organisms that will be displayed on the canvas.

     * @param type that represents the type of the organism
    
     * @return Point
     */
    private Point getRandomPositionForType(String type) {
      int gridSize = 50; // Adjust this value for spacing between organisms
      int x = (int) (Math.random() * getWidth() / gridSize) * gridSize;
      int y = (int) (Math.random() * getHeight() / gridSize) * gridSize;

      // Update the positions for the current type
      organismPositions.computeIfAbsent(type, k -> new ArrayList<>()).add(new Point(x, y));

      return new Point(x, y);
    }
    
    /**
     * Gives an organism a color dependant on their type.

     * @param type that represents the type of the organism
    
     * @return Color
     */
    private Color getColorForType(String type) {
      switch (type) {
        case "Cooperator":
          return Color.GREEN;
        case "Defector":
          return Color.RED;
        case "Partial Cooperator":
          return Color.YELLOW;
        default:
          return Color.GRAY;
      }
    } 
  }
  

  /**
   * create and display the visualizer given the initial counts.

   * @param args is the signature for the main method
   */
  public static void main(String[] args) {
    // Initialized population with initial counts
    Map<String, Integer> initialCounts  = new HashMap<>();
    initialCounts.put("Cooperator", 70);
    initialCounts.put("Defector", 30);
    initialCounts.put("Partial Cooperator", 0);
    
    Population population = new Population(initialCounts);
    
    // create and display the visualizer given the initial counts
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new ExtraCredit(population).setVisible(true);
      }
    });
  }
  
}
