/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.pojo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */

public class LendingDetail {
    
    private Date dueDate;
    
    private double amount;

    private int bookID;

    private int lendingID;
    
    private String bookName;
    private Date dateLending;
    

    public LendingDetail() {
    }
    
    public LendingDetail(String bookName, double amount, Date dueDate, Date dateLending, int lendingID) {
        this.bookName = bookName;
        this.amount = amount;
        this.dueDate = dueDate;
        this.dateLending = dateLending;
        this.lendingID = lendingID;
    }

    public LendingDetail(Date dueDate, double amount, int bookID, int lendingID) {
        this.dueDate = dueDate;
        this.amount = amount;
        this.bookID = bookID;
        this.lendingID = lendingID;
    }
    
    public LendingDetail(ResultSet rs) throws SQLException {
        this.dueDate = rs.getDate("dueDate");
        this.amount = rs.getDouble("amount");
        this.bookID = rs.getInt("bookID");
        this.lendingID = rs.getInt("lendingID");
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the bookID
     */
    public int getBookID() {
        return bookID;
    }

    /**
     * @param bookID the bookID to set
     */
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    /**
     * @return the lendingID
     */
    public int getLendingID() {
        return lendingID;
    }

    /**
     * @param lendingID the lendingID to set
     */
    public void setLendingID(int lendingID) {
        this.lendingID = lendingID;
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
     * @return the dateLending
     */
    public Date getDateLending() {
        return dateLending;
    }

    /**
     * @param dateLending the dateLending to set
     */
    public void setDateLending(Date dateLending) {
        this.dateLending = dateLending;
    }
    
    

    

}
