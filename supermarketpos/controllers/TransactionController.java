/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import supermarketpos.views.TransactionView;

/**
 *
 * @author Anthony
 */
public class TransactionController implements ActionListener{
    
    TransactionView view = null;
    
    public TransactionController(TransactionView view){
        this.view = view;
    }
    
    public void control(){
        view.getCancelTransactionBtn().addActionListener(this);
        view.getProceedTransactionBtn().addActionListener(this);
        
        view.setTitle("Make Transaction");
        view.setDefaultCloseOperation(view.DISPOSE_ON_CLOSE);
        view.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == view.getCancelTransactionBtn()){
            view.dispose();
        }
        else if(e.getSource() == view.getProceedTransactionBtn()){
        
        }
    }
    
}
