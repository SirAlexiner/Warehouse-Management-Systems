package warehouse.gui.utilities;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.jthemedetecor.OsThemeDetector;
import java.util.logging.Level;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;

/**
 * The class is used to set the theme of the GUI to either Dark or Light mode
 * depending on the Operating System's theme.
 * To do this the code utilizes
 * <a href="https://github.com/Dansoftowner/jSystemThemeDetector">OsThemeDetector</a>
 *  /
 *  <a href="https://github.com/JFormDesigner/FlatLaf">FlatLaf</a>
 *
 * @author Daniel Gyoerffy / JFormDesigner
 * @version 3.8 / 2.6
 * @since 2022-01-03 / 2022-10-18
 */
@UtilityClass
public class AppTheme {

  /**
   * If the Operating System is in Dark Mode, set the GUI to FlatLaf's Dark Theme,
   * otherwise set the GUI to FlatLaf's Light Theme.
   *
   * @param frame The JFrame that you want to change the theme of.
   */
  public void setTheme(JFrame frame) {
    // A string that is used to log an error message if an error occurs.
    String err = "Failed to initialize LaF: %s";
    // Setting the UI of the JTable to show the horizontal and vertical lines,
    // and it is setting the spacing between the cells to 5.
    // It is also setting the background of the title pane to false.
    UIManager.put("Table.showHorizontalLines", true);
    UIManager.put("Table.showVerticalLines", true);
    UIManager.put("Table.intercellSpacing", 5);
    UIManager.put("TitlePane.unifiedBackground", false);
    // Getting the Operating System's theme.
    final OsThemeDetector check = OsThemeDetector.getDetector();
    // Checking if the Operating System is in Dark Mode.
    final boolean isDarkThemeUsed = check.isDark();
    // Checking if the Operating System is in Dark Mode,
    // and if it is, it is setting the Look and Feel of the application to FlatLaf's Dark Theme,
    // otherwise it is setting the Look and Feel of the application to FlatLaf's Light Theme.
    if (isDarkThemeUsed) {
      try {
        UIManager.setLookAndFeel(new FlatDarkLaf());
      } catch (Exception ex) {
        ErrorLogger.LOGGER.log(Level.FINEST, String.format(err, ex));
      }
      SwingUtilities.updateComponentTreeUI(frame);
    } else {
      try {
        UIManager.setLookAndFeel(new FlatLightLaf());
      } catch (Exception ex) {
        ErrorLogger.LOGGER.log(Level.FINEST, String.format(err, ex));
      }
      SwingUtilities.updateComponentTreeUI(frame);
    }
    // A function that detects the change in the Operating System Theme
    // and changes the Look and Feel of the application to match the Operating System Theme.
    themeChanged(frame, err);
  }

  /**
   * The above function is a function that detects the change in the Operating System Theme
   * and changes the Look and Feel of the application to match the Operating System Theme.
   *
   * @param frame The JFrame that you want to update the UI of.
   * @param err The error message to be logged if an error occurs.
   */
  private void themeChanged(JFrame frame, String err) {
    // Getting the Operating System's theme.
    final OsThemeDetector change = OsThemeDetector.getDetector();
    // Registering a listener that detects the change in the Operating System's theme,
    change.registerListener(isDark -> SwingUtilities.invokeLater(() -> {
      // Checking if the Operating System is in Dark Mode,
      // and if it is, it is setting the Look and Feel of the application to FlatLaf's Dark Theme,
      // otherwise it is setting the Look and Feel of the application to FlatLaf's Light Theme.
      if (Boolean.TRUE.equals(isDark)) {
        try {
          FlatAnimatedLafChange.showSnapshot();
          UIManager.setLookAndFeel(new FlatDarkLaf());
          FlatLaf.updateUI();
          FlatAnimatedLafChange.hideSnapshotWithAnimation();
        } catch (Exception ex) {
          ErrorLogger.LOGGER.log(Level.FINEST, String.format(err, ex));
        }
        SwingUtilities.updateComponentTreeUI(frame);
      } else {
        try {
          FlatAnimatedLafChange.showSnapshot();
          UIManager.setLookAndFeel(new FlatLightLaf());
          FlatLaf.updateUI();
          FlatAnimatedLafChange.hideSnapshotWithAnimation();
        } catch (Exception ex) {
          ErrorLogger.LOGGER.log(Level.FINEST, String.format(err, ex));
        }
        SwingUtilities.updateComponentTreeUI(frame);
      }
    }));
  }
}