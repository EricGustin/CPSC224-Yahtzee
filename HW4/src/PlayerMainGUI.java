import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * This program creates the frame where the player can roll their dice, and also display the scoreboard. The user has
 * the capability to keep the dice that they want to keep.
 * CPSC 224-01, Spring 2020
 * Homework #4
 * Sources: Horstmann Chapter 11 on GridBagLayouts
 *
 * @author Eric Gustin
 * @version v4.0 03/26/20
 */
public class PlayerMainGUI extends JFrame {
  /**
   * diceButtons represents the JToggleButtons that the user can select in order to specify which dice they want
   * to keep
   */
  private ArrayList<JToggleButton> diceButtons;
  /**
   * diceImages represents the images of the dice that are in the player's hand
   */
  private ArrayList<JToggleButton> diceImages;
  /**
   * player represents the Player object
   */
  private Player player;
  /**
   * rollButton represents the button that the user can click once they are ready to roll a new hand
   */
  private JButton rollButton;
  /**
   * viewScorecardButton represents the button that the user can click if they want to see their current
   * game's scorecard
   */
  private JButton viewScorecardButton;
  /**
   * keepAll represents the status of the user's decision to either keep all of their dice, or not keep all of
   * their dice
   */
  private boolean keepAll;

  /**
   * Constructor for the PlayerMainGUI class. Sets up the JFrame with the components that it needs
   * @param player_object represents a Player object
   */
  public PlayerMainGUI(Player player_object) {
    player = player_object;
    player.setCurrentTurn(1); // new hand, so it is the first turn
    keepAll = false;
    diceButtons = new ArrayList<JToggleButton>();
    diceImages = new ArrayList<JToggleButton>();

    GridBagLayout layout = new GridBagLayout();
    setLayout(layout);
    JLabel instructionLabel = new JLabel(
            "Click on the buttons that correspond to the dice you to want to keep, then click roll");
    instructionLabel.setFont(new Font("Serif", Font.BOLD, 24));
    SetConstraints.setConstraints(this,
            instructionLabel, 100, 100, 0, 0, 5, 2);

    // create the dice images and dice buttons components
    for (int i = 0; i < player.getPlayerHand().getSizeOfHand(); i++) {
      JToggleButton dieToggleButton = new JToggleButton(
              String.valueOf(player.getPlayerHand().getHand().get(i).getSideUp()));
      JToggleButton dieImageToggleButton = new JToggleButton(
              new ImageIcon(player.getPlayerHand().getHand().get(i).getSideUp()+"up.png"));
      dieImageToggleButton.setBorder(new LineBorder(Color.BLACK));
      diceImages.add(dieImageToggleButton);
      diceButtons.add(dieToggleButton);
      if (i < 5 ) { // this triple if block helps format the images since the number of images is dynamic
        SetConstraints.setConstraints(this,
                dieImageToggleButton, 100, 100, i, 2, 1, 1);
        SetConstraints.setConstraints(this,
                dieToggleButton, 100, 100, i, 3, 1, 1);
      } if (i >= 5 && i < 10) {
        SetConstraints.setConstraints(this,
                dieImageToggleButton, 100, 100, i - 5, 5, 1, 1);
        SetConstraints.setConstraints(this,
                dieToggleButton, 100, 100, i - 5, 6, 1, 1);
      } if (i >= 10) {
        SetConstraints.setConstraints(this,
                dieImageToggleButton, 100, 100, i - 10, 7, 1, 1);
        SetConstraints.setConstraints(this,
                dieToggleButton, 100, 100, i - 10, 8, 1, 1);
      }
    }

    // create roll and scorecard buttons
    rollButton = new JButton("Roll");
    SetConstraints.setConstraints(this,
            rollButton, 100, 100, 0, 9, 1, 1);
    ActionListener listener = event -> updateDice();
    rollButton.addActionListener(listener);
    viewScorecardButton = new JButton("Scorecard");
    SetConstraints.setConstraints(this,
            viewScorecardButton, 100, 100, 1, 9, 1, 1);
    ActionListener scorecardPressed = event -> makeScorecard();
    viewScorecardButton.addActionListener(scorecardPressed);
    setVisible(true);
  }

  /**
   * Called when the view scorecard button is clicked, adds the scorecard to the current frame for the user to see
   */
  public void makeScorecard() {
    ScorecardGUI scorecardGUI = new ScorecardGUI(this);
    pack(); // temporarily resize the JFrame so that the scorecard can be seen
    setSize(new Dimension(900, 750)); // reset the size of the frame to its original dimensions
  }

  /**
   * Creates a ScoringOptionsGUI object and Jframe and disposes of the current Jframe
   */
  public void makeScoringOptions() {
    ScoringOptionsGUI scoringOptionsGUI = new ScoringOptionsGUI(player);
    scoringOptionsGUI.setTitle("Yahtzee!");
    scoringOptionsGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    scoringOptionsGUI.setSize(new Dimension(900, 750));
    scoringOptionsGUI.setVisible(true);
    dispose();
  }

  /**
   * Called when the roll button is clicked. Rolls a new hand based on the user's preference and updates the JFrame's
   * components
   */
  public void updateDice() {
    keepAll = true;
    for (JToggleButton die : diceButtons) { // check to see if the user wants to keep all of their dice
      if (!die.isSelected()) {
        keepAll = false;
        break;
      }
    }
    if (player.getCurrentTurn() <= player.getNumOfTurns() && !keepAll) { // reroll
      for (int i = 0; i < player.getPlayerHand().getSizeOfHand(); i++) {
        if (!diceButtons.get(i).isSelected())
          player.rollNewDie(i);
        diceButtons.get(i).setText(String.valueOf(player.getPlayerHand().getHand().get(i).getSideUp()));
        diceButtons.get(i).setSelected(false);
        diceImages.get(i).setIcon(new ImageIcon(player.getPlayerHand().getHand().get(i).getSideUp()+"up.png"));
        diceImages.get(i).setSelected(false);
      }
      player.setCurrentTurn(player.getCurrentTurn() + 1);
      setVisible(true);
      if (player.getNumOfTurns()+1  == player.getCurrentTurn() || player.getNumOfTurns() == 1) {
        makeScoringOptions();
      }
    }
    else {
      makeScoringOptions(); // go to scoring options frame
    }
  }

  /**
   * Getter function for Player object
   * @return Player object
   */
  public Player getPlayer() { return player; }
}
