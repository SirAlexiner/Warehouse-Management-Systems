package warehouse.inventory.utilities;

import lombok.experimental.UtilityClass;
import warehouse.inventory.initializers.SupportedCountriesList;

/**
 * It takes in a bunch of product information and returns a product key.
 */
@UtilityClass
public class ProductKey {

  /**
   * "Given a product's origin, name, manufacturer, color, height, length, and width,
   * return the product's key."
   * The function is a bit long, but it's not too bad.
   * It's a bit hard to read, but it's not too bad.
   *
   * @param productOrigin The country of origin of the product.
   * @param productName The name of the product.
   * @param manufacturer The name of the manufacturer.
   * @param productColor The color of the product.
   * @param productHeight The height of the product in millimeters.
   * @param productLength The length of the product in millimeters.
   * @param productWidth The width of the product in millimeters.
   * @return A string.
   */
  public String getProductKey(
      Object productOrigin,
      Object productName,
      Object manufacturer,
      Object productColor,
      Object productHeight,
      Object productLength,
      Object productWidth
  ) {
    String regionCode = getRegionCode(productOrigin);
    String manufacturerIdCode = getManufacturerIdCode(manufacturer);
    String articleIdCode = getArticleIdCode(
        productName,
        productColor,
        productHeight,
        productLength,
        productWidth
    );
    String control = String.valueOf(String.valueOf(productOrigin).charAt(0));
    return regionCode + manufacturerIdCode + articleIdCode + control;
  }

  /**
   * It takes a string and returns the sum of the numeric values of each character in the string.
   *
   * @param string The string to be converted to a numeric value.
   * @return The sum of the numeric values of the characters in the string.
   */
  private int getNumericValue(String string) {
    int numericValue = 0;
    for (int j = 0; j < string.length(); j++) {
      try {
        numericValue += string.charAt(j);
      } catch (Exception e) {
        numericValue += Character.getNumericValue(string.charAt(j));
      }
    }
    return numericValue;
  }

  /**
   * If the country is supported, return the region code, otherwise return "00".
   *
   * @param productOrigin The country of origin of the product.
   * @return The region code for the country of origin of the product.
   */
  private String getRegionCode(Object productOrigin) {
    String regionCode;
    // It's converting the productOrigin object to a string, and then converting it to lowercase.
    String country = String.valueOf(productOrigin).toLowerCase();
    if (SupportedCountriesList.isCountrySupported(country)) {
      regionCode = SupportedCountriesList.getSupportedCountries().get(country);
    } else {
      regionCode = "00";
    }
    return regionCode;
  }

  /**
   * The function takes a manufacturer object as an argument
   * and returns a string of the manufacturer's id code.
   *
   * @param manufacturer The manufacturer of the product.
   * @return The manufacturerIdCode is being returned.
   */
  private String getManufacturerIdCode(Object manufacturer) {
    int manufacturerId = getNumericValue(String.valueOf(manufacturer));
    String manufacturerIdCode;
    if (manufacturerId > 9 && manufacturerId < 100) {
      manufacturerIdCode = "00" + manufacturerId;
    } else if (manufacturerId > 99 && manufacturerId < 1000) {
      manufacturerIdCode = "0" + manufacturerId;
    } else if (manufacturerId >= 1000) {
      manufacturerIdCode = String.valueOf(manufacturerId).replaceAll("\\w(?=\\w{4})", "");
    } else {
      manufacturerIdCode = "000" + manufacturerId;
    }
    return manufacturerIdCode;
  }

  /**
   * It takes 5 parameters,
   * converts them to strings,
   * gets their numeric values,
   * adds them up, and returns a string with leading zeros.
   *
   * @param productName The name of the product.
   * @param productColor The color of the product.
   * @param productHeight The height of the product in cm
   * @param productLength The length of the product in cm
   * @param productWidth The width of the product in cm.
   * @return The articleIdCode is being returned.
   */
  private String getArticleIdCode(
      Object productName,
      Object productColor,
      Object productHeight,
      Object productLength,
      Object productWidth
  ) {
    int articleId =
          getNumericValue(String.valueOf(productName))
        + getNumericValue(String.valueOf(productColor))
        + getNumericValue(String.valueOf(productHeight))
        + getNumericValue(String.valueOf(productLength))
        + getNumericValue(String.valueOf(productWidth));
    String articleIdCode;
    if (articleId > 9 && articleId < 100) {
      articleIdCode = "0000" + articleId;
    } else if (articleId > 99 && articleId < 1000) {
      articleIdCode = "000" + articleId;
    } else if (articleId > 999 && articleId < 10000) {
      articleIdCode = "00" + articleId;
    } else if (articleId > 9999 && articleId < 100000) {
      articleIdCode = "0" + articleId;
    } else if (articleId >= 100000) {
      articleIdCode = String.valueOf(articleId).replaceAll("\\w(?=\\w{6})", "");
    } else {
      articleIdCode = "00000" + articleId;
    }
    return articleIdCode;
  }
}
