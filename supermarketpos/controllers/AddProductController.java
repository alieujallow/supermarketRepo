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
import supermarketpos.models.TableModel;
import supermarketpos.views.AddProductView;

/**
 *
 * @author Anthony
 */
public class AddProductController implements ActionListener{
    
    AddProductView view = null;
    Database db = null;
    TableModel model = null;
    
    public AddProductController(AddProductView view, Database db, TableModel model){
        this.view = view;
        this.db = db;
        this.model = model;
    }
    
    public void control(){
        view.getAddProductBtn().addActionListener(this);
        view.getCancelBtn().addActionListener(this);
        
        view.setTitle("Add new Product");
        view.setDefaultCloseOperation(view.DISPOSE_ON_CLOSE);
        view.setVisible(true);
        view.pack();
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == view.getAddProductBtn()){
                  
            String productName = view.getProductNameJtf().getText();
            String price = view.getPriceJtf().getText();
            String quantity = view.getQuantityJtf().getText();
            
            if(price != null && !price.equalsIgnoreCase("") && quantity != null && !quantity.equalsIgnoreCase("") &&
                   productName != null && !productName.equalsIgnoreCase("")){
            try{
                int q = Integer.parseInt(view.getQuantityJtf().getText());
                double p = Double.parseDouble(view.getPriceJtf().getText());

                if(p > 0 && q > 0){
                    db.connectToDatabase();
                    db.addProductTodatabase(productName, p, q);
                    db.closeDatabaseConnection();

                    JOptionPane.showMessageDialog(null, "Product added successfully");

                    view.getProductNameJtf().setText("");
                    view.getPriceJtf().setText("");
                    view.getQuantityJtf().setText("");
                }
                else{
                    JOptionPane.showMessageDialog(view, "Price and Quantity must be more than 0"
                      , "Error Message " ,JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(view, "Price and Quantity must be "
                       + "numeric", "Error Message " ,JOptionPane.ERROR_MESSAGE);
            }
        }
            else{
                JOptionPane.showMessageDialog(view, "Fields cannot be empty "
                      , "Error Message " ,JOptionPane.ERROR_MESSAGE);
            }
           
        }
        else if(e.getSource() == view.getCancelBtn()){
            view.dispose();
        }
    }
    
}
