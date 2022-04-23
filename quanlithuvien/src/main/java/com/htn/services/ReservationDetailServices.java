/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.ReservationDetail;
import com.htn.pojo.ReservationTicket;
import com.htn.pojo.Book;
import com.htn.pojo.BookStatus;
import com.htn.pojo.Constants;
import com.htn.pojo.ReservationStatus;
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
public class ReservationDetailServices {
    
    
    /*
        Thêm chi tiết phiếu đặt
    */
    public boolean addReservationDetail (ReservationDetail rd) throws SQLException{
        String sql = "INSERT INTO reservation_detail (dueDate, bookID, reservationID) " + "VALUES (?, ?, ?)";
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, rd.getDueDate());
            stm.setInt(2, rd.getBookID());
            stm.setInt(3, rd.getReservationID());

            stm.executeUpdate();

            conn.commit();
        }
        return true;
    }
        
    /*
        Hàm đặt sách
    */
    public boolean reserveBook(int bookID, int accountID) throws SQLException {
        BookServices bs = new BookServices();
        ReserveTicketServices rts = new ReserveTicketServices();
        
        // Kiểm trạng thái sách
        Book book = bs.getBookByID(bookID);
        if (book.getStatus() != BookStatus.AVAILABLE) {
            Utils.showBox("Sách này đã có người mượn hoặc đang được bảo dưỡng!!", Alert.AlertType.ERROR).show();
            return false;
        }
        
        /*
            Nếu tìm ReserveTicket với trạng thái RESERVING mà không có => thì tạo mới 
        */
        ReservationTicket accountReservingReserveTicket = rts.getReservingReserveTicketByAccountID(accountID);
        int reserveTicketID = -1;
        if (accountReservingReserveTicket == null) {
            ReservationTicket rt = new ReservationTicket(0, Utils.getCurrentDate2(), ReservationStatus.RESERVING, accountID);
            reserveTicketID = rts.addReserveTicket(rt);
        } else {
            reserveTicketID = accountReservingReserveTicket.getId();
        }
        
        // ngày đáo hạn = currentDay + 2
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, Constants.MAX_RESERVATION_DAYS);
        Date dueDate = Utils.toSqlDate(Utils.xuatNgayThangNam2(calendar.getTime()));
        
        /*
            Add Chi Tiết Phiếu đặt (ReservationDetail), 
            Thất bại thì trả về false
        */
        ReservationDetail ld = new ReservationDetail(dueDate, bookID, reserveTicketID);
        if (this.addReservationDetail(ld) == false) {
            Utils.showBox("Xảy ra lỗi trong quá trình thêm chi tiết đặt!!", Alert.AlertType.ERROR).show();
            return false;
        } else {
            // Nếu thêm ReservationDetail thành công => cập nhật trạng thái sách sang RESERVED
            bs.updateBookStatus(bookID, BookStatus.RESERVED);
        }
        
        // Tăng tổng số lượng sách đã đặt lên 1 
        if (rts.incrementTotalBooksReserved(reserveTicketID) == false) {
            Utils.showBox("Xảy ra lỗi trong quá trình tăng số lượng sách đặt!!", Alert.AlertType.ERROR).show();
            return false;
        }
        
        
        return true;
    }
}
