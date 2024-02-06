# ArtificialLifeSimulator

## Overview
Artificial Life is a field of computer science that leverages computers to gain insights into the fundamental processes of living systems. This README provides an overview of an artificial life simulation project focused on modeling cooperation between bacteria using object-oriented programming.

## Implementing Artificial Life

### Classes and Sub-Classes
- **Organism Class** (in the "simulator" package)
  - Represents individual bacterium in the population.
  - Constructor and methods:
    - `Organism()`: Initializes organism-specific state with zero energy.
    - `void update()`: Updates the organism, with a default gain of one energy point.
    - `int getEnergy()`: Returns the current energy of the organism.
    - `void incrementEnergy()`: Increments organism's energy by 1.
    - `void decrementEnergy()`: Decrements organism's energy by 1; cannot go below 0.
    - `abstract String getType()`: Returns the type of the organism as a string.
    - `abstract Organism reproduce()`: Creates an offspring organism and returns it.
    - `abstract double getCooperationProbability()`: Returns the cooperation probability of the organism.
    - `abstract boolean cooperates()`: Returns whether the organism cooperates or not.
  - Sub-Classes:
    - **Cooperator**: Always cooperates.
    - **Defector**: Never cooperates.
    - **PartialCooperator**: Cooperates with a 50% probability.

- **Population Class** (in the "simulator" package)
  - Represents the virtual petri dish containing organisms.
  - Constructor and methods:
    - `Population(Map<String, Integer> counts)`: Constructs a population based on organism counts provided.
    - `void update()`: Loops through organisms, updates them, checks cooperation, and handles reproduction.
    - `double calculateCooperationMean()`: Calculates the mean cooperation probability of all organisms.
    - `Map<String, Integer> getPopulationCounts()`: Returns the counts of all organisms in the population.

- **ALifeSim Class**
  - Driver class to create and run the simulation.
  - Main method:
    - `java ALifeSim <#/iterations> <#/cooperators> <#/defectors> <#/partial cooperators>`: Simulates the population for a given number of iterations and reports the final organism counts and mean cooperation probability.

## Cooperation Dynamics
The simulation focuses on studying cooperation dynamics between bacteria of the same species. Organisms have a cooperation probability, influencing their likelihood to cooperate with neighbors. When an organism cooperates, it loses one energy unit but gives eight other organisms an energy unit (randomly selected).

## Usage
To run the simulation:
```bash
java ALifeSim <#/iterations> <#/cooperators> <#/defectors> <#/partial cooperators>
```
## Expected Output
```
Cooperators = <final #/cooperators>
Defectors   = <final #/defectors> 
Partial     = <final #/partial cooperators>  

Mean Cooperation Probability = <mean cooperation probability>
```
