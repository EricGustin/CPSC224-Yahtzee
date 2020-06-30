import javax.swing.*;
import java.awt.*;
/**
 * Helper class for other classes that need to add constraints to components in a quick and concise way
 * CPSC 224-01, Spring 2020
 * Homework #4
 * No Sources to cite
 *
 * @author Eric Gustin
 * @version v4.0 03/26/20
 */
public class SetConstraints {
  /**
   * Places constraints on a component and adds it to the JFrame
   * @param frame is the frame that the components belong to
   * @param component is the component that the constraints will be put on
   * @param weightX determines how much the component is willing to move in the x direction as the frame changes size
   * @param weightY determines how much the component is willing to move in the y direction as the frame changes size
   * @param gridX column value
   * @param gridY row value
   * @param gridWidth number of columns it occupies
   * @param gridHeight number of rows it occupies
   */
  public static void setConstraints(JFrame frame,Component component,
                                    int weightX, int weightY, int gridX, int gridY, int gridWidth, int gridHeight) {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = weightX;
    constraints.weighty = weightY;
    constraints.gridx = gridX; // column value
    constraints.gridy = gridY; // row value
    constraints.gridwidth = gridWidth; // number of columns it occupies
    constraints.gridheight = gridHeight; // number of rows it occupies
    frame.add(component, constraints);
  }
}
