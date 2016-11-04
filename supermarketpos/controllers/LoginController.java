
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketpos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
    loginView.setVisible(true);
    loginView.pack();
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
          String pass = String.valueOf(password);

          if(!userName.equals("") && !pass.equals(""))
          {
            Database.getInstance().connectToDatabase();
            if(Database.getInstance().validateEmployee(userName,pass))
            {
                MenuView menuView = new MenuView();
                MenuController  menuController = new MenuController(menuView,userName);
                menuController.control();
                Database.getInstance().closeDatabaseConnection();
                loginView.setVisible(false);
            }
            else
            {
               JOptionPane.showMessageDialog(loginView,"You have entered a wrong passoword or employeeID",
                                              "Error Message " ,JOptionPane.ERROR_MESSAGE);
               loginView.getEmployeeIDTextField().setText("");
               loginView.getPasswordTextField().setText("");
               Database.getInstance().closeDatabaseConnection();
            }
         }
          else
          {
              JOptionPane.showMessageDialog(loginView,"You should enter both your username and password",
                                            "Error Message " ,JOptionPane.ERROR_MESSAGE);
          } 
      }
  }
  
}
