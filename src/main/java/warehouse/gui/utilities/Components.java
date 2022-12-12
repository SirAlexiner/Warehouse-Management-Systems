package warehouse.gui.utilities;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * The Components class is used to store all the GUI components used in the application.
 */
public class Components {
  private static Components singleInstance = null;
  public final String[] columnNames = new String[]{
      "Product ID", "Product Category", "Country of Origin", "Product Name", "Manufacturer",
      "Price", "Quantity / Weight", "Color", "Height", "Length", "Width"
  };
  public String[] categoryArray = new String[]{};
  public String[] productCountries = new String[]{};
  public final String[] productCurrency = new String[]{
      "", "USD", "EURO", "NOK", "SEK", "DKK", "GBP"
  };
  public final String[] productWeight = new String[]{"", "Kg", "Hg", "g", "lb", "st"};
  public final String[] productColor = new String[]{
      "", "Red", "Orange", "Yellow", "Green", "Blue",
      "Purple", "Pink", "Brown", "Light Grey", "Dark Grey",
      "Black", "White", "Multicolor",
      "Metallic", "Rose  Gold", "Gold", "Silver", "Bronze",
  };
  public final String[] productUnitArray = new String[]{
      "", "Meter", "Centimeter", "Millimeter", "Inches", "Feet"
  };
  public Object[] spreadsheetData = null;
  /*------------------------------------------*/
  // LOGIN GUI
  public JLabel logoLabel = null;
  public JLabel loginNameLabel = null;
  public JLabel userLabel = null;
  public JTextField user = null;
  public JLabel passLabel = null;
  public JPasswordField pass = null;
  public JButton loginBtn = null;
  /*------------------------------------------*/
  // MAIN GUI
  public JMenuBar menu = null;
  public JMenuItem newProduct = null;
  public JMenuItem newCategory = null;
  public JMenuItem editCategory = null;
  public JMenuItem deleteCategory = null;
  public JMenuItem help = null;
  public JMenuItem about = null;
  public Boolean searchActive = null;
  public JLabel searchCategoryLabel = null;
  public JComboBox<Object> searchCategory = null;
  public JLabel searchNameLabel = null;
  public JTextField searchName = null;
  public JTable spreadsheet = null;
  public DefaultTableModel spreadsheetModel = null;
  public TableRowSorter<TableModel> rowSorter = null;
  public JScrollPane scrollPane = null;
  public JLabel spreadsheetSize = null;
  public JProgressBar spreadsheetProgress = null;
  /*------------------------------------------*/
  // NEW/EDIT PRODUCT GUI
  public JMenuBar productMenu = null;
  public JLabel productSelectLabel = null;
  public JTextField productSelect = null;
  public JLabel categorySelectLabel = null;
  public JComboBox<Object> categorySelect = null;
  public JLabel productOriginLabel = null;
  public JComboBox<Object> productOrigin = null;
  public JLabel productNameLabel = null;
  public JTextField productName = null;
  public JLabel productManufacturerLabel = null;
  public JTextField productManufacturer = null;
  public JLabel productPriceLabel = null;
  public JTextField productPrice = null;
  public JLabel priceCurrencyLabel = null;
  public JComboBox<Object> priceCurrency = null;
  public JLabel productQuantityLabel = null;
  public JTextField productQuantity = null;
  public JCheckBox weightReq = null;
  public JComboBox<Object> weightSelect = null;
  public JCheckBox colorReq = null;
  public JComboBox<Object> colorSelect = null;
  public JCheckBox dimensionsReq = null;
  public JLabel heightLabel = null;
  public JTextField productHeight = null;
  public JLabel widthLabel = null;
  public JTextField productWidth = null;
  public JLabel lengthLabel = null;
  public JTextField productLength = null;
  public JLabel productUnitLabel = null;
  public JComboBox<String> productUnit = null;
  public JButton newProductBtn = null;
  /*------------------------------------------*/
  // ADD CATEGORY GUI
  public JTextField addCategory = null;
  public JButton addCategoryBtn = null;
  /*------------------------------------------*/
  // EDIT CATEGORY GUI
  public JLabel categoryRenameLabel = null;
  public JComboBox<Object> categoryRenameSelect = null;
  public JLabel newCategoryNameLabel = null;
  public JTextField newCategoryName = null;
  public JLabel renameCategoryWarningLabel = null;
  public JButton renameCategoryBtn = null;
  /*------------------------------------------*/
  // DELETE PRODUCT/CATEGORY GUI
  public JLabel deleteProductLabel = null;
  public JTextField deleteProduct = null;
  public JComboBox<Object> deleteCategorySelect = null;
  public JLabel deleteItemWarningLabel = null;
  public JButton deleteItemBtn = null;
  /*------------------------------------------*/
  // ORDER GUI
  public JLabel orderProductLabel = null;
  public JTextField orderProduct = null;
  public JLabel orderProductQntLabel = null;
  public JTextField orderProductQnt = null;
  public JButton orderProductBtn = null;
  /*------------------------------------------*/
  // HELP GUI
  public JLabel helpLabel = null;
  public JScrollPane scrollHelp = null;
  /*------------------------------------------*/
  // ABOUT GUI
  public JLabel picLabel = null;
  public JLabel aboutLabel = null;

  /**
   * If the singleInstance variable is null, create a new Components object
   * and assign it to the singleInstance variable.
   * Otherwise, return the Components object that's already in the singleInstance variable
   *
   * @return The singleInstance of the Components class.
   */
  public static Components getComponents() {
    if (singleInstance == null) {
      singleInstance = new Components();
    }
    return singleInstance;
  }
}

