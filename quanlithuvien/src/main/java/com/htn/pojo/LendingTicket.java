/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.pojo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LendingTicket {
    
    private int id;

    private int totalBookLended;

    private Date dateLending;

    private int status;
    
    private int accountID;

    public LendingTicket() {
    }

    public LendingTicket(int id, int totalBookLended, Date dateLanding, int status, int accountID) {
        this.id = id;
        this.totalBookLended = totalBookLended;
        this.dateLending = dateLanding;
        this.status = status;
        this.accountID = accountID;
    }
    
    public LendingTicket(int totalBookLended, Date dateLanding, int status, int accountID) {
        this.totalBookLended = totalBookLended;
        this.dateLending = dateLanding;
        this.status = status;
        this.accountID = accountID;
    }
    
    public LendingTicket(ResultSet rs) throws SQLException {
        this.totalBookLended = rs.getInt("totalBookLended");
        this.dateLending = rs.getDate("dateLending");
        this.status = rs.getInt("status");
        this.accountID = rs.getInt("accountID");
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
     * @return the totalBookLended
     */
    public int getTotalBookLended() {
        return totalBookLended;
    }

    /**
     * @param totalBookLended the totalBookLended to set
     */
    public void setTotalBookLended(int totalBookLended) {
        this.totalBookLended = totalBookLended;
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

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
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

    

	

}
