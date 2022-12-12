package warehouse.gui.construction.contents;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.time.Year;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * It creates the About panel.
 */
@UtilityClass
public class CreateAbout {

  /**
   * It creates a JLabel with an icon and a JLabel with some text.
   *
   * @param panel The panel you want to add the components to.
   */
  public void constructAboutPanel(JPanel panel) {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // It creates a new icon from the file guiIcon.svg.
    FlatSVGIcon guiIcon = new FlatSVGIcon("guiIcon.svg", 90, 90);
    // It creates a new JLabel with an icon and a JLabel with some text.
    comp.picLabel = new JLabel();
    comp.picLabel.setIcon(guiIcon);
    comp.aboutLabel = new JLabel(
        "<html><p>Warehouse Management Systems<br>"
            + "©2022 - " + Year.now().getValue() + " Torgrim Thorsen<br>\n"
            + "<hr>Version: 1.0</p></html>");
    // It adds the components to the panel.
    panel.add(comp.picLabel);
    panel.add(comp.aboutLabel);
  }
}
