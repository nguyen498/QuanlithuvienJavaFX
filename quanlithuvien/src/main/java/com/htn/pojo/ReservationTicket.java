/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.pojo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationTicket {
    
    private int id;

    private int totalBookReserved;

    private Date createdDate;

    private int status;
    
    private int accountID;

    public ReservationTicket() {
    }

    public ReservationTicket(int id, int totalBookReserved, Date dateLanding, int status, int accountID) {
        this.id = id;
        this.totalBookReserved = totalBookReserved;
        this.createdDate = dateLanding;
        this.status = status;
        this.accountID = accountID;
    }
    
    public ReservationTicket(int totalBookReserved, Date dateLanding, int status, int accountID) {
        this.totalBookReserved = totalBookReserved;
        this.createdDate = dateLanding;
        this.status = status;
        this.accountID = accountID;
    }
    
    public ReservationTicket(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.totalBookReserved = rs.getInt("totalBookReserved");
        this.createdDate = rs.getDate("createdDate");
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
     * @return the totalBookReserved
     */
    public int getTotalBookReserved() {
        return totalBookReserved;
    }

    /**
     * @param totalBookReserved the totalBookReserved to set
     */
    public void setTotalBookReserved(int totalBookReserved) {
        this.totalBookReserved = totalBookReserved;
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
