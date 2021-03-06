
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.models;

/**
 *
 * @author Alieu
 */
//import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;


public class Database 
{
   private static Database instance = new Database();

    /*
    *
    *gets the instance of the database
    */
    public static Database getInstance() {
        return instance;
    }

    Connection conn = null;

   /**
    * 
    * connects to the database
    */
    public void connectToDatabase() {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/";

        //  Database credentials
        final String USER = "root";
        final String PASSWORD = "kontihene12";
        Statement statement;
        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            //createa query
            statement = conn.createStatement();
            String createDatabase = "CREATE DATABASE IF NOT EXISTS supermarketdatabase;";

            String useDatabase = "USE supermarketdatabase;";

            String createProductTable = "CREATE TABLE IF NOT EXISTS products(\n"
                    + "  productID Int UNIQUE NOT NULL AUTO_INCREMENT,\n"
                    + "  productName char(100),\n"
                    + "  price decimal,\n"
                    + "  quantity Int,\n"
                    + "  primary key (productID) \n"
                    + ");";

            String createEmployeesTable = "CREATE TABLE IF NOT EXISTS employees(\n"
                    + "  employeeID Int UNIQUE NOT NULL AUTO_INCREMENT,\n"
                    + "  name char(100),\n"
                    + "  role char(100),\n"
                    + "  userName char(100) UNIQUE,\n"
                    + "  password char(100),\n"
                    + "  primary key (employeeID) \n"
                    + ");";

            
            //String user1  = "Insert into employees (name,role,userName,password) values(\"Alieu jallow\",\"clerk\",\"arl\",\"12\");";
            
            String createTransactionsTable = "CREATE TABLE IF NOT EXISTS transactions(\n"
             + "  transactionID Int UNIQUE NOT NULL AUTO_INCREMENT,\n"
             + "  productId Int,\n"    
             + "  productName char(100),\n"
             + "  role char(100),\n"
             + "  userName char(100),\n"
             + "  date Date,\n"
             + "  primary key (transactionID), \n"
             + "  foreign key (productId) references  products(productID)\n"
             + ");";
            //Execute a query
            statement.executeUpdate(createDatabase);
            statement.executeUpdate(useDatabase);
            statement.executeUpdate(createProductTable);
            statement.executeUpdate(createEmployeesTable);
            statement.executeUpdate(createTransactionsTable);
            //statement.execute(user1);

        } catch (Exception ex) {
            System.err.println("Could not connect to the database");
            System.err.println(ex);
        } finally {

        }
    }

    /**
     * 
     * adds to the database
     * @param productName
     * @param price
     * @param quantity 
     */
    public void addProductTodatabase(String productName, double price, int quantity) {
        try {
            String query = " insert into products ( productName,price,quantity)"
                    + " values (?, ?, ?)";
            java.sql.PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, productName);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println(ex);
            System.err.println("Could not add to the database");
        }
    }

    /**
     * 
     * deletes from the database
     * @param name 
     */
    public void delete(String name) {
        ResultSet resultSet;
        try {
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT productName FROM products");
            while (resultSet.next()) {
                if (resultSet.getString(1).equalsIgnoreCase(name)) {
                    String deleteProduct = "DELETE FROM products WHERE productName= ?";
                    java.sql.PreparedStatement preparedStatement = conn.prepareStatement(deleteProduct);
                    preparedStatement.setString(1, name);
                    preparedStatement.executeUpdate();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
            System.err.println("Could not DELETE.............");
        }
    }

    /**
     * 
     * fetches data
     * @return 
     */
    public ArrayList fetchDataFromDatabase() {
        ResultSet resultSet;
        ArrayList<String[]> rows = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                String[] tmp = new String[4];
                tmp[0] = resultSet.getString(1);
                tmp[1] = resultSet.getString(2);
                tmp[2] = resultSet.getString(3);
                tmp[3] = resultSet.getString(4);
                rows.add(tmp);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            System.err.println("Could not fetch.............");
        }
        return rows;
    }

    /**
     * 
     * close database connection
     */
    public void closeDatabaseConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * 
     * gets column headers
     * @return 
     */
    public String[] getColumnHeaders() {
        String[] columnNames = null;
        ResultSet resultSet;
        try {
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM products");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            columnNames = new String[numberOfColumns];

            for (int i = 1; i <= numberOfColumns; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }
        } catch (Exception ex) {

        }
        return columnNames;
    }

    /**
     * updates product
     * @param productName
     * @param price
     * @param quantity
     * @param productid 
     */
    public void updateProduct(String productName, double price, int quantity, int productid) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE products SET productName=?, price=?,"
                    + "quantity=? WHERE productID=" + "'" + productid + "'");
            ps.setString(1, productName);
            ps.setDouble(2, price);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("could not update");
        }
    }

   /**
    * 
    * inserts transaction
    * @param proID
    * @param proN
    * @param rol
    * @param usN
    * @param date 
    */
    public void insertTransaction(int proID,String proN,String rol,String usN,String date)
    {
        try {
            String query = " insert into transactions ( productId,productName,role,userName,date)"
                    + " values (?, ?, ?, ?, ?)";
            java.sql.PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, proID);
            preparedStatement.setString(2, proN);
            preparedStatement.setString(3, rol);
            preparedStatement.setString(4, usN);
            preparedStatement.setString(5, date);
           
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println(ex);
            System.err.println("Could not add to transactions");
        }  
    }
    
    
   /**
    * 
    * gets data
    * @return 
    */
    public ArrayList getDataFromTransactionsTable()
    {
        ResultSet resultSet;
        ArrayList<String[]> rows = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM transactions");
            while (resultSet.next()) {
                String[] tmp = new String[4];
                tmp[0] = resultSet.getString(1);
                tmp[1] = resultSet.getString(2);
                tmp[2] = resultSet.getString(3);
                tmp[3] = resultSet.getString(4);
                rows.add(tmp);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            System.err.println("Could not fetch.............");
        }
        return rows;
    }
    
    
    
    /**
     * 
     * validates credentials
     * @param username
     * @param password
     * @return 
     */
    public boolean validateEmployee(String username, String password) {
        boolean isValid = false;
        try {
            String query = "SELECT * FROM employees WHERE userName=?";
            java.sql.PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(3).equalsIgnoreCase("clerk")
                        && resultSet.getString(5).equalsIgnoreCase(String.valueOf(password))) {
                    isValid = true;
                }
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return isValid;
    }
}
