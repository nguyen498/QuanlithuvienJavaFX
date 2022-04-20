/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.Constants;
import com.htn.pojo.LendingDetail;
import com.htn.pojo.LendingStatus;
import com.htn.pojo.LendingTicket;
import com.htn.utils.JdbcUtils;

import com.htn.utils.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import javafx.scene.control.Alert;

/**
 *
 * @author Administrator
 */
public class LendingDetailServices {
    
    
    /*
        Thêm chi tiết hóa đơn
    */
    public boolean addLendingDetail (LendingDetail ld) throws SQLException{
        String sql = "INSERT INTO lending_detail (dueDate, ammount, bookID, lendingID) " + "VALUES (?, ?, ?, ?)";
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, ld.getDueDate());
            stm.setDouble(2, ld.getAmount());
            stm.setInt(3, ld.getBookID());
            stm.setInt(4, ld.getLendingID());

            stm.executeUpdate();

            conn.commit();
        }
        return true;
    }
    
    /*
        Hàm cho mượn sách 
    */
    public boolean lendingBook(int bookID, int accountID, int numberBookLending) throws SQLException {
        BookServices bs = new BookServices();
        LendingTicketServices lts = new LendingTicketServices();
        
        // SL Sách đã mượn + SL sách muốn mượn > SL sách cho phép thì k cho mượn
        if ((lts.getTotalBooksLended() + numberBookLending) > Constants.MAX_BOOKS_LENDING_TO_A_USER()) {
            Utils.showBox("Ngơời dùng đã mượn quá số lượng sách cho phép!!", Alert.AlertType.ERROR).show();
            return false;
        }
        
        /*
        
        // Sách đã có người nào đặt chưa ?
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bs.getBookByID(bookID));
        
        if (bookReservation != null && bookReservation.getMemberId() != this.getId()) {
            // Sách đã có người đặt VÀ người đặt không phải là bản thân
            Utils.showBox("Sách đã bị người khác đặt!!", Alert.AlertType.ERROR).show();
            return false;
        } else if (bookReservation != null) {
            // id của người đặt = id của bản thân, update thông tin đặt sang COMPLETED 
            // (tức quá trình đặt sách thành công => chuyển sang mượn sách)
            bookReservation.updateStatus(ReservationStatus.COMPLETED);
        }
        
        */

        // Tạo phiếu mượn (LendingTicket) cho người dùng nếu chưa có
        LendingTicket lt = new LendingTicket(0, Utils.getCurrentDate(), LendingStatus.BORROWING.toInt(), accountID);
        
        int lendingTicketID = lts.addLendingTicket(lt);
                
        // ngày đáo hạn
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        
        Date dueDate = Utils.toSqlDate(Utils.xuatNgayThangNam2(calendar.getTime()));
        // Giá tiền sách
        double bookPrice = bs.getBookByID(bookID).getPrice();
                
        // Thêm sách muốn mượn, account, số lượng sách thuê vào Chi Tiết Phiếu mượn (LendingDetail), thất bại thì trả về false
        LendingDetail ld = new LendingDetail(dueDate, bookPrice, bookID, lendingTicketID);
        if (this.addLendingDetail(ld) == false) 
            return false;
        
        // Tạo hóa đơn mượn sách, thất bại thì trả về false
//        PaymentServices ps = new PaymentServices();
//        if (ps.checkout(bookID, cardID) == false) 
//            return false;

        // Tăng tổng số lượng sách đã mượn lên 1 
        lts.incrementTotalBooksLended(lendingTicketID);
        return true;
  }
}
