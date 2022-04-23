/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.ReservationTicket;
import com.htn.pojo.Book;
import com.htn.pojo.Payment;
import com.htn.pojo.Account;
import com.htn.pojo.BookStatus;
import com.htn.pojo.Constants;
import com.htn.pojo.LendingDetail;
import com.htn.pojo.LendingStatus;
import com.htn.pojo.LendingTicket;
import com.htn.pojo.ReservationStatus;
import com.htn.utils.JdbcUtils;

import com.htn.utils.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
        String sql = "INSERT INTO lending_detail (dueDate, amount, bookID, lendingID) " + "VALUES (?, ?, ?, ?)";
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
    
    public List<LendingDetail> getTraSachLendingDetail (int lendingTicketID) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql =    "SELECT book.name, lending_detail.amount, lending_detail.dueDate, lendingticket.dateLending, lending_detail.lendingID\n" +
                            "FROM lending_detail, lendingticket, book\n" +
                            "WHERE lendingID = ?\n" +
                            "AND lending_detail.lendingID = lendingticket.id\n" +
                            "AND book.id = lending_detail.bookID";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, lendingTicketID);

            ResultSet rs = stm.executeQuery();

            List<LendingDetail> lendingDetails = new ArrayList<>();

            while (rs.next()) {
                LendingDetail ld = new LendingDetail();
                ld.setBookName(rs.getString("book.name"));
                ld.setAmount(rs.getDouble("lending_detail.amount"));
                ld.setDueDate(rs.getDate("lending_detail.dueDate"));
                ld.setDateLending(rs.getDate("lendingticket.dateLending"));
                ld.setLendingID(rs.getInt("lending_detail.lendingID"));
                
                lendingDetails.add(ld);
            }
            
            return lendingDetails;
        }
    }
    
    
    public List<LendingDetail> getLendingDetailListByAccountID (int accountID) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql =    "SELECT lending_detail.* \n" +
                            "FROM librarydb.lendingticket, librarydb.lending_detail\n" +
                            "WHERE lendingticket.accountID = ? \n" +
                            "AND lending_detail.lendingID = lendingticket.id\n" +
                            "AND lendingticket.status = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, accountID);
            stm.setInt(2, LendingStatus.BORROWING);

            ResultSet rs = stm.executeQuery();
            
            List<LendingDetail> lendingDetails = new ArrayList<>();

            while (rs.next()) {
                LendingDetail lendingDetail = new LendingDetail(rs);
                lendingDetails.add(lendingDetail);
            }
            
            return lendingDetails;
        }
    }
    
    
    
    
    /*
        Hàm cho mượn sách 
    */
    public boolean lendingBook(int bookID, int accountID, int numberBookLending) throws SQLException {
        BookServices bs = new BookServices();
        LendingTicketServices lts = new LendingTicketServices();
        ReserveTicketServices rs = new ReserveTicketServices();
        
                
        // SL Sách đã mượn + SL sách muốn mượn > SL sách cho phép thì k cho mượn
        if ((lts.getTotalBooksLended() + numberBookLending) > Constants.MAX_BOOKS_LENDING_TO_A_USER) {
            Utils.showBox("Ngơời dùng đã mượn quá số lượng sách cho phép!!", Alert.AlertType.ERROR).show();
            return false;
        }
        
        
        /*
            Tìm trong ReservationTicket bằng bookID với status = RESERVED
            Nếu có tức là quyển sách đang được đặt bỏi ai đó 
            => kiểm tra ReservationTicket.accountID có = accountID của người định đặt sách k
            => Nếu đúng thì cho mượn 
            => nếu sai thì không cho mượn
        */
        ReservationTicket reservationTicket = rs.getReservingReserveTicketByBookID(bookID);
        
        if (reservationTicket != null && reservationTicket.getAccountID() != accountID) {
            // Sách đã có người đặt VÀ người đặt không phải là bản thân
            Utils.showBox("Sách đã bị người khác đặt, Vui lòng chọn sách khác!!", Alert.AlertType.ERROR).show();
            return false;
        }
        
        /*
            Nếu tìm lendingTicket với trạng thái BORROWING mà không có => thì tạo mới 
        */
        LendingTicket accountBorrowingLendingTicket = lts.getBorrowingLendingTicketByAccountID(accountID);
        int lendingTicketID = -1;
        if (accountBorrowingLendingTicket == null) {
            LendingTicket lt = new LendingTicket(0, Utils.getCurrentDate2(), LendingStatus.BORROWING, accountID);
            lendingTicketID = lts.addLendingTicket(lt);
        } else {
            lendingTicketID = accountBorrowingLendingTicket.getId();
        }
        
        // ngày đáo hạn = currentDay + 30
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, Constants.MAX_LENDING_DAYS);
        Date dueDate = Utils.toSqlDate(Utils.xuatNgayThangNam2(calendar.getTime()));
        
        // Giá tiền sách
        double bookPrice = bs.getBookByID(bookID).getPrice();
                
        /*
            Thêm sách muốn mượn, account, số lượng sách thuê vào Chi Tiết Phiếu mượn (LendingDetail), 
            Thất bại thì trả về false
        */
        LendingDetail ld = new LendingDetail(dueDate, bookPrice, bookID, lendingTicketID);
        if (this.addLendingDetail(ld) == false) {
            Utils.showBox("Xảy ra lỗi trong quá trình thêm chi tiết mượn!!", Alert.AlertType.ERROR).show();
            return false;
        } else {
            // Nếu thêm lendingDetail thành công => cập nhật trạng thái sách sang BORROWED
            bs.updateBookStatus(bookID, BookStatus.BORROWED);
        }

        // Tăng tổng số lượng sách đã mượn lên 1 
        if (lts.incrementTotalBooksLended(lendingTicketID) == false) {
            Utils.showBox("Xảy ra lỗi trong quá trình tăng số lượng sách mượn!!", Alert.AlertType.ERROR).show();
            return false;
        }
        
        return true;
  }
    
    
    /*
        Hàm trả sách
    */
    public boolean returnBook(int accountID, int lendingTicketID) throws SQLException, ParseException {
        PaymentServices ps = new PaymentServices();
        BookServices bs = new BookServices();
        LendingTicketServices lts = new LendingTicketServices();
        LendingDetailServices lds = new LendingDetailServices();
        
        // Add hóa đơn vào databse
        Payment paymentInfo = ps.getAccountPaymentInfo(accountID);
        Payment payment2 = new Payment(
                paymentInfo.getTotalBookPrice(),
                paymentInfo.getTotalCheckout(), 
                paymentInfo.getFine(),
                Utils.getCurrentDate2(),
                accountID,
                lendingTicketID
        );
        
        if (ps.addPayment(payment2) == false) {
            Utils.showBox("Xảy ra lỗi trong quá trình thêm Hóa đơn!!", Alert.AlertType.ERROR).show();
            return false;
        }
        
        // Lặp từng quyền sách và đưa status sách về AVAILABLE
        List<LendingDetail> lendingDetails = lds.getLendingDetailListByAccountID(accountID);
        for (LendingDetail lendingDetail : lendingDetails) {
            int bookID = lendingDetail.getBookID();
            if (bs.updateBookStatus(bookID, BookStatus.AVAILABLE) == false) {
                Utils.showBox("Xảy ra lỗi trong quá trình Cập nhật trạng thái sách!!", Alert.AlertType.ERROR).show();
                return false;
            }
        }
        
        // Cập nhật lendingTicket.status thành RETURNED
        if (lts.updateLendingTicketStatus(lendingTicketID, LendingStatus.RETURNED) == false) {
            Utils.showBox("Xảy ra lỗi trong quá trình Cập nhật trạng thái lendingTicket!!", Alert.AlertType.ERROR).show();
            return false;
        }
        
        return true;
    }    
    
}
