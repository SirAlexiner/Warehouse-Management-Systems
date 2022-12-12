package warehouse.gui.construction.contents;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * This class is responsible for constructing the Delete panel.
 */
@UtilityClass
public class CreateDelete {

  /**
   * This function constructs the Delete panel for the delete product and delete category windows.
   *
   * @param panel The panel that the components will be added to.
   * @param window The window that the panel is being constructed for.
   */
  public void constructDeletePanel(JPanel panel, GuiWindows window) {
    // Getting the Components object.
    Components comp = Components.getComponents();
    // This is a conditional statement that checks if the window is the delete product window.
    // If it is, it will create a label and text field for the product ID.
    // If it is not, it will create a label and combo box for the product category.
    if (window.equals(GuiWindows.DELETE_PRODUCT)) {
      comp.deleteProductLabel = new JLabel("Product ID");
      comp.deleteProduct = new JTextField(5);
      String key = String.valueOf(
          comp.spreadsheet.getValueAt(comp.spreadsheet.getSelectedRow(), 0)
      );
      comp.deleteProduct.setText(key);
      comp.deleteProduct.setEnabled(false);
    } else {
      comp.deleteProductLabel = new JLabel("Product Category");
      comp.deleteCategorySelect = new JComboBox<>(comp.categoryArray);
      comp.deleteCategorySelect.setToolTipText("Please Select Category to Delete");
      comp.deleteItemWarningLabel = new JLabel("This Change does not affect Products");
    }
    // Creating a button with the text "DELETE" and setting the background and foreground colors.
    comp.deleteItemBtn = new JButton("DELETE");
    comp.deleteItemBtn.setBackground(Color.decode("#990000"));
    comp.deleteItemBtn.setForeground(Color.decode("#FFFFFF"));
    // Adding the deleteProductLabel to the panel.
    panel.add(comp.deleteProductLabel);
    // This is a conditional statement that checks if the window is the delete product window.
    // If it is, it will add the Product ID textfield to the panel.
    // If it is not, it will add the product category combo box and the warning label to the panel.
    if (window.equals(GuiWindows.DELETE_PRODUCT)) {
      panel.add(comp.deleteProduct);
    } else {
      panel.add(comp.deleteCategorySelect);
      panel.add(comp.deleteItemWarningLabel);
    }
    // Adding the delete button to the panel.
    panel.add(comp.deleteItemBtn);
  }
}
