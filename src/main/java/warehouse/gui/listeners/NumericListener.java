package warehouse.gui.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import warehouse.gui.utilities.Components;
import warehouse.inventory.initializers.ProductList;
import warehouse.inventory.utilities.Product;

/**
 * It's a class that implements the KeyListener interface and is used to limit the number of digits
 * that can be entered into a text field.
 */
public class NumericListener implements KeyListener {

  @Override
  public void keyTyped(KeyEvent e) {
    // Auto Stub
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // Getting the Components class.
    Components comp = Components.getComponents();
    // Casting the source of the KeyEvent to a JTextField object.
    JTextField obj = (JTextField) e.getSource();
    // It's getting the name of the JTextField object.
    String objName = obj.getName();
    // It's checking if the user is typing in the price text field,
    // then the user can only type in a number with a maximum of 6 digits before the decimal point
    // and 2 digits after the decimal point. If the user is typing in the weight text field,
    // then the user can only type in a number with a maximum of 3
    // digits before the decimal point and 2 digits after the decimal point.
    // If the user is typing in the dimensions text field,
    // then the user can only type in a number with a maximum of 3 digits
    // before the decimal point and 3 digits after the decimal point.
    // If the user is typing in the quantity text field,
    // then the user can only type in a number with a maximum of 6 digits.
    // If the user is typing in the quantity text field,
    // and the product has a weight, then the user can only type in a number with a maximum of 3
    // digits before the decimal point and 2 digits after the decimal point.
    // Otherwise, the user can only type in a number with a maximum of 6 digits.
    if (objName != null && objName.equals("Price")) {
      setMaxPrice(e, obj);
    } else if (comp.weightReq == null || comp.dimensionsReq == null) {
      isOrderMenuActive(e, comp, obj);
    } else if (comp.weightReq.isSelected() && comp.weightReq.isShowing()
            && comp.dimensionsReq.isSelected() && comp.dimensionsReq.isShowing()
    ) {
      weightIsSelected(e, obj);
      dimensionsAreSelected(e, obj, objName);
    } else if (comp.dimensionsReq.isSelected() && comp.dimensionsReq.isShowing()) {
      dimensionsAreSelected(e, obj, objName);
    } else if (comp.weightReq.isSelected() && comp.weightReq.isShowing()) {
      weightIsSelected(e, obj);
    } else {
      isOrderMenuActive(e, comp, obj);
    }
  }

  /**
   * If the user is typing in the dimensions text field,
   * then the user can only type in a number with a maximum of 3 digits
   * before the decimal point and 3 digits after the decimal point.
   * If the user is typing in the price text field,
   * then the user can only type in a number with a maximum of 6 digits before the decimal point
   * and 2 digits after the decimal point. If the user is typing in the weight text field,
   * then the user can only type in a number with a maximum of 3
   * digits before the decimal point and 2 digits after the decimal point
   *
   * @param e The KeyEvent that is being passed in.
   * @param obj The text field that the user is typing in.
   * @param objName The name of the JTextField object.
   */
  private static void dimensionsAreSelected(KeyEvent e, JTextField obj, String objName) {
    if (objName != null && objName.equals("dimensions")) {
      if (e.getKeyChar() == KeyEvent.VK_PERIOD) {
        obj.setEditable(!obj.getText().contains("."));
      } else if (!obj.getText().contains(".")) {
        setDigitCap(e, obj, 3);
      } else {
        int i = digitsBeforeDecimal(obj);
        setDigitCap(e, obj, i + 4);
      }
    } else if (objName != null && objName.equals("Price")) {
      setMaxPrice(e, obj);
    } else {
      weightIsSelected(e, obj);
    }
  }


