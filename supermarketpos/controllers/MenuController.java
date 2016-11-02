/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import supermarketpos.models.Database;
import supermarketpos.models.TableModel;
import supermarketpos.views.AddProductView;
import supermarketpos.views.AllProductsView;
import supermarketpos.views.MenuView;
import supermarketpos.views.TransactionView;

/**
 *
 * @author Anthony
 */
public class MenuController implements ActionListener{
    
    MenuView view = null;
    
    public MenuController(MenuView view){
        this.view = view;
    }
    
    public void control(){
        view.getAddProductBtn().addActionListener(this);
        view.getViewProductBtn().addActionListener(this);
        view.getMakeTransactionBtn().addActionListener(this);
        
        view.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == view.getAddProductBtn()){
            AddProductView productView = new AddProductView();
            Database db = Database.getInstance();
            TableModel model = TableModel.getInstance();
            AddProductController productController = new AddProductController(productView, db, model);
            productController.control();
        }
        else if(e.getSource() == view.getMakeTransactionBtn()){
            TransactionView transactionView = new TransactionView();
            TransactionController transactionController = new TransactionController(transactionView);
            transactionController.control();
        }
        else if(e.getSource() == view.getViewProductBtn()){
            AllProductsView productsView = new AllProductsView();
            TableModel model = new TableModel();
            AllProductsController productsController = new AllProductsController(productsView, model);
            productsController.control();
        }
    }  
}

