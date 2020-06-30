import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
/**
 * This program creates JFrame that shows the user their scoring options at the end of each hand
 * CPSC 224-01, Spring 2020
 * Homework #4
 * No Sources to cite
 *
 * @author Eric Gustin
 * @version v4.0 03/26/20
 */
public class ScoringOptionsGUI extends JFrame {
  /**
   * represents a Player object
   */
  private Player player;

  /**
   * Constructor for the ScoringOptionsGUI. Sets up the components for the JFrame
   * @param player_object Player object that will be assigned to the player variable
   */
  public ScoringOptionsGUI(Player player_object) {

    player = player_object;
    player.sortHand();
    GridBagLayout layout = new GridBagLayout();
    setLayout(layout);

    // add components
    JLabel instructionLabel = new JLabel(
            "Here is the result of your last roll. Select the line you want to score on");
    instructionLabel.setFont(new Font("Serif", Font.BOLD, 18));
    SetConstraints.setConstraints(this,
            instructionLabel, 100, 100, 0, 0, 5, 2);
    int i = 0; // helps keep track of each component's x position
    for (Die die : player.getPlayerHand().getHand()) {
      JToggleButton dieImage = new JToggleButton(new ImageIcon(die.getSideUp() + "up.png"));
      dieImage.setBorder(new LineBorder(Color.BLACK));
      if (i < 5) {
        SetConstraints.setConstraints(this,
                dieImage, 100, 100, i, 2, 1, 1);
      } if (i >= 5 && i < 10) {
        SetConstraints.setConstraints(this,
                dieImage, 100, 100, i - 5, 4, 1, 1);
      } if (i >= 10) {
        SetConstraints.setConstraints(this,
                dieImage, 100, 100, i - 10, 6, 1, 1);
      }
      i++;
    }
    JLabel lineLabel1 = new JLabel("Line");
    SetConstraints.setConstraints(this,
            lineLabel1, 100, 100, 0, 9, 1, 1);
    JLabel scoreLabel1 = new JLabel("Score");
    SetConstraints.setConstraints(this,
            scoreLabel1, 100, 100, 1, 9, 1, 1);
    JLabel lineLabel2 = new JLabel("Line");
    SetConstraints.setConstraints(this,
            lineLabel2, 100, 100, 3, 9, 1, 1);
    JLabel scoreLabel2 = new JLabel("Score");
    SetConstraints.setConstraints(this,
            scoreLabel2, 100, 100, 4, 9, 1, 1);

    // call the displayScoringOptions method. Adds components that show the user their potential scores on each line
    player.getScorecard().displayScoringOptions(this);
  }
}
