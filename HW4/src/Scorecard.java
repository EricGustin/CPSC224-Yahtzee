import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * This program uses the Hand class to display a player's scorecard as well as their scoring options. The player's
 * scorecard and scoring options update after each hand. The methods in this class also add components to various
 * JFrames
 * CPSC 224-01, Spring 2020
 * Homework #4
 * No Sources to cite
 *
 * @author Eric Gustin
 * @version v4.0 03/26/20
 */
public class Scorecard {
  /**
   * upper_total represents the player's total score for the upper region of the scorecard
   */
  private int upper_total;
  /**
   * lower_total represents the player's total score for the lower region of the scorecard
   */
  private int lower_total;
  /**
   * upper_scorecard is an array that holds each upper line's code, selected status, and points awarded if selected.
   * Each element in upper_scorecard follows the format of "code,selected status,points award if selected"
   */
  private String[] upper_scorecard;
  /**
   * lower_scorecard is an array that holds each lower line's code, selected status, and points awarded if selected.
   * Each element in lower_scorecard follows the format of "code,selected status,points award if selected"
   */
  private String[] lower_scorecard;
  /**
   * upper_potential_scores represents the current hand's potential scoring options for each upper line
   */
  private int[] upper_potential_scores;
  /**
   * upper_potential_scores represents the current hand's potential scoring options for each lower line
   */
  private int[] lower_potential_scores;
  /**
   * hand represents the Hand object passed to the Scorecard class
   */
  private Hand hand;
  /**
   * player represents the Player object
   */
  private Player player;
  /**
   * lines_left keeps track of the number of lines yet to be chosen
   */
  private int lines_left;
  /**
   * pts_to_get_bonus is the summation of the number of sides on a die multiplied by 3
   */
  private int pts_to_get_bonus;

  private ArrayList<JToggleButton> lineButtons;

  /**
   * Scorecard is the constructor for the Scorecard class. Initializes hand, upper_total, lower_total, upper_scorecard,
   * lower_scorecard, lines_left, upper_potential_scores, lower_potential_scores
   * @param hand_object represents the Hand object that will determine the scoring
   * @param player_object represents the Player object that
   */
  public Scorecard(Hand hand_object, Player player_object) {
    hand = hand_object;
    player = player_object;
    upper_total = 0;
    lower_total = 0;
    pts_to_get_bonus = 0;
    for (int i = 1; i <= hand.getHand().get(0).getNumOfSides(); i++)
      pts_to_get_bonus += 3*i;
    upper_scorecard = new String[hand.getHand().get(0).getNumOfSides()]; // init size of upper_scorecard
    lower_scorecard = new String[] {"3K,n,0", "4K,n,0", "FH,n,0", "SS,n,0", "LS,n,0", "Y,n,0", "C,n,0"};

    lines_left = upper_scorecard.length + lower_scorecard.length; // init number of lines left on scoring options
    for (int i = 0; i < upper_scorecard.length; i++) // initialize values of upper scorecard
      upper_scorecard[i] = (i+1) + ",n,0";
    upper_potential_scores = new int[upper_scorecard.length];

    for (int potential_score: upper_potential_scores) // initialize upper_potential_scores array to all 0s.
      potential_score = 0;
    lower_potential_scores = new int[lower_scorecard.length];
    for (int potential_score: lower_potential_scores) // initialize lower_potential_scores array to all 0s.
      potential_score = 0;
    lineButtons = new ArrayList<JToggleButton>();
  }

  /**
   * getLinesLeft is a getter function for the lines_left variable
   * @return lines_left
   */
  public int getLinesLeft() { return lines_left; }

