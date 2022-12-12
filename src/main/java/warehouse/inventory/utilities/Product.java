package warehouse.inventory.utilities;

import lombok.Getter;
import warehouse.inventory.initializers.ProductList;


/**
 * It takes an array of product information, generates a key, and adds the product to the HashMap.
 */
public class Product {
  @Getter
  private final Object[] productInfo;

  /**
   * It takes an array of objects, generates a key,
   * and adds the product to the HashMap using the key.
   *
   * @param newProduct An array of Objects that contains the product information.
   */
  public Product(Object[] newProduct) {
    productInfo = newProduct;
    String key = ProductKey.getProductKey(
        newProduct[1],
        newProduct[2],
        newProduct[3],
        newProduct[6],
        newProduct[7],
        newProduct[8],
        newProduct[9]
    );
    ProductList.getProducts().put(key, this);
  }
}
