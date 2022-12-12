package warehouse.gui.construction.contents.boundaries;

import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * It sets the bounds of the components in the new product panel.
 */
@UtilityClass
public class NewProductBounds {

  /**
   * It sets the bounds of the components in the new product panel.
   */
  public void setNewProductBounds() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // It sets the bounds of the components in the new product window.
    comp.productMenu.setBounds(0, 0, 575, 30);
    comp.categorySelectLabel.setBounds(25, 40, 250, 30);
    comp.categorySelect.setBounds(25, 65, 250, 30);
    comp.productOriginLabel.setBounds(300, 40, 250, 30);
    comp.productOrigin.setBounds(300, 65, 250, 30);
    comp.productNameLabel.setBounds(25, 115, 250, 30);
    comp.productName.setBounds(25, 140, 250, 30);
    comp.productManufacturerLabel.setBounds(300, 115, 250, 30);
    comp.productManufacturer.setBounds(300, 140, 250, 30);
    comp.productPriceLabel.setBounds(25, 190, 250, 30);
    comp.productPrice.setBounds(25, 215, 250, 30);
    comp.priceCurrencyLabel.setBounds(300, 190, 250, 30);
    comp.priceCurrency.setBounds(300, 215, 250, 30);
    comp.productQuantityLabel.setBounds(25, 265, 250, 30);
    comp.productQuantity.setBounds(25, 290, 250, 30);
    comp.weightReq.setBounds(300, 265, 70, 30);
    comp.weightSelect.setBounds(300, 295, 250, 30);
    comp.colorReq.setBounds(25, 340, 60, 30);
    comp.colorSelect.setBounds(25, 370, 525, 30);
    comp.dimensionsReq.setBounds(25, 415, 90, 30);
    comp.heightLabel.setBounds(25, 455, 250, 30);
    comp.productHeight.setBounds(25, 480, 250, 30);
    comp.widthLabel.setBounds(300, 455, 250, 30);
    comp.productWidth.setBounds(300, 480, 250, 30);
    comp.lengthLabel.setBounds(25, 530, 250, 30);
    comp.productLength.setBounds(25, 555, 250, 30);
    comp.productUnitLabel.setBounds(300, 530, 250, 30);
    comp.productUnit.setBounds(300, 555, 250, 30);
    comp.newProductBtn.setBounds(300, 605, 250, 30);
  }
}
