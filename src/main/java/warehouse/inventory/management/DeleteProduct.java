package warehouse.inventory.management;

import java.awt.Window;
import java.util.logging.Level;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.utilities.Components;
import warehouse.inventory.initializers.ProductList;
import warehouse.inventory.utilities.ProductToSpreadsheet;


/**
 * It deletes a product from the database.
 */
@UtilityClass
public class DeleteProduct {

  /**
   * It deletes a product from the product list.
   */
  public void commitDeleteProduct() {
    // Creating a new JDialog object.
    JDialog popUp = CreateGuiFrame.getPopUp();
    String[] objButtons = {"Confirm", "Cancel"};
    // Creating a pop-up window with a warning message and two buttons.
    int promptResult = JOptionPane.showOptionDialog(
        popUp,
        "Are you sure you want to Delete this Product?",
        "WMS: Warning!",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.WARNING_MESSAGE,
        null,
        objButtons,
        objButtons[1]);
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Checking if the user clicked on the "Confirm" button.
    if (promptResult == JOptionPane.YES_OPTION) {
      // remove the product from the product list.
      ProductList.getProducts().remove(comp.deleteProduct.getText());
      // Updating the spreadsheet.
      try {
        ProductToSpreadsheet.refreshSpreadsheet();
      // Catching an exception and logging it.
      } catch (Exception e) {
        ErrorLogger.LOGGER.log(
            Level.SEVERE,
            String.format("An error occurred while updating the spreadsheet: %s", e)
        );
      }
      // It closes the pop-up window.
      Window[] windows = Window.getWindows();
      for (Window w : windows) {
        if (w instanceof JDialog dialog && w.isShowing()) {
          dialog.dispose();
        }
      }
    }
  }
}
