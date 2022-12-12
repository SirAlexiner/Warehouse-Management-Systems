package warehouse.gui.construction.contents.boundaries;

import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * This class sets the bounds for the components in the About window.
 */
@UtilityClass
public class AboutBounds {

  /**
   * Set the bounds of the components in the About window.
   */
  public void setAboutBounds() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Setting the bounds of the components in the about window.
    comp.picLabel.setBounds(5, 0, 90, 90);
    comp.aboutLabel.setBounds(100, 15, 325, 75);
  }
}
