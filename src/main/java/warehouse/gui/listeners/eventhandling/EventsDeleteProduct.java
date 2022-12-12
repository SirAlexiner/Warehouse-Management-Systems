package warehouse.gui.listeners.eventhandling;

import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.inventory.management.DeleteProduct;

/**
 * This class is responsible for adding an action listener to the delete product button.
 */
@UtilityClass
public class EventsDeleteProduct {

  /**
   * When the delete button is clicked, delete the product.
   */
  public void setEventsDeleteProduct() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Adding an action listener to the delete product button.
    comp.deleteItemBtn.addActionListener(e -> DeleteProduct.commitDeleteProduct());
  }
}
