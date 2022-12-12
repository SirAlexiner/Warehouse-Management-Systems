package warehouse.gui.construction.contents;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.MenuButton;

/**
 * It creates the main panel of the application.
 */
@UtilityClass
public class CreateMain {

  /**
   * It creates the main panel of the application.
   *
   * @param panel The JPanel that the components will be added to.
   */
  public void constructMainPanel(JPanel panel) {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Creating a new icon for the add button.
    FlatSVGIcon addIcon = new FlatSVGIcon("add.svg", 20, 20);
    // Creating a new menu item called "New" and adding a new menu item called "New Product" to it.
    JMenu newMenu = new JMenu("New");
    comp.newProduct = new JMenuItem("New Product");
    newMenu.add(comp.newProduct);
    comp.newProduct.setIcon(addIcon);
    // Creating a new menu item called "New Category" and adding it to the "New" menu.
    comp.newCategory = new JMenuItem("New Category");
    newMenu.add(comp.newCategory);
    comp.newCategory.setIcon(addIcon);
    // Creating a new icon for the edit button.
    FlatSVGIcon editIcon = new FlatSVGIcon("edit.svg", 20, 20);
    // Creating a new menu item called "Edit"
    // and adding a new menu item called "Rename Product Category" to it.
    JMenu editMenu = new JMenu("Edit");
    comp.editCategory = new JMenuItem("Rename Product Category");
    editMenu.add(comp.editCategory);
    comp.editCategory.setIcon(editIcon);
    // Creating a new icon for the delete button.
    FlatSVGIcon deleteIcon = new FlatSVGIcon("delete.svg", 20, 20);
    // Creating a new menu item called "Delete"
    // and adding a new menu item called "Delete Product Category" to it.
    JMenu deleteMenu = new JMenu("Delete");
    comp.deleteCategory = new JMenuItem("Delete Product Category");
    deleteMenu.add(comp.deleteCategory);
    comp.deleteCategory.setIcon(deleteIcon);
    // Creating a new icon for the help button.
    FlatSVGIcon helpIcon = new FlatSVGIcon("help.svg", 20, 20);
    // Creating a new menu item called "Help" and adding a new menu item called "Help" to it.
    JMenu helpMenu = new JMenu("Help");
    comp.help = new JMenuItem("Help");
    helpMenu.add(comp.help);
    comp.help.setIcon(helpIcon);
    // Creating a new icon for the info button.
    FlatSVGIcon infoIcon = new FlatSVGIcon("info.svg", 20, 20);
    // Creating a new menu item called "About" and adding it to the "Help" menu.
    comp.about = new JMenuItem("About");
    helpMenu.add(comp.about);
    comp.about.setIcon(infoIcon);
    // Creating a new menu bar and adding the menu items to it.
    comp.menu = new JMenuBar();
    comp.menu.add(newMenu);
    comp.menu.add(new MenuButton("Search"));
    comp.menu.add(editMenu);
    comp.menu.add(deleteMenu);
    comp.menu.add(helpMenu);
    // Creating a new JLabel called "Product Category:",
    // a new JComboBox called "comp.searchCategory" and adding the "comp.categoryArray" to it,
    // setting the tooltip text of the JComboBox
    // to "Please Select the Product Category to Search From",
    // creating a new JLabel called "Product Details: (Default: Name)"
    // and creating a new JTextField called "comp.searchName" with a size of 5.
    comp.searchCategoryLabel = new JLabel("Product Category:");
    comp.searchCategory = new JComboBox<>(comp.categoryArray);
    comp.searchCategory.setToolTipText("Please Select the Product Category to Search From");
    comp.searchNameLabel = new JLabel("Product Details: (Default: Name)");
    comp.searchName = new JTextField(5);
    // Creating a new DefaultTableModel called "comp.spreadsheetModel"
    // and adding the "comp.spreadsheetData" and "comp.columnNames" to it.
    // It is also overriding the getColumnClass method to return the class of the column.
    // If the column is 0, 5, 6 or greater than 7, it will return the Integer class.
    // Otherwise, it will return the String class.
    comp.spreadsheetModel = new DefaultTableModel(
        (Object[][]) comp.spreadsheetData, comp.columnNames
    ) {
      @Override

      public Class getColumnClass(int column) {
        if (column == 0 || column == 5 || column == 6 || column > 7) {
          return Integer.class;
        }
        return String.class;
      }
    };
    // Creating a new JTable called "comp.spreadsheet" and adding the "comp.spreadsheetModel" to it.
    // also overriding the isCellEditable method to return false.
    comp.spreadsheet = new JTable(comp.spreadsheetModel) {
      @Override

        public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    // Removing the border of the JTable.
    comp.spreadsheet.setBorder(BorderFactory.createEmptyBorder());
    // Setting the alignment of the text in the JTable to the right,
    // setting the border of the JTable to an empty border,
    // setting the default renderer of the JTable to the tableRenderer,
    // creating a new TableRowSorter and adding it to the JTable.
    DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
    tableRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
    tableRenderer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));
    comp.spreadsheet.setDefaultRenderer(String.class, tableRenderer);
    comp.spreadsheet.setDefaultRenderer(Integer.class, tableRenderer);
    comp.rowSorter = new TableRowSorter<>(comp.spreadsheet.getModel());
    comp.spreadsheet.setRowSorter(comp.rowSorter);
    // Creating a new JScrollPane called "comp.scrollPane" and adding the "comp.spreadsheet" to it.
    comp.scrollPane = new JScrollPane(
        comp.spreadsheet,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
    );
    // Removing the border of the JScrollPane.
    comp.scrollPane.setBorder(BorderFactory.createEmptyBorder());
    // Creating a new JLabel called "Number of items in system:",
    // a new JProgressBar called "comp.spreadsheetProgress" with a minimum value of 0
    // and a maximum value of 12000000,
    // setting the value of the JProgressBar to 0,
    // setting the foreground of the JProgressBar to a purple colour,
    // setting the string painted of the JProgressBar to true,
    // and setting the string of the JProgressBar to "0".
    comp.spreadsheetSize = new JLabel("Number of items in system:");
    comp.spreadsheetProgress = new JProgressBar(0, 12000000);
    comp.spreadsheetProgress.setValue(0);
    comp.spreadsheetProgress.setForeground(Color.decode("#967bb6"));
    comp.spreadsheetProgress.setStringPainted(true);
    comp.spreadsheetProgress.setString("0");
    // Adding the components to the panel.
    panel.add(comp.menu);
    panel.add(comp.scrollPane);
    panel.add(comp.searchNameLabel);
    panel.add(comp.searchName);
    panel.add(comp.searchCategoryLabel);
    panel.add(comp.searchCategory);
    panel.add(comp.spreadsheetSize);
    panel.add(comp.spreadsheetProgress);
  }
}
