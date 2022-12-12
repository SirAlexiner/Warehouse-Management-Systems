package warehouse.gui.construction.contents.boundaries;

import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * It sets the bounds of the components in the edit category panel.
 */
@UtilityClass
public class EditCategoryBounds {

  /**
   * It sets the bounds of the components in the edit category panel.
   */
  public void setEditCategoryBounds() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // It sets the bounds of the components in the edit category window.
    comp.categoryRenameLabel.setBounds(25, 10, 250, 30);
    comp.categoryRenameSelect.setBounds(25, 40, 275, 30);
    comp.newCategoryNameLabel.setBounds(25, 85, 250, 30);
    comp.newCategoryName.setBounds(25, 110, 275, 30);
    comp.renameCategoryWarningLabel.setBounds(65, 155, 275, 25);
    comp.renameCategoryBtn.setBounds(25, 180, 275, 30);
  }
}
