package warehouse.gui.utilities;

import java.util.logging.Level;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.inventory.initializers.ProductList;
import warehouse.inventory.utilities.Product;

/**
 * It takes the selected product from the JTable,
 * and autofills the input fields with the product's information.
 */
@UtilityClass
public class AutoFillEdit {

  /**
   * It takes the selected product from the JTable,
   * and uses the product ID to find the product in the HashMap.
   * It then uses the getter methods of the product to autofill the input fields.
   */
  public void fillEditInputFields() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Getting the product ID from the JTable.
    String key = String.valueOf(
        comp.spreadsheet.getValueAt(comp.spreadsheet.getSelectedRow(), 0)
    );
    // Getting the product from the HashMap using the product ID.
    Product product = ProductList.getProducts().get(key);
    comp.productSelect.setText(key);
    comp.categorySelect.setSelectedItem(product.getProductInfo()[0]);
    comp.categorySelect.setEnabled(true);
    comp.productOrigin.setSelectedItem(product.getProductInfo()[1]);
    comp.productName.setText(String.valueOf(product.getProductInfo()[2]));
    comp.productManufacturer.setText(String.valueOf(product.getProductInfo()[3]));
    String priceCurrency = String.valueOf(product.getProductInfo()[4]);
    String price = priceCurrency.split(" ")[0];
    String currency = priceCurrency.split(" ")[1];
    comp.productPrice.setText(price);
    comp.productPrice.setEnabled(true);
    comp.priceCurrency.setSelectedItem(currency);
    comp.priceCurrency.setEnabled(true);
    String quantityWeight = String.valueOf(product.getProductInfo()[5]);
    // Checking if the quantityWeight string contains any letters.
    // If it does, it splits the string into two strings, one containing the quantity,
    // and the other containing the weight.
    // If it doesn't, it sets the quantityWeight string as the quantity.
    if (quantityWeight.matches(".*[a-zA-Z]")) {
      String quantity = quantityWeight.split(" ")[0];
      String weight = quantityWeight.split(" ")[1];
      comp.productQuantity.setText(quantity);
      comp.productQuantity.setEnabled(true);
      comp.weightSelect.setSelectedItem(weight);
      comp.weightReq.setSelected(true);
    } else {
      comp.productQuantity.setText(quantityWeight);
      comp.productQuantity.setEnabled(true);
      comp.weightSelect.setSelectedItem("");
    }
    // Checking if the product has a color.
    // If it does, it sets the color in the colorSelect JComboBox.
    if (!String.valueOf(product.getProductInfo()[6]).equals("")) {
      comp.colorSelect.setSelectedItem(String.valueOf(product.getProductInfo()[6]));
      comp.colorReq.setSelected(true);
    }
    // Checking if the product has a dimension.
    // If it does, it sets the dimensions in the input fields.
    if (!product.getProductInfo()[7].equals("")) {
      comp.dimensionsReq.setSelected(true);
      comp.productHeight.setText(String.valueOf(product.getProductInfo()[7]).split(" ")[0]);
      comp.productLength.setText(String.valueOf(product.getProductInfo()[8]).split(" ")[0]);
      comp.productWidth.setText(String.valueOf(product.getProductInfo()[9]).split(" ")[0]);
      String unit = String.valueOf(product.getProductInfo()[9]).split(" ")[1];
      // Checking the unit of measurement, and setting the unit in the JComboBox.
      switch (unit) {
        case "m" -> comp.productUnit.setSelectedItem("Meter");
        case "cm" -> comp.productUnit.setSelectedItem("Centimeter");
        case "mm" -> comp.productUnit.setSelectedItem("Millimeter");
        case "in" -> comp.productUnit.setSelectedItem("Inches");
        case "ft" -> comp.productUnit.setSelectedItem("Feet");
        default -> ErrorLogger.LOGGER.log(Level.WARNING, "Unable to collect unit of measurement");
      }
    }
    // enable the newProductBtn button.
    comp.newProductBtn.setEnabled(true);
  }
}
