package warehouse.inventory.management;

import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import lombok.experimental.UtilityClass;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.utilities.CheckValidInput;
import warehouse.gui.utilities.Components;
import warehouse.gui.windowsenum.GuiWindows;
import warehouse.inventory.initializers.ProductList;
import warehouse.inventory.utilities.Product;
import warehouse.inventory.utilities.ProductToSpreadsheet;

/**
 * The OrderProduct class is used to commit orders to the application.
 */
@UtilityClass
public class OrderProduct {


  /**
   * This function is used to commit the order of a product to the spreadsheet.
   */
  public void commitOrderProduct() {
    // Creating a pop.up window.
    JDialog popUp = CreateGuiFrame.getPopUp();
    // Checking if the input is valid.
    if (CheckValidInput.isValid(GuiWindows.ORDER)) {
      // This is a pop.up window that asks the user if they want to confirm the order.
      String[] objButtons = {"Confirm", "Cancel"};
      int promptResult = JOptionPane.showOptionDialog(
          popUp,
          "Are you sure you want to Order this Quantity of the Product?",
          "WMS: Confirm!",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE,
          null,
          objButtons,
          objButtons[1]);
      // Getting the components from the Components class.
      Components comp = Components.getComponents();
      // This is checking if the user has confirmed the order.
      if (promptResult == JOptionPane.YES_OPTION) {
        // This is getting the key of the product that the user wants to order.
        String key = comp.orderProduct.getText();
        // This is getting the quantity of the product that the user wants to order.
        double orderQuantity = Double.parseDouble(comp.orderProductQnt.getText());
        // This is getting the product from the product list.
        Product product = ProductList.getProducts().get(key);
        // This is getting the quantity of the product from the product list.
        String quantityWeight = String.valueOf(product.getProductInfo()[5]);
        // This is checking if the quantity of the product is a weight.
        if (quantityWeight.matches(".*[a-zA-Z]")) {
          // This is getting the quantity of the product from the product list.
          double quantity = Double.parseDouble(quantityWeight.split(" ")[0]);
          // This is getting the weight of the product.
          String weight = quantityWeight.split(" ")[1];
          // Adding the order quantity to the quantity of the product.
          quantity += orderQuantity;
          // This is setting the quantity of the product to the quantity of the product
          // plus the order quantity.
          product.getProductInfo()[5] = quantity + " " + weight;
        } else {
          // This is getting the quantity of the product from the product list.
          int quantity = Integer.parseInt(String.valueOf(product.getProductInfo()[5]));
          // This is adding the order quantity to the quantity of the product.
          quantity += orderQuantity;
          product.getProductInfo()[5] = quantity;
        }
        // This is refreshing the spreadsheet and closing the pop-up window.
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
