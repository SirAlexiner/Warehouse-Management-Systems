package warehouse.gui.construction.contents.boundaries;

import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * This class sets the bounds for the components in the delete category and delete product windows.
 */
@UtilityClass
public class DeleteBounds {

  /**
   * It sets the bounds of the components in the delete window.
   *
   * @param window The window that is being set up.
   */
  public void setDeleteBounds(GuiWindows window) {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Setting the bounds of the delete product label.
    comp.deleteProductLabel.setBounds(25, 10, 250, 30);
    // Checking if the window is the delete category window,
    // if it is then it sets the bounds of the delete category select combo box,
    // if it is not then it sets the bounds of the delete product textfield.
    if (window.equals(GuiWindows.DELETE_CATEGORY)) {
      comp.deleteCategorySelect.setBounds(25, 35, 250, 30);
    } else {
      comp.deleteProduct.setBounds(25, 35, 250, 30);
    }
    // Checking if the window is the delete category window,
    // if it is then it sets the bounds of the delete category warning label,
    // and delete item button
    // if it is not then it sets the bounds of the delete item button.
    if (window.equals(GuiWindows.DELETE_CATEGORY)) {
      comp.deleteItemWarningLabel.setBounds(50, 80, 250, 30);
      comp.deleteItemBtn.setBounds(25, 105, 250, 30);
    } else {
      comp.deleteItemBtn.setBounds(25, 80, 250, 30);
    }
  }
}
