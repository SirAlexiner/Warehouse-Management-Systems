package warehouse.gui.listeners;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import warehouse.gui.utilities.Components;

/**
 * It resizes the components of the GUI when the window is resized.
 */
public class ResizeListener implements ComponentListener {
  @Override
  public void componentResized(ComponentEvent e) {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Getting the source of the event.
    Component c = (Component) e.getSource();
    // Checking if the search is active or not. If it is active,
    // it will set the bounds of the components to the values in the if statement.
    // If it is not active,
    // it will set the bounds of the components to the values in the else statement.
    if (Boolean.TRUE.equals(comp.searchActive)) {
      comp.menu.setBounds(0, 0, c.getWidth(), 25);
      comp.searchCategoryLabel.setBounds(10, 30, 100, 25);
      comp.searchCategory.setBounds(110, 30, 100, 25);
      comp.searchNameLabel.setBounds(230, 30, 175, 25);
      comp.searchName.setBounds(405, 30, 100, 25);
      comp.spreadsheet.setBounds(0, 60, c.getWidth(), c.getHeight() - 90);
      comp.scrollPane.setBounds(-1, 60, c.getWidth() + 1, c.getHeight() - 90);

    } else {
      comp.menu.setBounds(0, 0, c.getWidth(), 25);
      comp.searchCategoryLabel.setBounds(10, 30, 100, 25);
      comp.searchCategory.setBounds(110, 30, 100, 25);
      comp.searchNameLabel.setBounds(230, 30, 175, 25);
      comp.searchName.setBounds(405, 30, 100, 25);
      comp.spreadsheet.setBounds(0, 25, c.getWidth(), c.getHeight() - 55);
      comp.scrollPane.setBounds(-1, 25, c.getWidth() + 1, c.getHeight() - 55);
    }
    comp.spreadsheetSize.setBounds(15, c.getHeight() - 30, 170, 30);
    comp.spreadsheetProgress.setBounds(175, c.getHeight() - 22, 250, 15);
  }

  @Override
  public void componentMoved(ComponentEvent e) {
    // Auto Stub
  }

  @Override
  public void componentShown(ComponentEvent e) {
    // Auto Stub
  }

  @Override
  public void componentHidden(ComponentEvent e) {
    // Auto Stub
  }
}
