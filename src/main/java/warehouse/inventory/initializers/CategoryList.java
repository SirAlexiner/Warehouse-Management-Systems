package warehouse.inventory.initializers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import warehouse.gui.CreateGuiFrame;

/**
 * This class is used to check if a category already exists in the database.
 */
@UtilityClass
public class CategoryList {
  @Getter
  // Creating a new ArrayList of type String.
  private final List<String> productCategories = new ArrayList<>();

  /**
   * If the category exists, construct a prompt window tied to popup and return false.
   * Otherwise, return true.
   *
   * @param category The category to be checked
   * @return A boolean value.
   */
  public boolean categoryNotInList(String category) {
    JDialog popUp = CreateGuiFrame.getPopUp();
    if (CategoryList.productCategories.contains(category)) {
      String[] objButtons = {"OK"};
      JOptionPane.showOptionDialog(
          popUp,
          "This Category already exists.",
          "WMS: Notice!",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE,
          null,
          objButtons,
          objButtons[0]);
      return false;
    } else {
      return true;
    }
  }
}
