package com.htn.Test;

import com.htn.pojo.LendingDetail;
import com.htn.pojo.LendingTicket;
import com.htn.pojo.ReservationDetail;
import com.htn.pojo.ReservationTicket;
import com.htn.services.ReservationDetailServices;
import com.htn.services.ReserveTicketServices;
import com.htn.utils.JdbcUtils;
import com.htn.utils.Utils;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class Reserve_Bokk_Test {
    ReserveTicketServices RTS = new ReserveTicketServices();
    ReservationDetailServices RDS = new ReservationDetailServices();


    @Test
    public void GetReserveTicketTest() throws SQLException {
        assertNotEquals(0,RTS.getReserveTicket().size());
    }

    @Test
    public void AddReserveTicketTest() throws SQLException {
        ReservationTicket temp = new ReservationTicket();
        temp.setCreatedDate((Date) Utils.toSqlDate("1/1/2000"));
        temp.setAccountID(1);
        temp.setTotalBookReserved(2);
        temp.setStatus(1);
        int key = RTS.addReserveTicket(temp);
        assertEquals(GetAllReserveTicket().get(GetAllReserveTicket().size() - 1).getId(),key);
    }


    @Test
    public void GetReserverTicketByIDTest() throws SQLException {
        ReservationTicket temp = RTS.getReservationTicketByID(GetAllReserveTicket().get(0).getId());
        assertEquals(GetAllReserveTicket().get(0).getId(),temp.getId());
    }

    @Test
    public void GetReserverTicketByBookIDTest() throws SQLException {
        ReservationTicket temp = RTS.getReservingReserveTicketByBookID(GetAllReserveDetail().get(0).getBookID());
        assertEquals(GetAllReserveTicket().get(0).getId(),temp.getId());
    }

    @Test
    public void BookIncrementTest() throws SQLException {
        int i = GetAllReserveTicket().get(0).getId();
        ReservationTicket temp = RTS.getReservationTicketByID(i);
        RTS.incrementTotalBooksReserved(i);
        assertNotEquals(RTS.getReservationTicketByID(i).getTotalBookReserved(),temp.getTotalBookReserved());
    }

    public List<ReservationTicket> GetAllReserveTicket() throws SQLException {
        ResultSet a = JdbcUtils.getConn().prepareStatement("Select * from reservationticket").executeQuery();
        List<ReservationTicket> acc = new ArrayList<ReservationTicket>();
        while(a.next())
        {
            acc.add(new ReservationTicket(a));
        }
        return acc;
    }

    public List<ReservationDetail> GetAllReserveDetail() throws SQLException {
        ResultSet a = JdbcUtils.getConn().prepareStatement("Select * from reservation_detail").executeQuery();
        List<ReservationDetail> acc = new ArrayList
                <ReservationDetail>();
        while(a.next())
        {
            acc.add(new ReservationDetail(a));
        }
        return acc;
    }

}
