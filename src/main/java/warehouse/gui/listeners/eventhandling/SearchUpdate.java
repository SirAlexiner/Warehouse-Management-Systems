package warehouse.gui.listeners.eventhandling;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * It takes the text from the search field and the category selected from the drop-down menu
 * and filters the table based on the input.
 */
@UtilityClass
public class SearchUpdate {

  /**
   * It takes the text from the search field and the category selected from the dropdown menu,
   * and then filters the table based on the text and category.
   */
  public void updateDoc() {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Getting the searchName component from the Components class.
    JTextField searchName = comp.searchName;
    // Getting the selected item from the dropdown menu.
    Object searchCat = comp.searchCategory.getSelectedItem();
    // Removing all the special characters from the text.
    String cat = String.valueOf(searchCat).replaceAll("[$&+:;=?@#|<>^*()%!\\\\/]", "");
    String text = searchName.getText().replaceAll("[$&+:;=?@#|<>^()%!\\\\/]", "");
    // Creating a list of filters and adding the first filter to the list.
    List<RowFilter<Object, Object>> filters = new ArrayList<>(2);
    filters.add(RowFilter.regexFilter("(?i)" + cat, 1));
    // Checking if the text starts with a certain character
    // and then adding a filter to the list of filters.
    if (text.startsWith("*")) {
      filters.add(RowFilter.regexFilter("(?i)" + text.replace("*", "")));
    } else if (text.startsWith("org*")) {
      filters.add(RowFilter.regexFilter("(?i)" + text.replace("org*", ""), 2));
    } else if (text.startsWith("man*")) {
      filters.add(RowFilter.regexFilter("(?i)" + text.replace("man*", ""), 4));
    } else if (text.startsWith("clr*")) {
      filters.add(RowFilter.regexFilter("(?i)" + text.replace("clr*", ""), 7));
    } else {
      filters.add(RowFilter.regexFilter("(?i)" + text, 3));
    }
    // Creating a filter that filters the table based on the filters in the list.
    RowFilter<Object, Object> rf = RowFilter.andFilter(filters);
    // Setting the filter to the rowSorter.
    comp.rowSorter.setRowFilter(rf);
  }
}
