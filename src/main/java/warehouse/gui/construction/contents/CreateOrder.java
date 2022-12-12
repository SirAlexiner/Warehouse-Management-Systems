package warehouse.gui.construction.contents;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * It creates the order panel.
 */
@UtilityClass
public class CreateOrder {

  /**
   * It creates a panel with a label, a text field, and a button.
   *
   * @param panel The panel that the components will be added to.
   */
  public void constructOrderPanel(JPanel panel) {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Creating a label and a text field.
    comp.orderProductLabel = new JLabel("Product ID:");
    comp.orderProduct = new JTextField(5);
    // Getting the value of the first column of the selected row.
    String key = String.valueOf(
        comp.spreadsheet.getValueAt(comp.spreadsheet.getSelectedRow(), 0)
    );
    // Setting the text of the text field to the value of the first column of the selected row.
    comp.orderProduct.setText(key);
    // Creating a new label with the text "Quantity:".
    comp.orderProductQntLabel = new JLabel("Quantity:");
    // Creating a new text field with a width of 5 characters
    // and setting the tooltip text to "Please Enter the Quantity / Weight to Order".
    comp.orderProductQnt = new JTextField(5);
    comp.orderProductQnt.setToolTipText("Please Enter the Quantity / Weight to Order");
    // Creating a new button with the text "Order" and disabling the text field.
    comp.orderProductBtn = new JButton("Order");
    comp.orderProduct.setEnabled(false);
    // Adding the components to the panel.
    panel.add(comp.orderProductLabel);
    panel.add(comp.orderProduct);
    panel.add(comp.orderProductQntLabel);
    panel.add(comp.orderProductQnt);
    panel.add(comp.orderProductBtn);
  }
}
