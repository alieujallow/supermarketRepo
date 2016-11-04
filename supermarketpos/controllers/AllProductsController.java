/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import supermarketpos.models.Database;
import supermarketpos.models.Product;
import supermarketpos.models.TableModel;
import supermarketpos.views.AllProductsView;
import supermarketpos.views.EditProductView;

/**
 *
 * @author Anthony
 */
public class AllProductsController implements ActionListener {

    //instance variables
    AllProductsView view = null;
    TableModel model = null;
    Database db = null;

    /*
    *constructor
    */
    public AllProductsController(AllProductsView view, TableModel model, Database db) {
        this.view = view;
        this.model = model;
        this.db = db;

        view.getTable().setModel(model);
    }

    /**
     * 
     * control method
     */
    public void control() {
        view.getEditProductBtn().addActionListener(this);
        view.getDeleteProductBtn().addActionListener(this);
        view.getSearchButton().addActionListener(this);

        view.setTitle("All Products");
        view.setDefaultCloseOperation(view.DISPOSE_ON_CLOSE);
        view.setVisible(true);
        view.pack();
    }

   /**
    * action performed method
    * @param e 
    */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getEditProductBtn()) {
            int row = view.getTable().getSelectedRow();

            if (row != -1) {
                int productID = Integer.parseInt((String) model.getValueAt(row, 0));
                String productName = (String) model.getValueAt(row, 1);
                double price = Double.parseDouble((String) model.getValueAt(row, 2));
                int quantity = Integer.parseInt((String) model.getValueAt(row, 3));

                Product product = new Product(productName, price, quantity);
                EditProductView editProductView = new EditProductView();
                TableModel model = new TableModel();
                EditProductController EditProductController = new EditProductController(editProductView, product, productID, model, row);
            }
        } else if (e.getSource() == view.getDeleteProductBtn()) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", null, JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                int selectedRow = view.getTable().getSelectedRow();
                if (selectedRow != -1) {

                    String productName = (String) model.getValueAt(selectedRow, 1);
                    TableModel.getArray().remove(selectedRow);
                    model.convertTo2DArray();

                    db.connectToDatabase();
                    db.delete(productName);
                    db.closeDatabaseConnection();

                    model.fireTableRowsDeleted(selectedRow, selectedRow);
                    JOptionPane.showMessageDialog(null, "Record deleted");
                }
            }
        }
        else if(e.getSource() == view.getSearchButton()){
            String value = view.getSearchField().getText();
            
            for (int row = 0; row <= view.getTable().getRowCount() - 1; row++) {
                for (int col = 0; col <= view.getTable().getColumnCount() - 1; col++) {
                    if (value.equals(view.getTable().getValueAt(row, col))) {

                        // this will automatically set the view of the scroll in the location of the value
                        view.getTable().scrollRectToVisible(view.getTable().getCellRect(row, 0, true));

                        // this will automatically set the focus of the searched/selected row/value
                        view.getTable().setRowSelectionInterval(row, row);

                        for (int i = 0; i <= view.getTable().getColumnCount() - 1; i++) {

                            view.getTable().getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
                        }
                    }
                }
            }
        }

    }
private class HighlightRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(row == table.getSelectedRow()) {
            setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLACK));
        }

        return this;
    }
}
}
