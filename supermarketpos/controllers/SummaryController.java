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
import supermarketpos.views.SummaryView;

/**
 *
 * @author Anthony
 */
public class SummaryController implements ActionListener {

    //instance variables
    SummaryView summaryView;
    String[] names;
    int[] quantity;
    Double[] prices;
    Double sum = 0.0;

    //constructor
    public SummaryController(SummaryView sv, String[] nm, int[] qty) {
        names = nm;
        quantity = qty;
        summaryView = sv;

        prices = new Double[names.length];
        Database.getInstance().connectToDatabase();
        ArrayList rows = Database.getInstance().fetchDataFromDatabase();
        Database.getInstance().closeDatabaseConnection();

        //prints the receipt
        int totalQuantity = 0;
        summaryView.getreceipt().append("\t************************************************\n");
        summaryView.getreceipt().append("\tPRODUCT\tQUANTITY\tPRICE \n");
        summaryView.getreceipt().append("\t************************************************\n");
        for (int i = 0; i < rows.size(); i++) {
            String[] temp = (String[]) rows.get(i);
            for (int j = 0; j < names.length; j++) {
                if (names[j].equalsIgnoreCase(temp[1])) {
                    prices[j] = Double.parseDouble(temp[2]);
                    summaryView.getreceipt().append("\t" + names[j] + "\t" + quantity[j] + "\t" + prices[j] + " \n");
                    sum = sum + (quantity[j] * prices[j]);
                    totalQuantity = totalQuantity + quantity[j];
                }
            }
        }
        summaryView.getreceipt().append("\t************************************************\n");
        summaryView.getreceipt().append("\tTOTAL\t" + totalQuantity + "\tGHC " + sum);

        summaryView.getCancelButton().addActionListener(this);
        summaryView.getCheckOutButton().addActionListener(this);
        summaryView.getCancelButton().setActionCommand("cancel");
        summaryView.getCheckOutButton().setActionCommand("checkout");

        summaryView.setDefaultCloseOperation(summaryView.DISPOSE_ON_CLOSE);
        summaryView.setTitle("Summary of Transaction");
        summaryView.setVisible(true);
    }

    //action performed method
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("cancel")) {
            summaryView.setVisible(false);
        } else if (e.getActionCommand().equalsIgnoreCase("checkout")) {
            String amount = JOptionPane.showInputDialog(null, "Enter the amount");
            try {
                double change = Double.parseDouble(amount);
                if (change >= sum) {
                    double x = change - sum;
                    JOptionPane.showMessageDialog(null, "CHANGE: " + x);
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient Funds");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "You have entered an inavalid amount");
            }
        }
    }
}
