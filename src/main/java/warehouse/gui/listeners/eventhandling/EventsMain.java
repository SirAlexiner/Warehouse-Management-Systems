package warehouse.gui.listeners.eventhandling;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import lombok.experimental.UtilityClass;
import warehouse.gui.CreateGui;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.construction.SpreadsheetMenu;
import warehouse.gui.listeners.ResizeListener;
import warehouse.gui.utilities.Components;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * It sets up all the events for the main window.
 */
@UtilityClass
public class EventsMain {

  /**
   * It sets the events for the main window.
   */
  public void setEventsMain() {
    // Getting the main window.
    JFrame main = CreateGuiFrame.getMain();
    // Getting the components from the main window.
    Components comp = Components.getComponents();
    // Adding a listener to the main window,
    // so that when the window is resized, the spreadsheet is resized too.
    main.getContentPane().addComponentListener(new ResizeListener());
    // Adding an action listener to the buttons.
    comp.newProduct.addActionListener(e -> CreateGui.createGuiWindow(GuiWindows.NEW_PRODUCT));
    comp.newCategory.addActionListener(e -> CreateGui.createGuiWindow(GuiWindows.NEW_CATEGORY));
    comp.editCategory.addActionListener(e -> CreateGui.createGuiWindow(GuiWindows.EDIT_CATEGORY));
    comp.deleteCategory.addActionListener(e -> CreateGui.createGuiWindow(
        GuiWindows.DELETE_CATEGORY
    ));
    comp.help.addActionListener(e -> CreateGui.createGuiWindow(GuiWindows.HELP));
    comp.about.addActionListener(e -> CreateGui.createGuiWindow(GuiWindows.ABOUT));
    comp.searchCategory.addActionListener(e -> SearchUpdate.updateDoc());
    //
    // A key listener for the search category text field.
    comp.searchCategory.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // Auto Stub
      }

      @Override
      public void keyPressed(KeyEvent e) {
        // Checking if the key pressed is not a letter, up, down or escape.
        if ((!comp.searchCategory.isFocusOwner()
            || e.getKeyCode() != KeyEvent.VK_UP)
            && e.getKeyCode() != KeyEvent.VK_DOWN
            && e.getKeyCode() != KeyEvent.VK_ESCAPE
            && !Character.isLetter(e.getKeyCode())
        ) {
          e.consume();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // Auto Stub
      }
    });
    // Adding a document listener to the search name text field.
    comp.searchName.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        SearchUpdate.updateDoc();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        SearchUpdate.updateDoc();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        SearchUpdate.updateDoc();
      }
    });
    // Adding a mouse listener to the spreadsheet,
    // so that when the user double-clicks on a cell, a menu appears.
    comp.spreadsheet.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
          JPopupMenu spreadsheetMenu = SpreadsheetMenu.getSpreadsheetMenu();
          spreadsheetMenu.show(e.getComponent(), e.getX(), e.getY());
        }
      }
    });
    // A key listener for the spreadsheet.
    comp.spreadsheet.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // Auto Stub
      }

      @Override
      public void keyPressed(KeyEvent e) {
        // Checking if the user pressed the enter key and if the user selected a row.
        // If the user did,
        // then it consumes the event, so that the spreadsheet doesn't move to the next row.
        // Then it gets the spreadsheet menu and shows it at the position of the selected cell.
        if (e.getKeyChar() == KeyEvent.VK_ENTER && comp.spreadsheet.getSelectedRow() >= 0) {
          e.consume();
          JPopupMenu spreadsheetMenu = SpreadsheetMenu.getSpreadsheetMenu();
          int row = comp.spreadsheet.getSelectedRow();
          int col = comp.spreadsheet.getSelectedColumn();
          Rectangle r = comp.spreadsheet.getCellRect(row, col, false);
          spreadsheetMenu.show(e.getComponent(), 100, r.y + 20);
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // Auto Stub
      }
    });
  }
}
