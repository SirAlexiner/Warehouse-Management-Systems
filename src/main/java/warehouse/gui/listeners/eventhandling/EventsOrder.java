package warehouse.gui.listeners.eventhandling;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lombok.experimental.UtilityClass;
import warehouse.gui.listeners.NumericListener;
import warehouse.gui.utilities.Components;
import warehouse.inventory.management.OrderProduct;

/**
 * It sets the events for the order product panel.
 */
@UtilityClass
public class EventsOrder {


  private final JTextField textField = new JTextField();

  /**
   * It sets the events for the order product panel.
   */
  public void setEventsOrder() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Adding a key listener to the order product quantity text field.
    comp.orderProductQnt.addKeyListener(new NumericListener());
    // Setting the background of the text field to the default background.
    comp.orderProductQnt.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.orderProductQnt.setBackground(textField.getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.orderProductQnt.setBackground(textField.getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.orderProductQnt.setBackground(textField.getBackground());
      }
    });
    // Adding an action listener to the order product quantity text field
    // and the order product button.
    comp.orderProductQnt.addActionListener(e -> OrderProduct.commitOrderProduct());
    comp.orderProductBtn.addActionListener(e -> OrderProduct.commitOrderProduct());
  }
}
