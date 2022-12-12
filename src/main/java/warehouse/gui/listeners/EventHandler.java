package warehouse.gui.listeners;

import java.util.logging.Level;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.listeners.eventhandling.EventsDeleteCategory;
import warehouse.gui.listeners.eventhandling.EventsDeleteProduct;
import warehouse.gui.listeners.eventhandling.EventsEditCategory;
import warehouse.gui.listeners.eventhandling.EventsEditProduct;
import warehouse.gui.listeners.eventhandling.EventsLogin;
import warehouse.gui.listeners.eventhandling.EventsMain;
import warehouse.gui.listeners.eventhandling.EventsNewCategory;
import warehouse.gui.listeners.eventhandling.EventsNewProduct;
import warehouse.gui.listeners.eventhandling.EventsOrder;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * It's a class that adds events to the GUI.
 */
@UtilityClass
public class EventHandler {

  /**
   * It takes a GuiWindows enum and then calls the appropriate setEvents function for that enum.
   *
   * @param window The window you want to add events to.
   */
  public void addEvent(GuiWindows window) {
    switch (window) {
      case LOGIN -> EventsLogin.setEventsLogin();
      case MAIN -> EventsMain.setEventsMain();
      case NEW_PRODUCT -> EventsNewProduct.setEventsNewProduct();
      case EDIT_PRODUCT -> EventsEditProduct.setEventsEditProduct();
      case ORDER -> EventsOrder.setEventsOrder();
      case DELETE_PRODUCT -> EventsDeleteProduct.setEventsDeleteProduct();
      case NEW_CATEGORY -> EventsNewCategory.setEventsNewCategory();
      case EDIT_CATEGORY -> EventsEditCategory.setEventsEditCategory();
      case DELETE_CATEGORY -> EventsDeleteCategory.setEventsDeleteCategory();
      default -> ErrorLogger.LOGGER.log(Level.WARNING, "Unable to find GUI");
    }
  }
}
