/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import java.util.List;
import com.htn.pojo.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.htn.utils.JdbcUtils;

/**
 *
 * @author admin
 */
public class BookServices {
    public List<Book> getBook (String kw) throws SQLException{
    
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM book WHERE name like concat ('%',?,'%');";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw == null)
                kw = "";
            
            stm.setString(1, kw);

            ResultSet rs = stm.executeQuery();

            List<Book> books = new ArrayList<>();

            while (rs.next()) {
                Book book = new Book(rs);
                books.add(book);
            }
         return books;
         }
    }
    
    
    public Book getBookByID (int id) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM book WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                Book book = new Book(rs);
                return book;
            }
            
            return null;
        }
    }
    
    public boolean addBook (Book b) throws SQLException{
        String sql = "INSERT INTO Book(name, description, price, dateOfPurcharse, publicationPlace, status) " + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, b.getName());
                stm.setString(2, b.getDescription());
                stm.setFloat(3,(float) b.getPrice());
                stm.setDate(4, b.getDateOfPurcharse());
                stm.setString(5, b.getPublicationPlace());
                stm.setInt(6, b.getStatus());
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
    
    
    public boolean updateBook (Book b) throws SQLException{
        String sql = "UPDATE book SET name = ?, description = ?, price = ?, dateOfPurcharse = ?, publicationPlace = ?, status = ? WHERE id in (?);";
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, b.getName());
                stm.setString(2, b.getDescription());
                stm.setFloat(3,(float) b.getPrice());
                stm.setDate(4, b.getDateOfPurcharse());
                stm.setString(5, b.getPublicationPlace());
                stm.setInt(6, b.getStatus());
                stm.setInt(7, b.getId());
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
    
    public boolean updateBookStatus (int bookID, int status) throws SQLException{
        String sql = "UPDATE book SET status = ? WHERE id in (?);";
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, bookID);
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
    
    public boolean deleteBook (int i) throws SQLException{
        String sql = "DELETE FROM book WHERE id =?";
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, "" + i);
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
}

