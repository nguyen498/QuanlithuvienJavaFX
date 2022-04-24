/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class BookAuthor {
    private String bookName;
    private String authorName;

    public BookAuthor() {
    }

    public BookAuthor(String bookName, String authorName) {
        this.bookName = bookName;
        this.authorName = authorName;
    }
    
    public BookAuthor(ResultSet rs) throws SQLException {
        this.bookName = rs.getString("book.name");
        this.authorName = rs.getString("author.name");
    }

    /**
     * @return the bookName
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * @param bookName the bookName to set
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * @return the authorName
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * @param authorName the authorName to set
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}