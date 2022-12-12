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
public class CheckNewProductInput {
  private boolean isValid = true;

  /**
   * "If all the input fields are valid, return true, otherwise, shake the popup and return false."
   *
   * @param comp The component that contains all the input fields.
   * @param popup The popup that will shake if the input is invalid.
   * @param highlightColor The color that the text field will be highlighted with when it's invalid.
   * @return A boolean value.
   */
  public boolean hasNewProductValidInput(
      Components comp,
      String popup,
      String highlightColor
  ) {
    isCategoryValid(comp, highlightColor);
    isOriginValid(comp, highlightColor);
    isManufacturerValid(comp, highlightColor);
    isNameValid(comp, highlightColor);
    isPriceValid(comp, highlightColor);
    isCurrencyValid(comp, highlightColor);
    isQuantityValid(comp, highlightColor);
    isWeightValid(comp, highlightColor);
    isColorValid(comp, highlightColor);
    isDimensionsValid(comp, highlightColor);
    if (isValid) {
      return true;
    }
    ErrorShake.startShake(popup);
    return false;
  }

  /**
   * If the dimensions are required,
   * check that the height, length, width,
   * and unit are not empty, and that the height, length, and width are not zero.
   *
   * @param comp The Components class that contains all the components of the GUI.
   * @param highlightColor The color that the text field will be highlighted
   *                       with if the input is invalid.
   */
  private void isDimensionsValid(
      Components comp,
      String highlightColor
  ) {
    if (comp.dimensionsReq.isSelected()) {
      if (Objects.equals(comp.productHeight.getText(), "")
          ||
          Objects.equals(comp.productHeight.getText(), ".")
          ||
          Double.parseDouble(comp.productHeight.getText()) == 0
          ||
          comp.productHeight.getText().length() > 1
          && String.valueOf(comp.productHeight.getText().charAt(0)).equals("0")
          && !String.valueOf(comp.productHeight.getText().charAt(1)).equals(".")
      ) {
        comp.productHeight.setBackground(Color.decode(highlightColor));
        isValid = false;
      }
      if (Objects.equals(comp.productLength.getText(), "")
          ||
          Objects.equals(comp.productLength.getText(), ".")
          ||
          Double.parseDouble(comp.productLength.getText()) == 0
          ||
          comp.productLength.getText().length() > 1
          && String.valueOf(comp.productLength.getText().charAt(0)).equals("0")
          && !String.valueOf(comp.productLength.getText().charAt(1)).equals(".")
      ) {
        comp.productLength.setBackground(Color.decode(highlightColor));
        isValid = false;
      }
      if (Objects.equals(comp.productWidth.getText(), "")
          ||
          Objects.equals(comp.productWidth.getText(), ".")
          ||
          Double.parseDouble(comp.productWidth.getText()) == 0
          ||
          comp.productWidth.getText().length() > 1
          && String.valueOf(comp.productWidth.getText().charAt(0)).equals("0")
          && !String.valueOf(comp.productWidth.getText().charAt(1)).equals(".")
      ) {
        comp.productWidth.setBackground(Color.decode(highlightColor));
        isValid = false;
      }
      if (Objects.equals(comp.productUnit.getSelectedItem(), "")) {
        comp.productUnit.setBackground(Color.decode(highlightColor));
        isValid = false;
      }
    }
  }

