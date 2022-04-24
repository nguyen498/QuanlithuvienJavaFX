/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.BookAuthor;
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
public class AuthorBookServices {
    public List <BookAuthor> getBookAuthor () throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT book.name , author.name\n" +
                         "FROM librarydb.author, librarydb.book, librarydb.author_book\n" +
                         "WHERE author.id = author_book.authorID AND author_book.bookID = book.id";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            List<BookAuthor> bookAuthors = new ArrayList<>();

            while (rs.next()) {
                BookAuthor bookAuthor = new BookAuthor(rs);
                bookAuthors.add(bookAuthor);
            }
         return bookAuthors;
         }
    }
    
    public boolean addAuthorBook (int bookID, int authorID) throws SQLException{
        String sql = "INSERT INTO author_book (authorID, bookID) " + "VALUES (?, ?)";
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, authorID);
                stm.setInt(2, bookID);
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
}
