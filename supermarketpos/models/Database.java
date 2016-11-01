/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.models;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Anthony
 */
public class Database {
    
    public void updateProduct(String productName, double price, int quantity, int productid){
        try{ 
            PreparedStatement ps = conn.prepareStatement("UPDATE records SET productName=?, price=?,"
                    + "quantity=? WHERE productID="+"'"+productid+"'");
            
            ps.setString(2, productName);
            ps.setDouble(3, price);
            ps.setInt(4, quantity);
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    } 
    
    
    public boolean validateEmployee(String username, String password){
        boolean isValid = false;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "
                              + "employees WHERE userName="+"'"+username+"'");
            
            if(resultSet != null){
                if(resultSet.getString(3).equalsIgnoreCase("clerk") && 
                            resultSet.getString(5).equalsIgnoreCase(password)){
                    isValid = true;
                } 
            }
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        
        return isValid;
    }
     
}
