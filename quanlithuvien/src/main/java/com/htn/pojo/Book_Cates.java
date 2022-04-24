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
public class Book_Cates {
    private String bookName;
    private String cateName;

    public Book_Cates() {
    }

    public Book_Cates(String bookName, String CateName) {
        this.bookName = bookName;
        this.cateName = CateName;
    }
    
    public Book_Cates(ResultSet rs) throws SQLException {
        this.bookName = rs.getString("book.name");
        this.cateName = rs.getString("category.name");
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
     * @return the cateName
     */
    public String getCateName() {
        return cateName;
    }

    /**
     * @param CateName the cateName to set
     */
    public void setCateName(String CateName) {
        this.cateName = CateName;
    }
    
    
}
