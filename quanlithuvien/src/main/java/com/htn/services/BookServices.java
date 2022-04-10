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
import java.sql.Date;

/**
 *
 * @author admin
 */
public class BookServices {
    public List<Book> getBook (String kw) throws SQLException{
    
        try (Connection conn = JdbcUtils.getConn()) {
           String sql = "SELECT * FROM book";
           if (kw != null && !kw.isEmpty())
               sql += "WHERE name like concat ('%',?,'%')";
           PreparedStatement stm = conn.prepareStatement(sql);
           if (kw != null && !kw.isEmpty())
               stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           
           List<Book> books = new ArrayList<>();
           
           while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               String description = rs.getString("description");
               double price = rs.getDouble("price");
               Date dateOfPurcharse = rs.getDate("dateOfPurcharse");
               String publicationPlace = rs.getString("publicationPlace");
               int status = rs.getInt("status");    
               books.add(new Book(id,name,description ,price ,dateOfPurcharse,publicationPlace, status));
           }
        return books;
        }
    }
    
    public boolean addBook (Book b) throws SQLException{
        String sql = "INSERT INTO (name, description, price, dateOfPurcharse, publicationPlace, status) VALUES (?, ?, ?, ?, ?, ?)";
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
}

