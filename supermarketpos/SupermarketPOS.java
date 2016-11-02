/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos;

import supermarketpos.controllers.MenuController;
import supermarketpos.views.MenuView;

/**
 *
 * @author Anthony
 */
public class SupermarketPOS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(menuView);
        menuController.control();
    }
    
}
