/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.htn.quanlithuvien;

import com.htn.utils.JdbcUtils;
import com.htn.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class LoginController implements Initializable {

    @FXML private TextField txtUsername;
    @FXML private TextField txtPass;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void loginHandle (ActionEvent evt) throws IOException, SQLException{
        if(txtUsername.getText().equals("")){
            Utils.showBox("Chưa nhập Username", Alert.AlertType.ERROR).show();
        }
        else if (txtPass.getText().equals("")){
            Utils.showBox("Chưa nhập Passwword", Alert.AlertType.ERROR).show();
        }
        else {
            try(Connection conn = JdbcUtils.getConn()){
                String sql = "SELECT * FROM account WHERE name = ? AND password = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, txtUsername.getText());
                stm.setString(2, txtPass.getText());
                
                ResultSet rs = stm.executeQuery();
                if(rs.next()){
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ManagerGUI.fxml"));
        
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage ();
                    stage.setScene(scene);
                    stage.setTitle("Manager");
                    stage.show();
                }
                else{
                    Utils.showBox("Đăng nhập thất bại!", Alert.AlertType.ERROR).show();
                }
            }
        }
        
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
