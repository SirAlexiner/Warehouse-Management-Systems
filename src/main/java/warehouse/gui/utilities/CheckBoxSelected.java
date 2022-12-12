package warehouse.gui.utilities;

import javax.swing.JTextField;
import lombok.experimental.UtilityClass;

/**
 * If the checkbox is selected, enable the fields and if it's not selected, disable the fields.
 */
@UtilityClass
public class CheckBoxSelected {
  // It's creating a new Components object and assigning it to the variable comp.
  private final Components comp = Components.getComponents();
  // It's creating a new JTextField object and assigning it to the variable textField.
  private final JTextField textField = new JTextField();

  /**
   * If the product is a "box", enable the dimensions fields.
   */
  public void dimensionsRequired() {
    comp.productLength.setEnabled(true);
    comp.productHeight.setEnabled(true);
    comp.productWidth.setEnabled(true);
    comp.productUnit.setEnabled(true);
  }

  /**
   * "If the product is not a "box", disable the text fields and combo box for the dimensions
   * and unit of measurement."
   * The first thing we do is remove any error highlight from the text fields.
   * We do this by setting the background color
   * of the text fields to the default background color of the text fields
   */
  public void dimensionsNotRequired() {
    comp.productHeight.setBackground(textField.getBackground());
    comp.productWidth.setBackground(textField.getBackground());
    comp.productLength.setBackground(textField.getBackground());
    comp.productHeight.setText(null);
    comp.productWidth.setText(null);
    comp.productLength.setText(null);
    comp.productHeight.setEnabled(false);
    comp.productWidth.setEnabled(false);
    comp.productLength.setEnabled(false);
    comp.productUnit.setEnabled(false);
    comp.productUnit.setSelectedItem("");
  }
}
