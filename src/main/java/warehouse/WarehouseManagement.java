package warehouse;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import warehouse.gui.CreateGui;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * WMS (Warehouse Management System) for use in Inventory Management in large businesses.
 *
 * @author Torgrim Thorsen
 * @version 1.0
 * @since 2022-11-03
 */
public class WarehouseManagement {

  /**
   * The main function is the entry point of the program.
   * It creates an ErrorLogger object
   * and configures the logger to write to a file called ErrorLog.log.
   * It then throws the login window in a try-catch block. If an error occurs, it is
   * logged to the ErrorLog.log file
   */
  public static void main(String[] args) {
    // create an ErrorLogger object
    // and configures the logger to write to a file called ErrorLog.log.
    ErrorLogger errorLogger = new ErrorLogger();
    errorLogger.configureLogger("ErrorLog.log", "./cfg/logs");
    // Checking if the file loggedInBefore.txt exists,
    // and if it does, it checks if the time since last login is less than
    // 8 hours. If it is, it opens the main window, otherwise it opens the login window.
    try {
      File f = new File("./cfg/loggedInBefore.txt");
      if (f.exists() && !f.isDirectory() && f.isHidden()) {
        checkTimeSinceLastLogin(f);
      } else {
        CreateGui.createGuiWindow(GuiWindows.LOGIN);
      }
    // catch any exception that occurs and logs it to the ErrorLog.log file.
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An Error Occurred starting the application: %s", e)
      );
    }
  }

  /**
   * If the current time is less than 8 hours after the last login time,
   * then open the main window, otherwise open the
   * login window.
   *
   * @param f The file to check the time since last login
   */
  private static void checkTimeSinceLastLogin(File f) {
    try (Scanner scan = new Scanner(f)) {
      if (System.currentTimeMillis() - 28800000 < Long.parseLong(scan.nextLine())) {
        CreateGui.createGuiWindow(GuiWindows.MAIN);
      } else {
        CreateGui.createGuiWindow(GuiWindows.LOGIN);
      }
    // Catching any exception that occurs and logs it to the ErrorLog.log file.
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An Error Occurred while checking time since last login: %s", e)
      );
      CreateGui.createGuiWindow(GuiWindows.LOGIN);
    }
  }
}