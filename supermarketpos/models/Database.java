/**
 *
 * @author Alieu
 */

//import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Database 
{
     Connection conn = null;
  // connects to the database and create tables
  public void connectToDatabase()
  {
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost/";
    
    //  Database credentials
    final String USER = "root";
    final String PASSWORD = "";
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
        + "  userName char(100),\n"
        + "  password char(100),\n"
        + "  primary key (employeeID) \n"
        + ");";
      
      
      /*  String createTransactionsTable = "CREATE TABLE IF NOT EXISTS transactions(\n"
       + "  transactionID Int UNIQUE NOT NULL AUTO_INCREMENT IDENTITY(1,1),\n"
       + "  name char(100),\n"
       + "  role char(100),\n"
       + "  userName char(100),\n"
       + "  password char(100),\n"
       + "  primary key (employeeID) \n"
       + ");";*/
      
      //Execute a query
      statement.executeUpdate(createDatabase);
      statement.executeUpdate(useDatabase);
      statement.executeUpdate(createProductTable);
      statement.executeUpdate(createEmployeesTable);
      
    }
    catch (Exception ex)
    {
      System.err.println("Could not connect to the database");
      System.err.println(ex);
    }
    finally
    {
      
    }
  }
  
   //adding to the database
    public void addProductTodatabase(String productName, double price, int quantity) 
    {
        try {
            String query = " insert into products ( productName,price,quantity)"
                    + " values (?, ?, ?)";
            java.sql.PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, productName);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);
            preparedStatement.execute();
        } catch (Exception ex)
        {
            System.out.println(ex);
            System.err.println("Could not add to the database");
        }
    }
  
  

  
  
  
  
  public static void main(String[]args)
  {
    Database db = new Database();
    db.connectToDatabase();
    db.addProductTodatabase("donsimon",3.9,9);
  }
}
