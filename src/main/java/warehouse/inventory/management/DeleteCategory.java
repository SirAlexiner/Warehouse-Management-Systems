package warehouse.inventory.management;

import java.awt.Window;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import lombok.experimental.UtilityClass;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.utilities.CheckValidInput;
import warehouse.gui.utilities.Components;
import warehouse.gui.windowsenum.GuiWindows;
import warehouse.inventory.initializers.CategoryList;

/**
 * It deletes a category.
 */
@UtilityClass
public class DeleteCategory {

  /**
   * It deletes a category from the category list.
   */
  public void commitDeleteCategory() {
    // Creating a pop-up window.
    JDialog popUp = CreateGuiFrame.getPopUp();
    // Checking if the input is valid.
    if (CheckValidInput.isValid(GuiWindows.DELETE_CATEGORY)) {
      String[] objButtons = {"Confirm", "Cancel"};
      // Creating a pop-up window with a warning message.
      int promptResult = JOptionPane.showOptionDialog(
          popUp,
          "Are you sure you want to Delete this Category?",
          "WMS: Warning!",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.WARNING_MESSAGE,
          null,
          objButtons,
          objButtons[1]);
      // Getting the components from the Components class.
      Components comp = Components.getComponents();
      // Checking if the user has clicked on the "Confirm" button.
      if (promptResult == JOptionPane.YES_OPTION) {
        // It removes the selected category from the category list.
        CategoryList.getProductCategories().remove(
            String.valueOf(comp.deleteCategorySelect.getSelectedItem())
        );
        comp.searchCategory.setModel(
            new DefaultComboBoxModel<>(CategoryList.getProductCategories().toArray())
        );
        comp.categoryArray = CategoryList.getProductCategories().toArray(new String[0]);
        // It closes the pop-up window.
        Window[] windows = Window.getWindows();
        for (Window w : windows) {
          if (w instanceof JDialog dialog && w.isShowing()) {
            dialog.dispose();
          }
        }
      }
    }
  }
}
