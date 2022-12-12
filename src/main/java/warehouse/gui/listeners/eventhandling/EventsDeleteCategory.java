package warehouse.gui.listeners.eventhandling;

import javax.swing.JComboBox;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.inventory.management.DeleteCategory;

/**
 * This class sets the events for the delete category panel.
 */
@UtilityClass
public class EventsDeleteCategory {
  // Creating a new JComboBox object.
  private final JComboBox<Object> comboBox = new JComboBox<>();

  /**
   * When the delete category button is clicked, delete the category.
   */
  public void setEventsDeleteCategory() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Setting the background of the delete category select to the background of the combo box.
    comp.deleteCategorySelect.addActionListener(
        e -> comp.deleteCategorySelect.setBackground(comboBox.getBackground())
    );
    // Adding an action listener to the delete item button.
    comp.deleteItemBtn.addActionListener(e -> DeleteCategory.commitDeleteCategory());
  }
}
