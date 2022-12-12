package warehouse.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.AppTheme;
import warehouse.gui.utilities.Components;
import warehouse.gui.windowsenum.GuiWindows;
import warehouse.inventory.initializers.ProductList;
import warehouse.inventory.utilities.ProductToSpreadsheet;
import warehouse.inventory.utilities.persistence.CategoriesFromFile;
import warehouse.inventory.utilities.persistence.CountriesFromFile;
import warehouse.inventory.utilities.persistence.ProductsFromFile;

/**
 * The CreateGui class is used to create the main GUI and the different popup windows.
 */
@UtilityClass
public class CreateGui {

  /**
   * When the Alt key is pressed, the first menu item is selected and opened.
   *
   * @param frame The JFrame to add the Alt Menu Key to.
   */
  private void setupAltMenuKey(final JFrame frame) {
    Action menuAction = new AbstractAction() {

      @Override
      public void actionPerformed(ActionEvent e) {
        Components comp = Components.getComponents();
        JMenuBar menuBar = comp.menu;
        JMenu menu = menuBar.getMenu(0);
        menu.requestFocusInWindow();
        menu.doClick();
      }
    };
    JRootPane rootPane = frame.getRootPane();
    ActionMap actionMap = rootPane.getActionMap();
    final String menuActionKey = "expandMenu";
    actionMap.put(menuActionKey, menuAction);
    InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, 0, true), menuActionKey);
  }

  /**
   * It adds an action to the root pane of the dialog
   * that will expand the menu when the user presses the Alt key.
   *
   * @param frame The JDialog that you want to add the Alt+Menu shortcut to.
   */
  private void setupAltMenuKey(final JDialog frame) {
    Action menuAction = new AbstractAction() {

      @Override
      public void actionPerformed(ActionEvent e) {
        Components comp = Components.getComponents();
        JMenuBar menuBar = comp.productMenu;
        JMenu menu = menuBar.getMenu(0);
        menu.doClick();
      }
    };
    JRootPane rootPane = frame.getRootPane();
    ActionMap actionMap = rootPane.getActionMap();
    final String menuActionKey = "expandMenu";
    actionMap.put(menuActionKey, menuAction);
    InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, 0, true), menuActionKey);
  }

  /**
   * It creates a new GUI window, and if it's the Main window,
   * it also loads the data from the files in a separate thread.
   *
   * @param window The window to be created.
   */
  public void createGuiWindow(GuiWindows window) {
    if (window.equals(GuiWindows.LOGIN) || window.equals(GuiWindows.MAIN)) {
      JFrame frame = CreateGuiFrame.getFrame(window);
      // It sets the theme of the frame.
      AppTheme.setTheme(frame);
      // It adds an action to the root pane of the frame
      // that will expand the menu when the user presses the Alt key.
      setupAltMenuKey(frame);
      Thread a = new Thread(() -> CreateGuiFrame.show(frame));
      a.start();
      Thread b = new Thread(() -> {
        // It checks if the window is the main window.
        if (window.equals(GuiWindows.MAIN)) {
          // It loads the data from the files.
          loadDataFromFile();
        }
      });
      b.start();
    } else {
      JDialog frame = CreateGuiFrame.getDialog(window);
      // It checks if the window is the New Product or Edit Product window,
      // and if it is, it adds an action to the root pane of the dialog
      // that will expand the menu when the user presses the Alt key.
      if (window.equals(GuiWindows.NEW_PRODUCT) || window.equals(GuiWindows.EDIT_PRODUCT)) {
        setupAltMenuKey(frame);
      }
      // It shows the dialog.
      CreateGuiFrame.show(frame);
    }
  }

  /**
   * It checks if the file exists, and if it does, it loads the data from the file.
   */
  private static void loadDataFromFile() {
    // It loads the countries from the file.
    CountriesFromFile.getCountries();
    // It checks if the category file exists, and if it does, it loads the data from the file.
    File categories = new File("./cfg/Categories.txt");
    if (categories.exists() && !categories.isDirectory()) {
      CategoriesFromFile.getCategories();
    }
    // It checks if the products file exists, and if it does, it loads the data from the file.
    File products = new File("./cfg/Products.txt");
    if (products.exists() && !products.isDirectory()) {
      ProductsFromFile.getProducts();
    }
    // It checks if there are any products in the list,
    // and if there are, it refreshes the spreadsheet.
    if (ProductList.getProducts().size() > 0) {
      ProductToSpreadsheet.refreshSpreadsheet();
    }
  }
}
