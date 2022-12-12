package warehouse.gui.listeners.eventhandling;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.DisableKeyCombo;
import warehouse.inventory.management.AddCategory;

/**
 * It sets the events for the new category text field and button.
 */
@UtilityClass
public class EventsNewCategory {
  // A reference to the default text field.
  private final JTextField textField = new JTextField();

  /**
   * It sets the events for the add category text field and button.
   */
  public void setEventsNewCategory() {
    // Getting a reference to the components.
    Components comp = Components.getComponents();
    // Disabling the control V key combo.
    comp.addCategory.addKeyListener(DisableKeyCombo.disableControlV());
    // Setting the key listener for the add category text field.
    comp.addCategory.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // Checking if the key typed is a letter, comma, space or backspace.
        comp.addCategory.setEditable(
            Character.isLetter(e.getKeyChar())
                || e.getKeyChar() == KeyEvent.VK_COMMA
                || e.getKeyChar() == KeyEvent.VK_SPACE
                || e.getKeyChar() == KeyEvent.VK_BACK_SPACE
        );
      }

      @Override
      public void keyPressed(KeyEvent e) {
        // Auto Stub
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // Auto Stub
      }
    });
    // Setting the background color of the text field to the default color.
    comp.addCategory.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.addCategory.setBackground(textField.getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.addCategory.setBackground(textField.getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.addCategory.setBackground(textField.getBackground());
      }
    });
    // Setting the action listener for the add category text field and button.
    comp.addCategory.addActionListener(e -> AddCategory.commitAddCategory());
    comp.addCategoryBtn.addActionListener(e -> AddCategory.commitAddCategory());
  }
}
