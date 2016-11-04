/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.models;

/**
 * A class to represent a product to be sold in a supermarket.
 * 
 * @author Anthony
 */
public class Product {
    
    //Instance variables
    private String productName;
    private Double price;
    private Integer quantity;
    
    /**
     * A constructor that initializes the values of the instance variables.
     * 
     * @param productName name of the product.
     * @param price price of the product.
     * @param quantity quantity of the product.
     */
    public Product(String productName, double price, int quantity){
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
    
    /**
     * A method that returns the name of a product.
     * 
     * @return productName the name of the product as a String.
     */
    public String getProductName(){
        return productName;
    }
    
    /**
     * A method that returns the price of a product.
     * 
     * @return price the name of the product as a double.
     */
    public Double getPrice(){
        return price;
    }
    
    /**
     * A method that returns the quantity of a product.
     * 
     * @return quantity the name of the product as a int.
     */
    public Integer getQuantity(){
        return quantity;
    }
    
  
    /**
     * A method updates the name of a product to a new value.
     * 
     * @param newProductName the new value of the product as a String.
     */
    public void setProductName(String newProductName){
        productName = newProductName;
    }
    
    /**
     * A method updates the price of a product to a new value.
     * 
     * @param newPrice the new value of the product as a double.
     */
    public void setPrice(double newPrice){
        price = newPrice;
    }
    
    /**
     * A method updates the quantity of a product to a new value.
     * 
     * @param newQuantity the new value of the product as a int.
     */
    public void setQuantity(int newQuantity){
        quantity = newQuantity;
    }
    
}
