package warehouse.gui.listeners.eventhandling;

import javax.swing.JComboBox;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.ErrorHighlightProduct;
import warehouse.inventory.management.EditProduct;

/**
 * This class sets the events for the edit product form.
 */
@UtilityClass
public class EventsEditProduct {
  // Creating a new JComboBox object.
  private final JComboBox<Object> comboBox = new JComboBox<>();

  /**
   * This function sets the action listeners for the edit product form.
   */
  public void setEventsEditProduct() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Setting the background of the combo boxes to the default background.
    comp.categorySelect.addActionListener(
        e -> comp.categorySelect.setBackground(comboBox.getBackground())
    );

    comp.priceCurrency.addActionListener(
        e -> comp.priceCurrency.setBackground(comboBox.getBackground())
    );

    comp.weightSelect.addActionListener(
        e -> comp.weightSelect.setBackground(comboBox.getBackground())
    );
    // Removing the error highlight from the components.
    ErrorHighlightProduct.removeErrorHighlight(comp);
    // Adding an action listener to the new product button.
    comp.newProductBtn.addActionListener(e -> EditProduct.commitEditProduct());
  }
}
