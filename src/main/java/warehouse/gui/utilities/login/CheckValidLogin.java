package warehouse.gui.utilities.login;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.utilities.Components;

/**
 * This class checks if the login information is valid.
 */
@UtilityClass
public class CheckValidLogin {
  // Creating a new instance of the Components class.
  private final Components comp = Components.getComponents();

  /**
   * This function checks if the user input matches the login information in the Login.txt file.
   *
   * @return A boolean value
   */
  public boolean isLoginValid() {
    // Declaring the variables user and pass.
    String user = null;
    String pass = null;
    // This is a try-with-resources statement.
    // It is used to automatically close resources that are opened in the try block.
    // This is reading the Login.txt file.
    try (InputStream log = CheckValidLogin.class.getResourceAsStream("/Login.txt");
         BufferedReader loginFileReader = new BufferedReader(
             new InputStreamReader(Objects.requireNonNull(log)))
    ) {
      Scanner scan = new Scanner(loginFileReader);
      while (scan.hasNextLine()) {
        // Reading the Login.txt file and splitting the data into two variables.
        String data = scan.nextLine();
        String[] login = data.split("\\|");
        user = login[0];
        pass = login[1];
      }
      // This is catching the FileNotFoundException and logging it.
    } catch (FileNotFoundException e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("Failed to locate Login information: %s", e)
      );
      // This is catching any other exceptions that may occur and logging them.
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An error occurred during Login: %s", e)
      );
    }
    boolean isValid = true;
    // This is checking if the user input matches the login information in the Login.txt file.
    if (comp.user.getText().stripTrailing().equals("")
        || !comp.user.getText().equals(Aes256.decrypt(user))
    ) {
      ErrorShake.startShake("Login");
      comp.user.setBackground(Color.decode("#967bb6"));
      isValid = false;
    }
    // This is checking if the password input matches the login information in the Login.txt file.
    char @NonNull [] password = Aes256.decrypt(pass).toCharArray();
    if (comp.pass.getPassword().length == 0
        || !Arrays.equals(comp.pass.getPassword(), password)
    ) {
      ErrorShake.startShake("Login");
      comp.pass.setBackground(Color.decode("#967bb6"));
      isValid = false;
    }
    // Writing the current time in milliseconds to a file called loggedInBefore.txt.
    try (FileWriter loggedInBefore = new FileWriter("./cfg/loggedInBefore.txt")) {
      Path path = Paths.get("./cfg/loggedInBefore.txt");
      loggedInBefore.write(String.valueOf(System.currentTimeMillis()));
      // Hiding the file loggedInBefore.txt.
      Files.setAttribute(path, "dos:hidden", true);
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(
          Level.SEVERE,
          String.format("An error occurred while remembering Login: %s", e)
      );
    }
    // Returning the boolean value isValid.
    return isValid;
  }
}

