package warehouse.gui.utilities;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;
import warehouse.ErrorLogger;
import warehouse.gui.CreateGui;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * The MenuButton class is used to construct dev specific JMenus.
 * <hr>
 * The default JMenu component does not allow for ButtonListeners,
 * without consuming the MenuListener,
 * as such this class constructs a new JMenu that consumes the MenuListener,
 * and adds a ButtonListener.
 * This is done so some Menu Actions can be accessed without going into a submenu.
 *
 * @see JMenu
 */
public class MenuButton extends JMenu {
  // Getting the main JFrame from the CreateGuiFrame class.
  private static final JFrame main = CreateGuiFrame.getMain();
  // A constant that is used to check if the search button is pressed.
  private static final String SEARCH = "Search";
  // A constant that is used to check if the new category button is pressed.
  private static final String NEW_CATEGORY = "New Category";
  // A constant that is used to check if the help button is pressed.
  private static final String HELP = "Help";

  /** The MenuButton constructor is used to construct dev specific JMenu.
   *
   * @param name (String) Name and text of the button
   */
  public MenuButton(String name) {
    // It sets the text of the JMenu to the name of the button.
    setText(name);
    // Adding a MenuKeyListener to the JMenu,
    // so that when a key is pressed, the menuPressed method is called.
    addMenuKeyListener(new MenuKeyListener() {
      @Override
      public void menuKeyTyped(MenuKeyEvent e) {
        // Auto Stub
      }

      @Override
      public void menuKeyPressed(MenuKeyEvent e) {
        menuPressed(e);
      }

      @Override
      public void menuKeyReleased(MenuKeyEvent e) {
        // Auto Stub
      }
    });
    // Consuming the MenuListener, so that the JMenu can be used as a button.
    consumeMenuListener();
    // Adding a MouseListener to the JMenu,
    // so that when the mouse is clicked, the mousebuttonClicked method is called.
    addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        mousebuttonClicked();
      }

      @Override
      public void mousePressed(MouseEvent e) {
        // Auto Stub
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // Auto Stub
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        // Auto Stub
      }

      @Override
      public void mouseExited(MouseEvent e) {
        // Auto Stub
      }
    });
  }

  /**
   * If the button is the search button,
   * then check if the search window is open,
   * if it is, close it,
   * if it isn't, open it.
   * If the button is the new category button, open the new category window.
   * If the button is the help button, open the help window
   */
  private void mousebuttonClicked() {
    Components comp = Components.getComponents();
    switch (getText()) {
      case SEARCH -> {
        if (Boolean.TRUE.equals(comp.searchActive)) {
          closeSearch(comp);
        } else {
          openSearch(comp);
        }
      }
      case NEW_CATEGORY -> CreateGui.createGuiWindow(GuiWindows.NEW_CATEGORY);
      case HELP -> CreateGui.createGuiWindow(GuiWindows.HELP);
      default -> ErrorLogger.LOGGER.log(Level.WARNING, "JMenu has no ClickEvent");
    }
  }

  /**
   * If the user presses the enter key, open the menu that has focus.
   *
   * @param e The MenuKeyEvent that was triggered
   */
  private static void menuPressed(MenuKeyEvent e) {
    Components comp = Components.getComponents();
    JMenuBar menu;
    if (comp.productMenu != null && comp.productMenu.isShowing()) {
      menu = comp.productMenu;
    } else {
      menu = comp.menu;
    }
    JMenu focusedMenu = null;
    for (int i = 0; i < menu.getMenuCount(); i++) {
      if (menu.getMenu(i).isSelected()) {
        focusedMenu = menu.getMenu(i);
      }
    }
    enterSearch(e, comp, focusedMenu);
    if (e.getKeyChar() == KeyEvent.VK_ENTER && focusedMenu != null) {
      openMenuOnEnter(comp, focusedMenu);
    }
  }

  /**
   * If the user presses the enter key while the search menu is focused,
   * the search window is toggled.
   * If the user presses the enter key while the new category menu is focused,
   * the new category window is opened.
   * If the user presses the enter key while the help menu is focused,
   * the help window is opened.
   *
   * @param comp The Components class, which contains all the components of the GUI.
   * @param focusedMenu The menu that is currently focused.
   */
  private static void openMenuOnEnter(Components comp, JMenu focusedMenu) {
    switch (focusedMenu.getText()) {
      case SEARCH -> {
        if (Boolean.TRUE.equals(comp.searchActive)) {
          closeSearch(comp);
        } else {
          openSearch(comp);
        }
      }
      case NEW_CATEGORY -> CreateGui.createGuiWindow(GuiWindows.NEW_CATEGORY);
      case HELP -> {
        if (comp.productMenu != null && comp.productMenu.isShowing()) {
          CreateGui.createGuiWindow(GuiWindows.HELP);
        }
      }
      default -> {
        // DO NOTHING
      }
    }
  }

  /**
   * Consume the MenuListener to point at nothing.
   */
  private void consumeMenuListener() {
    addMenuListener(new MenuListener() {
      @Override
      public void menuSelected(MenuEvent e) {
        // Auto Stub
      }

      @Override
      public void menuDeselected(MenuEvent e) {
        // Auto Stub
      }

      @Override
      public void menuCanceled(MenuEvent e) {
       // Auto Stub
      }
    });
  }

  /**
   * It closes the search bar and resizes the spreadsheet to fit the window.
   *
   * @param comp The Components class
   */
  private static void closeSearch(Components comp) {
    SearchActive.setSearch(false);
    if (comp.searchName != null) {
      comp.searchName.setText(null);
    }
    if (comp.searchCategory != null) {
      comp.searchCategory.setSelectedItem("");
      comp.rowSorter.setRowFilter(null);
    }
    int windowWidth = main.getContentPane().getWidth();
    int windowHeight = main.getContentPane().getHeight();
    comp.spreadsheet.setBounds(0, 25, windowWidth, windowHeight - 55);
    comp.scrollPane.setBounds(-1, 25, windowWidth + 1, windowHeight - 55);
  }

  /**
   * It opens the search bar and then resizes the spreadsheet
   * and scrollpane components to fit the window.
   *
   * @param comp The Components class that contains the spreadsheet and scrollpane
   */
  private static void openSearch(Components comp) {
    SearchActive.setSearch(true);
    int windowWidth = main.getContentPane().getWidth();
    int windowHeight = main.getContentPane().getHeight();
    comp.spreadsheet.setBounds(0, 60, windowWidth, windowHeight - 90);
    comp.scrollPane.setBounds(-1, 60, windowWidth + 1, windowHeight - 90);
  }

  /**
   * If the user presses the down arrow key while the search menu is selected,
   * remove focus from the search menu and send it to the search window.
   *
   * @param e The MenuKeyEvent that was triggered
   * @param comp The Components class, which contains all the components of the GUI
   * @param focusedMenu The menu that is currently selected
   */
  private static void enterSearch(MenuKeyEvent e, Components comp, JMenu focusedMenu) {
    if (focusedMenu != null && e.getKeyCode() == KeyEvent.VK_DOWN
        && Boolean.TRUE.equals(comp.searchActive)
        && focusedMenu.getText().equals(SEARCH)) {
      focusedMenu.setSelected(false);
      comp.searchCategory.requestFocusInWindow();
    }
  }
}
