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

    private String bookID;

    private String lendingTicketID;

    private double amount;

    private Date returnDate;

    private Date dueDate;

    public LendingDetail() {
    }

    public LendingDetail(String bookID, String lendingTicketID, double amount, Date returnDate, Date dueDate) {
        this.bookID = bookID;
        this.lendingTicketID = lendingTicketID;
        this.amount = amount;
        this.returnDate = returnDate;
        this.dueDate = dueDate;
    }

        
        
    /**
     * @return the bookID
     */
    public String getBookID() {
        return bookID;
    }

    /**
     * @param bookID the bookID to set
     */
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    /**
     * @return the lendingTicketID
     */
    public String getLendingTicketID() {
        return lendingTicketID;
    }

    /**
     * @param lendingTicketID the lendingTicketID to set
     */
    public void setLendingTicketID(String lendingTicketID) {
        this.lendingTicketID = lendingTicketID;
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
     * @return the returnDate
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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
        
        

}
