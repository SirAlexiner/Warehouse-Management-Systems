package warehouse.inventory.utilities;

import java.util.Locale;
import java.util.logging.Level;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.StringTitle;

/**
 * It takes the user input from the GUI and formats it into an array of objects.
 */
@UtilityClass
public class FormatProductDetails {

  /**
   * It takes the user input from the GUI
   * and returns an array of objects that can be used to create a product.
   *
   * @return An array of objects.
   */
  public Object[] constructProduct() {
    // Getting the components from the GUI.
    Components comp = Components.getComponents();
    // Creating an array of objects.
    Object[] product = new Object[10];
    // Getting the selected item from the dropdown menu.
    String category = String.valueOf(comp.categorySelect.getSelectedItem());
    String origin = String.valueOf(comp.productOrigin.getSelectedItem());
    // Getting the quantity of the product from the GUI.
    double quantity = Double.parseDouble(comp.productQuantity.getText());
    // Declaring the variables.
    double height;
    double width;
    double length;
    // Assigning the category to the first index of the array.
    product[0] = category;
    // Assigning the origin of the product to the second index of the array.
    product[1] = origin;
    // Getting the product name from the GUI and converting it to title case.
    String productName = StringTitle.toTitleCase(comp.productName.getText()).stripTrailing();
    // Assigning the product name to the third index of the array.
    product[2] = productName;
    // Getting the text from the manufacturer text field and converting it to title case.
    String manufacturer = StringTitle.toTitleCase(
        comp.productManufacturer.getText()
    ).stripTrailing();
    // Assigning the manufacturer to the fourth index of the array.
    product[3] = manufacturer;
    // Setting the default locale to US.
    Locale.setDefault(Locale.US);
    // Getting the price of the product from the GUI and converting it to a double.
    double price = Double.parseDouble(comp.productPrice.getText());
    // Formatting the price of the product to two decimal places
    // and then adding the currency to the end of the price.
    String priceFormatted = String.format("%.2f", price);
    String currency = String.valueOf(comp.priceCurrency.getSelectedItem());
    // Assigning the priceFormatted to the fifth index of the array.
    product[4] = priceFormatted + " " + currency;
    // Initializing the array with empty strings.
    product[5] = "";
    String color = "";
    product[6] = color;
    product[7] = "";
    product[8] = "";
    product[9] = "";
    // Checking if the weight is required for the product.
    // if it is, then get the quantity of the product and the unit of measurement from the GUI
    // and then assign it to the array.
    // if it isn't, then cast the double quantity to an integer.
    // and assign it to the array.
    boolean weightSelected = comp.weightReq.isSelected();
    if (weightSelected) {
      product[5] = quantity + " " + comp.weightSelect.getSelectedItem();
    } else {
      product[5] = (int) quantity;
    }
    // Checking if the color is required for the product.
    // if it is, then assign the selected item from the color dropdown menu to the array.
    boolean colorSelected = comp.colorReq.isSelected();
    if (colorSelected) {
      product[6] = comp.colorSelect.getSelectedItem();
    }
    boolean dimensionsSelected = comp.dimensionsReq.isSelected();
    // Checking if the dimensions are required for the product.
    // If they are, then it gets the unit of measurement from the GUI and assigns it to a variable.
    // Then it checks the unit of measurement and assigns the correct abbreviation to the variable.
    if (dimensionsSelected) {
      String unit = String.valueOf(comp.productUnit.getSelectedItem());
      switch (unit) {
        case "Meter" -> unit = "m";
        case "Centimeter" -> unit = "cm";
        case "Millimeter" -> unit = "mm";
        case "Inches" -> unit = "in";
        case "Feet" -> unit = "ft";
        default -> ErrorLogger.LOGGER.log(Level.WARNING, "Unable to find unit of measurement");
      }
      // Getting the height, length and width of the product from the GUI
      // and converting it to a double.
      height = Double.parseDouble(comp.productHeight.getText());
      length = Double.parseDouble(comp.productLength.getText());
      width = Double.parseDouble(comp.productWidth.getText());
      // Assigning the height, length and width of the product to the array.
      product[7] = height + " " + unit;
      product[8] = length + " " + unit;
      product[9] = width + " " + unit;
    }
    // Returning the array of objects.
    return product;
  }
}
