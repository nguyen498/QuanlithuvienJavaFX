/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.htn.quanlithuvien;

import com.htn.pojo.LendingDetail;
import com.htn.pojo.Account;
import com.htn.pojo.Book;
import com.htn.services.AccountServices;
import com.htn.services.BookServices;
import com.htn.services.LendingDetailServices;
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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ClientUIController implements Initializable {
    private static final BookServices s = new BookServices();
    private static final AccountServices a = new AccountServices();
    private static final LendingDetailServices lds = new LendingDetailServices();
    
    // Book Table
    @FXML 
    private TableView <Book> tbBook;
    
    // Các Cột Thông tin sách
    @FXML 
    private TextField txtChonSach;
    @FXML 
    private TextField txtBookID;
    @FXML 
    private TextField txtBookPrice;
    
    // Các Cột Thực hiện cho mượn
    @FXML 
    private TextField txtChonDocGia;
    @FXML 
    private TextField txtAccountID;
    @FXML 
    private TextField txtTotalBookLending;
    @FXML 
    private TextField txtReturnDate;
    
    // Các trường còn lại
    @FXML
    private Pane banner;
    
    @FXML
    private Button adminBtn;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnLending;

    @FXML
    private Button btnNavDatSach;

    @FXML
    private Button btnNavMuonSach;

    @FXML
    private Button btnNavTraSach;

    @FXML
    private Button btnSearchAccount;

    @FXML
    private Button btnSearchBook;

    @FXML
    private Label lbLink;

    @FXML
    private Label lbTitle;

    @FXML
    private TableView<Account> tbAccount;

    @FXML
    private TextField txtSearchAccount;

    @FXML
    private TextField txtSearchBook;

    @FXML
    private Button btnTraSach;

    @FXML
    private GridPane gpGroupTableMuonSach;

    @FXML
    private GridPane gpGroupTableTraSach;

    @FXML
    private GridPane gpGroupTextBoxMuonSach;

    @FXML
    private GridPane gpGroupTextBoxTraSach;
    
    
    
    
//    Table Trả Sách
    @FXML
    private TableView<Account> tbTraSachAccount;
    
    @FXML
    private TableView<LendingDetail> tbTraSachLendingDetail;
    
    @FXML
    private TextField txtTraSachChonDocGia;
    @FXML
    private TextField txtTraSachMaDocGia;
//    @FXML
//    private TextField txtTraSachSoLuongMuon;
    @FXML
    private TextField txtTraSachMaPhieuMuon;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           // Mặc định navigation mượn sách xuất hiện trước
            toggleNavagition("MUONSACH");

            // Book Table
            generateBookTable();
            loadDataBook(null);

            //Account Table
            generateAccountTable();
            loadDataAccount(null);
            
            //TraSach Account Table
            generateTableTraSachAccount();
            loadtbTraSachAccountData(null);
            
            //TraSach Account Table
            generateTableTraSachLendingDetail();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void loadAllData() throws SQLException {
        // Book Table
        loadDataBook(null);
            
        //Account Table
        loadDataAccount(null);
        
        //Account Table
        loadtbTraSachAccountData(null);
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
        if(b != null && b.getStatus() == 0){
            Utils.showBox("Hiện tại sách này không cho mượn, vui lòng chọn sách khác", Alert.AlertType.ERROR).show();
        }
        else {
            txtChonSach.setText(b.getName());
            txtBookID.setText("" + b.getId());
            txtBookPrice.setText("" + b.getPrice());
        }
    }
    
    
    private void loadDataAccount (String kw) throws SQLException{
        this.tbAccount.setItems(FXCollections.observableList(a.getAccount(kw)));  
    }
    
    private void generateAccountTable (){
        TableColumn col1 = new TableColumn("Id");
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col1.setPrefWidth(50);
        
        TableColumn col2 = new TableColumn("Name");
        col2.setCellValueFactory(new PropertyValueFactory("name"));
        col2.setPrefWidth(100);
        
        TableColumn col4 = new TableColumn("Username");
        col4.setCellValueFactory(new PropertyValueFactory("username"));
        col4.setPrefWidth(100);
        
        TableColumn col5 = new TableColumn("Gender");
        col5.setCellValueFactory(new PropertyValueFactory("gender"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("Birthday");
        col6.setCellValueFactory(new PropertyValueFactory("birthday"));
        col6.setPrefWidth(100);
        
        TableColumn col7 = new TableColumn("Type");
        col7.setCellValueFactory(new PropertyValueFactory("type"));
        col7.setPrefWidth(100);
        
        this.tbAccount.getColumns().addAll(col1, col2, col4, col5, col6, col7);
    }
    
    public void bindingAccount (MouseEvent evt){
        Account acc = tbAccount.getSelectionModel().getSelectedItem();  
        
        if (acc != null) {
            txtChonDocGia.setText("" + acc.getName());
            txtAccountID.setText("" + acc.getId());
            txtTotalBookLending.setText("" + 1);
        }
    }
    
    
    public void handleNavigationClick (ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == btnNavDatSach) {
            loadAllData();
            lbLink.setText("/home/datsach");
            lbTitle.setText("Đặt Sách");
            banner.setBackground(new Background(new BackgroundFill(Color.rgb(43, 63, 99), CornerRadii.EMPTY, Insets.EMPTY)));
            toggleNavagition("DATSACH");
            
            
        } else if (event.getSource() == btnNavMuonSach) {
            loadAllData();
            lbLink.setText("/home/muonsach");
            lbTitle.setText("Mượn Sách");
            banner.setBackground(new Background(new BackgroundFill(Color.rgb(99, 43, 63), CornerRadii.EMPTY, Insets.EMPTY)));
            toggleNavagition("MUONSACH");
            
        } else if (event.getSource() == btnNavTraSach) {
            loadAllData();
            lbLink.setText("/home/trasach");
            lbTitle.setText("Trả Sách");
            banner.setBackground(new Background(new BackgroundFill(Color.rgb(42, 28, 66), CornerRadii.EMPTY, Insets.EMPTY)));
            toggleNavagition("TRASACH");
            
        }
    }
    
    private void toggleNavagition(String trigger) {
        switch (trigger) {
            case "DATSACH":
                gpGroupTableMuonSach.setOpacity(0);
                gpGroupTextBoxMuonSach.setOpacity(0);
                gpGroupTableTraSach.setOpacity(0);
                gpGroupTextBoxTraSach.setOpacity(0);
                btnTraSach.setOpacity(0);
                btnLending.setOpacity(0);
                break;
                
            case "MUONSACH":
                gpGroupTableMuonSach.setOpacity(1);
                gpGroupTextBoxMuonSach.setOpacity(1);
                gpGroupTableMuonSach.toFront();
                gpGroupTextBoxMuonSach.toFront();
                gpGroupTableTraSach.setOpacity(0);
                gpGroupTextBoxTraSach.setOpacity(0);
                btnTraSach.setOpacity(0);
                btnLending.setOpacity(1);
                btnLending.toFront();
                break;
                
            case "TRASACH":
                gpGroupTableMuonSach.setOpacity(0);
                gpGroupTextBoxMuonSach.setOpacity(0);
                gpGroupTableTraSach.setOpacity(1);
                gpGroupTextBoxTraSach.setOpacity(1);
                gpGroupTableTraSach.toFront();
                gpGroupTextBoxTraSach.toFront();
                btnTraSach.setOpacity(1);
                btnTraSach.toFront();
                btnLending.setOpacity(0);
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    
    public void handleClose (ActionEvent event) throws IOException {
        if (event.getSource() == btnExit) {
            System.exit(0);
        }
    }
    
    
    public void handleLendingBtn (ActionEvent event) throws IOException, SQLException {
        int bookID = Integer.parseInt(txtBookID.getText());
        int accountID = Integer.parseInt(txtAccountID.getText());
        int numberBookLending = Integer.parseInt(txtTotalBookLending.getText());
        
        if (lds.lendingBook(bookID, accountID, numberBookLending) == true)
            Utils.showBox("Mượn sách thành công", Alert.AlertType.CONFIRMATION).show();
        else
            Utils.showBox("Có lỗi xảy ra ", Alert.AlertType.ERROR).show();
    }
   
    
    
    private void loadtbTraSachAccountData(String kw) {
        try {
            // Load Book Data
            this.tbTraSachAccount.setItems(FXCollections.observableList(a.getTraSachAccount(null)));
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void generateTableTraSachAccount (){
        TableColumn col1 = new TableColumn("Id");
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col1.setPrefWidth(50);
        
        TableColumn col2 = new TableColumn("Name");
        col2.setCellValueFactory(new PropertyValueFactory("name"));
        col2.setPrefWidth(100);
        
        TableColumn col5 = new TableColumn("Gender");
        col5.setCellValueFactory(new PropertyValueFactory("gender"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("Birthday");
        col6.setCellValueFactory(new PropertyValueFactory("birthday"));
        col6.setPrefWidth(100);
        
        TableColumn col7 = new TableColumn("TotalBook");
        col7.setCellValueFactory(new PropertyValueFactory("totalBookLended"));
        col7.setPrefWidth(100);
        
        TableColumn col8 = new TableColumn("LendingTicketID");
        col8.setCellValueFactory(new PropertyValueFactory("lendingTicketID"));
        col8.setPrefWidth(100);
        
        this.tbTraSachAccount.getColumns().addAll(col1, col2, col5, col6, col7, col8);
    }
    
    public void bindingtbTraSachAccount (MouseEvent evt) throws SQLException{
        Account acc = tbTraSachAccount.getSelectionModel().getSelectedItem();  
        
        this.tbTraSachLendingDetail.setItems(FXCollections.observableList(lds.getTraSachLendingDetail(acc.getLendingTicketID())));
        txtTraSachChonDocGia.setText(acc.getName());
        txtTraSachMaDocGia.setText("" + acc.getId());
        txtTraSachMaPhieuMuon.setText("" + acc.getLendingTicketID());
    }
    
    private void generateTableTraSachLendingDetail (){
        TableColumn col1 = new TableColumn("DueDate");
        col1.setCellValueFactory(new PropertyValueFactory("dueDate"));
        col1.setPrefWidth(50);
        
        TableColumn col5 = new TableColumn("Amount");
        col5.setCellValueFactory(new PropertyValueFactory("amount"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("BookID");
        col6.setCellValueFactory(new PropertyValueFactory("bookID"));
        col6.setPrefWidth(100);
        
        TableColumn col7 = new TableColumn("LendingID");
        col7.setCellValueFactory(new PropertyValueFactory("lendingID"));
        col7.setPrefWidth(100);
        
        
        this.tbTraSachLendingDetail.getColumns().addAll(col1, col5, col6, col7);
    }
}
