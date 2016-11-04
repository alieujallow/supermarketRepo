/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * A class to represent a Table model for viewing data in tabular form.
 *
 * @author Anthony
 */
public class TableModel extends AbstractTableModel {

    //instance variables
    Database db;
    String[] columnNames;
    static Object[][] data;
    static TableModel theModel = null;
    static ArrayList<String[]> rows;

    //constructor
    public TableModel() {
        db = Database.getInstance();
        db.connectToDatabase();
        columnNames = db.getColumnHeaders();
        rows = db.fetchDataFromDatabase();
        db.closeDatabaseConnection();
        convertTo2DArray();
    }

    //converts to two dimensional array
    public void convertTo2DArray() {
        data = new Object[rows.size()][4];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }
    }

    /**
     * Allows the user to access the data array outside this class.
     *
     * @return data a static instance of the data array.
     */
    public static ArrayList getArray() {
        return rows;
    }

    /**
     * Creates a new Table model instance or returns the existing one if any
     * exists.
     *
     * @return theModel a static instance of the Table model class
     */
    public static TableModel getInstance() {
        if (theModel == null) {
            theModel = new TableModel();
        }
        return theModel;
    }

    /**
     * A methods that returns an value at a specified cell.
     *
     * @return data[row][column] the value at the specified cell indicated by
     * the row and column.
     */
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    /**
     * A methods that returns an integer representing the number of columns in
     * the data.
     *
     * @return columnNames.length the number of columns in the data array.
     */
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * A methods that returns an integer representing the number of rows in the
     * data.
     *
     * @return data.length the number of items in the data array.
     */
    public int getRowCount() {
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

    //returns the column classes
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    //returns the column names
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void setValueAt(Object value, int row,int col)
    {
        data[row][col]=value;
        fireTableCellUpdated(row,col);
    }
}
