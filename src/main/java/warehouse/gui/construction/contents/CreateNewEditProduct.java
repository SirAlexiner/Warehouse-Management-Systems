package warehouse.gui.construction.contents;

import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lombok.experimental.UtilityClass;
import warehouse.gui.listeners.NumericListener;
import warehouse.gui.utilities.AutoFillEdit;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.MenuButton;
import warehouse.gui.windowsenum.GuiWindows;

/**
 * This class is responsible for constructing the GUI for the New Product and Edit Product windows.
 */
@UtilityClass
public class CreateNewEditProduct {

  /**
   * This function constructs the GUI for the New Product and Edit Product windows.
   *
   * @param panel The JPanel that the components will be added to.
   * @param window The window that the panel is being constructed for.
   */
  public void constructNewEditProductPanel(JPanel panel, GuiWindows window) {
    // Declaring a constant string that will be used as a tooltip for the numeric fields.
    final String toolTipNumeric = "Please Enter a Numeric Value";
    final String dimensions = "dimensions";
    // Getting the Components class and assigning it to the variable comp.
    Components comp = Components.getComponents();
    // Creating a menu bar and adding two buttons to it.
    comp.productMenu = new JMenuBar();
    comp.productMenu.add(new MenuButton("New Category"));
    comp.productMenu.add(new MenuButton("Help"));
    // This is checking if the window is the Edit Product window.
    // If it is, then it will add a label and text field for the product ID.
    if (window.equals(GuiWindows.EDIT_PRODUCT)) {
      comp.productSelectLabel = new JLabel("Product ID");
      comp.productSelect = new JTextField(5);
    }
    // Creating a label and a combo box for the category of the product.
    comp.categorySelectLabel = new JLabel("Category");
    comp.categorySelect = new JComboBox<>(comp.categoryArray);
    comp.categorySelect.setToolTipText("Please Select the Product Category");
    // Creating a label and a text field for the product name.
    comp.productNameLabel = new JLabel("Product Name");
    comp.productName = new JTextField(5);
    comp.productName.setToolTipText("Please Provide the Product Name");
    // Creating a label and a combo box for the country of origin of the product.
    comp.productOriginLabel = new JLabel("Country of Origin");
    // Creating a new JComboBox and adding the contents of the productCountries array to it.
    comp.productOrigin = new JComboBox<>(
        // Sorting the contents of the productCountries array and then converting it to an array.
        Arrays.stream(comp.productCountries).sorted().toArray()
    );
    comp.productOrigin.setToolTipText("Please Select the Country of Origin");
    // Creating a label and a text field for the product manufacturer.
    comp.productManufacturerLabel = new JLabel("Product Manufacturer");
    comp.productManufacturer = new JTextField(5);
    comp.productManufacturer.setToolTipText("Please Enter the Product Manufacturer");
    // Creating a label and a text field for the product price.
    comp.productPriceLabel = new JLabel("Product Price");
    comp.productPrice = new JTextField(5);
    comp.productPrice.setName("Price");
    // Adding a key listener to the product price text field.
    comp.productPrice.addKeyListener(new NumericListener());
    comp.productPrice.setToolTipText(toolTipNumeric);
    comp.priceCurrencyLabel = new JLabel("Currency");
    // Creating a new JComboBox and adding the contents of the productCurrency array to it.
    comp.priceCurrency = new JComboBox<>(
        Arrays.stream(comp.productCurrency).sorted().toArray()
    );
    comp.priceCurrency.setToolTipText("Please Select the Currency");
    // Creating a new JCheckBox and setting it to false.
    comp.weightReq = new JCheckBox("Weight?");
    comp.weightReq.setSelected(false);
    // This is creating a label and a text field for the product quantity.
    comp.productQuantityLabel = new JLabel("Weight / Quantity");
    comp.productQuantity = new JTextField(5);
    comp.productQuantity.addKeyListener(new NumericListener());
    comp.productQuantity.setName("weight");
    comp.productQuantity.setToolTipText(toolTipNumeric);
    // This is creating a new JComboBox and adding the contents of the productWeight array to it.
    comp.weightSelect = new JComboBox<>(comp.productWeight);
    comp.weightSelect.setToolTipText("If Applicable Please Enter the Unit of Weight");
    // Creating a new JCheckBox and setting the text to "Color?".
    comp.colorReq = new JCheckBox("Color?");
    // Creating a new JComboBox and adding the contents of the productColor array to it.
    comp.colorSelect = new JComboBox<>(
        Arrays.stream(comp.productColor).sorted().toArray()
    );
    comp.colorSelect.setToolTipText("Please Select the Product Color");
    // This is creating a new JCheckBox and setting the text to "Dimensions?".
    comp.dimensionsReq = new JCheckBox("Dimensions?");
    comp.dimensionsReq.setSelected(false);
    // Creating a label and a text field for the product height.
    comp.heightLabel = new JLabel("Height");
    comp.productHeight = new JTextField(5);
    comp.productHeight.setName(dimensions);
    comp.productHeight.addKeyListener(new NumericListener());
    comp.productHeight.setToolTipText(toolTipNumeric);
    // Creating a label and a text field for the product length.
    comp.lengthLabel = new JLabel("Length");
    comp.productLength = new JTextField(5);
    comp.productLength.setName(dimensions);
    comp.productLength.addKeyListener(new NumericListener());
    comp.productLength.setToolTipText(toolTipNumeric);
    // Creating a label and a text field for the product width.
    comp.widthLabel = new JLabel("Width");
    comp.productWidth = new JTextField(5);
    comp.productWidth.setName(dimensions);
    comp.productWidth.addKeyListener(new NumericListener());
    comp.productWidth.setToolTipText(toolTipNumeric);
    // This is creating a label and a combo box for the unit of measurement of the product.
    comp.productUnitLabel = new JLabel("Unit of Measurement");
    comp.productUnit = new JComboBox<>(comp.productUnitArray);
    comp.productUnit.setToolTipText("Please Select the Unit of Measurement");
    // This is checking if the window is the Edit Product window.
    // if it is, then it will create a new JButton and set the text to "Commit Changes".
    // if it is not, it will create a new JButton and set the text to "Add / Order New Product".
    if (window.equals(GuiWindows.EDIT_PRODUCT)) {
      // Creating a new JButton and setting the text to "Commit Changes".
      comp.newProductBtn = new JButton("Commit Changes");
    } else {
      comp.newProductBtn = new JButton("Add / Order New Product");
    }
    // This is checking if the window is the Edit Product window.
    // If it is, then it will disable all the input fields and the add button.
    // It will also call the fillEditInputFields function from the AutoFillEdit class.
    if (window.equals(GuiWindows.EDIT_PRODUCT)) {
      comp.productSelect.setEnabled(false);
      comp.categorySelect.setEnabled(false);
      comp.productOrigin.setEnabled(false);
      comp.productName.setEnabled(false);
      comp.productManufacturer.setEnabled(false);
      comp.productPrice.setEnabled(false);
      comp.priceCurrency.setEnabled(false);
      comp.productQuantity.setEnabled(false);
      comp.weightReq.setEnabled(false);
      comp.dimensionsReq.setEnabled(false);
      comp.colorReq.setEnabled(false);
      comp.newProductBtn.setEnabled(false);
      AutoFillEdit.fillEditInputFields();
    }
    comp.weightSelect.setEnabled(false);
    comp.colorSelect.setEnabled(false);
    comp.productLength.setEnabled(false);
    comp.productHeight.setEnabled(false);
    comp.productWidth.setEnabled(false);
    comp.productUnit.setEnabled(false);
    // Adding the menu bar to the panel.
    panel.add(comp.productMenu);
    // This is checking if the window is the Edit Product window.
    // if it is, then it will add the productSelectLabel and productSelect to the panel.
    if (window.equals(GuiWindows.EDIT_PRODUCT)) {
      panel.add(comp.productSelectLabel);
      panel.add(comp.productSelect);
    }
    // Adding all the components to the panel.
    panel.add(comp.categorySelectLabel);
    panel.add(comp.categorySelect);
    panel.add(comp.productOriginLabel);
    panel.add(comp.productOrigin);
    panel.add(comp.productNameLabel);
    panel.add(comp.productName);
    panel.add(comp.productManufacturerLabel);
    panel.add(comp.productManufacturer);
    panel.add(comp.productPriceLabel);
    panel.add(comp.productPrice);
    panel.add(comp.priceCurrencyLabel);
    panel.add(comp.priceCurrency);
    panel.add(comp.productManufacturer);
    panel.add(comp.productManufacturer);
    panel.add(comp.productQuantityLabel);
    panel.add(comp.productQuantity);
    panel.add(comp.weightReq);
    panel.add(comp.weightSelect);
    panel.add(comp.colorReq);
    panel.add(comp.colorSelect);
    panel.add(comp.dimensionsReq);
    panel.add(comp.heightLabel);
    panel.add(comp.productHeight);
    panel.add(comp.lengthLabel);
    panel.add(comp.productLength);
    panel.add(comp.widthLabel);
    panel.add(comp.productWidth);
    panel.add(comp.productUnitLabel);
    panel.add(comp.productUnit);
    panel.add(comp.newProductBtn);
  }
}