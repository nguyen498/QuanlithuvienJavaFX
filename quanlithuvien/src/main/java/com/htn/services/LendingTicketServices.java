/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.htn.pojo.LendingTicket;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Administrator
 */
public class LendingTicketServices {
    
    public int addLendingTicket (LendingTicket ltk) throws SQLException{
        String sql = "INSERT INTO lendingticket (dateLending, totalBookLended, status, accountID) " + "VALUES (?, ?, ?, ?)";
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setDate(1, ltk.getDateLending());
            stm.setInt(2, ltk.getTotalBookLended());
            stm.setInt(3, ltk.getStatus());
            stm.setInt(4, ltk.getAccountID());

            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();  
            int key = rs.next() ? rs.getInt(1) : 0;
           
            System.err.println(key);

            conn.commit();
            return key;
        }
    }
    
    /*
        Lấy tổng số lượng sách đã thuê
    */
    public int getTotalBooksLended () throws SQLException{
        int TotalBooksLended = 0;
        
        return TotalBooksLended;
    }
    
    public LendingTicket getLendingTicketByID (int id) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM lendingticket WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                LendingTicket lt = new LendingTicket(rs);
                return lt;
            }
            
            return null;
        }
    }
    
    
    public LendingTicket getLendingTicketByAccountID (int accountID) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM lendingticket WHERE accountID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, accountID);

            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                LendingTicket lt = new LendingTicket(rs);
                return lt;
            }
            
            return null;
        }
    }
    
    public LendingTicket getLendingTicketByAccountID2 (int accountID) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM lendingticket WHERE accountID = ?");
            stm.setInt(1, accountID);

            ResultSet rs = stm.executeQuery();

            LendingTicket q = null;
            if (rs.next()) {
                q = new LendingTicket();
                q.setId(rs.getInt("id"));
            }

            return q;
        }
    }
    
    
    /*
        Tăng tổng số lượng sách đã mượn lên 1 
    */
    public void incrementTotalBooksLended (int lendingTicketID) throws SQLException{
        LendingTicket lt = this.getLendingTicketByID(lendingTicketID);
        
        String sql = "UPDATE lendingticket SET totalBookLended = ? WHERE id in (?);";
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, lt.getTotalBookLended() + 1);
            stm.setInt(2, lendingTicketID);

            stm.executeUpdate();

            conn.commit();
        }
    }
}
