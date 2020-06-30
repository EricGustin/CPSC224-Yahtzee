/**
 * The player class represents a player in a game of Yahtzee. This class uses the Hand and Scorecard classes
 * CPSC 224-01, Spring 2020
 * Homework #4
 * No Sources to cite
 *
 * @author Eric Gustin
 * @version v4.0 03/26/20
 */
public class Player {

  /**
   * hand represents the player's hand of dice
   */
  private Hand hand;
  /**
   * scorecard represents the player's scorecard
   */
  private Scorecard scorecard;
  /**
   * curr_turns represents the player's current turn in a hand of Yahtzee
   */
  private int curr_turn;
  /**
   * NUM_OF_TURNS represents the number of turns allowed for a single hand of Yahtzee
   */
  private int NUM_OF_TURNS;

  /**
   * Constructor for the Player class. Creates a new hand and scorecard object, and initializes NUM_OF_TURNS,
   * curr_turn, play_again_status, and dice_to_keep
   * @param num_of_dice represents the number of dice in a player's hand
   * @param num_of_sides represents the number of sides on a die
   * @param num_of_turns represents the number of turns per hand
   */
  public Player(int num_of_dice, int num_of_sides, int num_of_turns) {
    hand = new Hand(num_of_dice, num_of_sides);
    scorecard = new Scorecard(hand, this);
    NUM_OF_TURNS = num_of_turns;
    curr_turn = 1;

  }

  /**
   * getPlayerHand is a getter function for the hand object. Calls Hand.getHand()
   * @return hand.getHand() the player's hand of dice
   */
  public Hand getPlayerHand() {
    return hand;
  }

  /**
   * Rolls a single die
   * @param dieIndex index of the die to roll in the player's hand
   */
  public void rollNewDie(int dieIndex) {
    hand.getHand().get(dieIndex).roll();
  }

  /**
   * sortHand calls the sortHand() method on the player's hand
   */
  public void sortHand() { hand.sortHand(); }

  /**
   * setCurrentTurn changes the value of curr_turn
   * @param new_curr_turn is the value that curr_turn is set to
   */
  public void setCurrentTurn(int new_curr_turn) { curr_turn = new_curr_turn; }

  /**
   * getCurrentTurn is a getter method for the curr_turn int variable
   * @return curr_turn the current turn of the player
   */
  public int getCurrentTurn() { return curr_turn; }

  /**
   * getNumOfTurns is a getter method for the NUM_OF_TURNS int variable
   * @return NUM_OF_TURNS the number of turns in a game of Yahtzee
   */
  public int getNumOfTurns() { return NUM_OF_TURNS; }

  /**
   * Getter function for a Scorecard object
   * @return scorecard
   */
  public Scorecard getScorecard() { return scorecard; }
}