  /**
   * If the color is required and the color is not selected, then highlight the color select box.
   *
   * @param comp The Components class object that contains all the JComponents
   *             that are used in the GUI.
   * @param highlightColor the color to highlight the text field with if it's invalid
   */
  private void isColorValid(Components comp, String highlightColor) {
    if (
        comp.colorReq.isSelected()
            &&
            Objects.equals(comp.colorSelect.getSelectedItem(), "")
    ) {
      comp.colorSelect.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }

  /**
   * If the weight is required and the weight is empty, then highlight the weight field.
   *
   * @param comp The component that is being checked
   * @param highlightColor The color to highlight the input field with if it's invalid
   */
  private void isWeightValid(Components comp, String highlightColor) {
    if (comp.weightReq.isSelected()
        &&
        Objects.equals(comp.weightSelect.getSelectedItem(), "")
    ) {
      comp.weightSelect.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }

  /**
   * If the quantity field is empty,
   * contains only a decimal point,
   * is equal to zero,
   * or contains a leading zero,
   * then the quantity field is highlighted.
   *
   * @param comp the Components object that contains the JTextFields
   *             and JComboBoxes that are being checked
   * @param highlightColor The color that the text field will be highlighted
   *                       with if the input is invalid.
   */
  private void isQuantityValid(Components comp, String highlightColor) {
    if (
        Objects.equals(comp.productQuantity.getText(), "")
            ||
            Objects.equals(comp.productQuantity.getText(), ".")
            ||
            Double.parseDouble(comp.productQuantity.getText()) == 0
            ||
            comp.productQuantity.getText().length() > 1
                && String.valueOf(comp.productQuantity.getText().charAt(0)).equals("0")
                && !String.valueOf(comp.productQuantity.getText().charAt(1)).equals(".")
    ) {
      comp.productQuantity.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }

  /**
   * If the currency dropdown is empty,
   * highlight the background and set the isValid boolean to false.
   *
   * @param comp The Components class object that contains all the JComponents
   *             that are used in the GUI.
   * @param highlightColor The color that the background of the component
   *                       will be set to if the input is invalid.
   */
  private void isCurrencyValid(Components comp, String highlightColor) {
    if (Objects.equals(comp.priceCurrency.getSelectedItem(), "")) {
      comp.priceCurrency.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }

  /**
   * If the price is empty,
   * contains only a decimal point,
   * is zero,
   * or starts with a zero and is not followed by a decimal point,
   * then the price field is highlighted.
   *
   * @param comp The Components class object that contains all the components of the JFrame.
   * @param highlightColor The color that the text field will be highlighted
   *                       with if the input is invalid.
   */
  private void isPriceValid(Components comp, String highlightColor) {
    if (
        Objects.equals(comp.productPrice.getText(), "")
            ||
            Objects.equals(comp.productPrice.getText(), ".")
            ||
            Double.parseDouble(comp.productPrice.getText()) == 0
            ||
            comp.productPrice.getText().length() > 1
                && String.valueOf(comp.productPrice.getText().charAt(0)).equals("0")
                && !String.valueOf(comp.productPrice.getText().charAt(1)).equals(".")
    ) {
      comp.productPrice.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }

  /**
   * If the product name is empty, highlight the background and set the isValid boolean to false.
   *
   * @param comp The Components class object that contains all the JComponents.
   * @param highlightColor The color that the text field will be highlighted with.
   */
  private void isNameValid(Components comp, String highlightColor) {
    if (Objects.equals(comp.productName.getText().stripTrailing(), "")) {
      comp.productName.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }

  /**
   * If the manufacturer field is empty, highlight it.
   *
   * @param comp The Components class object
   * @param highlightColor The color that the text field will be highlighted with.
   */
  private void isManufacturerValid(
      Components comp,
      String highlightColor
  ) {
    if (Objects.equals(comp.productManufacturer.getText().stripTrailing(), "")) {
      comp.productManufacturer.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }

  /**
   * If the product origin is not selected, then the product origin field will be highlighted.
   *
   * @param comp The Components class object
   * @param highlightColor The color that the text field will be highlighted with.
   */
  private void isOriginValid(Components comp, String highlightColor) {
    if (Objects.equals(comp.productOrigin.getSelectedItem(), "")) {
      comp.productOrigin.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }

  /**
   * If the category select box is empty or null,
   * highlights the background and set the isValid boolean to false.
   *
   * @param comp The Components class object
   * @param highlightColor The color that the background of the component
   *                       will be set to if the input is invalid.
   */
  private void isCategoryValid(Components comp, String highlightColor) {
    if (Objects.equals(comp.categorySelect.getSelectedItem(), "")
        ||
        Objects.equals(comp.categorySelect.getSelectedItem(), null)
    ) {
      comp.categorySelect.setBackground(Color.decode(highlightColor));
      isValid = false;
    }
  }
}
