import java.util.Random;

/**
 * Represents a die for a Yahtzee game
 * CPSC 224-01, Spring 2020
 * Homework #3
 * No Sources to cite
 *
 * @author Eric Gustin
 * @version v3.0 02/27/20
 */
public  class Die {
  /**
   * NUM_OF_SIDES represents the number of sides on the die
   */
  private int NUM_OF_SIDES;
  /**
   * sideUp represents the current status of the up facing side of the die
   */
  private int sideUp;

  /**
   * Constructor for Die. Initializes NUM_OF_SIDES and sideUp to their starting values
   * @param num_of_sides the number of sides for a die
   */
  public Die(int num_of_sides) {
    NUM_OF_SIDES = num_of_sides;
    sideUp = 0;
  }

  /**
   * getNumOfSides is a getter function for the NUM_OF_SIDES int variable
   * @return NUM_OF_SIDES
   */
  public int getNumOfSides() {
    return NUM_OF_SIDES;
  }

  /**
   * getSideUp is a getter function for the sideUp int variable
   * @return sideUp
   */
  public int getSideUp() {
    return sideUp;
  }

  /**
   * roll simulates the pseudo-random rolling of a single die
   */
  public void roll() {
    Random rand = new Random();
    sideUp = rand.nextInt(getNumOfSides())+1;
  }
}
