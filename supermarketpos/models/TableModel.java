/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.models;

import javax.swing.table.AbstractTableModel;

/**
 * A class to represent a Table model for viewing data in tabular form.
 * 
 * @author Anthony
 */
public class TableModel extends AbstractTableModel{
    
    static Object[][] data;
    static TableModel theModel = null;
    
    String[] columnNames =  new String [] {
                "Product Name", "Price", "Quantity"
            };
    
    /**
     * Allows the user to access the data array outside this class.
     * 
     * @return data a static instance of the data array.
     */
    public static Object getArray(){
        return data;
    }
    
    /**
     * Creates a new Table model instance or returns the existing one if any exists.
     * 
     * @return theModel a static instance of the Table model class
     */
    public static TableModel getInstance(){
        if (theModel==null)
            theModel= new TableModel();
        return theModel;
    }
    
    /**
     * A methods that returns an value at a specified cell.
     * 
     * @return data[row][column] the value at the specified cell indicated by 
     *          the row and column.
     */
    public Object getValueAt(int row, int column){
        return data[row][column];
    }
    
    /**
     * A methods that returns an int representing the number of columns in the data.
     * 
     * @return columnNames.length the number of columns in the data array.
     */
    public int getColumnCount(){
        return columnNames.length;
    }
    
    /**
     * A methods that returns an int representing the number of rows in the data.
     * 
     * @return data.length the number of items in the data array.
     */
    public int getRowCount(){
        return data.length;
    }
    
    /**
     * A methods that indicates that a cell in the data model cannot be edited.
     * 
     * @return false.
     */
    public boolean isCellEditable(int row, int col) {
	return false;
    }
    
    
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
}
