package warehouse.gui.utilities.checkvalidinput;

import java.awt.Color;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.login.ErrorShake;

/**
 * It checks if the input fields are valid.
 */
@UtilityClass
public class CheckEditProductInput {
  private boolean isValid = true;


  /**
   *  This function checks if the input fields in the edit product popup are valid.
   *
   * @param comp The component that contains all the input fields.
   * @param popup The popup that will shake if the input is invalid.
   * @param highlightColor The color that the textfield will be highlighted
   *                       with if the input is invalid.
   * @return A boolean value.
   */
  public boolean hasEditProductValidInput(
      Components comp,
      String popup,
      String highlightColor) {
    isCategoryValid(comp, highlightColor);
    isPriceValid(comp, highlightColor);
    isCurrencyValid(comp, highlightColor);
    isQuantityValid(comp, highlightColor);
    if (isValid) {
      return true;
    } else {
      ErrorShake.startShake(popup);
      return false;
    }
  }

  /**
   * If the quantity text field is empty,
   * set the background color to red and set the isValid boolean to false.
   *
   * @param comp The Components class object that contains all the components of the JFrame.
   * @param highlightColor The color that the text field will be highlighted with.
   */
  private void isQuantityValid(Components comp, String highlightColor) {
    if (Objects.equals(comp.productQuantity.getText(), "")) {
      comp.productQuantity.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }

  /**
   * If the currency dropdown is empty, highlight the background.
   *
   * @param comp The Components class object
   * @param highlightColor The color that the field will be highlighted in.
   */
  private void isCurrencyValid(Components comp, String highlightColor) {
    if (Objects.equals(comp.priceCurrency.getSelectedItem(), "")) {
      comp.priceCurrency.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }

  /**
   * If the price text field is empty, hightlight the background.
   *
   * @param comp The Components class object that contains all the components of the form.
   * @param highlightColor The color that the text field will be highlighted with.
   */
  private void isPriceValid(Components comp, String highlightColor) {
    if (Objects.equals(comp.productPrice.getText().stripTrailing(), "")) {
      comp.productPrice.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }

  /**
   * If the category select is empty or null,
   * set the background to the highlight color and set isValid to false.
   *
   * @param comp The Components class object
   * @param highlightColor The color to highlight the field with if it's invalid.
   */
  private void isCategoryValid(Components comp, String highlightColor) {
    if (
        Objects.equals(comp.categorySelect.getSelectedItem(), "")
            ||
            Objects.equals(comp.categorySelect.getSelectedItem(), null)
    ) {
      comp.categorySelect.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }
}
