package warehouse.gui.utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import lombok.experimental.UtilityClass;

/**
 * If the user presses CTRL-V, consume the key event.
 */
@UtilityClass
public class DisableKeyCombo {

  /**
   * If the key combo is CTRL-V, consume the key event.
   *
   * @return A KeyListener object.
   */
  public KeyListener disableControlV() {
    return new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
          e.consume();
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
          e.consume();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
          e.consume();
        }
      }
    };
  }
}
