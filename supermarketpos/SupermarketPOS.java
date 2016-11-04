/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos;

import supermarketpos.controllers.LoginController;
import supermarketpos.views.LoginView;

/**
 *
 * @author Anthony
 */
public class SupermarketPOS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginView  loginView = new LoginView();
        LoginController  loginController = new LoginController (loginView);
        
    }
    
}
