package warehouse.gui.utilities;

import lombok.experimental.UtilityClass;

/**
 * This class sets the search state to true or false.
 */
@UtilityClass
public class SearchActive {

  /**
   * Set the search state to the given state.
   *
   * @param state true or false
   */
  public void setSearch(boolean state) {
    Components comp = Components.getComponents();
    comp.searchActive = state;
  }
}