  /**
   * If the user presses the period key,
   * then the text field is set to not editable if it already contains a period.
   * Otherwise, if the text field does not contain a period,
   * then the digit cap is set to 3. Otherwise, the digit cap is
   * set to the number of digits before the decimal plus 2
   *
   * @param e The KeyEvent that is being processed.
   * @param obj The JTextField that the user is typing in.
   */
  private static void weightIsSelected(KeyEvent e, JTextField obj) {
    if (e.getKeyChar() == KeyEvent.VK_PERIOD) {
      obj.setEditable(!obj.getText().contains("."));
    } else if (!obj.getText().contains(".")) {
      setDigitCap(e, obj, 3);
    } else {
      int i = digitsBeforeDecimal(obj);
      setDigitCap(e, obj, i + 3);
    }
  }

  /**
   * If the order menu is active, and the product has a weight,
   * then allow the user to enter a decimal value to two decimal places.
   * Otherwise, limit the user to entering 6 digits.
   *
   * @param e The KeyEvent object
   * @param comp The Components class
   * @param obj The JTextField object that is being typed in.
   */
  private static void isOrderMenuActive(KeyEvent e, Components comp, JTextField obj) {
    if (comp.orderProductQnt != null && comp.orderProductQnt.isShowing()) {
      if (productHasWeight(comp)) {
        if (e.getKeyChar() == KeyEvent.VK_PERIOD) {
          obj.setEditable(!obj.getText().contains("."));
        } else if (!obj.getText().contains(".")) {
          setDigitCap(e, obj, 3);
        } else {
          int i = digitsBeforeDecimal(obj);
          setDigitCap(e, obj, i + 3);
        }
      } else {
        setDigitCap(e, obj, 6);
      }
    } else {
      setDigitCap(e, obj, 6);
    }
  }


  /**
   * It returns the number of digits before the decimal point in a JTextField.
   *
   * @param obj The JTextField object that you want to check.
   * @return The number of digits before the decimal point.
   */
  private static int digitsBeforeDecimal(JTextField obj) {
    int i = 0;
    boolean notFoundPeriod = true;
    while (i < obj.getText().length() && notFoundPeriod) {
      if (Character.isDigit(obj.getText().charAt(i))) {
        i++;
      } else {
        notFoundPeriod = false;
      }
    }
    return i;
  }


  /**
   * If the product has a weight, return true.
   *
   * @param comp the component that is being checked
   * @return A boolean value.
   */
  private static boolean productHasWeight(Components comp) {
    String key = String.valueOf(comp.orderProduct.getText());
    Product product = ProductList.getProducts().get(key);
    String quantityWeight = String.valueOf(product.getProductInfo()[5]);
    return quantityWeight.matches(".*[a-zA-Z]");
  }

  /**
   * If the user presses the period key,
   * then the text field is set to be editable if it doesn't already contain a period.
   * and the digit cap is set to 6.
   * Otherwise, if the text field does contain a period,
   * then the number of digits before the decimal is found
   * and the digit cap is set to that number plus two decimals.
   *
   * @param e   The KeyEvent that triggered the method.
   * @param obj The JTextField that the user is typing in.
   */
  private static void setMaxPrice(KeyEvent e, JTextField obj) {
    if (e.getKeyChar() == KeyEvent.VK_PERIOD) {
      obj.setEditable(!obj.getText().contains("."));
    } else if (!obj.getText().contains(".")) {
      setDigitCap(e, obj, 6);
    } else {
      int i = digitsBeforeDecimal(obj);
      setDigitCap(e, obj, i + 3);
    }
  }

  /**
   * If the key pressed is a digit between 0 and 9,
   * and the text field's length is less than the cap,
   * or if the key pressed is the backspace key, the text field is editable.
   *
   * @param e   The KeyEvent object that is passed to the method.
   * @param obj The JTextField object that the key listener is attached to.
   * @param cap The maximum number of digits allowed in the text field.
   */
  private static void setDigitCap(KeyEvent e, JTextField obj, int cap) {
    obj.setEditable(
        e.getKeyChar() >= '0' && e.getKeyChar() <= '9'
            && obj.getText().length() < cap
            || e.getKeyChar() == KeyEvent.VK_BACK_SPACE
    );
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // Auto Stub
  }
}
