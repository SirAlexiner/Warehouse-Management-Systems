package warehouse.gui.construction.contents;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;


/**
 * It creates the components for the Edit Category panel.
 */
@UtilityClass
public class CreateEditCategory {

  /**
   * It creates a panel that allows the user to rename a category.
   *
   * @param panel The panel that the components will be added to.
   */
  public void constructEditCategoryPanel(JPanel panel) {
    // Creating a new instance of the Components class.
    Components comp = Components.getComponents();
    // Creating the components for the Edit Category panel.
    comp.categoryRenameLabel = new JLabel("Category to Rename:");
    comp.categoryRenameSelect = new JComboBox<>(comp.categoryArray);
    comp.newCategoryNameLabel = new JLabel("Enter New Name:");
    comp.newCategoryName = new JTextField(5);
    comp.renameCategoryWarningLabel = new JLabel("This Change does not affect Products");
    comp.renameCategoryBtn = new JButton("Rename");
    // Adding the components to the panel.
    panel.add(comp.categoryRenameLabel);
    panel.add(comp.categoryRenameSelect);
    panel.add(comp.newCategoryNameLabel);
    panel.add(comp.newCategoryName);
    panel.add(comp.renameCategoryWarningLabel);
    panel.add(comp.renameCategoryBtn);
  }
}
