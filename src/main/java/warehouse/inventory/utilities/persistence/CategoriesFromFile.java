package warehouse.inventory.utilities.persistence;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.StringTitle;
import warehouse.inventory.initializers.CategoryList;

/**
 * It reads the categories from a file and adds them to the category list.
 */
@UtilityClass
public class CategoriesFromFile {


  /**
   * It reads a file, and adds the contents of the file to a list.
   */
  public void getCategories() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Creating a new input stream and buffered reader.
    try (@NonNull InputStream in = new FileInputStream("./cfg/Categories.txt");
         BufferedReader categoryFileReader = new BufferedReader(
             new InputStreamReader(in))
    ) {
      // Creating a new scanner object, and passing the buffered reader as a parameter.
      Scanner categoryReader = new Scanner(categoryFileReader);
      // Reading the file, and adding the contents of the file to a list.
      while (categoryReader.hasNext()) {
        String data = categoryReader.nextLine();
        if (CategoryList.categoryNotInList(StringTitle.toTitleCase(data).stripTrailing())) {
          CategoryList.getProductCategories().add(StringTitle.toTitleCase(data).stripTrailing());
          Collections.sort(CategoryList.getProductCategories());
          comp.categoryArray = (CategoryList.getProductCategories()).toArray(new String[0]);
          JComboBox<Object> searchCategory = comp.searchCategory;
          searchCategory.setModel(
              new DefaultComboBoxModel<>(CategoryList.getProductCategories().toArray())
          );
        }
      }
    // Catching the FileNotFoundException and logging it.
    } catch (FileNotFoundException e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("Unable to locate the category file: %s", e)
      );
    // Catching any exception that is thrown, and logging it.
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An error occurred while getting the categories from file: %s", e)
      );
    }
  }
}
