package warehouse.gui.construction.contents.boundaries;

import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * It sets the bounds of the components in the edit product panel.
 */
@UtilityClass
public class EditProductBounds {

  /**
   * It sets the bounds of the components in the edit product panel.
   */
  public void setEditProductBounds() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // It sets the bounds of the components in the edit product window.
    comp.productMenu.setBounds(0, 0, 575, 30);
    comp.productSelectLabel.setBounds(25, 40, 525, 30);
    comp.productSelect.setBounds(25, 65, 525, 30);
    comp.categorySelectLabel.setBounds(25, 115, 250, 30);
    comp.categorySelect.setBounds(25, 140, 250, 30);
    comp.productOriginLabel.setBounds(300, 115, 250, 30);
    comp.productOrigin.setBounds(300, 140, 250, 30);
    comp.productNameLabel.setBounds(25, 190, 250, 30);
    comp.productName.setBounds(25, 215, 250, 30);
    comp.productManufacturerLabel.setBounds(300, 190, 250, 30);
    comp.productManufacturer.setBounds(300, 215, 250, 30);
    comp.productPriceLabel.setBounds(25, 265, 250, 30);
    comp.productPrice.setBounds(25, 290, 250, 30);
    comp.priceCurrencyLabel.setBounds(300, 265, 250, 30);
    comp.priceCurrency.setBounds(300, 290, 250, 30);
    comp.productQuantityLabel.setBounds(25, 340, 250, 30);
    comp.productQuantity.setBounds(25, 370, 250, 30);
    comp.weightReq.setBounds(300, 340, 70, 30);
    comp.weightSelect.setBounds(300, 370, 250, 30);
    comp.colorReq.setBounds(25, 415, 60, 30);
    comp.colorSelect.setBounds(25, 445, 525, 30);
    comp.dimensionsReq.setBounds(25, 485, 90, 30);
    comp.heightLabel.setBounds(25, 525, 250, 30);
    comp.productHeight.setBounds(25, 550, 250, 30);
    comp.widthLabel.setBounds(300, 525, 250, 30);
    comp.productWidth.setBounds(300, 550, 250, 30);
    comp.lengthLabel.setBounds(25, 600, 250, 30);
    comp.productLength.setBounds(25, 625, 250, 30);
    comp.productUnitLabel.setBounds(300, 600, 250, 30);
    comp.productUnit.setBounds(300, 625, 250, 30);
    comp.newProductBtn.setBounds(300, 680, 250, 30);
  }
}