  /**
   * displayScorecard displays the player's upper and lower scorecard for the current Yahtzee game. This method
   * has two cases. 1. Add components to the gameOverGUI. 2. Add components to the scorecardGUI's getPlayerMainGUI
   * @param scorecardGUI represents the ScorecardGUI
   * @param gameOverGUI represents the gameOverGUI
   */
  public void displayScorecard(ScorecardGUI scorecardGUI, GameOverGUI gameOverGUI) {
    int yPos = 12; // keeps track of the gridY for the JLabels that are being added to the GUI
    int xLine = 0; // gridX for the line category in the GUI. xScore is xLine + 1
    upper_total = 0;
    lower_total = 0;
    int upper_sub_total = 0;
    // in order to get the 35pt bonus, the user must average 3 of each dice. This changes with the number of sides.
    int bonus = 0;
    // curr_line[0] == line's code,  curr_line[1] == line's selected status,  curr_line[2] == points awarded if selected
    String[] curr_line;
    // upper scorecard
    for (String line: upper_scorecard) {
      curr_line = line.split(",");
      JLabel currLineLabel = new JLabel(curr_line[0]);
      JLabel currScoreLabel = new JLabel(curr_line[2]);
      if (getLinesLeft() <= 0) { // case for gameOverGUI
        SetConstraints.setConstraints(gameOverGUI,
                currLineLabel, 100, 100, xLine, yPos, 1, 1);
        SetConstraints.setConstraints(gameOverGUI,
                currScoreLabel, 100, 100, xLine + 1, yPos++, 1, 1);
      } else { // case for scorecardGUI's getPlayerMainGUI
        SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
                currLineLabel, 100, 100, xLine, yPos, 1, 1);
        SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
                currScoreLabel, 100, 100, xLine + 1, yPos++, 1, 1);
      }
      upper_sub_total += Integer.parseInt(curr_line[2]);
    }
    if (upper_sub_total >= pts_to_get_bonus)
      bonus = 35;
    upper_total = upper_sub_total + bonus;
    JLabel subTotalLabel = new JLabel("Sub Total");
    JLabel subTotalScoreLabel = new JLabel(String.valueOf(upper_sub_total));
    JLabel bonusLabel = new JLabel("Bonus");
    JLabel upperTotalLabel = new JLabel("Upper Total");
    JLabel upperTotalScoreLabel = new JLabel(String.valueOf(upper_total));
    JLabel bonusScoreLabel = new JLabel(String.valueOf(bonus));
    if (getLinesLeft() <= 0) { // case for gameOverGUI
      SetConstraints.setConstraints(gameOverGUI,
              subTotalLabel, 100, 100, xLine, yPos, 1, 1);
      SetConstraints.setConstraints(gameOverGUI,
              subTotalScoreLabel, 100, 100, xLine + 1, yPos++, 1, 1);
      SetConstraints.setConstraints(gameOverGUI,
              bonusLabel, 100, 100, xLine, yPos, 1, 1);
      SetConstraints.setConstraints(gameOverGUI,
              bonusScoreLabel, 100, 100, xLine + 1, yPos++, 1, 1);
      SetConstraints.setConstraints(gameOverGUI,
              upperTotalLabel, 100, 100, xLine, yPos, 1, 1);
      SetConstraints.setConstraints(gameOverGUI,
              upperTotalScoreLabel, 100, 100, xLine + 1, yPos++, 1, 1);
    } else { // case for scorecardGUI's getPlayerMainGUI
      SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
              subTotalLabel, 100, 100, xLine, yPos, 1, 1);
      SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
              subTotalScoreLabel, 100, 100, xLine + 1, yPos++, 1, 1);
      SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
              bonusLabel, 100, 100, xLine, yPos, 1, 1);
      SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
              bonusScoreLabel, 100, 100, xLine + 1, yPos++, 1, 1);
      SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
              upperTotalLabel, 100, 100, xLine, yPos, 1, 1);
      SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
              upperTotalScoreLabel, 100, 100, xLine + 1, yPos++, 1, 1);
    }
    // lower scorecard
    yPos = 12;
    xLine = 2;
    for (String line: lower_scorecard) {
      curr_line = line.split(",");
      JLabel lowerLineLabel = new JLabel(curr_line[0]);
      JLabel lowerScoreLabel = new JLabel(curr_line[2]);
      if (getLinesLeft() <= 0) { // case for gameOverGUI
        SetConstraints.setConstraints(gameOverGUI,lowerLineLabel,
                100, 100, xLine, yPos, 1, 1);
        SetConstraints.setConstraints(gameOverGUI,lowerScoreLabel,
                100, 100, xLine + 1, yPos++, 1, 1);
      } else { // case for scorecardGUI's getPlayerMainGUI
        SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
                lowerLineLabel, 100, 100, xLine, yPos, 1, 1);
        SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
                lowerScoreLabel, 100, 100, xLine + 1, yPos++, 1, 1);
      }
      lower_total += Integer.parseInt(curr_line[2]);
    }
    JLabel lowerTotalLabel = new JLabel("Lower Total");
    JLabel lowerTotalScoreLabel = new JLabel(String.valueOf(lower_total));
    JLabel grandTotalLabel = new JLabel("Grand Total");
    JLabel grandTotalScoreLabel = new JLabel(String.valueOf(upper_total + lower_total));
    if (getLinesLeft() <= 0) { // case for gameOverGUI
      SetConstraints.setConstraints(gameOverGUI,lowerTotalLabel,
              100, 100, xLine, yPos, 1, 1);
      SetConstraints.setConstraints(gameOverGUI,lowerTotalScoreLabel,
              100, 100, xLine + 1, yPos++, 1, 1);
      SetConstraints.setConstraints(gameOverGUI,grandTotalLabel,
              100, 100, xLine, yPos, 1, 1);
      SetConstraints.setConstraints(gameOverGUI,grandTotalScoreLabel,
              100, 100, xLine + 1, yPos++, 1, 1);
    } else { // case for scorecardGUI's getPlayerMainGUI
      SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
              lowerTotalLabel, 100, 100, xLine, yPos, 1, 1);
      SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
              lowerTotalScoreLabel, 100, 100, xLine + 1, yPos++, 1, 1);
      SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
              grandTotalLabel, 100, 100, xLine, yPos, 1, 1);
      SetConstraints.setConstraints(scorecardGUI.getPlayerMainGUI(),
              grandTotalScoreLabel, 100, 100, xLine + 1, yPos++, 1, 1);
    }
  }

  /**
   * displayScoringOptions displays the scoring options for a player after a full hand has been played. If a line is
   * selected, then it will not appear for the rest of the game
   * @param scoringOptionsGUI represents the ScoringOptions object
   */
  public void displayScoringOptions(ScoringOptionsGUI scoringOptionsGUI) {
    lineButtons.clear();
    // curr_line[0] == line's code,  curr_line[1] == line's selected status,  curr_line[2] == points awarded if selected
    String[] curr_line;
    int i = 1;
    for (int dieValue = 1; dieValue <= hand.getHand().get(0).getNumOfSides(); dieValue++) {
      int currentCount = 0;
      for (Die element : hand.getHand()) { // iterate through player's hand
        if (dieValue == element.getSideUp()) { // if the curr die value is in the players hand
          currentCount++;
        }
      }
      upper_potential_scores[dieValue-1] = dieValue * currentCount; // update the upper_potential_scores
      // access the upper_scorecard element that corresponds to the current dieValue.
      curr_line = upper_scorecard[dieValue-1].split(",");
      if (curr_line[1].equals("n")) { // only display a scoring option if it hasn't been selected yet

        JToggleButton currLineButton = new JToggleButton(String.valueOf(dieValue));
        SetConstraints.setConstraints(scoringOptionsGUI,
                currLineButton, 100, 100, 0, i + 10, 1, 1);
        ActionListener listener = event -> findPickCode(scoringOptionsGUI);
        currLineButton.addActionListener(listener);
        lineButtons.add(currLineButton);
        JLabel currScoreLabel = new JLabel(String.valueOf(upper_potential_scores[dieValue - 1]));
        SetConstraints.setConstraints(scoringOptionsGUI,
                currScoreLabel, 100, 100, 1, i + 10, 1, 1);
        i++;
      }

    }
    // update the lower_potential_scores. The number of lower_potential_scores is static, so it is hard coded
    lower_potential_scores[0] = (hand.maxOfAKindFound() >= 3) ? hand.totalAllDice() : 0;
    lower_potential_scores[1] = (hand.maxOfAKindFound() >= 4) ? hand.totalAllDice() : 0;
    lower_potential_scores[2] = (hand.fullHouseFound()) ? 25 : 0;
    lower_potential_scores[3] = (hand.maxStraightFound() >= 4) ? 30 : 0;
    lower_potential_scores[4] = (hand.maxStraightFound() >= 5) ? 40 : 0;
    lower_potential_scores[5] = (hand.maxOfAKindFound() >= 5) ? 50 : 0;
    lower_potential_scores[6] = hand.totalAllDice();

    i = 0;
    for (int lower_line_number = 1; lower_line_number <= lower_scorecard.length; ++lower_line_number) {
      curr_line = lower_scorecard[lower_line_number-1].split(",");
      if (curr_line[1].equals("n")) { // only display a scoring option if it hasn't been selected yet
        JToggleButton currLineButton = new JToggleButton(curr_line[0]);
        SetConstraints.setConstraints(scoringOptionsGUI,
                currLineButton, 100, 100, 3, i + 10, 1, 1);
        ActionListener listener = event -> findPickCode(scoringOptionsGUI);
        currLineButton.addActionListener(listener);
        lineButtons.add(currLineButton);
        JLabel currScoreLabel = new JLabel(String.valueOf(lower_potential_scores[lower_line_number - 1]));
        SetConstraints.setConstraints(scoringOptionsGUI,
                currScoreLabel, 100, 100, 4, i + 10, 1, 1);
        i++;
      }
    }
  }

  /**
   * The findPickCode method finds the line that the user selected to score on, and saves this selection in an array
   * If this method is called, and there are no more lines left to score on, then the game is over and a GameOverGUI
   * is created. Otherwise, the method creates a PlayerMainGUI object
   * @param scoringOptionsGUI represents the ScoringOptionsGUI object
   */
  private void findPickCode(ScoringOptionsGUI scoringOptionsGUI) {
    String selected_code = "";
    String[] part_of_scorecard = lower_scorecard;
    int[] potential_scores = lower_potential_scores;
    for (JToggleButton button : lineButtons) {
      if (button.isSelected()) {
        selected_code = button.getText();
        break;
      }
    }
    if ((selected_code.length() == 1 && Character.isDigit(selected_code.charAt(0))) ||
            (selected_code.length() == 2 && Character.isDigit(selected_code.charAt(1)))) {
      part_of_scorecard = upper_scorecard;
      potential_scores = upper_potential_scores;
    }

    String[] curr_line;
    for (int i = 0; i < part_of_scorecard.length; i++) {
      curr_line = part_of_scorecard[i].split(",");
      if (curr_line[0].equals(selected_code)) {
        part_of_scorecard[i] = selected_code + ",y," + potential_scores[i];
        lines_left -= 1;
      }
    }
    scoringOptionsGUI.dispose();
    if (getLinesLeft() > 0) {
      PlayerMainGUI playerMainGUI = new PlayerMainGUI(player);
      playerMainGUI.setTitle("Yahtzee!");
      playerMainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      playerMainGUI.setSize(new Dimension(900, 750));
      playerMainGUI.setVisible(true);
      playerMainGUI.updateDice();
    }
    else {
      System.out.println("Game over");
      GameOverGUI gameOverGUI = new GameOverGUI(player);
      gameOverGUI.setTitle("Yahtzee!");
      gameOverGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      gameOverGUI.setSize(new Dimension(900, 750));
      gameOverGUI.setVisible(true);
    }
  }
}
