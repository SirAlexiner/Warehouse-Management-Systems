package warehouse.gui.construction;

import java.util.logging.Level;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.construction.contents.boundaries.AboutBounds;
import warehouse.gui.construction.contents.boundaries.DeleteBounds;
import warehouse.gui.construction.contents.boundaries.EditCategoryBounds;
import warehouse.gui.construction.contents.boundaries.EditProductBounds;
import warehouse.gui.construction.contents.boundaries.HelpBounds;
import warehouse.gui.construction.contents.boundaries.LoginBounds;
import warehouse.gui.construction.contents.boundaries.NewCategoryBounds;
import warehouse.gui.construction.contents.boundaries.NewProductBounds;
import warehouse.gui.construction.contents.boundaries.OrderBounds;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * It's a class that sets the boundaries of the GUI.
 */
@UtilityClass
public class Bounds {

  /**
   * It sets the bounds of the GUI based on the window that is passed in.
   *
   * @param window The window that you want to set the bounds for.
   */
  public void setBounds(GuiWindows window) {
    switch (window) {
      case LOGIN -> LoginBounds.setLoginBounds();
      case NEW_PRODUCT -> NewProductBounds.setNewProductBounds();
      case NEW_CATEGORY -> NewCategoryBounds.setNewCategoryBounds();
      case EDIT_CATEGORY -> EditCategoryBounds.setEditCategoryBounds();
      case ORDER -> OrderBounds.setOrderBounds();
      case EDIT_PRODUCT -> EditProductBounds.setEditProductBounds();
      case DELETE_PRODUCT, DELETE_CATEGORY -> DeleteBounds.setDeleteBounds(window);
      case HELP -> HelpBounds.setHelpBounds();
      case ABOUT -> AboutBounds.setAboutBounds();
      default -> ErrorLogger.LOGGER.log(Level.WARNING, "Unable to find boundaries for GUI");
    }
  }
}
