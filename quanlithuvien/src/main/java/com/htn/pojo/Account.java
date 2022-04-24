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
public class Account {
    private int id;
    private String name;
    private String username;
    private String password;
    private String gender;
    private Date birthday;
    private int accountType;
    private int lendingTicketID;
    private int totalBookLended;

    public Account() {
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    

    public Account(int id, String name, String password, String username, String gender, Date birthday, int accountType) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.birthday = birthday;
        this.accountType = accountType;
    }
    
    public Account(String name, String username, String password, String gender, Date birthday, int accountType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.accountType = accountType;
    }
    
    public Account(int id, String name, String gender, Date birthday, int lendingTicketID, int totalBookLended) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.lendingTicketID = lendingTicketID;
        this.totalBookLended = totalBookLended;
    }
    
    public Account(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.password = rs.getString("password");
        this.username = rs.getString("username");
        this.gender = rs.getString("gender");
        this.birthday = rs.getDate("birthdate");
        this.accountType = rs.getInt("accountType");
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the accountType
     */
    public int getType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setType(int accountType) {
        this.accountType = accountType;
    }

    /**
     * @return the lendingTicketID
     */
    public int getLendingTicketID() {
        return lendingTicketID;
    }

    /**
     * @param lendingTicketID the lendingTicketID to set
     */
    public void setLendingTicketID(int lendingTicketID) {
        this.lendingTicketID = lendingTicketID;
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
    
}
