/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Anthony
 */
public class EditProductController implements ActionListener
{
    EditProductView editProductView;
    int productID;
    public EditProductController(EditProductView epv,int id)
    {
        editProductView=epv;
        productID=id;
        editProductView.getCancelButton().addActionListener(this);
        editProductView.getSaveButton().addActionListener(this);
        editProductView.getCancelButton().setActionCommand("cancel");
        editProductView.getsaveButton().setActionCommand("save");      
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
            Database.getInstance().updateProduct(productName,price,quantity,productID);
            editProductView.setVisible(false);
        }
    }
}
