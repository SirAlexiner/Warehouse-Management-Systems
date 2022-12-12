package warehouse.gui.listeners.eventhandling;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.DisableKeyCombo;
import warehouse.inventory.management.EditCategory;

/**
 * It sets the events for the edit category panel.
 */
@UtilityClass
public class EventsEditCategory {
  // Creating a new JTextField object.
  private final JTextField textField = new JTextField();
  // Creating a new JComboBox object.
  private final JComboBox<Object> comboBox = new JComboBox<>();

  /**
   * This function sets the events for the edit category dialog.
   */
  public void setEventsEditCategory() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // It sets the background of the combo box to the default background.
    comp.categoryRenameSelect.addActionListener(
        e -> comp.categoryRenameSelect.setBackground(comboBox.getBackground())
    );
    // It disables the control V key.
    comp.newCategoryName.addKeyListener(DisableKeyCombo.disableControlV());
    // Checking if the key typed is a letter, comma, space or backspace.
    comp.newCategoryName.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        comp.newCategoryName.setEditable(
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
    // Setting the background of the text field to the default background.
    comp.newCategoryName.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.newCategoryName.setBackground(textField.getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.newCategoryName.setBackground(textField.getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.newCategoryName.setBackground(textField.getBackground());
      }
    });
    // Setting the action listener for the text field and the button.
    comp.newCategoryName.addActionListener(e -> EditCategory.commitEditCategory());
    comp.renameCategoryBtn.addActionListener(e -> EditCategory.commitEditCategory());
  }
}
