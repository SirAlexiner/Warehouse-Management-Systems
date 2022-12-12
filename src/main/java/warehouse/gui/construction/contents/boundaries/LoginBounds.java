package warehouse.gui.construction.contents.boundaries;

import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * This class sets the bounds of the components in the login panel.
 */
@UtilityClass
public class LoginBounds {

  /**
   * Set the bounds of the components in the login panel.
   */
  public void setLoginBounds() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Setting the bounds of the components in the login panel.
    comp.logoLabel.setBounds(100, 25, 100, 100);
    comp.loginNameLabel.setBounds(60, 125, 200, 25);
    comp.userLabel.setBounds(25, 160, 100, 25);
    comp.user.setBounds(25, 180, 250, 30);
    comp.passLabel.setBounds(25, 220, 100, 25);
    comp.pass.setBounds(25, 240, 250, 30);
    comp.loginBtn.setBounds(25, 290, 250, 30);
  }
}
