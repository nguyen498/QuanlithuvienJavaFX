/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.BookStatus;
import com.htn.pojo.Constants;
import com.htn.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.htn.pojo.ReservationStatus;
import com.htn.pojo.ReservationTicket;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Administrator
 */
public class ReserveTicketServices {
    
    public int addReserveTicket (ReservationTicket rt) throws SQLException{
        String sql = "INSERT INTO reservationticket (createdDate, totalBookReserved, status, accountID) " + "VALUES (?, ?, ?, ?)";
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setDate(1, rt.getCreatedDate());
            stm.setInt(2, rt.getTotalBookReserved());
            stm.setInt(3, rt.getStatus());
            stm.setInt(4, rt.getAccountID());

            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();  
            int key = rs.next() ? rs.getInt(1) : 0;
           
            System.err.println(key);

            conn.commit();
            return key;
        }
    }
    
    
    public ReservationTicket getReservingReserveTicketByAccountID (int accountID) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM reservationticket WHERE accountID = ? AND status = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, accountID);
            stm.setInt(2, ReservationStatus.RESERVING);

            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                ReservationTicket rt = new ReservationTicket(rs);
                return rt;
            }
            
            return null;
        }
    }
    
    public ReservationTicket getReservingReserveTicketByBookID (int bookID) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT reservationticket.* FROM reservationticket, reservation_detail \n" +
                         "WHERE bookID = ? AND status = ? AND reservationticket.id = reservation_detail.reservationID";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, bookID);
            stm.setInt(2, ReservationStatus.RESERVING);

            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                ReservationTicket rt = new ReservationTicket(rs);
                return rt;
            }
            
            return null;
        }
    }
    
    
    
    public ReservationTicket getReservationTicketByID (int id) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM reservationticket WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                ReservationTicket rt = new ReservationTicket(rs);
                return rt;
            }
            
            return null;
        }
    }
    
    
    /*
        Tăng tổng số lượng sách đã mượn lên 1 
    */
    public boolean incrementTotalBooksReserved (int reserveTicketID) throws SQLException{
        ReservationTicket rt = this.getReservationTicketByID(reserveTicketID);
        
        String sql = "UPDATE reservationticket SET totalBookReserved = ? WHERE id in (?);";
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, rt.getTotalBookReserved() + 1);
            stm.setInt(2, reserveTicketID);

            stm.executeUpdate();

            conn.commit();
            
            return true;
        }
        
    }
    
    public boolean updateReservationTicketStatus (int reserveTicketID, int status) throws SQLException{
        String sql = "UPDATE reservationticket SET status = ? WHERE id in (?);";
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, reserveTicketID);
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
    
    
    public boolean createAutoUpdateBookStatus (int reserveTicketID) throws SQLException{
        String sql =    "create event if not exists auto_update_book_status_for_?\n" +
                        "on schedule at current_time + INTERVAL ? day\n" +
                        "do\n" +
                        "	-- Cập Nhật những sách đã đặt về trạng thái bình thường -- \n" +
                        "UPDATE book\n" +
                        "SET status = ?\n" +
                        "WHERE id IN (SELECT reservation_detail.bookID \n" +
                        "             FROM reservationticket, reservation_detail\n" +
                        "             where reservationticket.id = ? AND reservationticket.status = ?);";
        
        
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, reserveTicketID);
                stm.setInt(2, Constants.MAX_RESERVATION_DAYS);
                stm.setInt(3, BookStatus.AVAILABLE);
                stm.setInt(4, reserveTicketID);
                stm.setInt(5, ReservationStatus.RESERVING);
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
    
    public boolean createReservationTicketAutoRemoval (int reserveTicketID) throws SQLException{
        String sql =    "create event if not exists reservation_ticket_auto_removal_for_?\n" +
                        "on schedule at current_time + INTERVAL ? day\n" +
                        "do\n" +
                        "UPDATE reservationticket SET status = ? WHERE id = ?;";
        
        
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, reserveTicketID);
                stm.setInt(2, Constants.MAX_RESERVATION_DAYS);
                stm.setInt(3, ReservationStatus.RETURNED);
                stm.setInt(4, reserveTicketID);
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
}
