package warehouse.gui.construction.contents.boundaries;

import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * It sets the bounds of the components in the order panel.
 */
@UtilityClass
public class OrderBounds {

  /**
   * Set the bounds of the order panel components.
   */
  public void setOrderBounds() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Setting the bounds of the components in the order window.
    comp.orderProductLabel.setBounds(25, 10, 250, 30);
    comp.orderProduct.setBounds(25, 40, 275, 30);
    comp.orderProductQntLabel.setBounds(25, 85, 250, 30);
    comp.orderProductQnt.setBounds(25, 110, 275, 30);
    comp.orderProductBtn.setBounds(25, 165, 275, 30);
  }
}
