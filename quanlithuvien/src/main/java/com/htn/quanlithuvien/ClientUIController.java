/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.htn.quanlithuvien;

import com.htn.pojo.Book;
import com.htn.services.BookServices;
import com.htn.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ClientUIController implements Initializable {
    private static final BookServices s = new BookServices();
    // Book Table
    @FXML private TableView <Book> tbBook;
    // Các Cột Thông tin sách
    @FXML private TextField txtChonSach;
    @FXML private TextField txtBookID;
    @FXML private TextField txtBookPrice;
    // Các Cột Thực hiện cho mượn
    @FXML private TextField txtChonDocGia;
    @FXML private TextField txtAccountID;
    @FXML private TextField txtTotalBookLending;
    @FXML private TextField txtDueDate;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Book Table
        generateBookTable();
        loadDataBook(null);
    }    
    
    public void adminBtnOnclick (ActionEvent evt) throws IOException{
        showManagerGUI();
    }
    
   public void showManagerGUI () throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ManagerGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage ();
        stage.setScene(scene);
        stage.setTitle("Admin");
        stage.show();
    }
   
    private void loadDataBook(String kw) {
        try {
            // Load Book Data
            this.tbBook.setItems(FXCollections.observableList(s.getBook(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void generateBookTable (){
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
    
    public void bindingBook (MouseEvent evt){
        Book b = tbBook.getSelectionModel().getSelectedItem();  
        // Nếu status sách == 0 thì xuất thông báo k cho mượn
        if(b.getStatus() == 0){
            Utils.showBox("Hiện tại sách này không cho mượn, vui lòng chọn sách khác", Alert.AlertType.ERROR).show();
        }
        else {
            txtChonSach.setText(b.getName());
            txtBookID.setText("" + b.getId());
            txtBookPrice.setText("" + b.getPrice());
        }
    }
    
    
    
}
