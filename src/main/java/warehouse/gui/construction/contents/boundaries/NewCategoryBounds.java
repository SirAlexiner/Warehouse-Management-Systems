package warehouse.gui.construction.contents.boundaries;

import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * This class sets the bounds of the components in the add category panel.
 */
@UtilityClass
public class NewCategoryBounds {

  /**
   * Set the bounds of the add category components.
   */
  public void setNewCategoryBounds() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Setting the bounds of the components in the add category panel.
    comp.addCategory.setBounds(10, 10, 280, 30);
    comp.addCategoryBtn.setBounds(150, 50, 140, 30);
  }
}
