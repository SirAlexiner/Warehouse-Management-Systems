package warehouse.gui.utilities.checkvalidinput;

import java.awt.Color;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.login.ErrorShake;

/**
 * This class checks if the user has entered a valid input for the new category.
 */
@UtilityClass
public class CheckNewCategoryInput {

  /**
   * If the text field is empty, then shake the popup and highlight the text field.
   *
   * @param comp The Components class
   * @param popup The popup that will appear when the user has invalid input.
   * @param highlightColor The color that the text field will be highlighted with.
   * @return A boolean value.
   */
  public boolean hasNewCategoryValidInput(
      Components comp,
      String popup,
      String highlightColor) {
    if (comp.addCategory.getText().stripTrailing().equals("")) {
      ErrorShake.startShake(popup);
      comp.addCategory.setBackground(Color.decode(highlightColor));
      return false;
    } else {
      return true;
    }
  }
}
