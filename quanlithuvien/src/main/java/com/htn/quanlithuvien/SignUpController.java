/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.htn.quanlithuvien;

import com.htn.pojo.Account;
import com.htn.services.AccountServices;
import com.htn.utils.Utils;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class SignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static final AccountServices s = new AccountServices();
    
    @FXML private TextField txtName;
    @FXML private TextField txtUsername;
    @FXML private TextField txtPass;
    @FXML private TextField txtConfirm;
    @FXML private TextField txtBirthday;
    @FXML private ComboBox<String> cbGender;
    
    ObservableList<String> list = FXCollections.observableArrayList("Nam", "Nữ", "Khác");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbGender.setItems(list);
    }

    public void register (ActionEvent evt) throws SQLException{
       
        Account a = new Account(txtName.getText(), txtUsername.getText(), txtPass.getText(), cbGender.getValue() ,(Date) Utils.toSqlDate(txtBirthday.getText()), 0);
        if(txtName.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter name!", Alert.AlertType.WARNING).show();
        }
        else if(txtUsername.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter username!", Alert.AlertType.WARNING).show();
        }
        else if(txtPass.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter pass!", Alert.AlertType.WARNING).show();
        }
        else if(txtBirthday.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter birthday!", Alert.AlertType.WARNING).show();
        }
        else if(cbGender.getValue().equals("")){
            Utils.showBox("Not choose Gender!", Alert.AlertType.WARNING).show();
        }
        else{
            if(txtPass.getText().equals(txtConfirm.getText())){
                if (s.addAccount(a) == true) {
                    Utils.showBox("Successful!", Alert.AlertType.INFORMATION).show();
                } else {
                    Utils.showBox("Failed!", Alert.AlertType.WARNING).show();
                }
            }
            else{
                Utils.showBox("Confirm password failed!", Alert.AlertType.ERROR).show();
            }
        }
    }
}
