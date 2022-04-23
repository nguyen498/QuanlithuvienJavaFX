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

public class ReservationDetail {
    
    private Date dueDate;
    
    private int bookID;

    private int reservationID;
    
    private String bookName;
    private Date dateReserving;
    

    public ReservationDetail() {
    }
    
    public ReservationDetail(String bookName, Date dueDate, Date dateReserving, int reservationID) {
        this.bookName = bookName;
        this.dueDate = dueDate;
        this.dateReserving = dateReserving;
        this.reservationID = reservationID;
    }

    public ReservationDetail(Date dueDate, int bookID, int reservationID) {
        this.dueDate = dueDate;
        this.bookID = bookID;
        this.reservationID = reservationID;
    }
    
    public ReservationDetail(ResultSet rs) throws SQLException {
        this.dueDate = rs.getDate("dueDate");
        this.bookID = rs.getInt("bookID");
        this.reservationID = rs.getInt("reservationID");
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
     * @return the reservationID
     */
    public int getReservationID() {
        return reservationID;
    }

    /**
     * @param reservationID the reservationID to set
     */
    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
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
     * @return the dateReserving
     */
    public Date getDateReserving() {
        return dateReserving;
    }

    /**
     * @param dateReserving the dateReserving to set
     */
    public void setDateReserving(Date dateReserving) {
        this.dateReserving = dateReserving;
    }
    
    

    

}
