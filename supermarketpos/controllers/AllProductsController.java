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
import supermarketpos.views.AllProductsView;

/**
 *
 * @author Anthony
 */
public class AllProductsController implements ActionListener{
    
    AllProductsView view = null;
    TableModel model = null;
    Database db = null;
    
    public AllProductsController(AllProductsView view, TableModel model, Database db){
        this.view = view;
        this.model = model;
        this.db = db;
        
        view.getTable().setModel(model);
    }
    
    public void control(){
        view.getEditProductBtn().addActionListener(this);
        view.getDeleteProductBtn().addActionListener(this);
        
        view.setTitle("All Products");
        view.setDefaultCloseOperation(view.DISPOSE_ON_CLOSE);
        view.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == view.getEditProductBtn()){
            
        }
        else if(e.getSource() == view.getDeleteProductBtn()){
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?"
                                        ,null, JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){
                int selectedRow = view.getTable().getSelectedRow();
                if(selectedRow != -1){
                    
                    String productName = (String)model.getValueAt(selectedRow, 1);
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
    }
    
}
