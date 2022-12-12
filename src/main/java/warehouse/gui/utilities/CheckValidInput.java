package warehouse.gui.utilities;

import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.checkvalidinput.CheckDeleteCategoryInput;
import warehouse.gui.utilities.checkvalidinput.CheckEditCategoryInput;
import warehouse.gui.utilities.checkvalidinput.CheckEditProductInput;
import warehouse.gui.utilities.checkvalidinput.CheckNewCategoryInput;
import warehouse.gui.utilities.checkvalidinput.CheckNewProductInput;
import warehouse.gui.utilities.checkvalidinput.CheckOrderInput;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * It checks if the input is valid for the given window.
 */
@UtilityClass
public class CheckValidInput {
  // A static reference to the Components class.
  private final Components comp = Components.getComponents();
  // A constant that is used to highlight the text field when the input is invalid.
  private final String highlightColor = "#967bb6";
  // A constant that is used in the `isValid` function.
  private final String popup = "Popup";

  /**
   * The function checks if the input is valid for the given window.
   *
   * @param window The window that is being validated.
   * @return A boolean value.
   */
  public boolean isValid(GuiWindows window) {
    switch (window) {
      case NEW_PRODUCT -> {
        return CheckNewProductInput.hasNewProductValidInput(comp, popup, highlightColor);
      }
      case EDIT_PRODUCT -> {
        return CheckEditProductInput.hasEditProductValidInput(comp, popup, highlightColor);
      }
      case NEW_CATEGORY -> {
        return CheckNewCategoryInput.hasNewCategoryValidInput(comp, popup, highlightColor);
      }
      case EDIT_CATEGORY -> {
        return CheckEditCategoryInput.hasEditCategoryValidInput(comp, popup, highlightColor);
      }
      case DELETE_CATEGORY -> {
        return CheckDeleteCategoryInput.hasDeleteCategoryValidInput(comp, popup, highlightColor);
      }
      case ORDER -> {
        return CheckOrderInput.hasOrderValidInput(comp, popup, highlightColor);
      }
      default -> {
        return false;
      }
    }
  }
}
