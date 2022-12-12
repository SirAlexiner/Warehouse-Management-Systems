package warehouse.gui.construction;

import java.awt.Dimension;
import java.util.logging.Level;
import javax.swing.JPanel;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.listeners.EventHandler;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * This class creates the panels for the GUI.
 */
@UtilityClass
public class CreateGuiPanel {

  /**
   * It creates a panel,
   * adds the contents to it,
   * sets the bounds of the contents,
   * adds the event handlers, and sets the size of the panel.
   *
   * @param window The window that you want to create a panel for.
   * @return A JPanel with the contents of the window.
   */
  public JPanel creatPanel(GuiWindows window) {
    JPanel panel = new JPanel();
    CreateContents.createContentPanel(panel, window);
    switch (window) {
      case LOGIN -> {
        Bounds.setBounds(window);
        EventHandler.addEvent(window);
        panel.setPreferredSize(new Dimension(300, 345));
      }
      case MAIN -> {
        EventHandler.addEvent(window);
        panel.setPreferredSize(new Dimension(1120, 630));
      }
      case NEW_PRODUCT -> {
        Bounds.setBounds(window);
        EventHandler.addEvent(window);
        panel.setPreferredSize(new Dimension(575, 660));
      }
      case NEW_CATEGORY -> {
        Bounds.setBounds(window);
        EventHandler.addEvent(window);
        panel.setPreferredSize(new Dimension(300, 90));
      }
      case EDIT_CATEGORY -> {
        Bounds.setBounds(window);
        EventHandler.addEvent(window);
        panel.setPreferredSize(new Dimension(325, 235));
      }
      case DELETE_CATEGORY -> {
        Bounds.setBounds(window);
        EventHandler.addEvent(window);
        panel.setPreferredSize(new Dimension(300, 160));
      }
      case DELETE_PRODUCT -> {
        Bounds.setBounds(window);
        EventHandler.addEvent(window);
        panel.setPreferredSize(new Dimension(300, 130));
      }
      case ORDER -> {
        Bounds.setBounds(window);
        EventHandler.addEvent(window);
        panel.setPreferredSize(new Dimension(325, 220));
      }
      case EDIT_PRODUCT -> {
        Bounds.setBounds(window);
        EventHandler.addEvent(window);
        panel.setPreferredSize(new Dimension(575, 735));
      }
      case HELP -> {
        Bounds.setBounds(window);
        panel.setPreferredSize(new Dimension(575, 480));
      }
      case ABOUT -> {
        Bounds.setBounds(window);
        panel.setPreferredSize(new Dimension(300, 95));
      }
      default -> ErrorLogger.LOGGER.log(Level.WARNING, "Unable to find GUI");
    }
    panel.setLayout(null);
    return panel;
  }
}
