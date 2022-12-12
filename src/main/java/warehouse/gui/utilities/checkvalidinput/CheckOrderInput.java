package warehouse.gui.utilities.checkvalidinput;

import java.awt.Color;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;
import warehouse.gui.utilities.login.ErrorShake;

/**
 * It checks if the input in the order quantity text field is valid.
 */
@UtilityClass
public class CheckOrderInput {

  /**
   * If the quantity field is empty,
   * contains only a decimal point, is zero,
   * or contains a leading zero,
   * then shake the popup and highlight the quantity field.
   *
   * @param comp The Components class object
   * @param popup The popup window that will be displayed when the user inputs an invalid value.
   * @param highlightColor The color that the text field will be highlighted with.
   * @return A boolean value.
   */
  public boolean hasOrderValidInput(Components comp, String popup, String highlightColor) {
    if (Objects.equals(comp.orderProductQnt.getText(), "")
        ||
        Objects.equals(comp.orderProductQnt.getText(), ".")
        ||
        Double.parseDouble(comp.orderProductQnt.getText()) == 0
        ||
        comp.orderProductQnt.getText().length() > 1
            && String.valueOf(comp.orderProductQnt.getText().charAt(0)).equals("0")
            && !String.valueOf(comp.orderProductQnt.getText().charAt(1)).equals(".")
    ) {
      ErrorShake.startShake(popup);
      comp.orderProductQnt.setBackground(Color.decode(highlightColor));
      return false;
    } else {
      return true;
    }
  }
}
