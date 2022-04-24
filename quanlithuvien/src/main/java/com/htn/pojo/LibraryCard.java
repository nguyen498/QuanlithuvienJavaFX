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
 * @author admin
 */
public class LibraryCard {
    private int cardNumber;
    private String name;
    private String gender;
    private Date birthdate;
    private Date issuedAt;
    private int active;
    private int accountType;
    private int accountID;

    public LibraryCard() {
    }

    public LibraryCard(int cardNumber, String name, String gender, Date birthdate, Date issuedAt, int active, int accountType, int accountID) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.issuedAt = issuedAt;
        this.active = active;
        this.accountType = accountType;
        this.accountID = accountID;
    }
    
    public LibraryCard(String name, String gender, Date birthdate, Date issuedAt, int active, int accountType, int accountID) {
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.issuedAt = issuedAt;
        this.active = active;
        this.accountType = accountType;
        this.accountID = accountID;
    }
    
    public LibraryCard (ResultSet rs) throws SQLException{
        this.cardNumber = rs.getInt("cardNumber");
        this.name = rs.getString("name");
        this.gender = rs.getString("gender");
        this.birthdate = rs.getDate("birthdate");
        this.issuedAt = rs.getDate("issuedAt");
        this.active = rs.getInt("active");
        this.accountType = rs.getInt("accountType");
    }

    /**
     * @return the cardNumber
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * @return the issuedAt
     */
    public Date getIssuedAt() {
        return issuedAt;
    }

    /**
     * @param issuedAt the issuedAt to set
     */
    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    /**
     * @return the active
     */
    public int getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * @return the accountType
     */
    public int getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(int accountType) {
        this.accountType = accountType;
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
