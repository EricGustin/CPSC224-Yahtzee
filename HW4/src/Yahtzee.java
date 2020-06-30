import javax.swing.*;
import java.awt.*;
import java.util.Random;
/**
 * This program contains the main method in which a games of single player Yahtzee can repeatedly be played
 * CPSC 224-01, Spring 2020
 * Homework #4
 * Sources: Horstmann Chapter 11
 *
 * @author Eric Gustin
 * @version v4.0 03/26/20
 */
public class Yahtzee {

  /**
   * Main function for the Yahtzee game. Starts the game by creating a YahtzeeConfigGUI.
   *
   * @param args stores the incoming command line arguments for the program
   */
  public static void main(String[] args) {
    YahtzeeConfigGUI configuration =new YahtzeeConfigGUI();
    configuration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    configuration.setSize(new Dimension(500, 300));
    configuration.setVisible(true);
    new Random(System.currentTimeMillis()); // does the same thing as srand(time(0) in C++
  }
}