/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.Constants;
import com.htn.utils.Utils;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Administrator
 */
public class LendingDetailServices {
    
    
    
    
    
    /*
        Thêm chi tiết hóa đơn
    */
    public boolean addLendingDetail (int bookID, int AccountID, int totalBookLending) throws SQLException{
        
        return false;
    }
    
    /*
        Thanh toán mượn sách 
    */
    public boolean checkoutBookItem(int bookID, int AccountID, int totalBookLending) throws SQLException {
        BookServices bs = new BookServices();
        LendingTicketServices lts = new LendingTicketServices();
        
        // SL Sách đã mượn + SL sách muốn mượn >= SL sách cho phép thì k cho mượn
        if ((lts.getTotalBooksLended() + totalBookLending) >= Constants.MAX_BOOKS_LENDING_TO_A_USER()) {
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
        lts.addLendingTicket(AccountID);
        
        // Thêm sách muốn mượn, account, số lượng sách thuê vào Chi Tiết Phiếu mượn (LendingDetail), thất bại thì trả về false
        if (this.addLendingDetail(bookID, AccountID, totalBookLending) == false) 
            return false;
        
        /*
        
        // Tạo hóa đơn mượn sách, thất bại thì trả về false
        PaymentServices ps = new PaymentServices();
        if (ps.checkout(bookID, AccountID) == false) 
            return false;

        */

        // Tăng tổng số lượng sách đã mượn lên 1 
        lts.incrementTotalBooksLended();
        return true;
  }
}
