package warehouse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * The configureLogger method is used to configure the logger for error logging.
 */
public class ErrorLogger {
  public static final Logger LOGGER = Logger.getLogger("WMSLogger");

  /**
   * It creates a directory if it does not exist, creates a file handler,
   * sets the format of the log file, and adds the
   * file handler to the logger.
   *
   * @param name   The name of the log file.
   * @param folder The folder where the log file will be created.
   */
  public void configureLogger(String name, String folder) {
    // Creating a directory if it does not exist.
    try {
      Files.createDirectories(Path.of(folder));
    } catch (Exception e) {
      // Logging the exception to the log file.
      LOGGER.warning("Failed to make logger directory: " + e);
    }
    try {
      // Creating a new file handler.
      FileHandler fh = new FileHandler(folder + "/" + name);
      // Setting the format of the log file.
      fh.setFormatter(new SimpleFormatter());
      // Adding the file handler to the logger.
      Logger.getLogger("").addHandler(fh);
    } catch (Exception e) {
      // Logging the exception to the log file.
      LOGGER.warning(String.valueOf(e));
    }
  }
}
