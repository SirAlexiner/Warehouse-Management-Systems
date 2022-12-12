package warehouse.gui.construction.contents;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * This class creates a new category panel that
 * allows the user to add a new category to the database.
 */
@UtilityClass
public class CreateNewCategory {

  /**
   * This function creates a new JTextField and JButton, and adds them to the JPanel.
   *
   * @param panel The panel that the components will be added to.
   */
  public void constructNewCategoryPanel(JPanel panel) {
    // Creating a new instance of the Components class.
    Components comp = Components.getComponents();
    // Creating a new JTextField and JButton, and adding them to the JPanel.
    comp.addCategory = new JTextField(5);
    comp.addCategory.setToolTipText("Please Provide a New Product Category");
    comp.addCategoryBtn = new JButton("Add Category");
    // Adding the components to the panel.
    panel.add(comp.addCategory);
    panel.add(comp.addCategoryBtn);
  }
}
