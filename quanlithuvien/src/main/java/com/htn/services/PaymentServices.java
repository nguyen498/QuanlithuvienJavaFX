/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.Constants;
import com.htn.pojo.Payment;
import com.htn.pojo.LendingDetail;
import com.htn.utils.JdbcUtils;
import com.htn.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class PaymentServices {
    public List <Payment> getPayment () throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM payment;";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            List<Payment> lts = new ArrayList<>();

            while (rs.next()) {
                Payment lt = new Payment(rs);
                lts.add(lt);
            }
         return lts;
         }
    }
    
    public boolean addPayment (Payment p) throws SQLException{
        String sql = "INSERT INTO payment (totalBookPrice, totalCheckout, fine, createdDate, accountID, lendingID) " + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setDouble(1, p.getTotalBookPrice());
                stm.setDouble(2, p.getTotalCheckout());
                stm.setDouble(3,(float) p.getFine());
                stm.setDate(4, p.getCreatedDate());
                stm.setInt(5, p.getAccountID());
                stm.setInt(6, p.getLendingID());
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
    
    public Payment getAccountPaymentInfo (int accountID) throws SQLException, ParseException{
        // Get lendingDetail by accountID
        // Get Total bookprice by looping lendingdetail
        // Calc Fine by looping lendingdetail using dueDate
        // Calc TotalCheckout
        
        LendingDetailServices lds = new LendingDetailServices();
        List<LendingDetail> lendingDetails = lds.getLendingDetailListByAccountID(accountID);
        
        double totalBookPrice = 0;
        double totalCheckout = 0;
        double fine = 0;
        
        for (LendingDetail lendingDetail : lendingDetails) {
            // Get Total bookprice
            totalBookPrice += lendingDetail.getAmount();
            // Get Total Fine
            long datediff = Utils.findDateDifference(Utils.getCurrentDate2().toString(), lendingDetail.getDueDate().toString());
            if (datediff < 0) {
                fine += -datediff * Constants.FINE_IN_1_DAY; 
            }
        }
        // Get Total Checkout
        totalCheckout = totalBookPrice + fine;
        
        Payment payment = new Payment(totalBookPrice, totalCheckout, fine);
        
        return payment;
    }
}
