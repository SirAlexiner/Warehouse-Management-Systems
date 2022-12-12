package warehouse.gui.utilities.checkvalidinput;

import java.awt.Color;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.login.ErrorShake;

/**
 * It checks if the user has selected a category to delete.
 */
@UtilityClass
public class CheckDeleteCategoryInput {

  /**
   * If the selected item in the delete category select is empty or null,
   * then start the error shake animation,
   * set the background color of the select to the highlight color,
   * and return false. Otherwise, return true.
   *
   * @param comp The Components class
   * @param popup The popup window that will appear if the input is invalid.
   * @param highlightColor The color that the text field will be highlighted with.
   * @return A boolean value.
   */
  public boolean hasDeleteCategoryValidInput(
      Components comp,
      String popup,
      String highlightColor
  ) {
    if (Objects.equals(comp.deleteCategorySelect.getSelectedItem(), "")
        ||
        Objects.equals(comp.deleteCategorySelect.getSelectedItem(), null)
    ) {
      ErrorShake.startShake(popup);
      comp.deleteCategorySelect.setBackground(Color.decode(highlightColor));
      return false;
    } else {
      return true;
    }
  }
}
