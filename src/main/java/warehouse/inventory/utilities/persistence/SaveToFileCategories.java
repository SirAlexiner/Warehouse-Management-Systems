package warehouse.inventory.utilities.persistence;

import java.io.FileWriter;
import java.util.logging.Level;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.inventory.initializers.CategoryList;

/**
 * It saves the categories to a file.
 */
@UtilityClass
public class SaveToFileCategories {

  /**
   * creates a new file called Categories.txt in the directory,
   * and iterates through the list of categories and writes each one to the file.
   */
  public void saveCategories() {
    // It creates a new file called Categories.txt in the directory.
    try (FileWriter writer = new FileWriter("./cfg/Categories.txt")) {
      // Iterating through the list of categories and writing each one to the file.
      for (String category : CategoryList.getProductCategories()) {
        writer.write(category + "\n");
      }
    // Catching any exceptions that may occur while saving the categories to a file.
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An error occurred while saving categories to file: %s", e)
      );
    }
  }
}
