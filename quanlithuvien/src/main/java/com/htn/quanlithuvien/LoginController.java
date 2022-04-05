/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.htn.quanlithuvien;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void loginHandle (ActionEvent evt) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ManagerGUI.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage ();
        stage.setScene(scene);
        stage.setTitle("Manager");
        stage.show();
    }
    
    public void signUpHandle (ActionEvent evt) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("SignUp.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage ();
        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.show();
    }

}
