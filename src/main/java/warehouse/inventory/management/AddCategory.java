package warehouse.inventory.management;

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
 * It adds a category to the database.
 */
@UtilityClass
public class AddCategory {

  /**
   * This function adds a new category to the list of categories.
   */
  public void commitAddCategory() {
    // Creating a new JDialog object.
    JDialog popUp = CreateGuiFrame.getPopUp();
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Checking if the input is valid and if the category is not in the list.
    if (CheckValidInput.isValid(GuiWindows.NEW_CATEGORY)
        &&
        CategoryList.categoryNotInList(
            StringTitle.toTitleCase(comp.addCategory.getText()).stripTrailing()
        )) {
      // Checking if the list is empty and if it is, it adds an empty string to the list.
      if (CategoryList.getProductCategories().isEmpty()) {
        CategoryList.getProductCategories().add(0, "");
      }
      // Adding the category to the list.
      CategoryList.getProductCategories().add(
          StringTitle.toTitleCase(comp.addCategory.getText()).stripTrailing()
      );
      // Sorting the list of categories and then adding it to the combo box.
      Collections.sort(CategoryList.getProductCategories());
      comp.categoryArray = (CategoryList.getProductCategories()).toArray(new String[0]);
      comp.addCategory.setText("");
      JComboBox<Object> searchCategory = comp.searchCategory;
      searchCategory.setModel(
          new DefaultComboBoxModel<>(CategoryList.getProductCategories().toArray())
      );
      String[] objButtons = {"OK"};
      // Showing a pop-up window with the message "Category has been Added"
      // and the title "WMS: Success!"
      JOptionPane.showOptionDialog(
          popUp,
          "Category has been Added",
          "WMS: Success!",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE,
          null,
          objButtons,
          objButtons[0]);
      // Checking if the categorySelect is not null and if it is visible.
      // If it is, it sets the model of the categorySelect
      // to the array of categories.
      if (comp.categorySelect != null && comp.categorySelect.isShowing()) {
        comp.categorySelect.setModel(
            new DefaultComboBoxModel<>(CategoryList.getProductCategories().toArray())
        );
      }
      if (comp.newProductBtn != null && comp.newProductBtn.isShowing()) {
        popUp.dispose();
      }
    }
  }
}
