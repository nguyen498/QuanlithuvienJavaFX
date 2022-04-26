package com.htn.Test;

import com.htn.pojo.Book;
import com.htn.pojo.LendingDetail;
import com.htn.pojo.LendingTicket;
import com.htn.services.LendingDetailServices;
import com.htn.services.LendingTicketServices;
import com.htn.utils.JdbcUtils;
import com.htn.utils.Utils;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Lending_Book_Test {

    LendingDetailServices LDS = new LendingDetailServices();
    LendingTicketServices LTS = new LendingTicketServices();
    @Test
    public void GetLendingTicketTest() throws SQLException {
        assertNotEquals(0,GetAllLendingTicket().size());
    }

    @Test
    public void GetLendingDetailTest() throws SQLException {
        assertNotEquals(0,GetAllLendingDetail().size());
    }

    @Test
    public void GetLendingTicketReturnRightTest() throws SQLException {
        LendingTicket temp = GetAllLendingTicket().get(0);
        assertEquals(temp.getId(),LTS.getLendingTicketByID(temp.getId()).getId());
    }

    @Test
    public void GetLendingDetailReturnRightTest() throws SQLException {
        LendingTicket temp = GetAllLendingTicket().get(0);
        List<LendingDetail> details = new ArrayList<LendingDetail>(LDS.getTraSachLendingDetail(temp.getId()));
        for(int i = 0;i < details.size();i++)
        {
            assertEquals(temp.getId(),details.get(i).getLendingID());
        }
    }

    @Test
    public void GetLendingTicketWrongIdTest() throws SQLException {
        LendingTicket temp = LTS.getLendingTicketByID(-99);
        assertNull(temp);
    }

    @Test
    public void GetLendingDetailWrongIdTest() throws SQLException {
        List<LendingDetail> temp = LDS.getTraSachLendingDetail(-99);
        assertEquals(0,temp.size());
    }

    @Test
    public void AddLendingTicketTest() throws SQLException {
        LendingTicket temp = new LendingTicket();
            temp.setDateLending((Date) Utils.toSqlDate("1/1/2000"));
            temp.setAccountID(1);
            temp.setTotalBookLended(1);
            temp.setStatus(1);
            int key = LTS.addLendingTicket(temp);
            assertEquals(GetAllLendingTicket().get(GetAllLendingTicket().size() - 1).getId(),key);

    }

    @Test
    public void AddLendingDetailTest() throws SQLException {
        LendingTicket temp = GetAllLendingTicket().get(0);
        LendingDetail temp2 = new LendingDetail();
        temp2.setDueDate((Date) Utils.toSqlDate("1/1/2000"));
        temp2.setAmount(2);
        temp2.setLendingID(temp.getId());
        temp2.setBookID(1);
        temp2.setBookName("Toi ac");
        temp2.setDateLending((Date) Utils.toSqlDate("1/1/2000"));
        LDS.addLendingDetail(temp2);
        List<LendingDetail> acc = LDS.getTraSachLendingDetail(temp.getId());
        for(int i = 0;i < acc.size();i++)
        {
            assertEquals(temp.getId(),acc.get(i).getLendingID());
        }

    }


    @Test
    public void TestBookLentIncrement() throws SQLException {
        assertTrue(LTS.incrementTotalBooksLended(GetAllLendingTicket().get(0).getId()));
    }

    @Test
    public void TestUpdateLending() throws SQLException {
        assertTrue(LTS.updateLendingTicketStatus(GetAllLendingTicket().get(0).getId(),0));
    }

    public List<LendingDetail> GetAllLendingDetail() throws SQLException {
        ResultSet a = JdbcUtils.getConn().prepareStatement("Select * from lending_detail").executeQuery();
        List<LendingDetail> acc = new ArrayList<LendingDetail>();
        while(a.next())
        {
            acc.add(new LendingDetail(a));
        }
        return acc;
    }

    public List<LendingTicket> GetAllLendingTicket() throws SQLException {
        ResultSet a = JdbcUtils.getConn().prepareStatement("Select * from lendingticket").executeQuery();
        List<LendingTicket> acc = new ArrayList<LendingTicket>();
        while(a.next())
        {
            acc.add(new LendingTicket(a));
        }
        return acc;
    }

}
