package warehouse.inventory.utilities.persistence;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.Loader;
import warehouse.gui.utilities.StringTitle;
import warehouse.inventory.initializers.SupportedCountriesList;

/**
 * It reads the supported countries from a file and adds them to a list.
 */
@UtilityClass
public class CountriesFromFile {
  // Getting the main frame from the CreateGuiFrame class.
  private final JFrame main = CreateGuiFrame.getMain();

  /**
   * It reads a file, counts the number of lines in the file,
   * and then reads the file again to populate a HashMap and an
   * ArrayList.
   */
  public void getCountries() {
    // Reading the file and creating a buffered reader.
    try (InputStream in = CountriesFromFile.class.getResourceAsStream("/Countries.txt");
         BufferedReader countryCounterReader = new BufferedReader(
             new InputStreamReader(Objects.requireNonNull(in)))
    ) {
      // Creating a new scanner object and passing the buffered reader object to it.
      Scanner countryCounter = new Scanner(countryCounterReader);
      // Checking if the file has any lines,
      // if it does, it creates a progress bar and a dialog box to show the progress of
      // the file being read.
      if (countryCounter.hasNextLine()) {
        JDialog loader = new JDialog(main, "WMS: Please Wait", true);
        loader.setResizable(false);
        loader.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        FlatSVGIcon guiIcon = new FlatSVGIcon("guiIcon.svg");

        loader.setIconImage(guiIcon.getImage());
        JLabel message = new JLabel("Loading Countries from File...");
        int count = 0;
        while (countryCounter.hasNextLine()) {
          count++;
          countryCounter.nextLine();
        }
        JProgressBar progressBar = new JProgressBar(
            0,
            count
        );
        JButton clsBtn = new JButton("Close");
        Loader.constructLoader(loader, message, progressBar);
        InputStream file = CountriesFromFile.class.getResourceAsStream("/Countries.txt");
        BufferedReader countryReader = new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(file))
        );
        // Creating a new scanner object and passing the buffered reader object to it.
        Scanner countryScanner = new Scanner(countryReader);
        int i = 0;
        // Reading the file line by line,
        // splitting the line by the pipe character,
        // and then adding the country to a HashMap and an ArrayList.
        while (countryScanner.hasNextLine()) {
          String data = countryScanner.nextLine();
          String[] product = data.split("\\|");
          SupportedCountriesList.getSupportedCountries().put(product[0], product[1]);
          SupportedCountriesList.getCountries().add(
              StringTitle.toTitleCase(product[0]).stripTrailing()
          );
          Collections.sort(SupportedCountriesList.getCountries());
          Components comp = Components.getComponents();
          comp.productCountries = (SupportedCountriesList.getCountries()).toArray(new String[0]);
          i++;
          progressBar.setValue(i);
        }
        // Disposing the loader dialog box.
        Loader.disposeLoader(loader, clsBtn);
      }
    // Catching a FileNotFoundException and logging it.
    } catch (FileNotFoundException e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("Unable to locate the supported countries file: %s", e));
    // Catching any exception that is not a FileNotFoundException and logging it.
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An error occurred while loading supported countries from file: %s", e)
      );
    }
  }
}
