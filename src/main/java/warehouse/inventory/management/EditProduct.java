package warehouse.inventory.management;

import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import lombok.experimental.UtilityClass;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.utilities.CheckValidInput;
import warehouse.gui.windowsenum.GuiWindows;
import warehouse.inventory.utilities.FormatProductDetails;
import warehouse.inventory.utilities.Product;
import warehouse.inventory.utilities.ProductToSpreadsheet;

/**
 * A class that is used to edit a product.
 */
@UtilityClass
public class EditProduct {


  /**
   * This function is called when the user
   * clicks the "Commit Changes" button in the "Edit Product" window.
   */
  public void commitEditProduct() {
    // Getting the pop.up window.
    JDialog popUp = CreateGuiFrame.getPopUp();
    // Checking if the input is valid.
    // If it is, it will ask the user if they want to commit the changes.
    // If they do, it
    // will overwrite the product and refresh the spreadsheet.
    if (CheckValidInput.isValid(GuiWindows.EDIT_PRODUCT)) {
      String[] objButtons = {"Confirm", "Cancel"};
      int promptResult = JOptionPane.showOptionDialog(
          popUp,
          "Commit these changes?",
          "WMS: Confirm!",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.WARNING_MESSAGE,
          null,
          objButtons,
          objButtons[1]);
      if (promptResult == JOptionPane.YES_OPTION) {
        Object[] product = FormatProductDetails.constructProduct();
        new Product(product);
        ProductToSpreadsheet.refreshSpreadsheet();
        Window[] windows = Window.getWindows();
        for (Window w : windows) {
          if (w instanceof JDialog dialog && w.isShowing()) {
            dialog.dispose();
          }
        }
      }
    }
  }
}
