package warehouse.gui.construction.contents.boundaries;

import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * It sets the bounds of the Help panel.
 */
@UtilityClass
public class HelpBounds {

  /**
   * Set the bounds of the Help panel.
   */
  public void setHelpBounds() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Setting the bounds of the components in the About window.
    comp.helpLabel.setBounds(0, 0, 575, 480);
    comp.scrollHelp.setBounds(0, 0, 575, 480);
  }
}
