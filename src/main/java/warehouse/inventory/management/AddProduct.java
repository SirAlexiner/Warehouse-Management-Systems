package warehouse.inventory.management;

import java.util.logging.Level;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.utilities.CheckValidInput;
import warehouse.gui.utilities.ClearInput;
import warehouse.gui.windowsenum.GuiWindows;
import warehouse.inventory.initializers.ProductList;
import warehouse.inventory.utilities.FormatProductDetails;
import warehouse.inventory.utilities.Product;
import warehouse.inventory.utilities.ProductKey;
import warehouse.inventory.utilities.ProductToSpreadsheet;



/**
 * It adds a product to the database.
 */
@UtilityClass
public class AddProduct {


  /**
   * It checks if the input is valid, then it asks the user if they want to confirm the input,
   * and if they do, it adds the product to the hashmap and updates the spreadsheet.
   */
  public void commitAddProduct() {
    // Creating a pop-up window.
    JDialog popUp = CreateGuiFrame.getPopUp();
    // Checking if the input is valid.
    if (CheckValidInput.isValid(GuiWindows.NEW_PRODUCT)) {
      // Creating an array of objects that will be used to create a new product.
      Object[] product = FormatProductDetails.constructProduct();
      // Creating a key for the product.
      String key = ProductKey.getProductKey(
          product[1], product[2], product[3], product[6], product[7], product[8], product[9]
      );
      // It checks if the product is already in the hashmap.
      if (ProductList.productNotInMap(key)) {
        String[] objButtons = {"Confirm", "Cancel"};
        // Creating a pop-up window that asks the user if they want to confirm the input.
        int promptResult = JOptionPane.showOptionDialog(
            popUp,
            "Have you entered all the information correctly?\n"
                + "Some Details can't be changed later.",
            "WMS: Confirm!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            objButtons,
            objButtons[1]);
        // Checking if the user has confirmed the input.
        if (promptResult == JOptionPane.YES_OPTION) {
          // Creating a new product.
          new Product(product);
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
          // clear the input fields.
          ClearInput.clearInputFields();
        }
      }
    }
  }
}
