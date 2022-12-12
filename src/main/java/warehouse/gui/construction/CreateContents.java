package warehouse.gui.construction;

import java.util.logging.Level;
import javax.swing.JPanel;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.construction.contents.CreateAbout;
import warehouse.gui.construction.contents.CreateDelete;
import warehouse.gui.construction.contents.CreateEditCategory;
import warehouse.gui.construction.contents.CreateHelp;
import warehouse.gui.construction.contents.CreateLogin;
import warehouse.gui.construction.contents.CreateMain;
import warehouse.gui.construction.contents.CreateNewCategory;
import warehouse.gui.construction.contents.CreateNewEditProduct;
import warehouse.gui.construction.contents.CreateOrder;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * It creates the content panel for the GUI.
 */
@UtilityClass
public class CreateContents {

  /**
   * It creates a panel for the given window.
   *
   * @param panel The panel that the content will be added to.
   * @param window The window that is being created.
   */
  public void createContentPanel(JPanel panel, GuiWindows window) {
    switch (window) {
      case LOGIN -> CreateLogin.constructLoginPanel(panel);
      case MAIN -> CreateMain.constructMainPanel(panel);
      case NEW_PRODUCT, EDIT_PRODUCT -> CreateNewEditProduct.constructNewEditProductPanel(
          panel,
          window
      );
      case NEW_CATEGORY -> CreateNewCategory.constructNewCategoryPanel(panel);
      case EDIT_CATEGORY -> CreateEditCategory.constructEditCategoryPanel(panel);
      case DELETE_CATEGORY, DELETE_PRODUCT -> CreateDelete.constructDeletePanel(panel, window);
      case ORDER -> CreateOrder.constructOrderPanel(panel);
      case HELP -> CreateHelp.constructHelpPanel(panel);
      case ABOUT -> CreateAbout.constructAboutPanel(panel);
      default -> ErrorLogger.LOGGER.log(Level.WARNING, "Unable to find panel for GUI");
    }
  }
}

