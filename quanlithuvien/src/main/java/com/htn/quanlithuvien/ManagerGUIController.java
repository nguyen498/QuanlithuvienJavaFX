/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.htn.quanlithuvien;

import com.htn.pojo.Account;
import com.htn.pojo.Book;
import com.htn.services.AccountServices;
import com.htn.services.BookServices;
import com.htn.utils.Utils;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author admin
 */

public class ManagerGUIController implements Initializable {
    private static final BookServices s = new BookServices();
    private static final AccountServices a = new AccountServices();
    /**
     * Initializes the controller class.
     */
    //region attribute book
    @FXML private TableView <Book> tbBook;
    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtDescription;
    @FXML private TextField txtPrice;
    @FXML private TextField txtDateOfPurcharse;
    @FXML private TextField txtPublicationPlace;
    @FXML private TextField txtKeyWord;
    @FXML private ComboBox <String> cbStatus;
    
    ObservableList<String> list = FXCollections.observableArrayList("Còn", "Hết");
    //endregion
    
    //region attribute account
    @FXML private TableView <Account> tbAccount;
    @FXML private TextField txtIdAccount;
    @FXML private TextField txtNameAccount;
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private TextField txtbirthdate;
    @FXML private TextField txtKeywordAccount;
    @FXML private ComboBox <String> cbGender;
    
    ObservableList<String> listGender = FXCollections.observableArrayList("Nam", "Nữ", "Khác");
    //endregion
    
    //Region book manager
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //book
        this.loadColumnBook();
        this.loadDataBook(null);
        cbStatus.setItems(list);
        
        this.txtKeyWord.textProperty().addListener((evt) ->{
            this.loadDataBook(this.txtKeyWord.getText());
        });
        
