package warehouse.inventory.management;

import java.awt.Window;
import java.util.Collections;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import lombok.experimental.UtilityClass;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.utilities.CheckValidInput;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.StringTitle;
import warehouse.gui.windowsenum.GuiWindows;
import warehouse.inventory.initializers.CategoryList;

/**
 * This class is called when the user clicks the "Confirm" button in the "Edit Category" window.
 */
@UtilityClass
public class EditCategory {

  /**
   * This function is called when the user
   * clicks the "Confirm" button in the "Edit Category" window.
   */
  public void commitEditCategory() {
    // Getting the pop.up window.
    JDialog popUp = CreateGuiFrame.getPopUp();
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Checking if the input is valid and if the category is not in the list.
    if (CheckValidInput.isValid(GuiWindows.EDIT_CATEGORY)
        &&
        CategoryList.categoryNotInList(
            StringTitle.toTitleCase(comp.newCategoryName.getText())
        )) {
      // A pop.up window that asks the user if they are sure they want to rename the category.
      String[] objButtons = {"Confirm", "Cancel"};
      int promptResult = JOptionPane.showOptionDialog(
          popUp,
          "Are you sure you want to Rename this Category?",
          "WMS: Confirm!",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.WARNING_MESSAGE,
          null,
          objButtons,
          objButtons[1]);
      // Checking if the user clicked the "Confirm" button in the pop.up window.
      if (promptResult == JOptionPane.YES_OPTION) {
        // Removing the old category name from the list,
        // adding the new category name to the list,
        // sorting the list,
        // setting the model of the search category combo box to the list,
        // setting the category array to the list,
        // and disposing of the pop.up window.
        CategoryList.getProductCategories().remove(
            String.valueOf(comp.categoryRenameSelect.getSelectedItem())
        );
        CategoryList.getProductCategories().add(
            StringTitle.toTitleCase(comp.newCategoryName.getText())
        );
        Collections.sort(CategoryList.getProductCategories());
        JComboBox<Object> searchCategory = comp.searchCategory;
        searchCategory.setModel(
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
