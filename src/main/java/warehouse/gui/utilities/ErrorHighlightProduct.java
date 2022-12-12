package warehouse.gui.utilities;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lombok.experimental.UtilityClass;

/**
 * It removes the error highlight from the text fields when the user starts typing in them.
 */
@UtilityClass
public class ErrorHighlightProduct {

  // Creating a new text field to get the default background color of the text field.
  private final JTextField textField = new JTextField();

  /**
   * It removes the error highlight from the text fields when the user starts typing in them.
   *
   * @param comp The component that you want to remove the error highlight from.
   */
  public void removeErrorHighlight(Components comp) {
    // Removing the error highlight from the text field when the user starts typing in it.
    comp.productPrice.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.productPrice.setBackground(textField.getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.productPrice.setBackground(textField.getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.productPrice.setBackground(textField.getBackground());
      }
    });
    comp.productQuantity.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.productQuantity.setBackground(textField.getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.productQuantity.setBackground(textField.getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.productQuantity.setBackground(textField.getBackground());
      }
    });
  }
}
