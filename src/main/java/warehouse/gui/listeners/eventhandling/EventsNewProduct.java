package warehouse.gui.listeners.eventhandling;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.CheckBoxSelected;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.DisableKeyCombo;
import warehouse.gui.utilities.ErrorHighlightProduct;
import warehouse.inventory.management.AddProduct;

/**
 * It sets the events for the New Product GUI.
 */
@UtilityClass
public class EventsNewProduct {
  // Creating a new instance of the Components class.
  private final Components comp = Components.getComponents();
  // Creating a new text field.
  private final JTextField textField = new JTextField();
  // Creating a new JComboBox object.
  private final JComboBox<Object> comboBox = new JComboBox<>();

  /**
   * This function sets the events for the new product button.
   */
  public void setEventsNewProduct() {
    // Applying an action listener to the button.
    applyActionListener();
    // Removing the highlight from the text.
    removeHighlight();
    // Applying a key listener to the window.
    applyKeyListener();
    // Adding an action listener to the newProductBtn button.
    comp.newProductBtn.addActionListener(e -> AddProduct.commitAddProduct());
  }

  /**
   * It applies key listeners to the text fields in the GUI.
   */
  private void applyKeyListener() {
    // Declaring a variable called productNameLimit and assigning it a value of 45.
    int productNameLimit = 45;
    // Declaring a variable called productManufacturerLimit and assigning it a value of 45.
    int productManufacturerLimit = 45;
    // Adding a key listener to the productName text field.
    comp.productName.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        setProductNameCap(e, productNameLimit);
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
    // Adding a key listener to the productManufacturer text field.
    comp.productManufacturer.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        setProductManufacturerCap(e, productManufacturerLimit);
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
    // Adding a key listener to the weightReq text field.
    comp.weightReq.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        toggleWeightOnEnter(e);
      }

      @Override
      public void keyPressed(KeyEvent e) {
        e.consume();
      }

