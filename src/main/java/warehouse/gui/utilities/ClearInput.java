package warehouse.gui.utilities;

import lombok.experimental.UtilityClass;

/**
 * It clears all the input fields.
 */
@UtilityClass
public class ClearInput {

  /**
   * It clears all the input fields.
   */
  public void clearInputFields() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Clearing all the input fields.
    comp.categorySelect.setSelectedItem("");
    comp.productOrigin.setSelectedItem("");
    comp.productName.setText("");
    comp.productManufacturer.setText("");
    comp.productPrice.setText("");
    comp.priceCurrency.setSelectedItem("");
    comp.productQuantity.setText("");
    comp.weightSelect.setSelectedItem("");
    comp.productQuantity.setText("");
    comp.colorSelect.setSelectedItem("");
    comp.productHeight.setText("");
    comp.productLength.setText("");
    comp.productWidth.setText("");
    comp.productUnit.setSelectedItem("");
  }
}
