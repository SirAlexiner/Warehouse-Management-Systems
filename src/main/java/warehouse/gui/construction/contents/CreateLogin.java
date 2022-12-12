package warehouse.gui.construction.contents;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * It creates a login panel.
 */
@UtilityClass
public class CreateLogin {

  /**
   * It creates a login panel.
   *
   * @param panel The panel that the components will be added to.
   */
  public void constructLoginPanel(JPanel panel) {
    // Getting the components from the Components class.
    Components comp = Components.getComponents();
    // Creating a new icon for the logo.
    FlatSVGIcon guiIcon = new FlatSVGIcon("guiIcon.svg", 100, 100);
    
    // Creating the components for the login panel.
    comp.logoLabel = new JLabel();
    comp.logoLabel.setIcon(guiIcon);
    comp.loginNameLabel = new JLabel("Warehouse Management Systems");
    comp.userLabel = new JLabel("Username:");
    comp.user = new JTextField(5);
    comp.passLabel = new JLabel("Password:");
    comp.pass = new JPasswordField(5);
    comp.loginBtn = new JButton("Login");
    // Adding the components to the panel.
    panel.add(comp.logoLabel);
    panel.add(comp.loginNameLabel);
    panel.add(comp.userLabel);
    panel.add(comp.user);
    panel.add(comp.passLabel);
    panel.add(comp.pass);
    panel.add(comp.loginBtn);
  }
}
