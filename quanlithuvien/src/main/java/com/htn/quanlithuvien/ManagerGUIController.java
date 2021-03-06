/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.htn.quanlithuvien;

import com.htn.pojo.Account;
import com.htn.pojo.AccountType;
import com.htn.pojo.Author;
import com.htn.pojo.Book;
import com.htn.pojo.BookAuthor;
import com.htn.pojo.Book_Cates;
import com.htn.pojo.Category;
import com.htn.pojo.LendingTicket;
import com.htn.pojo.LibraryCard;
import com.htn.pojo.Payment;
import com.htn.pojo.ReservationTicket;
import com.htn.services.AccountServices;
import com.htn.services.AuthorBookServices;
import com.htn.services.AuthorServices;
import com.htn.services.BookServices;
import com.htn.services.CateBookServices;
import com.htn.services.CategoryServices;
import com.htn.services.LendingTicketServices;
import com.htn.services.LibraryCardServices;
import com.htn.services.PaymentServices;
import com.htn.services.ReserveTicketServices;
import com.htn.utils.Utils;
import com.htn.utils.Utils2;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
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
    private static final CategoryServices c = new CategoryServices();
    private static final AuthorServices au = new AuthorServices();
    private static final LibraryCardServices cardServices = new LibraryCardServices();
    private static final LendingTicketServices lendingTicketServices = new LendingTicketServices();
    private static final ReserveTicketServices reserveTicketServices = new ReserveTicketServices();
    private static final PaymentServices paymentServices = new PaymentServices();
    private static final AuthorBookServices authorBookServices = new AuthorBookServices();
    private static final CateBookServices cateBookServices = new CateBookServices();
    private Calendar calendar = Calendar.getInstance();
    
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
    
    ObservableList<String> list = FXCollections.observableArrayList("C??n", "H???t");
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
    @FXML private ComboBox <String> cbAccountType;
    
    ObservableList<String> listGender = FXCollections.observableArrayList("Nam", "N???", "Kh??c");
    ObservableList<String> listAccountType = FXCollections.observableArrayList("Admin", "Student");
    //endregion
    
    //region attribute category
    @FXML private TableView <Category> tbCategories;
    @FXML private TextField txtCategory;
    @FXML private TextField txtIdCategory;
    @FXML private TextField txtKeywordCategory;
    //endregion
    
    //region attribute authors
    @FXML private TableView <Author> tbAuthors;
    @FXML private TextField txtAuthor;
    @FXML private TextField txtIdAuthor;
    @FXML private TextField txtKeywordAuthor;
    //endregion
    
    //region attribute card
    @FXML private TableView <LibraryCard> tbCards;
    @FXML private ComboBox <Account> cbAccount;
    @FXML private TextField txtCardNumber;
    //endregion
    
    //region attribute LendingTicket
    @FXML private TableView <LendingTicket> tbLending;
    //endregion
    
    //region attribute ReservationTicket
    @FXML private TableView <ReservationTicket> tbReservation;
    //endregion
    
    //region attribute payment
    @FXML private TableView <Payment> tbPayment;
    //endregion
    
    //region attribute book_author
    @FXML private TableView <BookAuthor> tbBookAuthor;
    @FXML private ComboBox <Book> cbBook1;
    @FXML private ComboBox <Author> cbAuthors;
    //endregion
    
    //region attribute book_cate
    @FXML private TableView <Book_Cates> tbBookCate;
    @FXML private ComboBox <Book> cbBook2;
    @FXML private ComboBox <Category> cbCategories;
    //endregion
    
    //Region book manager
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.loadColumnBook();
        this.loadDataBook(null);
        cbStatus.setItems(list);
        cbStatus.setValue(list.get(0));
        cbGender.setItems(listGender);
        cbGender.setValue(listGender.get(0));
        cbAccountType.setItems(listAccountType);
        cbAccountType.setValue(listAccountType.get(1));
        try {
            this.cbAccount.setItems(FXCollections.observableList(a.getAccount(null)));
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.cbBook1.setItems(FXCollections.observableList(s.getBook(null)));
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.cbBook2.setItems(FXCollections.observableList(s.getBook(null)));
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.cbCategories.setItems(FXCollections.observableList(c.getCategory(null)));
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.cbAuthors.setItems(FXCollections.observableList(au.getAuthor(null)));
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtKeyWord.textProperty().addListener((evt) ->{
            this.loadDataBook(this.txtKeyWord.getText());
        });
        
        
        this.loadColumnAccount();
        try {
            this.loadDataAccount(null);
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtKeywordAccount.textProperty().addListener((evt) ->{
            try {
                this.loadDataAccount(this.txtKeywordAccount.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.loadColumnCategory();
        try {
            this.loadDataCategory(null);
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtKeywordCategory.textProperty().addListener((evt) -> {
            try {
                this.loadDataCategory(this.txtKeywordCategory.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.loadColumnAuthors();
        try {
            this.loadDataAuthors(null);
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtKeywordAuthor.textProperty().addListener((evt) -> {
            try {
                this.loadDataAuthors(this.txtKeywordAuthor.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.loadColumnCard();
        this.loadDataCards(null);
        
        this.loadLendingColumn();
        try {
            this.loadDataLending();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.loadReserveColumn();
        try {
            this.loadDataReservations();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.loadColumnPayment();
        try {
            this.loadDataPayment();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.loadColumnBookAuthor();
        try {
            this.loadDataBookAuthor();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.loadColumnBookCategory();
        try {
            this.loadDataBookCategory();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        if(txtName.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter Name!", Alert.AlertType.WARNING).show();
        }
        else if(txtDescription.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter Description!", Alert.AlertType.WARNING).show();
        }
        else if(txtPrice.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter Price!", Alert.AlertType.WARNING).show();
        }
        else if(!Utils.isNumeric(txtPrice.getText())){
            Utils.showBox("Price isn't number!", Alert.AlertType.WARNING).show();
        }
        else if(txtDateOfPurcharse.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter DateOfPurcharse!", Alert.AlertType.WARNING).show();
        }
        else if(Utils2.convertSqlDateToUtilDate((Date) Utils.toSqlDate(this.txtDateOfPurcharse.getText())).after(Utils2.convertSqlDateToUtilDate(Utils.getCurrentDate2()))){
            Utils.showBox("Date not invalid!", Alert.AlertType.WARNING).show();
        }
        else if(txtPublicationPlace.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter PublicationPlace!", Alert.AlertType.WARNING).show();
        }
        else if((Date) Utils.toSqlDate(this.txtDateOfPurcharse.getText()) == null){
            Utils.showBox("Not date format!", Alert.AlertType.WARNING).show();
        }
        else{
            String name = this.txtName.getText();
            String des = this.txtDescription.getText();
            Double price = Double.parseDouble(this.txtPrice.getText());
            Date dateOfPurcharse = (Date) Utils.toSqlDate(this.txtDateOfPurcharse.getText());
            String publicationPlace = this.txtPublicationPlace.getText();
            int status = 1;
            if(cbStatus.getSelectionModel().getSelectedItem().equals("C??n")){
               status = 1;
            }
            else if (cbStatus.getSelectionModel().getSelectedItem().equals("H???t")){
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
            cbStatus.setValue("H???t");
        }
        else if(b.getStatus() == 1){
            cbStatus.setValue("C??n");
        }
    }
    
    public void updateBook(ActionEvent evt) throws SQLException{
        if(txtName.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter Name!", Alert.AlertType.WARNING).show();
        }
        else if(txtDescription.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter Description!", Alert.AlertType.WARNING).show();
        }
        else if(txtPrice.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter Price!", Alert.AlertType.WARNING).show();
        }
        else if(!Utils.isNumeric(txtPrice.getText())){
            Utils.showBox("Price isn't number!", Alert.AlertType.WARNING).show();
        }
        else if(txtDateOfPurcharse.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter DateOfPurcharse!", Alert.AlertType.WARNING).show();
        }
        else if((Date) Utils.toSqlDate(this.txtDateOfPurcharse.getText()) == null){
            Utils.showBox("Not date format!", Alert.AlertType.WARNING).show();
        }
        else if(Utils2.convertSqlDateToUtilDate((Date) Utils.toSqlDate(this.txtDateOfPurcharse.getText())).after(Utils2.convertSqlDateToUtilDate(Utils.getCurrentDate2()))){
            Utils.showBox("Date not invalid!", Alert.AlertType.WARNING).show();
        }
        else if(txtPublicationPlace.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter PublicationPlace!", Alert.AlertType.WARNING).show();
        }
        else{
            int id = Integer.parseInt(this.txtId.getText());
            String name = this.txtName.getText();
            String des = this.txtDescription.getText();
            Double price = Double.parseDouble(this.txtPrice.getText());
            Date dateOfPurcharse = (Date) Utils.toSqlDate(this.txtDateOfPurcharse.getText());
            String publicationPlace = this.txtPublicationPlace.getText();
            int status = 1;
            if(cbStatus.getSelectionModel().getSelectedItem().equals("C??n")){
               status = 1;
            }
            else if (cbStatus.getSelectionModel().getSelectedItem().equals("H???t")){
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
        
        TableColumn col7 = new TableColumn("Type");
        col7.setCellValueFactory(new PropertyValueFactory("type"));
        col7.setPrefWidth(100);
        
        this.tbAccount.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
    }
    
    public void bindingAccount (MouseEvent evt){
        Account a = tbAccount.getSelectionModel().getSelectedItem();  
        
        txtIdAccount.setText("" + a.getId());
        txtNameAccount.setText("" + a.getName());
        txtUsername.setText("" + a.getUsername());
        txtPassword.setText("" + a.getPassword());
        txtbirthdate.setText("" + Utils.xuatNgayThangNam(a.getBirthday()));
        cbGender.setValue("" + a.getGender());
        if(a.getType()== AccountType.ADMIN){
            cbAccountType.setValue("Admin");
        }
        else if(a.getType() == AccountType.STUDENT){
            cbAccountType.setValue("Student");
        }
    }
    
    public void addAccount(ActionEvent evt) throws SQLException {
        if(txtNameAccount.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter name!", Alert.AlertType.WARNING).show();
        }
        else if(txtUsername.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter username!", Alert.AlertType.WARNING).show();
        }
        else if(txtPassword.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter pass!", Alert.AlertType.WARNING).show();
        }
        else if(txtbirthdate.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter birthday!", Alert.AlertType.WARNING).show();
        }
        else if(Utils2.convertSqlDateToUtilDate((Date) Utils.toSqlDate(this.txtbirthdate.getText())).after(Utils2.convertSqlDateToUtilDate(Utils.getCurrentDate2()))){
            Utils.showBox("Date not invalid!", Alert.AlertType.WARNING).show();
        }
        else if((Date) Utils.toSqlDate(this.txtbirthdate.getText()) == null){
            Utils.showBox("Not date format!", Alert.AlertType.WARNING).show();
        }
        else{
            int type = 1;
            String name = this.txtNameAccount.getText();
            String pass = this.txtPassword.getText();
            String username = this.txtUsername.getText();
            String gender = cbGender.getSelectionModel().getSelectedItem();
            Date birthdate = (Date) Utils.toSqlDate(this.txtbirthdate.getText());
            if(cbAccountType.getSelectionModel().getSelectedItem().equals("Admin")){
               type = 0;
            }
            else if (cbAccountType.getSelectionModel().getSelectedItem().equals("Student")){
               type = 1;
            }
        
            Account acc = new Account (name, pass, username, gender, birthdate, type);

            
            if (a.addAccount(acc) == true) {
                Utils.showBox("Add account successfully!", Alert.AlertType.INFORMATION).show();
                this.loadDataAccount(null);
            } else {
                 Utils.showBox("Add account failed", Alert.AlertType.WARNING).show();
            }
        }
    }
    
    public void updateAccount (ActionEvent evt) throws SQLException{
        if(txtNameAccount.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter name!", Alert.AlertType.WARNING).show();
        }
        else if(txtUsername.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter username!", Alert.AlertType.WARNING).show();
        }
        else if(txtPassword.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter pass!", Alert.AlertType.WARNING).show();
        }
        else if(txtbirthdate.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter birthday!", Alert.AlertType.WARNING).show();
        }
        else if((Date) Utils.toSqlDate(this.txtbirthdate.getText()) == null){
            Utils.showBox("Not date format!", Alert.AlertType.WARNING).show();
        }
        else if(Utils2.convertSqlDateToUtilDate((Date) Utils.toSqlDate(this.txtbirthdate.getText())).after(Utils2.convertSqlDateToUtilDate(Utils.getCurrentDate2()))){
            Utils.showBox("Date not invalid!", Alert.AlertType.WARNING).show();
        }
        else{
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
    
    //region category manager
    private void loadDataCategory (String kw) throws SQLException{
        this.tbCategories.setItems(FXCollections.observableList(c.getCategory(kw)));  
    }
    
    private void loadColumnCategory (){
        TableColumn col1 = new TableColumn("Id");
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col1.setPrefWidth(50);
        
        TableColumn col2 = new TableColumn("Name");
        col2.setCellValueFactory(new PropertyValueFactory("name"));
        col2.setPrefWidth(200);
        
        this.tbCategories.getColumns().addAll(col1, col2);
    }
    
    public void addCategory (ActionEvent evt) throws SQLException{
        if(txtCategory.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter name category!", Alert.AlertType.WARNING).show();
        }
        else{
            Category cate = new Category(txtCategory.getText());
        
            if (c.addCategory(cate) == true) {
                Utils.showBox("Add category successful!", Alert.AlertType.INFORMATION).show();
                this.loadDataCategory(null);
            } else {
                 Utils.showBox("Add category failed!", Alert.AlertType.WARNING).show();
            }
        }
    }
    
    public void bindingCategory (MouseEvent evt){
        Category cates = tbCategories.getSelectionModel().getSelectedItem();  
        
        txtIdCategory.setText("" + cates.getId());
        txtCategory.setText("" + cates.getName());
    }
    
    public void updateCategory (ActionEvent evt) throws SQLException{
        if(txtCategory.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter name category!", Alert.AlertType.WARNING).show();
        }
        else{
            Category cate = new Category( Integer.parseInt(txtIdCategory.getText()),txtCategory.getText());
        
            if (c.updateCategory(cate) == true) {
                Utils.showBox("update category successful!", Alert.AlertType.INFORMATION).show();
                this.loadDataCategory(null);
            } else {
                 Utils.showBox("update category failed!", Alert.AlertType.WARNING).show();
            }
        }
    }
    
    public void deleteCategory (ActionEvent evt) throws SQLException{
        int id = Integer.parseInt(this.txtIdCategory.getText());
        if (c.deleteCategory(id) == true) {
            Utils.showBox("Delete successful!", Alert.AlertType.INFORMATION).show();
            txtIdCategory.setText("");
            txtCategory.setText("");
            this.loadDataCategory(null);
        } else {
             Utils.showBox("Delete failed!", Alert.AlertType.WARNING).show();
        }
    }
    //endregion
    
    //region authors manager
    private void loadDataAuthors (String kw) throws SQLException{
        this.tbAuthors.setItems(FXCollections.observableList(au.getAuthor(kw)));  
    }
    
    private void loadColumnAuthors (){
        TableColumn col1 = new TableColumn("Id");
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col1.setPrefWidth(50);
        
        TableColumn col2 = new TableColumn("Name");
        col2.setCellValueFactory(new PropertyValueFactory("name"));
        col2.setPrefWidth(200);
        
        this.tbAuthors.getColumns().addAll(col1, col2);
    }
    
    public void addAuthor (ActionEvent evt) throws SQLException{
        if(txtAuthor.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter name author!", Alert.AlertType.WARNING).show();
        }
        else{
            Author author = new Author(txtAuthor.getText());
        
            if (au.addAuthor(author) == true) {
                Utils.showBox("Add author successful!", Alert.AlertType.INFORMATION).show();
                this.loadDataAuthors(null);
            } else {
                 Utils.showBox("Add author failed!", Alert.AlertType.WARNING).show();
            }
        }
    }
    
    public void bindingAuthor (MouseEvent evt){
        Author authors = tbAuthors.getSelectionModel().getSelectedItem();  
        
        txtIdAuthor.setText("" + authors.getId());
        txtAuthor.setText("" + authors.getName());
    }
    
    public void updateAuthor (ActionEvent evt) throws SQLException{
        if(txtAuthor.getText().replaceAll(" ", "").equals("")){
            Utils.showBox("Not enter name author!", Alert.AlertType.WARNING).show();
        }
        else{
            Author author = new Author( Integer.parseInt(txtIdAuthor.getText()),txtAuthor.getText());

            if (au.updateAuthor(author) == true) {
                Utils.showBox("update author successful!", Alert.AlertType.INFORMATION).show();
                this.loadDataAuthors(null);
            } else {
                 Utils.showBox("update author failed!", Alert.AlertType.WARNING).show();
            }   
        }
    }
    
    public void deleteAuthor(ActionEvent evt) throws SQLException{
        int id = Integer.parseInt(this.txtIdAuthor.getText());
        if (au.deleteAuthor(id) == true) {
            Utils.showBox("Delete successful!", Alert.AlertType.INFORMATION).show();
            txtIdAuthor.setText("");
            txtAuthor.setText("");
            this.loadDataAuthors(null);
        } else {
             Utils.showBox("Delete failed!", Alert.AlertType.WARNING).show();
        }
    }
    //endregion
    
    //region book_author manager
    private void loadColumnBookAuthor (){
        TableColumn col1 = new TableColumn("BookName");
        col1.setCellValueFactory(new PropertyValueFactory("bookName"));
        col1.setPrefWidth(200);
        
        TableColumn col2 = new TableColumn("AuthorName");
        col2.setCellValueFactory(new PropertyValueFactory("authorName"));
        col2.setPrefWidth(200);
        
        this.tbBookAuthor.getColumns().addAll(col1, col2);
    }
    
    private void loadDataBookAuthor () throws SQLException{
        this.tbBookAuthor.setItems(FXCollections.observableList(authorBookServices.getBookAuthor()));
    }
    
    public void addAuthorBook (ActionEvent evt) throws SQLException{
        if (authorBookServices.addAuthorBook(this.cbBook2.getSelectionModel().getSelectedItem().getId(), this.cbAuthors.getSelectionModel().getSelectedItem().getId()) == true) {
            Utils.showBox("Add successfully !", Alert.AlertType.INFORMATION).show();
            this.loadDataBookAuthor();
        } else {
            Utils.showBox("Add failed", Alert.AlertType.WARNING).show();
        }
    
    }
    //endregion
    
    //region book_cates manager
    private void loadColumnBookCategory (){
        TableColumn col1 = new TableColumn("BookName");
        col1.setCellValueFactory(new PropertyValueFactory("bookName"));
        col1.setPrefWidth(200);
        
        TableColumn col2 = new TableColumn("Category");
        col2.setCellValueFactory(new PropertyValueFactory("cateName"));
        col2.setPrefWidth(200);
        
        this.tbBookCate.getColumns().addAll(col1, col2);
    }
    
    private void loadDataBookCategory () throws SQLException{
        this.tbBookCate.setItems(FXCollections.observableList(cateBookServices.getBookCates()));
    }
    
    public void addCateBook (ActionEvent evt) throws SQLException{
        if (cateBookServices.addCateBook(this.cbBook1.getSelectionModel().getSelectedItem().getId(), this.cbCategories.getSelectionModel().getSelectedItem().getId()) == true) {
            Utils.showBox("Add successfully !", Alert.AlertType.INFORMATION).show();
            this.loadDataBookCategory();
        } else {
            Utils.showBox("Add failed", Alert.AlertType.WARNING).show();
        }
    
    }
    //endregion
    
    //region card manager
    private void loadColumnCard (){
        TableColumn col1 = new TableColumn("cardNumber");
        col1.setCellValueFactory(new PropertyValueFactory("cardNumber"));
        col1.setPrefWidth(100);
        
        TableColumn col2 = new TableColumn("Member name");
        col2.setCellValueFactory(new PropertyValueFactory("name"));
        col2.setPrefWidth(150);
        
        TableColumn col3 = new TableColumn("Gender");
        col3.setCellValueFactory(new PropertyValueFactory("gender"));
        col3.setPrefWidth(50);
        
        TableColumn col4 = new TableColumn("birthdate");
        col4.setCellValueFactory(new PropertyValueFactory("birthdate"));
        col4.setPrefWidth(100);
        
        TableColumn col5 = new TableColumn("IssuedAt");
        col5.setCellValueFactory(new PropertyValueFactory("issuedAt"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("Active");
        col6.setCellValueFactory(new PropertyValueFactory("active"));
        col6.setPrefWidth(50);
        
        TableColumn col7 = new TableColumn("AccountType");
        col7.setCellValueFactory(new PropertyValueFactory("accountType"));
        col7.setPrefWidth(100);
        
        this.tbCards.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
     }
    
    private void loadDataCards(String kw) {
        try {
            this.tbCards.setItems(FXCollections.observableList(cardServices.getCards(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void activeCard (ActionEvent evt) throws SQLException{
        String name = this.cbAccount.getSelectionModel().getSelectedItem().getName();
        String gender = this.cbAccount.getSelectionModel().getSelectedItem().getGender();
        Date birthday = this.cbAccount.getSelectionModel().getSelectedItem().getBirthday();
        int accountType = this.cbAccount.getSelectionModel().getSelectedItem().getType();
        int accountID = this.cbAccount.getSelectionModel().getSelectedItem().getId();
        LibraryCard lc = new LibraryCard (name, gender, birthday, Utils.toSqlDate(Utils.xuatNgayThangNam2(calendar.getTime())), 1, accountType, accountID);
        
        if (cardServices.addLibraryCard(lc) == true) {
            Utils.showBox("Active successfully !", Alert.AlertType.INFORMATION).show();
            this.loadDataCards(null);
        } else {
             Utils.showBox("Active failed", Alert.AlertType.WARNING).show();
        }
    }
    
    public void bindingCard(MouseEvent evt){
        LibraryCard lc = tbCards.getSelectionModel().getSelectedItem(); 
        this.txtCardNumber.setText("" + lc.getCardNumber());
    }
    
    public void active (ActionEvent evt) throws SQLException{
        int id = Integer.parseInt(this.txtCardNumber.getText());
        
        if (cardServices.changeActive(0, id) == true) {
            Utils.showBox("Active successfully !", Alert.AlertType.INFORMATION).show();
            this.loadDataCards(null);
        } else {
             Utils.showBox("Active failed", Alert.AlertType.WARNING).show();
        }
    }
    
    public void block (ActionEvent evt) throws SQLException{
        int id = Integer.parseInt(this.txtCardNumber.getText());
        
        if (cardServices.changeActive(1, id) == true) {
            Utils.showBox("Block successfully !", Alert.AlertType.INFORMATION).show();
            this.loadDataCards(null);
        } else {
             Utils.showBox("Block failed", Alert.AlertType.WARNING).show();
        }
    }
    //endregion
    
    //region lending
    public void loadLendingColumn (){
        TableColumn col1 = new TableColumn("ID");
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col1.setPrefWidth(50);
        
        TableColumn col2 = new TableColumn("Total");
        col2.setCellValueFactory(new PropertyValueFactory("totalBookLended"));
        col2.setPrefWidth(150);
        
        TableColumn col3 = new TableColumn("DateLending");
        col3.setCellValueFactory(new PropertyValueFactory("dateLending"));
        col3.setPrefWidth(150);
        
        TableColumn col4 = new TableColumn("Status");
        col4.setCellValueFactory(new PropertyValueFactory("status"));
        col4.setPrefWidth(50);
        
        TableColumn col5 = new TableColumn("AccountID");
        col5.setCellValueFactory(new PropertyValueFactory("accountID"));
        col5.setPrefWidth(50);
        this.tbLending.getColumns().addAll(col1, col2, col3, col4, col5);
    }
    
    public void loadDataLending () throws SQLException{
        this.tbLending.setItems(FXCollections.observableList(lendingTicketServices.getLendingTickets()));
    }
    //endreion
    
    //region reservation
    public void loadReserveColumn (){
        TableColumn col1 = new TableColumn("ID");
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col1.setPrefWidth(50);
        
        TableColumn col2 = new TableColumn("Total");
        col2.setCellValueFactory(new PropertyValueFactory("totalBookReserved"));
        col2.setPrefWidth(150);
        
        TableColumn col3 = new TableColumn("DateLending");
        col3.setCellValueFactory(new PropertyValueFactory("createdDate"));
        col3.setPrefWidth(150);
        
        TableColumn col4 = new TableColumn("Status");
        col4.setCellValueFactory(new PropertyValueFactory("status"));
        col4.setPrefWidth(50);
        
        TableColumn col5 = new TableColumn("AccountID");
        col5.setCellValueFactory(new PropertyValueFactory("accountID"));
        col5.setPrefWidth(50);
        this.tbReservation.getColumns().addAll(col1, col2, col3, col4, col5);
    }
    
    public void loadDataReservations () throws SQLException{
        this.tbReservation.setItems(FXCollections.observableList(reserveTicketServices.getReserveTicket()));
    }
    //endregion
    
    //region payment
    private void loadColumnPayment (){
        TableColumn col1 = new TableColumn("Id");
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col1.setPrefWidth(50);
        
        TableColumn col2 = new TableColumn("TotalCheckout");
        col2.setCellValueFactory(new PropertyValueFactory("totalCheckout"));
        col2.setPrefWidth(150);
        
        TableColumn col3 = new TableColumn("TotalCheckout");
        col3.setCellValueFactory(new PropertyValueFactory("totalCheckout"));
        col3.setPrefWidth(200);
        
        TableColumn col4 = new TableColumn("Fine");
        col4.setCellValueFactory(new PropertyValueFactory("fine"));
        col4.setPrefWidth(50);
        
        TableColumn col5 = new TableColumn("CreatedDate");
        col5.setCellValueFactory(new PropertyValueFactory("createdDate"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("AccountID");
        col6.setCellValueFactory(new PropertyValueFactory("accountID"));
        col6.setPrefWidth(100);
        
        TableColumn col7 = new TableColumn("LendingID");
        col7.setCellValueFactory(new PropertyValueFactory("lendingID"));
        col7.setPrefWidth(50);
        
        this.tbPayment.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
    }
    
     public void loadDataPayment () throws SQLException{
        this.tbPayment.setItems(FXCollections.observableList(paymentServices.getPayment()));
    }
    //endregion
}
