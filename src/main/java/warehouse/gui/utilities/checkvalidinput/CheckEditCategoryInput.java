package warehouse.gui.utilities.checkvalidinput;

import java.awt.Color;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.login.ErrorShake;

/**
 * It checks if the user has valid input for the edit category popup.
 */
@UtilityClass
public class CheckEditCategoryInput {

  /**
   * If the selected category is valid and the new category name is valid,
   * then return true. Otherwise, start shaking the popup and return false.
   *
   * @param comp The Components class that contains all the components of the popup.
   * @param popup the popup that will shake if the input is invalid
   * @param highlightColor The color that the textfield will be highlighted with if it is invalid.
   * @return A boolean value.
   */
  public boolean hasEditCategoryValidInput(
      Components comp,
      String popup,
      String highlightColor) {
    if (isSelectedCategoryValid(comp, highlightColor)
        && isNewCategoryNameValid(comp, highlightColor)) {
      return true;
    } else {
      ErrorShake.startShake(popup);
      return false;
    }
  }

  /**
   * If the new category name is empty,
   * highlight the text field and return false, otherwise return true.
   *
   * @param comp The Components class that contains all the components of the GUI.
   * @param highlightColor The color to highlight the text field with if the input is invalid.
   * @return A boolean value.
   */
  private boolean isNewCategoryNameValid(Components comp, String highlightColor) {
    if (Objects.equals(comp.newCategoryName.getText().stripTrailing(), "")) {
      comp.newCategoryName.setBackground(Color.decode(highlightColor));
      return false;
    } else {
      return true;
    }
  }

  /**
   * If the selected item in the categoryRenameSelect combo box is either an empty string or null,
   * then set the background color of the combo box to the color specified by the highlightColor
   * parameter and return false. Otherwise, return true.
   *
   * @param comp The Components class object
   * @param highlightColor The color to highlight the field with if it's invalid.
   * @return A boolean value.
   */
  private boolean isSelectedCategoryValid(Components comp, String highlightColor) {
    if (Objects.equals(comp.categoryRenameSelect.getSelectedItem(), "")
        ||
        Objects.equals(comp.categoryRenameSelect.getSelectedItem(), null)
    ) {
      comp.categoryRenameSelect.setBackground(Color.decode(highlightColor));
      return false;
    } else {
      return true;
    }
  }
}
