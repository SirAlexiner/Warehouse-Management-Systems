package warehouse.inventory.utilities;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;
import warehouse.gui.CreateGuiFrame;
import warehouse.gui.utilities.Components;
import warehouse.inventory.initializers.ProductList;


/**
 * It's a class that refreshes the spreadsheet after updates to its content.
 */
@UtilityClass
public class ProductToSpreadsheet {
  // It's a reference to the main frame of the application.
  private final JFrame main = CreateGuiFrame.getMain();
  // It's a reference to the popUp window of the application.
  private final JDialog popUp = CreateGuiFrame.getPopUp();


  /**
   * It creates a progress window, and updates the spreadsheet in a separate thread.
   */
  public void refreshSpreadsheet() {
    // It's creating an anonymous class that will be used to pass the references of the objects
    // that are created in the thread.
    var ref = new Object() {
      JProgressBar progressBar = new JProgressBar();
      JDialog tableLoader = new JDialog();
      JButton clsBtn = new JButton();
    };
    // It's creating a new thread that will create a new JDialog window
    // that will show the progress of the spreadsheet update.
    Thread a = new Thread(() -> {
      if (popUp != null && popUp.isVisible()) {
        ref.tableLoader = new JDialog(popUp, "WMS: Please Wait", true);
      } else {
        ref.tableLoader = new JDialog(main, "WMS: Please Wait", true);
      }
      ref.tableLoader.setResizable(false);
      ref.tableLoader.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      FlatSVGIcon guiIcon = new FlatSVGIcon("guiIcon.svg");
      ref.tableLoader.setIconImage(guiIcon.getImage());
      JLabel message = new JLabel("Updating Spreadsheet...");
      message.setBounds(10, 0, 250, 30);
      ref.progressBar = new JProgressBar(0, ProductList.getProducts().size());
      ref.progressBar.setBounds(10, 30, 250, 30);
      ref.progressBar.setForeground(Color.decode("#967bb6"));
      ref.progressBar.setStringPainted(true);
      ref.clsBtn = new JButton("Close");
      ref.clsBtn.addActionListener(e -> ref.tableLoader.dispose());
      ref.clsBtn.setEnabled(false);
      ref.clsBtn.setBounds(135, 70, 125, 30);
      JPanel loaderPanel = new JPanel();
      loaderPanel.setPreferredSize(new Dimension(270, 110));
      loaderPanel.add(message);
      loaderPanel.add(ref.progressBar);
      loaderPanel.add(ref.clsBtn);
      loaderPanel.setLayout(null);
      ref.tableLoader.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent we) {
          if (ref.progressBar.getValue() == ProductList.getProducts().size()) {
            ref.tableLoader.dispose();
          }
        }
      });
      ref.tableLoader.getContentPane().add(loaderPanel);
      ref.tableLoader.pack();
      ref.tableLoader.setLocationRelativeTo(null);
      ref.tableLoader.setVisible(true);
    });
    a.start();
    // It's creating a new thread that will update the spreadsheet and dispose the loader window.
    Thread b = new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        ErrorLogger.LOGGER.log(
            Level.FINE,
            String.format("Spreadsheet sleep was interrupted: %s", e)
        );
      }
      updateSpreadsheet(ref.progressBar);
      tryDisposeLoader(ref.tableLoader, ref.clsBtn);
    });
    b.start();
  }


  /**
   * Try to dispose the loader window after 1 second, but if it fails,
   * enable the close button and log the error.
   *
   * @param tableLoader The JDialog that is used to show the progress of the loading of the table.
   * @param clsBtn The button that will be enabled after the loader is disposed.
   */
  private void tryDisposeLoader(JDialog tableLoader, JButton clsBtn) {
    try {
      Thread.sleep(1000);
      tableLoader.dispose();
    } catch (Exception e) {
      clsBtn.setEnabled(true);
      Thread.currentThread().interrupt();
      ErrorLogger.LOGGER.log(
          Level.FINEST,
          String.format("Application was not able to wait and auto dispose Progress window: %s", e)
      );
    }
  }

  /**
   * It updates the spreadsheet with the current product list.
   *
   * @param progressBar The progress bar that will be updated as the spreadsheet is updated.
   */
  private void updateSpreadsheet(JProgressBar progressBar) {
    // It's getting the components of the main frame of the application.
    Components comp = Components.getComponents();
    // It's clearing the search fields and the table.
    if (comp.searchName != null) {
      comp.searchName.setText(null);
    }
    if (comp.searchCategory != null) {
      comp.searchCategory.setSelectedItem("");
      comp.rowSorter.setRowFilter(null);
    }
    comp.spreadsheet.getRowSorter().setSortKeys(null);
    comp.spreadsheetModel.setRowCount(0);
    comp.spreadsheetData = new Object[11];
    int i = 0;
    // It's getting the keys of the products in the product list.
    for (String key : ProductList.getProducts().keySet()) {
      // It's getting the product from the product list.
      Product product = ProductList.getProducts().get(key);
      // It's adding the product information to the spreadsheet.
      comp.spreadsheetData[0] = key;
      comp.spreadsheetData[1] = product.getProductInfo()[0];
      comp.spreadsheetData[2] = product.getProductInfo()[1];
      comp.spreadsheetData[3] = product.getProductInfo()[2];
      comp.spreadsheetData[4] = product.getProductInfo()[3];
      comp.spreadsheetData[5] = product.getProductInfo()[4];
      comp.spreadsheetData[6] = product.getProductInfo()[5];
      comp.spreadsheetData[7] = product.getProductInfo()[6];
      comp.spreadsheetData[8] = product.getProductInfo()[7];
      comp.spreadsheetData[9] = product.getProductInfo()[8];
      comp.spreadsheetData[10] = product.getProductInfo()[9];
      comp.spreadsheetModel.addRow(comp.spreadsheetData);
      i++;
      progressBar.setValue(i);
    }
    // It's updating the spreadsheet with the current product list.
    comp.spreadsheetModel.fireTableDataChanged();
    comp.spreadsheetProgress.setValue(ProductList.getProducts().size());
    comp.spreadsheetProgress.setString(String.valueOf(ProductList.getProducts().size()));
  }
}
