/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import supermarketpos.views.SummaryView;

/**
 *
 * @author Anthony
 */
public class SummaryController implements ActionListener
{
    SummaryView summaryView;
    String [] productNames;
    int [] quantity;
    
    public SummaryController(SummaryView sv, String [] productNames, int [] quantity)
    {
        summaryView=sv;
        this.productNames = productNames;
        this.quantity = quantity;
        
        summaryView.getCancelButton().addActionListener(this);
        summaryView.getCheckOutButton().addActionListener(this);
        summaryView.getCancelButton().setActionCommand("cancel");
        summaryView.getCheckOutButton().setActionCommand("checkout");
        
        JLabel heading = new JLabel("PRODUCT \t QUANTITY \t PRICE");
        summaryView.add(heading);
        for(int i = 0; i < productNames.length; i++){
            JLabel details = new JLabel(productNames[i] + "\t" + quantity[i]);
            summaryView.add(details);
        }
        
        summaryView.setDefaultCloseOperation(summaryView.DISPOSE_ON_CLOSE);
        summaryView.setTitle("Summary of Transaction");
        summaryView.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equalsIgnoreCase("cancel"))
        {
            summaryView.setVisible(false);
        }
        else if(e.getActionCommand().equalsIgnoreCase("checkout"))
        {

            String amount = JOptionPane.showInputDialog(null, "Enter the amount");
            double change = Double.parseDouble(amount);
            
            JOptionPane.showMessageDialog(null, "CHANGE: " + change);
        }
    }
}
