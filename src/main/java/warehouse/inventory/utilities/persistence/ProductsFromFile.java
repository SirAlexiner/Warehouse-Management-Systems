package warehouse.inventory.utilities.persistence;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.utilities.Loader;
import warehouse.inventory.utilities.Product;

/**
 * It reads the products from a file and adds them to the hash table.
 */
@UtilityClass
public class ProductsFromFile {

  /**
   * It reads the products file and loads the products into the program.
   */
  public void getProducts() {
    // Getting the main frame of the program.
    JFrame main = CreateGuiFrame.getMain();
    // Creating a new input stream from the file Products.txt
    // and then creating a new buffered reader from the input
    // stream.
    try (@NonNull InputStream in = new FileInputStream("./cfg/Products.txt");
         BufferedReader productCounterReader = new BufferedReader(
             new InputStreamReader(in))
    ) {
      // Creating a new scanner object from the buffered reader.
      Scanner productCounter = new Scanner(productCounterReader);
      // Checking if the file has any lines.
      if (productCounter.hasNextLine()) {
        // Creating a new JDialog with the title "WMS: Please Wait" and making it modal.
        JDialog loader = new JDialog(main, "WMS: Please Wait", true);
        // Making the dialog not resizable.
        loader.setResizable(false);
        // Preventing the user from closing the dialog.
        loader.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // Creating a new icon for the loader dialog.
        FlatSVGIcon guiIcon = new FlatSVGIcon("guiIcon.svg");
        // Setting the icon of the loader dialog to the icon that was created.
        loader.setIconImage(guiIcon.getImage());
        // Creating a new JLabel with the text "Loading Products from File..."
        // and setting the bounds of the label.
        JLabel message = new JLabel("Loading Products from File...");
        message.setBounds(10, 0, 250, 30);
        // Counting the number of lines in the file
        // and then using that number to set the maximum value of the progress bar.
        int count = 0;
        while (productCounter.hasNextLine()) {
          count++;
          productCounter.nextLine();
        }
        JProgressBar progressBar = new JProgressBar(
            0,
            count
        );
        // Creating a new JButton with the text "Close" and storing it in the variable clsBtn.
        JButton clsBtn = new JButton("Close");
        // A method that is used to construct the loader dialog.
        Loader.constructLoader(loader, message, progressBar);
        // Loading the products from the file into the program.
        loadProductsFromFile(progressBar);
        // Disposing the loader dialog after a second.
        Loader.disposeLoader(loader, clsBtn);
      }
      // Catching the FileNotFoundException and logging it.
    } catch (FileNotFoundException e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("Unable to locate the products file: %s", e)
      );
      // Catching any exception that is thrown and logging it.
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An error occurred while loading products from file: %s", e)
      );
    }
  }

  /**
   * Loads products from a file into a HashMap.
   *
   * @param progressBar The progress bar to update.
   */
  private static void loadProductsFromFile(JProgressBar progressBar) {
    // Creating a new input stream from the file Products.txt
    // and then creating a new buffered reader from the input
    // stream.
    try (@NonNull InputStream file = new FileInputStream("./cfg/Products.txt");
         BufferedReader productReader = new BufferedReader(
             new InputStreamReader(file))
    ) {
      // Creating a new scanner object from the buffered reader.
      Scanner productScanner = new Scanner(productReader);
      int i = 0;
      // Reading the products from the file and adding them to the hash table.
      while (productScanner.hasNextLine()) {
        String data = productScanner.nextLine();
        Object[] product = data.split("\\|");
        Object[] productFormatted = new Object[10];
        Arrays.fill(productFormatted, "");
        System.arraycopy(product, 0, productFormatted, 0, product.length);
        new Product(productFormatted);
        i++;
        progressBar.setValue(i);
      }
    // Catching the FileNotFoundException and logging it.
    } catch (FileNotFoundException e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("Unable to locate the products file: %s", e)
      );
    // Catching any exception that is thrown and logging it.
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An error occurred while loading products from file: %s", e)
      );
    }
  }
}
