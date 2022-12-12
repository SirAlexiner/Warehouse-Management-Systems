package warehouse.inventory.utilities.persistence;

import java.io.FileWriter;
import java.util.logging.Level;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.inventory.initializers.ProductList;
import warehouse.inventory.utilities.Product;

/**
 * It saves the products to a file.
 */
@UtilityClass
public class SaveToFileProducts {

  /**
   * creates a new file called Products.txt in
   * the directory, and writes the product information to the file
   */
  public void saveProducts() {
    // It creates a new file called Products.txt in the created directory.
    try (FileWriter writer = new FileWriter("./cfg/Products.txt")) {
      // Iterating through the keys of the products hashmap.
      for (String key : ProductList.getProducts().keySet()) {
        // It gets the product from the hashmap.
        Product productHash = ProductList.getProducts().get(key);
        // Getting the product information from the hashmap.
        Object[] product = productHash.getProductInfo();
        // Writing the product information to the file.
        writer.write(
            product[0]
                + "|" + product[1]
                + "|" + product[2]
                + "|" + product[3]
                + "|" + product[4]
                + "|" + product[5]
                + "|" + product[6]
                + "|" + product[7]
                + "|" + product[8]
                + "|" + product[9]
                + "\n"
        );
      }
    // Catching any exceptions that may occur while saving the products to a file.
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An error occurred while saving products to file: %s", e)
      );
    }
  }
}
