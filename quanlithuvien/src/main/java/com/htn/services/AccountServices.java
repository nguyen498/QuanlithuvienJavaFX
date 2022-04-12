/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.Account;
import com.htn.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class AccountServices {
    public boolean addAccount (Account a) throws SQLException{
        String sql = "INSERT INTO account (name,username, password, gender, birthdate, accountType) " + "VALUES (?, ?, ?, ?, ?, ?)";
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
}
