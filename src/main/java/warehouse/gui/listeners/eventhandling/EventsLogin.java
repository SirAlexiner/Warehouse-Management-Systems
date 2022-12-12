package warehouse.gui.listeners.eventhandling;

import java.util.logging.Level;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.CreateGui;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.login.CheckValidLogin;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * It sets the events for the login window.
 */
@UtilityClass
public class EventsLogin {
  // A static variable that is used to access the components of the login window.
  private final Components comp = Components.getComponents();

  /**
   * This function sets the events for the login button and the text fields.
   */
  public void setEventsLogin() {
    // Setting the background of the text field to the default background.
    comp.user.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.user.setBackground(new JTextField().getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.user.setBackground(new JTextField().getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.user.setBackground(new JTextField().getBackground());
      }
    });
    // Setting the background of the password field to the default background.
    comp.pass.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        comp.pass.setBackground(new JPasswordField().getBackground());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        comp.pass.setBackground(new JPasswordField().getBackground());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        comp.pass.setBackground(new JPasswordField().getBackground());
      }
    });
    // Adding an action listener to the password field.
    comp.pass.addActionListener(
        e -> loginToMain(CheckValidLogin.isLoginValid())
    );
    // Adding an action listener to the login button.
    comp.loginBtn.addActionListener(
        e -> loginToMain(CheckValidLogin.isLoginValid())
    );
  }

  /**
   * This function is used to log in to the main window.
   *
   * @param e boolean
   */
  private void loginToMain(boolean e) {
    // Getting the login frame.
    JFrame login = CreateGuiFrame.getLogin();
    // Getting the main frame.
    JFrame main = CreateGuiFrame.getMain();
    // Checking if the login is valid.
    if (e) {
      // Closing the login window.
      login.dispose();
      // Creating the main window.
      try {
        CreateGui.createGuiWindow(GuiWindows.MAIN);
      // Logging the exception.
      } catch (Exception ex) {
        ErrorLogger.LOGGER.log(Level.SEVERE, String.valueOf(ex));
      // Catching the out of memory error and displaying a message to the user.
      } catch (OutOfMemoryError ex) {
        // Logging the exception.
        ErrorLogger.LOGGER.log(Level.FINE, String.valueOf(ex));
        String[] objButtons = {"Ok"};
        int promptResult = JOptionPane.showOptionDialog(
            main,
            "Application ran out of memory.",
            "WMS: Warning!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            objButtons,
            objButtons[0]);
        // Checking if the user clicked on the "Ok" button.
        // If the user clicked on the "Ok" button, then the program will exit.
        if (promptResult == JOptionPane.YES_OPTION) {
          main.dispose();
          System.exit(137);
        }
      }
    }
  }
}