      @Override
      public void keyReleased(KeyEvent e) {
        e.consume();
      }
    });

    // Adding an action listener to the weightReq button.
    comp.weightReq.addActionListener(e -> toggleWeightOnClick());
    // Adding a key listener to the colorReq text field.
    comp.colorReq.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        toggleColorOnEnter(e);
      }

      @Override
      public void keyPressed(KeyEvent e) {
        e.consume();
      }

      @Override
      public void keyReleased(KeyEvent e) {
        e.consume();
      }
    });
    // Adding an action listener to the colorReq button.
    comp.colorReq.addActionListener(e -> toggleColorOnClick());
    // Adding a key listener to the dimensionsReq text field.
    comp.dimensionsReq.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        toggleDimensionsOnEnter(e);
      }

      @Override
      public void keyPressed(KeyEvent e) {
        e.consume();
      }

      @Override
      public void keyReleased(KeyEvent e) {
        e.consume();
      }
    });
    // Adding an action listener to the dimensionsReq button.
    comp.dimensionsReq.addActionListener(e -> toggleDimensionsOnClick());
  }

  /**
   * If the dimensions checkbox is selected,
   * then call the dimensionsRequired() function,
   * otherwise call the dimensionsNotRequired() function.
   */
  private void toggleDimensionsOnClick() {
    // Checking to see if the checkbox is selected or not.
    // If it is selected, it will call the dimensionsRequired() method.
    // If it is not selected, it will call the dimensionsNotRequired() method.
    boolean selected = comp.dimensionsReq.isSelected();
    if (selected) {
      CheckBoxSelected.dimensionsRequired();
    } else {
      CheckBoxSelected.dimensionsNotRequired();
    }
  }

  /**
   * If the user presses the enter key while the dimensions checkbox is selected,
   * then unselect the checkbox and call the dimensionsNotRequired() function.
   * If the user presses the enter key while the dimensions checkbox is not selected,
   * then select the checkbox and call the dimensionsRequired() function.
   *
   * @param e The KeyEvent that is being listened for.
   */
  private void toggleDimensionsOnEnter(KeyEvent e) {
    // Checking if the user has pressed the enter key and if the checkbox is selected.
    // If it is, it will deselect the checkbox and call the dimensionsNotRequired() method.
    // If the checkbox is not selected,
    // it will select the checkbox and call the dimensionsRequired() method.
    if (e.getKeyChar() == KeyEvent.VK_ENTER && comp.dimensionsReq.isSelected()) {
      comp.dimensionsReq.setSelected(false);
      CheckBoxSelected.dimensionsNotRequired();
    } else if (e.getKeyChar() == KeyEvent.VK_ENTER
        && !comp.dimensionsReq.isSelected()
    ) {
      comp.dimensionsReq.setSelected(true);
      CheckBoxSelected.dimensionsRequired();
    }
  }

  /**
   * If the colorReq checkbox is selected, enable the colorSelect combo box.
   */
  private void toggleColorOnClick() {
    // Checking to see if the colorReq checkbox is selected.
    // If it is, it enables the colorSelect combobox.
    // If it is not,
    // it disables the colorSelect combobox and sets the selected item to an empty string.
    boolean selected = comp.colorReq.isSelected();
    if (selected) {
      comp.colorSelect.setEnabled(true);
    } else {
      comp.colorSelect.setEnabled(false);
      comp.colorSelect.setSelectedItem("");
    }
  }

  /**
   * If the user presses the enter key,
   * and the colorReq checkbox is selected,
   * then unselect the checkbox and disable the colorSelect combobox.
   * Otherwise, if the user presses the enter key,
   * and the colorReq checkbox is not selected,
   * then select the checkbox and enable the colorSelect combobox.
   *
   * @param e The KeyEvent that was triggered.
   */
  private void toggleColorOnEnter(KeyEvent e) {
    // Checking if the user has pressed the enter key and if the colorReq checkbox is selected.
    // If it is, it will deselect the checkbox and disable the colorSelect combobox.
    // If the colorReq checkbox is not selected,
    // it will select the checkbox and enable the colorSelect combobox.
    if (e.getKeyChar() == KeyEvent.VK_ENTER && comp.colorReq.isSelected()) {
      comp.colorReq.setSelected(false);
      comp.colorSelect.setEnabled(false);
      comp.colorSelect.setSelectedItem("");
    } else if (e.getKeyChar() == KeyEvent.VK_ENTER && !comp.colorReq.isSelected()) {
      comp.colorReq.setSelected(true);
      comp.colorSelect.setEnabled(true);
    }
  }

  /**
   * If the weightReq checkbox is selected, enable the weightSelect combobox.
   */
  private void toggleWeightOnClick() {
    // Checking if the weightReq checkbox is selected.
    boolean selected = comp.weightReq.isSelected();
    // Setting the background color of the text field to the default background color.
    comp.productQuantity.setBackground(textField.getBackground());
    comp.productQuantity.setText("");
    // Checking if the selected item is true,
    // then it will enable the weightSelect.
    // If it is false, then it will disable
    // the weightSelect and set the selected item to nothing.
    if (selected) {
      comp.weightSelect.setEnabled(true);
    } else {
      comp.weightSelect.setEnabled(false);
      comp.weightSelect.setSelectedItem("");
    }
  }

  /**
   * If the user presses the enter key while the weightReq checkbox is selected,
   * then the weightReq checkbox is deselected and the weightSelect combobox is disabled.
   * If the user presses the enter key while the weightReq checkbox is not selected,
   * then the weightReq checkbox is selected and the weightSelect combobox is enabled.
   *
   * @param e The KeyEvent that is being listened for.
   */
  private void toggleWeightOnEnter(KeyEvent e) {
    // The below code is checking if the user has pressed the enter key
    // and if the weightReq checkbox is selected.
    // If so, it will set the background of the productQuantity textfield to the default background.
    // It will then set the weightReq checkbox to false and disable the weightSelect combobox.
    // It will also set the selected item of the weightSelect combobox to an empty string.
    // If the user has pressed the enter key and the weightReq checkbox is not selected,
    // it will set the background of the productQuantity textfield to the default background.
    // It will then set the weightReq checkbox to true and enable the weightSelect combobox.
    // It will also set the selected item of the weightSelect combobox to an empty string.
    if (e.getKeyChar() == KeyEvent.VK_ENTER && comp.weightReq.isSelected()) {
      comp.productQuantity.setBackground(textField.getBackground());
      comp.weightReq.setSelected(false);
      comp.weightSelect.setEnabled(false);
      comp.weightSelect.setSelectedItem("");
    } else if (e.getKeyChar() == KeyEvent.VK_ENTER && !comp.weightReq.isSelected()) {
      comp.productQuantity.setBackground(textField.getBackground());
      comp.weightReq.setSelected(true);
      comp.weightSelect.setEnabled(true);
      comp.weightSelect.setSelectedItem("");
    }
  }

  /**
   * If the key pressed is a letter, digit, period, comma, space, or backspace,
   * and the length of the text in the text field is less than the limit,
   * then the text field is editable.
   *
   * @param e The KeyEvent that is being listened to.
   * @param productManufacturerLimit The maximum number of characters allowed in the text field.
   */
  private void setProductManufacturerCap(KeyEvent e, int productManufacturerLimit) {
    // Checking if the key pressed is a letter, digit, period, comma, space, or backspace.
    // If it is, then it will allow the user to input the character.
    comp.productManufacturer.setEditable(
        Character.isLetter(e.getKeyChar())
            && comp.productManufacturer.getText().length() < productManufacturerLimit
            || Character.isDigit(e.getKeyChar())
            && comp.productManufacturer.getText().length() < productManufacturerLimit
            || e.getKeyChar() == KeyEvent.VK_PERIOD
            && comp.productManufacturer.getText().length() < productManufacturerLimit
            || e.getKeyChar() == KeyEvent.VK_COMMA
            && comp.productManufacturer.getText().length() < productManufacturerLimit
            || e.getKeyChar() == KeyEvent.VK_SPACE
            && comp.productManufacturer.getText().length() < productManufacturerLimit
            || e.getKeyChar() == KeyEvent.VK_BACK_SPACE
    );
  }

  /**
   * If the key pressed is a letter, digit, period, comma, space, or backspace,
   * and the length of the text in the text field is less than the limit,
   * then the text field is editable.
   *
   * @param e The KeyEvent object that is passed to the method.
   * @param productNameLimit The limit of characters that can be entered into the text field.
   */
  private void setProductNameCap(KeyEvent e, int productNameLimit) {
    // Checking if the key pressed is a letter, digit, period, comma, space, or backspace.
    // If it is, then it will allow the user to enter the character.
    comp.productName.setEditable(
        Character.isLetter(e.getKeyChar())
            && comp.productName.getText().length() < productNameLimit
            || Character.isDigit(e.getKeyChar())
            && comp.productName.getText().length() < productNameLimit
            || e.getKeyChar() == KeyEvent.VK_PERIOD
            && comp.productName.getText().length() < productNameLimit
            || e.getKeyChar() == KeyEvent.VK_COMMA
            && comp.productName.getText().length() < productNameLimit
            || e.getKeyChar() == KeyEvent.VK_SPACE
            && comp.productName.getText().length() < productNameLimit
            || e.getKeyChar() == KeyEvent.VK_BACK_SPACE
    );
  }

  /**
   * It removes the error highlight from the text fields.
   */
  private void removeHighlight() {
    // Adding a DocumentListener to the productName text field.
    comp.productName.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.productName.setBackground(textField.getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.productName.setBackground(textField.getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.productName.setBackground(textField.getBackground());
      }
    });
    // Adding a DocumentListener to the text field.
    comp.productManufacturer.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.productManufacturer.setBackground(textField.getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.productManufacturer.setBackground(textField.getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.productManufacturer.setBackground(textField.getBackground());
      }
    });
    // Removing the error highlight from the component.
    ErrorHighlightProduct.removeErrorHighlight(comp);
    // Adding a DocumentListener to the productHeight text field.
    comp.productHeight.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.productHeight.setBackground(textField.getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.productHeight.setBackground(textField.getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.productHeight.setBackground(textField.getBackground());
      }
    });
    // Adding a DocumentListener to the text field.
    comp.productLength.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.productLength.setBackground(textField.getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.productLength.setBackground(textField.getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.productLength.setBackground(textField.getBackground());
      }
    });
    // Adding a DocumentListener to the productWidth text field.
    comp.productWidth.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.productWidth.setBackground(textField.getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.productWidth.setBackground(textField.getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.productWidth.setBackground(textField.getBackground());
      }
    });
  }

  /**
   * It adds an action listener to each of the combo boxes in the form.
   */
  private void applyActionListener() {
    // Adding a key listener to the productName text field that will disable
    // the Control+V key combination.
    comp.productName.addKeyListener(DisableKeyCombo.disableControlV());
    // Disabling the key combination Ctrl+V for the productManufacturer text field.
    comp.productManufacturer.addKeyListener(DisableKeyCombo.disableControlV());
    // Setting the background color of the combo box to the default color.
    comp.categorySelect.addActionListener(
        e -> comp.categorySelect.setBackground(comboBox.getBackground())
    );
    comp.productOrigin.addActionListener(
        e -> comp.productOrigin.setBackground(comboBox.getBackground())
    );
    comp.priceCurrency.addActionListener(
        e -> comp.priceCurrency.setBackground(comboBox.getBackground())
    );
    comp.weightSelect.addActionListener(
        e -> comp.weightSelect.setBackground(comboBox.getBackground())
    );
    comp.colorSelect.addActionListener(
        e -> comp.colorSelect.setBackground(comboBox.getBackground())
    );
    comp.productUnit.addActionListener(
        e -> comp.productUnit.setBackground(comboBox.getBackground())
    );
  }
}
