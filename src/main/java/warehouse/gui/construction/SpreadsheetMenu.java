package warehouse.gui.construction;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import lombok.experimental.UtilityClass;
import warehouse.gui.CreateGui;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * It creates a popup menu for the spreadsheet.
 */
@UtilityClass
public class SpreadsheetMenu {

  /**
   * It creates a popup menu with three options, each with an icon.
   *
   * @return A JPopupMenu object.
   */
  public JPopupMenu getSpreadsheetMenu() {
    // It creates a new JPopupMenu object.
    JPopupMenu menu = new JPopupMenu();
    // It creates a new JMenuItem object with the name "Order" and an icon.
    JMenuItem order = new JMenuItem("Order");
    FlatSVGIcon addIcon = new FlatSVGIcon("add.svg", 20, 20);
    order.setIcon(addIcon);
    menu.add(order);
    // It creates a new JMenuItem object with the name "Edit" and an icon.
    JMenuItem editProduct = new JMenuItem("Edit");
    FlatSVGIcon editIcon = new FlatSVGIcon("edit.svg", 20, 20);
    editProduct.setIcon(editIcon);
    menu.add(editProduct);
    // It creates a new JMenuItem object with the name "Delete" and an icon.
    JMenuItem deleteProduct = new JMenuItem("Delete");
    FlatSVGIcon deleteIcon = new FlatSVGIcon("delete.svg", 20, 20);
    deleteProduct.setIcon(deleteIcon);
    menu.add(deleteProduct);
    // It adds an action listener to each menu item.
    order.addActionListener(e -> CreateGui.createGuiWindow(GuiWindows.ORDER));
    editProduct.addActionListener(e -> CreateGui.createGuiWindow(GuiWindows.EDIT_PRODUCT));
    deleteProduct.addActionListener(e -> CreateGui.createGuiWindow(GuiWindows.DELETE_PRODUCT));
    // It returns the menu object.
    return menu;
  }
}
