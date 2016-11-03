/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import supermarketpos.models.Database;
import supermarketpos.models.Product;
import supermarketpos.views.EditProductView;

/**
 *
 * @author Anthony
 */
public class EditProductController implements ActionListener
{
    EditProductView editProductView;
    Product product;
    public EditProductController(EditProductView epv,Product pr)
    {
        editProductView=epv;
        product=pr;
        
        editProductView.getNameTextField().setText(product.getProductName());
        editProductView.getQuantityTextField().setText(product.getQuantity().toString());
        editProductView.getPriceTextField().setText(product.getPrice().toString());
    
        editProductView.getCancelButton().addActionListener(this);
        editProductView.getSaveButton().addActionListener(this);
        editProductView.getCancelButton().setActionCommand("cancel");
        editProductView.getSaveButton().setActionCommand("save");      
    }

    EditProductController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equalsIgnoreCase("cancel"))
        {
            editProductView.setVisible(false);
        }
        else if(e.getActionCommand().equalsIgnoreCase("save"))
        {
            String productName= editProductView.getNameTextField().getText();
            int quantity = Integer.parseInt(editProductView.getQuantityTextField().getText());
            double price = Double.parseDouble(editProductView.getPriceTextField().getText());
            //Database.getInstance().updateProduct(productName,price,quantity,productID);
            editProductView.setVisible(false);
        }
    }
}
