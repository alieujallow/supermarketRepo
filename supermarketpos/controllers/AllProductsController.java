/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import supermarketpos.models.TableModel;
import supermarketpos.views.AllProductsView;

/**
 *
 * @author Anthony
 */
public class AllProductsController implements ActionListener{
    
    AllProductsView view = null;
    TableModel model;
    
    public AllProductsController(AllProductsView view, TableModel model){
        this.view = view;
        this.model = model;
        
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
        
        }
    }
    
}
