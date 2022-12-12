package warehouse.gui.utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.inventory.initializers.SupportedCountriesList;

/**
 * It constructs a progress window with a progressbar and a message label.
 */
@UtilityClass
public class Loader {

  /**
   * It constructs a progress window with a progressbar and a message label.
   *
   * @param loader The JDialog that will be used to display the progress window
   * @param message The message to display on the progress window
   * @param progressBar The progressbar that will be updated
   */
  public void constructLoader(JDialog loader, JLabel message, JProgressBar progressBar) {
    message.setBounds(10, 0, 250, 30);
    progressBar.setBounds(10, 30, 250, 30);
    progressBar.setForeground(Color.decode("#967bb6"));
    progressBar.setStringPainted(true);
    JButton clsBtn = new JButton("Close");
    clsBtn.addActionListener(e -> loader.dispose());
    clsBtn.setEnabled(false);
    clsBtn.setBounds(135, 70, 125, 30);
    JPanel loaderPanel = new JPanel();
    loaderPanel.setPreferredSize(new Dimension(270, 110));
    loaderPanel.add(message);
    loaderPanel.add(progressBar);
    loaderPanel.add(clsBtn);
    loaderPanel.setLayout(null);
    // Adding a window listener to the loader JDialog.
    loader.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent we) {
        // Checking if the progressbar has reached the end of the progressbar,
        // and if it has, it disposes of the loader.
        if (progressBar.getValue() == SupportedCountriesList.getSupportedCountries().size()) {
          loader.dispose();
        }
      }
    });
    Thread t = new Thread(() -> {
      loader.add(loaderPanel);
      loader.pack();
      loader.setLocationRelativeTo(null);
      loader.setVisible(true);
    });
    t.start();
  }

  /**
   * It disposes of a JDialog after a 1-second delay.
   *
   * @param loader The JDialog that is being displayed
   * @param clsBtn The next button that will be enabled after the loader is disposed.
   */
  public void disposeLoader(JDialog loader, JButton clsBtn) {
    // Disposing the loader dialog after a second.
    try {
      Thread.sleep(1000);
      loader.dispose();
      // Catching the InterruptedException, and log it.
      // And then setting the clsBtn to enabled,
      // logging the error and then interrupting the thread.
    } catch (InterruptedException e) {
      clsBtn.setEnabled(true);
      ErrorLogger.LOGGER.log(Level.WARNING, "Sleep Thread was interrupted: %s", e);
      Thread.currentThread().interrupt();
      // Catching any exception that is thrown, and log it.
      // And then en<ble the clsBtn.
    } catch (Exception e) {
      clsBtn.setEnabled(true);
      ErrorLogger.LOGGER.log(Level.WARNING, "An error occurred during sleep thread: %s", e);
    }
  }
}
