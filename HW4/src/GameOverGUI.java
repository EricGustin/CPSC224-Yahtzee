import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * This program creates a new JFrame after a full game of Yahtzee has been played. Shows the user their final
 * scorecard along with the option to play a new game of Yahtzee
 * CPSC 224-01, Spring 2020
 * Homework #4
 * Sources: Horstmann Chapter 11 on GridBagLayouts
 *
 * @author Eric Gustin
 * @version v4.0 03/26/20
 */
public class GameOverGUI extends JFrame {
  /**
   * represents the Player object
   */
  Player player;
  /**
   * represents the button that the user can click if they want to play another game of Yahtzee
   */
  JButton playAgainButton;

  /**
   * Constructor for the GameOverGUI class. Creates the game's final JFrame.
   * @param player_object represents the Player object
   */
  public GameOverGUI(Player player_object) {
    player = player_object;

    GridBagLayout layout = new GridBagLayout();
    setLayout(layout);

    if (player.getScorecard().getLinesLeft() <= 0) { // make sure that the game is over
      JLabel congratsLabel = new JLabel("Congratulations! You have successfully completed a game of Yahtzee.");
      congratsLabel.setFont(new Font("Serif", Font.BOLD, 28));
      JLabel textLabel = new JLabel("Here is your final scorecard:");
      textLabel.setFont(new Font("Serif", Font.BOLD, 26));
      SetConstraints.setConstraints(
              this, congratsLabel, 100, 100, 0, 0, 8, 2);
      SetConstraints.setConstraints(
              this, textLabel, 100, 100, 0, 2, 4, 1);
      JLabel infoLabel1 = new JLabel("Upper Scorecard:");
      infoLabel1.setFont(new Font("Serif", Font.BOLD, 20));
      JLabel infoLabel2 = new JLabel("Lower Scorecard:");
      infoLabel2.setFont(new Font("Serif", Font.BOLD, 20));
      SetConstraints.setConstraints(
              this, infoLabel1, 100, 100, 0, 10, 2, 1);
      SetConstraints.setConstraints(
              this, infoLabel2, 100, 100, 2, 10, 2, 1);
      player.getScorecard().displayScorecard(new ScorecardGUI(new PlayerMainGUI(player)), this);

      // create the play again button that calls the restartGame() function if it is clicked
      playAgainButton = new JButton("PLAY AGAIN");
      SetConstraints.setConstraints(
              this, playAgainButton, 100, 100, 6, 2, 1, 1);
      ActionListener listener = event -> restartGame();
      playAgainButton.addActionListener(listener);
      setVisible(true);
    }
  }

  /**
   * This method is called when the playAgainButton is clicked by the user. This method creates a YahtzeeConfigGUI
   * object which effectively starts a new game
   */
  private void restartGame() {
    YahtzeeConfigGUI configuration = new YahtzeeConfigGUI();
    configuration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    configuration.setSize(new Dimension(500, 300));
    configuration.setVisible(true);
    player = null; // delete the player object since the game is over
    this.dispose(); // dispose of this frame
  }
}
