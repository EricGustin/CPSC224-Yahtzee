import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * This program creates the JFrame where the user can select their configurations for the upcoming game of Yahtzee.
 * Once the player has selected their desired configurations and presses the play button, the frame is disposed of
 * and a new PlayerMainGUI frame is created.
 * CPSC 224-01, Spring 2020
 * Homework #4
 * Sources: Horstmann Chapter 11 on GridBagLayouts
 *
 * @author Eric Gustin
 * @version v4.0 03/26/20
 */
public class YahtzeeConfigGUI extends JFrame {
  /**
   * numOfSides represents the number of sides on a die
   */
  private JComboBox<Integer> numOfSides;
  /**
   * numOfDice represents the number of dice in a hand
   */
  private JComboBox<Integer> numOfDice;
  /**
   * numOfRolls represents the number of rolls per hand
   */
  private JComboBox<Integer> numOfRolls;
  /**
   * playButton represents a button for the user to push once they're ready to play
   */
  private JButton playButton;
  /**
   * configurations represents an int array that holds numOfSides, numOfDice, numOfRolls
   */
  private static int[] configurations = new int[3];

  public YahtzeeConfigGUI() {

    GridBagLayout layout = new GridBagLayout(); // create new GridBagLayout
    setLayout(layout);

    // create components for the JFrame and set their constraints
    JLabel welcomeLabel = new JLabel("Welcome to Lizard Spock Yahtzee!");
    welcomeLabel.setFont(new Font("Serif", Font.BOLD, 28));
    SetConstraints.setConstraints(
            this, welcomeLabel, 100, 100, 0, 0, 7, 1);
    JLabel numOfDiceLabel = new JLabel("Select the number of dice per hand: ");
    SetConstraints.setConstraints(
            this, numOfDiceLabel, 100, 100, 0, 3, 4, 1);
    numOfDice = new JComboBox<>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
    numOfDice.setSelectedIndex(4); // value of 5
    SetConstraints.setConstraints(
            this, numOfDice, 100, 100, 6, 3, 1, 1);
    JLabel numOfSidesLabel = new JLabel("Select the number of sides per die: ");
    SetConstraints.setConstraints(
            this, numOfSidesLabel, 100, 100, 0, 4, 4, 1);
    numOfSides = new JComboBox<>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
    numOfSides.setSelectedIndex(5); // value of 6
    SetConstraints.setConstraints(
            this, numOfSides, 100, 100, 6, 4, 1, 1);
    JLabel numOfRollsLabel = new JLabel("Select the number of rolls per turn: ");
    SetConstraints.setConstraints(
            this, numOfRollsLabel, 100, 100, 0, 5, 4, 1);
    numOfRolls = new JComboBox<>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
    numOfRolls.setSelectedIndex(2); // value of 3
    SetConstraints.setConstraints(
            this, numOfRolls, 100, 100, 6, 5, 1, 1);
    playButton = new JButton("PLAY");
    SetConstraints.setConstraints(
            this, playButton, 100, 100, 6, 8, 1, 1);
    ActionListener listener = event -> setConfigurations(); // calls setConfigurations() when the playButton is pushed
    playButton.addActionListener(listener);

    setVisible(true);
  }

  /**
   * Creates a player object will the user specified configurations as the parameters. Also creates a PlayerMainGUI
   * object, and disposes of the current JFrame
   */
  private void setConfigurations() {
    configurations[0] = numOfSides.getSelectedIndex() + 1; // +1 since indices start at 0 and the elements start at 1.
    configurations[1] = numOfDice.getSelectedIndex() + 1; // +1 since indices start at 0 and the elements start at 1.
    configurations[2] = numOfRolls.getSelectedIndex() + 1; // +1 since indices start at 0 and the elements start at 1.
    Player player0 = new Player(configurations[1], configurations[0], configurations[2]); // make player object
    PlayerMainGUI playerMainGUI = new PlayerMainGUI(player0); // make PlayerMainGUI object and JFrame
    playerMainGUI.setTitle("Yahtzee!");
    playerMainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    playerMainGUI.setSize(new Dimension(900, 750));
    playerMainGUI.setVisible(true);
    playerMainGUI.updateDice();
    this.dispose(); // dispose of this JFrame
  }
}
