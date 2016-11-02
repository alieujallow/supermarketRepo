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
public class SummaryController implements ActionListener
{
    SummaryView summaryView;
    public SummaryController(SummaryView sv)
    {
        summaryView=sv;
        summaryView.getCancelButton().addActionListener(this);
        summaryView.getCheckOutButton().addActionListener(this);
        summaryView.getCancelButton().setActionCommand("cancel");
        summaryView.getCheckOutButton().setActionCommand("checkout");
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equalsIgnoreCase("cancel"))
        {
            summaryView.setVisible(false);
        }
        else if(e.getActionCommand().equalsIgnoreCase("checkout"))
        {
            
        }
    }
}
