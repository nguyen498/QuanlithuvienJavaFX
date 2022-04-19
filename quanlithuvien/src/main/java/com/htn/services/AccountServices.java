/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.Account;
import com.htn.pojo.Constants;
import com.htn.utils.JdbcUtils;
import com.htn.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author admin
 */

public class AccountServices {
    public List<Account> getAccount (String kw) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM account WHERE name like concat ('%',?,'%');";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw == null)
                kw = "";
            
            stm.setString(1, kw);

            ResultSet rs = stm.executeQuery();

            List<Account> accounts = new ArrayList<>();

            while (rs.next()) {
                Account account = new Account(rs);
                accounts.add(account);
            }
         return accounts;
         }
    }
    
    public Account getAccountByID (int id) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM account WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                Account account = new Account(rs);
                return account;
            }
            
            return null;
        }
    }
    
    public boolean updateAccount (Account a) throws SQLException{
        String sql = "UPDATE account SET name = ?, password = ?, username = ?, gender = ?, birthdate = ? WHERE id in (?);";
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, a.getName());
                stm.setString(2, a.getPassword());
                stm.setString(3, a.getUsername());
                stm.setString(4, a.getGender());
                stm.setDate(5, a.getBirthday());
                stm.setInt(6, a.getId());
                
                stm.executeUpdate();

                conn.commit();
        }
        
        return true;
    }
    
    public boolean addAccount (Account a) throws SQLException{
        String sql = "INSERT INTO account (name, username, password, gender, birthdate, accountType) " + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, a.getName());
            stm.setString(2, a.getUsername());
            stm.setString(3, a.getPassword());
            stm.setString(4, a.getGender());
            stm.setDate(5, a.getBirthday());
            stm.setInt(6, a.getType());

            stm.executeUpdate();

            conn.commit();
        }
        return true;
    }
    
    public boolean deleteAccount (int i) throws SQLException{
        String sql = "DELETE FROM account WHERE id =?";
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, "" + i);
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
    
    
}
