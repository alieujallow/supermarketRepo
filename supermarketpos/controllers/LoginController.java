/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import supermarketpos.models.Database;
import supermarketpos.views.LoginView;
import supermarketpos.views.MenuView;

/**
 *
 * @author Anthony
 */
public class LoginController implements ActionListener 
{
    
  LoginView loginView;
  public LoginController(LoginView lv)
  {
    loginView = lv;
    loginView.getCancelButton().addActionListener(this);
    loginView.getLoginButton().addActionListener(this);
    loginView.getCancelButton().setActionCommand("cancel");
    loginView.getLoginButton().setActionCommand("login");
  }
  
  public void actionPerformed(ActionEvent e)
  {
      if(e.getActionCommand().equalsIgnoreCase("cancel"))
      {
          System.exit(0);
      }
      else if(e.getActionCommand().equalsIgnoreCase("login"))
      {
          String userName =loginView.getEmployeeIDTextField().getText();
          char[] password = loginView.getPasswordTextField().getPassword();
          if(Database.getInstance().validateEmployee(userName, password))
          {
              MenuView menuView = new MenuView();
              MenuController  menuController = new MenuController(menuView);
              loginView.setVisible(false);
          }
          else
          {
              System.out.println("You have entered a wrong passoword or employeeID");
          }  
      }
  }
  
}
