package warehouse.inventory.initializers;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import warehouse.gui.CreateGuiFrame;
import warehouse.inventory.utilities.Product;

/**
 * This class is used to create a new HashMap with a String as the key
 * and a Product as the value.
 */
@UtilityClass
public class ProductList {
  @Getter
  // Creating a new HashMap with a String as the key and a Product as the value.
  private final Map<String, Product> products = new HashMap<>();

  /**
   * If the product exists, display a prompt window and return false.
   * Otherwise, return true.
   *
   * @param key the product key.
   * @return A boolean value.
   */
  public boolean productNotInMap(String key) {
    JDialog popUp = CreateGuiFrame.getPopUp();
    if (ProductList.products.containsKey(key)) {
      String[] objButtons = {"OK"};
      JOptionPane.showOptionDialog(
          popUp,
          "This Product already exists.",
          "WMS: Notice!",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE,
          null,
          objButtons,
          objButtons[0]);
      return false;
    } else {
      return true;
    }
  }
}
