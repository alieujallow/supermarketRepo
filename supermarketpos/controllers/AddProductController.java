/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import supermarketpos.views.AddProductView;

/**
 *
 * @author Anthony
 */
public class AddProductController implements ActionListener{
    
    AddProductView view = null;
    
    public AddProductController(AddProductView view){
        this.view = view;
    }
    
    public void control(){
        view.getAddProductBtn().addActionListener(this);
        view.getCancelBtn().addActionListener(this);
        
        view.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == view.getAddProductBtn()){
        
        }
        else if(e.getSource() == view.getCancelBtn()){
            view.dispose();
        }
    }
    
}
