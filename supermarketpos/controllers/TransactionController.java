/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import supermarketpos.models.Database;
import supermarketpos.views.SummaryView;
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
    
    public void control()
    {
        view.getCancelTransactionBtn().addActionListener(this);
        view.getProceedTransactionBtn().addActionListener(this);
        Database.getInstance().connectToDatabase();
        ArrayList rows =Database.getInstance().fetchDataFromDatabase();
        Database.getInstance().closeDatabaseConnection();
        
        DefaultListModel listModel;
        listModel = new DefaultListModel();
        for(int i=0;i<rows.size();i++)
        {
            String[] temp = (String[])rows.get(i);
            listModel.addElement(temp[1]);
        }
        
        view.getProductList().setModel(listModel);
        
        view.setTitle("Make Transaction");
        view.setDefaultCloseOperation(view.DISPOSE_ON_CLOSE);
        view.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == view.getCancelTransactionBtn()){
            view.dispose();
        }
        else if(e.getSource() == view.getProceedTransactionBtn())
        {
            view.getProductList().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            int[] selectedIx = view.getProductList().getSelectedIndices();
            String[] names = new String[selectedIx.length];
            int[] quantity = new int[selectedIx.length];
            
            for(int i =0;i<names.length;i++)
            {
                names[i]= (String)view.getProductList().getModel().getElementAt(selectedIx[i]);
                int qty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the quantity for "+names[i]));
                quantity[i]= qty; 
            }
           
            SummaryView summaryView = new SummaryView();
            SummaryController  summaryController = new SummaryController(summaryView, names, quantity);
            view.dispose();
        }
    }
    
}
