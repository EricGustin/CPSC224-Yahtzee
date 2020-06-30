import javax.swing.*;
import java.awt.*;
/**
 * This program adds to the playerMainGUI JFrame by showing the user their current scorecard. Calls the
 * displayScorecard() that is in the Scorecard class
 * CPSC 224-01, Spring 2020
 * Homework #4
 * No Sources to cite
 *
 * @author Eric Gustin
 * @version v4.0 03/26/20
 */
public class ScorecardGUI extends JFrame {
  /**
   * represents the PlayerMainGUI object
   */
  private PlayerMainGUI playerMainGUI;

  /**
   * Constructor for the ScorecardGUI class. Adds components to the PlayerMainGUI JFrame
   * @param playerMainGUI_object represents the PlayerMainGUI object to be added to
   */
  public ScorecardGUI(PlayerMainGUI playerMainGUI_object) {

    playerMainGUI = playerMainGUI_object;

    if (playerMainGUI.getPlayer().getScorecard().getLinesLeft() > 0) { // ensure that the game is not over
      // add components to the PlayerMainGUI JFrame
      JLabel infoLabel1 = new JLabel("Upper Scorecard:");
      infoLabel1.setFont(new Font("Serif", Font.BOLD, 20));
      JLabel infoLabel2 = new JLabel("Lower Scorecard:");
      infoLabel2.setFont(new Font("Serif", Font.BOLD, 20));
      JLabel lineLabel1 = new JLabel("Line");
      JLabel scoreLabel1 = new JLabel("Score");
      JLabel lineLabel2 = new JLabel("Line");
      JLabel scoreLabel2 = new JLabel("Score");
      SetConstraints.setConstraints(
              playerMainGUI, infoLabel1, 100, 100, 0, 10, 2, 1);
      SetConstraints.setConstraints(
              playerMainGUI, infoLabel2, 100, 100, 2, 10, 2, 1);
      SetConstraints.setConstraints(
              playerMainGUI, lineLabel1, 100, 100, 0, 11, 1, 1);
      SetConstraints.setConstraints(
              playerMainGUI, scoreLabel1, 100, 100, 1, 11, 1, 1);
      SetConstraints.setConstraints(
              playerMainGUI, lineLabel2, 100, 100, 2, 11, 1, 1);
      SetConstraints.setConstraints(
              playerMainGUI, scoreLabel2, 100, 100, 3, 11, 1, 1);

      // call the displayScorecard method in order to get the data for the player's hand so that they can be
      // made into components of the JFrame
      playerMainGUI.getPlayer().getScorecard().displayScorecard(
              this, new GameOverGUI(playerMainGUI.getPlayer()));
    }
  }

  /**
   * getter function for the PlayerMainGUI object
   * @return playerMainGUI
   */
  public PlayerMainGUI getPlayerMainGUI() { return playerMainGUI; }
}