        //book
        this.loadColumnAccount();
        try {
            this.loadDataAccount(null);
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbGender.setItems(listGender);
        this.txtKeywordAccount.textProperty().addListener((evt) ->{
            try {
                this.loadDataAccount(this.txtKeywordAccount.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }    
    
    private void loadDataBook(String kw) {
        try {
            // Load Book Data
            this.tbBook.setItems(FXCollections.observableList(s.getBook(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private void loadColumnBook (){
        TableColumn col1 = new TableColumn("Id");
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col1.setPrefWidth(50);
        
        TableColumn col2 = new TableColumn("Book name");
        col2.setCellValueFactory(new PropertyValueFactory("name"));
        col2.setPrefWidth(150);
        
        TableColumn col3 = new TableColumn("Description");
        col3.setCellValueFactory(new PropertyValueFactory("description"));
        col3.setPrefWidth(200);
        
        TableColumn col4 = new TableColumn("Price");
        col4.setCellValueFactory(new PropertyValueFactory("price"));
        col4.setPrefWidth(50);
        
        TableColumn col5 = new TableColumn("Date of purcharse");
        col5.setCellValueFactory(new PropertyValueFactory("dateOfPurcharse"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("Publication place");
        col6.setCellValueFactory(new PropertyValueFactory("publicationPlace"));
        col6.setPrefWidth(100);
        
        TableColumn col7 = new TableColumn("Status");
        col7.setCellValueFactory(new PropertyValueFactory("status"));
        col7.setPrefWidth(50);
        
        this.tbBook.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
     }
    
    public void addBook(ActionEvent evt) throws SQLException {
        String name = this.txtName.getText();
        String des = this.txtDescription.getText();
        Double price = Double.parseDouble(this.txtPrice.getText());
        Date dateOfPurcharse = (Date) Utils.toSqlDate(this.txtDateOfPurcharse.getText());
        String publicationPlace = this.txtPublicationPlace.getText();
        int status = 1;
        if(cbStatus.getSelectionModel().getSelectedItem().equals("Còn")){
           status = 1;
        }
        else if (cbStatus.getSelectionModel().getSelectedItem().equals("Hết")){
           status = 0;
        }

        Book b = new Book (name, des, price, dateOfPurcharse, publicationPlace, status);
        if (s.addBook(b) == true) {
            Utils.showBox("Add successful!", Alert.AlertType.INFORMATION).show();
            this.loadDataBook(null);
        } else {
             Utils.showBox("Add failed!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void bindingBook (MouseEvent evt){
        Book b = tbBook.getSelectionModel().getSelectedItem();  
        txtId.setText("" + b.getId());
        txtName.setText(b.getName());
        txtDescription.setText(b.getDescription());
        txtPrice.setText("" + b.getPrice());
        txtDateOfPurcharse.setText("" + Utils.xuatNgayThangNam(b.getDateOfPurcharse()));
        txtPublicationPlace.setText(b.getPublicationPlace());
        if(b.getStatus() == 0){
            cbStatus.setValue("Hết");
        }
        else if(b.getStatus() == 1){
            cbStatus.setValue("Còn");
        }
    }
    
    public void updateBook(ActionEvent evt) throws SQLException{
        int id = Integer.parseInt(this.txtId.getText());
        String name = this.txtName.getText();
        String des = this.txtDescription.getText();
        Double price = Double.parseDouble(this.txtPrice.getText());
        Date dateOfPurcharse = (Date) Utils.toSqlDate(this.txtDateOfPurcharse.getText());
        String publicationPlace = this.txtPublicationPlace.getText();
        int status = 1;
        if(cbStatus.getSelectionModel().getSelectedItem().equals("Còn")){
           status = 1;
        }
        else if (cbStatus.getSelectionModel().getSelectedItem().equals("Hết")){
           status = 0;
        }

        Book b = new Book (id, name, des, price, dateOfPurcharse, publicationPlace, status);
        if (s.updateBook(b) == true) {
            Utils.showBox("Update successful!", Alert.AlertType.INFORMATION).show();
            this.loadDataBook(null);
        } else {
             Utils.showBox("Update failed!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void deleteBook (ActionEvent evt) throws SQLException{
        int id = Integer.parseInt(this.txtId.getText());
        if (s.deleteBook(id) == true) {
            Utils.showBox("Delete successful!", Alert.AlertType.INFORMATION).show();
            txtId.setText("");
            txtName.setText("");
            txtDescription.setText("");
            txtPrice.setText("" );
            txtDateOfPurcharse.setText("");
            txtPublicationPlace.setText("");
            this.loadDataBook(null);
        } else {
             Utils.showBox("Delete failed!", Alert.AlertType.WARNING).show();
        }
    }
    //endregion
    
    //Region account manager
    private void loadDataAccount (String kw) throws SQLException{
        this.tbAccount.setItems(FXCollections.observableList(a.getAccount(kw)));
    }
    
    private void loadColumnAccount (){
        TableColumn col1 = new TableColumn("Id");
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col1.setPrefWidth(50);
        
        TableColumn col2 = new TableColumn("Name");
        col2.setCellValueFactory(new PropertyValueFactory("name"));
        col2.setPrefWidth(100);
        
        TableColumn col3 = new TableColumn("Password");
        col3.setCellValueFactory(new PropertyValueFactory("password"));
        col3.setPrefWidth(100);
        
        TableColumn col4 = new TableColumn("Username");
        col4.setCellValueFactory(new PropertyValueFactory("username"));
        col4.setPrefWidth(100);
        
        TableColumn col5 = new TableColumn("Gender");
        col5.setCellValueFactory(new PropertyValueFactory("gender"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("Birthday");
        col6.setCellValueFactory(new PropertyValueFactory("birthday"));
        col6.setPrefWidth(100);
        
        this.tbAccount.getColumns().addAll(col1, col2, col3, col4, col5, col6);
    }
    
    public void bindingAccount (MouseEvent evt){
        Account a = tbAccount.getSelectionModel().getSelectedItem();  
        
        txtIdAccount.setText("" + a.getId());
        txtNameAccount.setText("" + a.getName());
        txtUsername.setText("" + a.getUsername());
        txtPassword.setText("" + a.getPassword());
        txtbirthdate.setText("" + Utils.xuatNgayThangNam(a.getBirthday()));
        cbGender.setValue("" + a.getGender());
    }
    
    public void updateAccount (ActionEvent evt) throws SQLException{
        int id = Integer.parseInt(this.txtIdAccount.getText());
        String name = this.txtNameAccount.getText();
        String pass = this.txtPassword.getText();
        String username = this.txtUsername.getText();
        String gender = cbGender.getSelectionModel().getSelectedItem();
        Date birthdate = (Date) Utils.toSqlDate(this.txtbirthdate.getText());

        Account acc = new Account (id, name, pass, username, gender, birthdate, 0);
        if (a.updateAccount(acc) == true) {
            Utils.showBox("Update successful!", Alert.AlertType.INFORMATION).show();
            this.loadDataAccount(null);
        } else {
             Utils.showBox("Update failed!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void deleteAccount (ActionEvent evt) throws SQLException{
        int id = Integer.parseInt(this.txtIdAccount.getText());
        if (a.deleteAccount(id) == true) {
            Utils.showBox("Delete successful!", Alert.AlertType.INFORMATION).show();
            txtIdAccount.setText("");
            txtNameAccount.setText("");
            txtPassword.setText("");
            txtUsername.setText("" );
            txtbirthdate.setText("");
            cbGender.setValue("");
            this.loadDataAccount(null);
        } else {
             Utils.showBox("Delete failed!", Alert.AlertType.WARNING).show();
        }
    }
    //endregion
}
