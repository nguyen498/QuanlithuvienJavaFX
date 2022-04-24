/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.Book_Cates;
import com.htn.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CateBookServices {
    public List <Book_Cates> getBookCates () throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT book.name , category.name\n" +
                         "FROM librarydb.category, librarydb.book, librarydb.category_book\n" +
                         "WHERE category.id = category_book.categoryID AND category_book.bookID = book.id";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            List<Book_Cates> bookCates = new ArrayList<>();

            while (rs.next()) {
                Book_Cates bookCate = new Book_Cates(rs);
                bookCates.add(bookCate);
            }
         return bookCates;
         }
    }
    
    public boolean addCateBook (int bookID, int cateID) throws SQLException{
        String sql = "INSERT INTO category_book (categoryID, bookID) " + "VALUES (?, ?)";
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, cateID);
                stm.setInt(2, bookID);
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
}
