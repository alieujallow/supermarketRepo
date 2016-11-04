/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import supermarketpos.models.Database;
import supermarketpos.models.Product;
import supermarketpos.models.TableModel;
import supermarketpos.views.EditProductView;

/**
 *
 * @author Anthony
 */
public class EditProductController implements ActionListener
{
    EditProductView editProductView;
    Product product;
    int productID, row;
    TableModel model;
    
    public EditProductController(EditProductView epv, Product pr, int id, TableModel model, int row)
    {
        editProductView = epv;
        product = pr;
        productID = id;
        this.model = model;
        this.row = row;
        
        editProductView.getNameTextField().setText(product.getProductName());
        editProductView.getQuantityTextField().setText(product.getQuantity().toString());
        editProductView.getPriceTextField().setText(product.getPrice().toString());
    
        editProductView.getCancelButton().addActionListener(this);
        editProductView.getSaveButton().addActionListener(this);
        editProductView.getCancelButton().setActionCommand("cancel");
        editProductView.getSaveButton().setActionCommand("save");      
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equalsIgnoreCase("cancel"))
        {
            editProductView.setVisible(false);
        }
        else if(e.getActionCommand().equalsIgnoreCase("save"))
        {
            String productName = editProductView.getNameTextField().getText();
            String quantity = editProductView.getQuantityTextField().getText();
            String price = editProductView.getPriceTextField().getText();
            
            if(price != null && !price.equalsIgnoreCase("") && quantity != null && !quantity.equalsIgnoreCase("") &&
                   productName != null && !productName.equalsIgnoreCase("")){
            try{
                int q = Integer.parseInt(editProductView.getQuantityTextField().getText());
                double p = Double.parseDouble(editProductView.getPriceTextField().getText());

                if(p > 0 && q > 0){
                    String [] editedProduct = new String [4];
                    editedProduct[0] = Integer.toString(productID);
                    editedProduct[1] = productName;
                    editedProduct[2] = Double.toString(p);
                    editedProduct[3] = Integer.toString(q);

                    TableModel.getArray().set(row, editedProduct);
                    model.convertTo2DArray();

                    Database.getInstance().connectToDatabase();
                    Database.getInstance().updateProduct(productName, p, q, productID);
                    Database.getInstance().closeDatabaseConnection();

                    model.fireTableRowsUpdated(row, row);
                    editProductView.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(editProductView, "Price and Quantity must be more than 0"
                      , "Error Message " ,JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(editProductView, "Price and Quantity must be "
                       + "numeric", "Error Message " ,JOptionPane.ERROR_MESSAGE);
            }
        }
            else{
                JOptionPane.showMessageDialog(editProductView, "Fields cannot be empty "
                      , "Error Message " ,JOptionPane.ERROR_MESSAGE);
            }
    }
        
    }
}
