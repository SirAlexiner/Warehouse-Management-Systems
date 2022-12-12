package warehouse.gui.construction.contents;

import java.awt.Font;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import lombok.experimental.UtilityClass;
import warehouse.gui.utilities.Components;

/**
 * It creates a JLabel with a bunch of HTML in it,
 * and then puts it in a JScrollPane.
 */
@UtilityClass
public class CreateHelp {

  /**
   * It creates a JLabel with a bunch of HTML in it,
   * and then puts it in a JScrollPane.
   *
   * @param panel The panel to add the help panel to
   */
  public void constructHelpPanel(JPanel panel) {
    // Getting the Components class, which is a singleton.
    Components comp = Components.getComponents();
    // Creating a JScrollPane with a JLabel inside it,
    // and then it is setting the JLabel to have an HTML text,
    // and then it is setting the scrollbar to have a unit increment of 16.
    // This is to make the scrollbar scroll faster.
    // It is also setting the scrollbar to scroll when the arrow keys are pressed.
    comp.helpLabel = new JLabel();
    comp.helpLabel.setBorder(new EmptyBorder(5, 20, 30, 20));
    comp.helpLabel.setFont(new Font("Default", Font.PLAIN, 14));
    comp.helpLabel.setHorizontalAlignment(SwingConstants.LEFT);
    comp.helpLabel.setVerticalAlignment(SwingConstants.TOP);
    comp.helpLabel.setText("""
        <html>
        <h1 style="color: rgb(150, 123, 182);">HELP: </h1>
        <hr>
        <h2 style="color: rgb(150, 123, 182);">Navigation:</h2>
        <hr>
        <div style="margin-left: 25px;">
        <h2>Keyboard Navigation</h2>
        <hr>
        <p>This application supports using only the keyboard to navigate:</p>
        <div style="margin-left: 25px;">
        <h2>Menu</h2>
        <hr>
        <p>Pressing the ALT key should open the first menu item of a menubar,<br>
        If the Active window has a menu.<br>
        From here you can navigate the menu using the arrow keys,<br>
        and enter to open the menu.<br>
        <br>
        To exit out of the menu press the ESC key.
        </p>
        <h2>Search</h2>
        <hr>
        <p>
        To enter the Search bar: open the menu,<br>
        and navigate to the "Search" item,<br>
        if the search bar is open,<br>
        you can press the down arrow key to enter the search bar.<br>
        if it isn't open: do so by pressing enter.<br>
        you can now press the down arrow to enter the search bar.
        </p>
        <h2>Spreadsheet</h2>
        <hr>
        <p>
        You can enter the spreadsheet by pressing the TAB key<br>
        or the arrow keys.<br>
        Using the arrow keys you move between the products in the table.<br>
        Pressing enter will bring up the spreadsheet menu for that item.<br>
        <i>See "The Product Menu" below for more info.</i><br>
        <br>
        To go to the end of the table you can press the END key.<br>
        And to go to the top the HOME key.<br>
        <br>
        To exit out of the menu press the ESC key.
        </p>
        <h2>Changing Fields</h2>
        <hr>
        <p>
        To move between any of the many fields or buttons of the application,<br>
        You can press the TAB key to move onto the next,<br>
        or SHIFT-TAB to go to previous.
        </p>
        <h2>Checkboxes</h2>
        <hr>
        <p>
        Checkboxes can be selected by the TAB key, see Above.<br>
        To toggle the state of the Checkbox press Enter.
        </p>
        <h2>Selection Boxes</h2>
        <hr>
        <p>
        To open a selection bar to see all the options,<br> press enter.<br>
        Alternatively you can use the arrow keys<br>
        to walk through the selections.<br>
        <br>
        You can also start typing the option on the keyboard,<br>
        and it should show up.<br>
        i.e: if you have a list of countries, you can type "sw" to get Sweden.
        
        </p>
        <h2>Closing the Active window</h2>
        <hr>
        <p>
        To close the any application The Windows, press ALT-F4.
        </p>
        </div>
        </div>
        <h2 style="color: rgb(150, 123, 182);">Menu Items:</h2>
        <hr>
        <div style="margin-left: 25px;">
        <h2>New</h2>
        <hr>
        <p>Open either the New Product window,<br>
        or the New Category window from the submenu.<br>
        <i>See "The Windows" below for more info.</i>
        </p>
        <h2>Search</h2>
        <hr>
        <p>
        Opens the search bar,<br>
        lets you search by category and<br>
        /or product details.<br>
        by default it searches by name,<br>
        but you can search in other fields as follows:
        <ul>
        <li>Typing * let's you search in all fields</li>
        <li>Typing org* let's you search by country</li>
        <li>Typing man* let's you search by manufacturer</li>
        <li>Typing clr* let's you search by color</li>
        </ul>
        </p>
        <h2>Edit</h2>
        <hr>
        <p> Opens the Edit Category window.<br>
        <i>See "The Windows" below for more info.</i>
        </p>
        <h2>Delete</h2>
        <hr>
        <p>
        Opens the Delete Category window.<br>
        <i>See "The Windows" below for more info.</i>
        </p>
        <h2>Help</h2>
        <hr>
        <p>Open either the Help window (This window),<br>
        or the About window from the submenu.<br>
        <i>See "The Windows" below for more info.</i>
        </p>
        </div>
        <br>
        <h2 style="color: rgb(150, 123, 182);">The Spreadsheet:</h2>
        <hr>
        <div style="margin-left: 25px;">
        <h2>Selecting a Product (Mouse)</h2>
        <hr>
        <p>To Select a product:<br>
        right click the product once.
        </p>
        <h2>Bringing up the Product Menu (Mouse)</h2>
        <hr>
        <p>To bring up the product menu:<br>
        right click the product twice
        </p>
        <br>
        <h2 style="color: rgb(150, 123, 182);">The Product Menu:</h2>
        <hr>
        <div style="margin-left: 25px;">
        <h2>Order</h2>
        <hr>
        <p>This opens the Order window for the selected product.<br>
        <i>See "The Windows" below for more info.</i>
        </p>
        <h2>Edit</h2>
        <hr>
        <p>This opens the Edit Product window for the selected product.<br>
        <i>See "The Windows" below for more info.</i>
        </p>
        <h2>Delete</h2>
        <hr>
        <p>This opens the Delete Product window for the selected product.<br>
        <i>See "The Windows" below for more info.</i>
        </p>
        </div>
        </div>
        <h2 style="color: rgb(150, 123, 182);">The Windows:</h2>
        <hr>
        <p>
        <i><b>
        This Application does not allow CTRL-V (Paste)<br>
        This is to prevent unwanted errors with the application.
        </b></i>
        </p>
        <div style="margin-left: 25px;">
        <h2>New Product Window</h2>
        <hr>
        <p> This window is essentially a product form,<br>
        it allows you to create new products and add them to the application.<br>
        <br>
        If any of the input fields should be grayed out,<br>
        it is either because the input is not valid for the field,<br>
        or because it received an input that cause it to violate<br>
        any of these restrictions:
        <ul>
        <li> Character Limit:
        <ul>
        <li>The Application has a character limit of 45 characters,<br>
        This applies to:<br>
        Categories.<br>
        Product Names.<br>
        and manufacturers.
        </li>
        </ul>
        </li>
        <li> Numeric values:
        <ul>
        <li>Price:
        <ul>
        <li>The maximum price this application
        allows is 999'999.99<br>
        This is regardless of Currency.<br>
        </li>
        </ul>
        </li>
        <li> Quantity:
        <ul>
        <li>Quantity can only be expressed as integers<br>
        to a maximum of: 999'999</li>
        </ul>
        </li>
        <li>Weight:
        <ul>
        <li>If you've selected to list a product as the weight in stock<br>
        for that product,<br>
        then the maximum allowed weight is 999.99.<br>
        This is regardless of weight unit.
        </li>
        </ul>
        </li>
        <li>Dimensions:
        <ul>
        <li>If dimensions are required for a Product,<br>
        the maximum value is: 999.999<br>
        This applies to:<br>
        Height.<br>
        Length.<br>
        Width.<br>
        <br>
        This is regardless of the unit of measurement.
        </li>
        </ul>
        </li>
        </ul>
        </li>
        </ul>
        <div style="margin-left: 25px;">
        <hr>
        <i><b>
        Take note to check the information in the inputs<br>
        before committing the product,<br>
        as only Category, Price, Currency and Quantity<br>
        / Weight can be changed later.
        </b></i>
        <br>
        <br>
        </div>
        </p>
        <h2>New Category</h2>
        <hr>
        <p>
        This window is allows you a to add a new category to the application.<br>
        <i>See Above for restrictions.</i><br>
        </p>
        <h2>Edit Category</h2>
        <hr>
        <p>
        This window is allows you a to edit a category in the application.<br>
        This can be if a typo or a category is no longer needed.<br>
        <i>See Above for restrictions.</i>
        </p>
        <br>
        <p>
        <i>
        Changes to the category does not change the category<br>
        of existing products,<br>
        The products must be moved to the new category manually.
        </i>
        </p>
        <h2>Delete Category</h2>
        <hr>
        <p>
        This window is allows you a to delete a category in the application.<br>
        This can be if a category is no longer needed.
        </p>
        <br>
        <p>
        <i>
        Changes to the category does not change the category<br>
        of existing products,<br>
        The products must be moved to new category or deleted manually.
        </i>
        </p>
        <h2>Help</h2>
        <hr>
        <p>
        This window shows the Help window.<br>
        <i><b>That would be this window.</b></i>
        </p>
        <h2>About</h2>
        <hr>
        <p>
        This window brings up the About menu.<br>
        The window shows information about the application.
        </p>
        <h2>Order</h2>
        <hr>
        <p>
        This window allows you to increase the Quantity<br>
        / weight of the selected product.<br>
        Depending on the selected product
        it can be increased by quantity<br>
        or weight.<br>
        <i>See above for restrictions.</i>
        </p>
        <h2>Edit Product</h2>
        <hr>
        <p>
        This window allows you to edit the selected product.<br>
        <i>See above for restrictions</i>
        </p>
        <h2>Delete Product</h2>
        <hr>
        <p>
        This window allows you to delete the selected product.<br>
        </p>
        </div>
        </html>
        """);
    comp.scrollHelp = new JScrollPane(
        comp.helpLabel,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
    );
    comp.scrollHelp.getVerticalScrollBar().setUnitIncrement(16);
    JScrollBar vertical = comp.scrollHelp.getVerticalScrollBar();
    InputMap im = vertical.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
    im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
    // Adding the scrollHelp component to the panel.
    panel.add(comp.scrollHelp);
  }
}
