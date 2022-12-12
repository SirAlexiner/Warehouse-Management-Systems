package warehouse.gui;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.construction.CreateGuiPanel;
import warehouse.gui.windowsenum.GuiWindows;
import warehouse.inventory.utilities.persistence.SaveToFileCategories;
import warehouse.inventory.utilities.persistence.SaveToFileProducts;

/**
 * It creates the GUI JFrames and JDialogs, and returns them to the caller.
 */
@UtilityClass
public class CreateGuiFrame {
  @Getter
  // A private variable that is used to store the JFrame that is created
  // by the method `getFrame(GuiWindows window).
  private JFrame login;
  @Getter
  // A private variable that is used to store the JFrame that is created
  // by the method `getFrame(GuiWindows window).
  private JFrame main;
  @Getter
  // A private variable that is used to store the JDialog that is created
  // by the method `getDialog(GuiWindows window).
  private JDialog popUp;
  // A private variable that is used to store the path to the icon that is used for the GUI.
  private final String guiIconSvg = "guiIcon.svg";

  /**
   * This function sets the location of the frame to the center of the screen
   * and then makes the frame visible.
   *
   * @param frame The JFrame that you want to show.
   */
  public void show(JFrame frame) {
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  /**
   * This function sets the location of the GUI to the center of the screen
   * and then sets the GUI to visible.
   *
   * @param frame The JDialog frame that you want to show.
   */
  public void show(JDialog frame) {
    frame.setLocationRelativeTo(CreateGuiFrame.main);
    frame.setVisible(true);
  }

  /**
   * The function takes an Enum as a parameter and returns a JFrame
   * with the correct components and setting for the Enum passed.
   *
   * @param window This is the window that is being constructed, this is passed as an Enum.
   * @return A JFrame
   */
  public JFrame getFrame(GuiWindows window) {
    if (window.equals(GuiWindows.MAIN)) {
      main = new JFrame("WMS");
      FlatSVGIcon guiIcon = new FlatSVGIcon(guiIconSvg);
      main.setIconImage(guiIcon.getImage());
      main.setResizable(true);
      main.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      // Adding a listener to the JFrame, so that when the user clicks the close button,
      // a dialog box will appear asking the user if they want to close the program.
      main.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent we) {
          String[] objButtons = {"Yes", "No"};
          int promptResult = JOptionPane.showOptionDialog(main,
              "Are you sure you want to exit?",
              "WMS: Warning!",
              JOptionPane.DEFAULT_OPTION,
              JOptionPane.WARNING_MESSAGE,
              null,
              objButtons,
              objButtons[1]
          );
          // Saving the data to the file before the program is closed.
          if (promptResult == JOptionPane.YES_OPTION) {
            saveDataToFile();
            System.exit(0);
          }
        }
      });
      main.getContentPane().add(CreateGuiPanel.creatPanel(window));
      main.pack();
      return main;
    } else if (window.equals(GuiWindows.LOGIN)) {
      login = new JFrame("WMS: Login");
      FlatSVGIcon guiIcon = new FlatSVGIcon(guiIconSvg);
      login.setIconImage(guiIcon.getImage());
      login.setResizable(true);
      login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      login.getContentPane().add(CreateGuiPanel.creatPanel(window));
      login.pack();
      return login;
    } else {
      return null;
    }
  }


  /**
   * It saves the data to file.
   */
  private static void saveDataToFile() {
    try {
      SaveToFileCategories.saveCategories();
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An error occurred saving categories to file: %s", e)
      );
    }
    try {
      SaveToFileProducts.saveProducts();
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An error occurred saving products to file: %s", e)
      );
    }
  }

  /**
   * It creates a JDialog, sets its title, icon, size, and returns it.
   *
   * @param window This is the enum that is passed to the method,
   *               it is used to determine which GUI to create.
   * @return A JDialog with a JPanel and its components.
   */
  public JDialog getDialog(GuiWindows window) {
    popUp = new JDialog(main);
    FlatSVGIcon guiIcon = new FlatSVGIcon(guiIconSvg);
    popUp.setIconImage(guiIcon.getImage());
    popUp.setModal(true);
    switch (window) {
      case NEW_PRODUCT -> popUp.setTitle("WMS: New Product");
      case NEW_CATEGORY -> popUp.setTitle("WMS: New Category");
      case EDIT_CATEGORY -> popUp.setTitle("WMS: Rename Product Categories.txt");
      case DELETE_PRODUCT -> popUp.setTitle("WMS: Delete Product");
      case ORDER -> popUp.setTitle("WMS: Order Product");
      case EDIT_PRODUCT -> popUp.setTitle("WMS: Edit Product");
      case DELETE_CATEGORY -> popUp.setTitle("WMS: Delete Product Categories.txt");
      case HELP -> popUp.setTitle("WMS: Help");
      case ABOUT -> popUp.setTitle("WMS: About");
      default -> ErrorLogger.LOGGER.log(Level.WARNING, "Unable to find GUI");
    }
    popUp.setResizable(false);
    if (window.equals(GuiWindows.ABOUT)
        || window.equals(GuiWindows.HELP)
        || window.equals(GuiWindows.NEW_CATEGORY)
    ) {
      popUp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    } else {
      popUp.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      popUp.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent we) {
          String[] objButtons = {"Yes", "No"};
          int promptResult = JOptionPane.showOptionDialog(popUp,
              "Are you sure you want to close the window?",
              "WMS: Warning!",
              JOptionPane.DEFAULT_OPTION,
              JOptionPane.WARNING_MESSAGE,
              null,
              objButtons,
              objButtons[1]
          );
          if (promptResult == JOptionPane.YES_OPTION) {
            we.getWindow().dispose();
          }
        }
      });
    }
    popUp.getContentPane().add(CreateGuiPanel.creatPanel(window));
    popUp.pack();
    return popUp;
  }
}
