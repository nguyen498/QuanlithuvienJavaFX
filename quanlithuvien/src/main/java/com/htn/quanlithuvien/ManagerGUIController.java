/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.htn.quanlithuvien;

import com.htn.pojo.Account;
import com.htn.pojo.Book;
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
    //endregion
    
    //Region book manager
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadColumnBook();
        this.loadDataBook(null);
        cbStatus.setItems(list);
        
        this.txtKeyWord.textProperty().addListener((evt) ->{
            this.loadDataBook(this.txtKeyWord.getText());
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
    private void loadDataAccount (String kw){
        
    }
    //endregion
}
