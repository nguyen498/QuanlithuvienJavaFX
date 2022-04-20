/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.pojo;

import java.sql.Date;

/**
 *
 * @author Administrator
 */

public class LendingDetail {
    
    private Date dueDate;
    
    private double amount;

    private int bookID;

    private int lendingID;

    public LendingDetail() {
    }

    public LendingDetail(Date dueDate, double amount, int bookID, int lendingID) {
        this.dueDate = dueDate;
        this.amount = amount;
        this.bookID = bookID;
        this.lendingID = lendingID;
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
    
    

    

}
