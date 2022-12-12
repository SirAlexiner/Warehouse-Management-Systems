package warehouse.gui.utilities.login;

import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;
import lombok.experimental.UtilityClass;

/**
 * It shakes the window that called it.
 */
@UtilityClass
public class ErrorShake {
  // A timer that is used to shake the window.
  private Timer shakeTimer;

  /**
   * Iterate through all windows, if the window is visible and is an instance of JFrame or JDialog,
   * shake it.
   *
   * @param instanceOfWindow This is the instance of the window that called the shake.
   */
  public void startShake(String instanceOfWindow) {
    for (Window w : Window.getWindows()) {
      if (instanceOfWindow.equals("Login")) {
        if (w instanceof JFrame frame && w.isVisible()) {
          shakeWindow(frame);
        }
      } else {
        if (w instanceof JDialog dialog && w.isVisible()) {
          shakeWindow(dialog);
        }
      }
    }
    final Runnable runnable =
        (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.default");
    if (runnable != null) {
      runnable.run();
    }
  }

  /**
   * It takes a window object, and shakes it for a specified amount of time.
   *
   * @param window The window to shake.
   */
  private void shakeWindow(Object window) {
    Container obj;
    try {
      obj = (JFrame) window;
    } catch (Exception e) {
      obj = (JDialog) window;
    }
    final Point startLocation = obj.getLocation();
    final long startTime;
    if (shakeTimer != null) {
      stopShake(startLocation, obj);
    }
    startTime = System.currentTimeMillis();
    shakeTimer = new Timer(5, e -> {
      double pi = Math.PI;
      double shakes = 5;
      int shakeDuration = 500;
      long elapsed = System.currentTimeMillis() - startTime;
      double wave = (pi * elapsed) / ((shakeDuration * 0.5) / shakes);
      int shakeDistance = 10;
      int shakeX = (int) ((Math.sin(wave) * shakeDistance) + startLocation.x);
      try {
        JFrame frame = (JFrame) window;
        frame.setLocation(shakeX, startLocation.y);
        frame.repaint();
        if (elapsed >= shakeDuration) {
          stopShake(startLocation, frame);
        }
      } catch (Exception ex) {
        Window dialog = (Window) window;
        dialog.setLocation(shakeX, startLocation.y);
        dialog.repaint();
        if (elapsed >= shakeDuration) {
          stopShake(startLocation, dialog);
        }
      }
    });
    shakeTimer.start();
  }

  /**
   * Stop the timer and set the window back to its original position.
   *
   * @param startLocation The location of the window before the shake.
   * @param obj The object to shake.
   */
  private void stopShake(Point startLocation, Container obj) {
    shakeTimer.stop();
    obj.setLocation(startLocation);
    obj.repaint();
  }
}
