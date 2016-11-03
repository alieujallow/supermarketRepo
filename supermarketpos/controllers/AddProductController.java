/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == view.getAddProductBtn()){
            String productName = view.getProductNameJtf().getText();
            double price = Double.parseDouble(view.getPriceJtf().getText());
            int quantity = Integer.parseInt(view.getQuantityJtf().getText());
            
            db.connectToDatabase();
            db.addProductTodatabase(productName, price, quantity);
            db.closeDatabaseConnection();

            JOptionPane.showMessageDialog(null, "Product added successfully");
            
            view.getProductNameJtf().setText("");
            view.getPriceJtf().setText("");
            view.getQuantityJtf().setText("");
        }
        else if(e.getSource() == view.getCancelBtn()){
            view.dispose();
        }
    }
    
}
