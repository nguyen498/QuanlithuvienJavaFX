/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.pojo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment {

    private int id;
    
    private double totalBookPrice;
    
    private double totalCheckout;

    private double fine;

    private Date createdDate;

    private int accountID;
    
    private int lendingID;

    public Payment() {
    }

    public Payment(int id, double totalBookPrice, double totalCheckout, double fine, Date createdDate, int accountID, int lendingID) {
        this.id = id;
        this.totalBookPrice = totalBookPrice;
        this.totalCheckout = totalCheckout;
        this.fine = fine;
        this.createdDate = createdDate;
        this.accountID = accountID;
        this.lendingID = lendingID;
    }
    
    public Payment(double totalBookPrice, double totalCheckout, double fine, Date createdDate, int accountID, int lendingID) {
        this.totalBookPrice = totalBookPrice;
        this.totalCheckout = totalCheckout;
        this.fine = fine;
        this.createdDate = createdDate;
        this.accountID = accountID;
        this.lendingID = lendingID;
    }
    
    public Payment(double totalBookPrice, double totalCheckout, double fine) {
        this.totalBookPrice = totalBookPrice;
        this.totalCheckout = totalCheckout;
        this.fine = fine;
    }

    public Payment(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.totalBookPrice = rs.getDouble("totalBookPrice");
        this.totalCheckout = rs.getDouble("totalCheckout");
        this.fine = rs.getDouble("fine");
        this.createdDate = rs.getDate("createdDate");
        this.accountID = rs.getInt("accountID");
        this.lendingID = rs.getInt("lendingID");
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the totalBookPrice
     */
    public double getTotalBookPrice() {
        return totalBookPrice;
    }

    /**
     * @param totalBookPrice the totalBookPrice to set
     */
    public void setTotalBookPrice(double totalBookPrice) {
        this.totalBookPrice = totalBookPrice;
    }

    /**
     * @return the totalCheckout
     */
    public double getTotalCheckout() {
        return totalCheckout;
    }

    /**
     * @param totalCheckout the totalCheckout to set
     */
    public void setTotalCheckout(double totalCheckout) {
        this.totalCheckout = totalCheckout;
    }

    /**
     * @return the fine
     */
    public double getFine() {
        return fine;
    }

    /**
     * @param fine the fine to set
     */
    public void setFine(double fine) {
        this.fine = fine;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the accountID
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    public void setAccountID(int accountID) {
        this.accountID = accountID;
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
