/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class LendingTicketServices {
    public void addLendingTicket (int accountID) throws SQLException{
        AccountServices as = new AccountServices();
        
        // Nếu người dùng chưa có phiếu mượn => Tạo mới
        if (as.getAccountByID(accountID) == null) {
            // Create
            
        }
    }
    
    /*
        Lấy tổng số lượng sách đã thuê
    */
    public int getTotalBooksLended () throws SQLException{
        int TotalBooksLended = 0;
        
        return TotalBooksLended;
    }
    
    /*
        Tăng tổng số lượng sách đã mượn lên 1 
    */
    public void incrementTotalBooksLended () throws SQLException{
        
    }
}
