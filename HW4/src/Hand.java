import java.util.ArrayList;

/**
 * This program is used to create a player's hand of Yahtzee. Uses the Die class
 * CPSC 224-01, Spring 2020
 * Homework #4
 * Sources to cite: Gaddis chapter 8 bubble sort
 *
 * @author Eric Gustin
 * @version v4.0 03/26/20
 */
public class Hand {

  /**
   * SIZE_OF_HAND represents the number of dice in a player's hand
   */
  private int SIZE_OF_HAND;
  /**
   * hand represents a player's hand of dice
   */
  private ArrayList<Die> hand;

  /**
   * Hand is the constructor for the Hand class. Initializes SIZE_OF_HAND and hand
   * @param size_of_hand the number of dice in a player's hand
   * @param num_of_sides the number of sides per die which is used to fill the hand with dice that follow
   *                     this configuration
   */
  public Hand(int size_of_hand, int num_of_sides) {
    SIZE_OF_HAND = size_of_hand;

    hand = new ArrayList<Die>(); // initialize empty hand
    for (int i = 0; i < SIZE_OF_HAND; i++) // initialize hand with new Die objects.
      hand.add(new Die(num_of_sides));
  }

  /**
   * getSizeOfHand is a getter function for the SIZE_OF_HAND int variable
   * @return SIZE_OF_HAND the number of dice in a player's hand
   */
  public int getSizeOfHand() {
    return SIZE_OF_HAND;
  }

  /**
   * getHand is a getter function for the player's hand
   * @return hand, the player's hand of dice
   */
  ArrayList<Die> getHand() {
    return hand;
  }


  /**
   * maxOfAKindFound calculates and returns the count of the Die value occurring most in the hand but
   * not the value itself
   * @return maxCount the largest of-a-kind found in the player's hand
   */
  public int maxOfAKindFound() {
    int maxCount = 0;
    int currentCount;
    for (int dieValue = 1; dieValue <= hand.get(0).getNumOfSides(); dieValue++) {
      currentCount = 0;
      for (Die element : hand) {
        if (dieValue == element.getSideUp())
          currentCount++;
      }
      if (currentCount > maxCount)
        maxCount = currentCount;
    }
    return maxCount;
  }

  /**
   * totalAllDice calculates and returns the total value of all dice in a hand
   * @return total the summed value of all the dice in a player's hand
   */
  public int totalAllDice() {
    int total = 0;
    for (Die element : hand)
      if (element.getSideUp() != 0) // ensure that the program doesn't accidentally try to add non digits
        total += element.getSideUp();
    return total;
  }

  /**
   * sort hand  sorts the hand so that straights can be evaluated by using the bubble sort
   * from Gaddis chapter 8 translated into Java
   */
  public void sortHand() {
    boolean swap;
    Die temp;

    do {
      swap = false;
      for (int count = 0; count < (getSizeOfHand() - 1); count++) {
        if (hand.get(count).getSideUp() > hand.get(count + 1).getSideUp()) {
          temp = hand.get(count);
          hand.set(count, hand.get(count + 1));
          hand.set(count + 1, temp);
          swap = true;
        }
      }
    } while (swap);
  }

  /**
   * maxStraightFound calculates and returns the length of the longest straight found in a hand
   * @return maxLength the longest straight found in the player's hand
   */
  public int maxStraightFound() {
    int maxLength = 1;
    int curLength = 1;
    for (int counter = 0; counter < getSizeOfHand()-1; counter++) {
      if (hand.get(counter).getSideUp() + 1 == hand.get(counter + 1).getSideUp()) //jump of 1
        curLength++;
      else if (hand.get(counter).getSideUp() + 1 < hand.get(counter + 1).getSideUp()) //jump of >= 2
        curLength = 1;
      if (curLength > maxLength)
        maxLength = curLength;
    }
    return maxLength;
  }

  /**
   * fullHouseFound determines whether a full house exists and returns true if so. Else, returns false. A full house
   * is a 3 of a kind and a 2 of a kind in the same hand. A 5 of a kind is also a full house.
   * @return foundFH a boolean that represents whether or not a player's hand contains a full house
   */
  public boolean fullHouseFound() {
    if (maxOfAKindFound() >= 5) return true;  // check for 5 of a kind
    boolean found3K = false;
    boolean found2k = false;
    // check for 3 of a kind
    int currentCount;
    for (int dieValue = 1; dieValue <= hand.get(0).getNumOfSides(); ++dieValue) {
      currentCount = 0;
      for (int diePosition = 0; diePosition < getSizeOfHand(); ++diePosition) {
        if (hand.get(diePosition).getSideUp() == dieValue)
          ++currentCount;
      }
      if (currentCount == 2)
        found2k = true;
      if (currentCount == 3)
        found3K = true;
    }
    if (found2k && found3K)
      return true;
    return false;
  }
}