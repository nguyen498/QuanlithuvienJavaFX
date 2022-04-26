package com.htn.Test;

import com.htn.pojo.LibraryCard;
import com.htn.pojo.ReservationTicket;
import com.htn.services.LibraryCardServices;
import com.htn.utils.JdbcUtils;
import com.htn.utils.Utils;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class LibraryCardTest {
    LibraryCardServices LCS = new LibraryCardServices();
    @Test
    public void LibraryCardActive() throws SQLException {
        assertTrue(LCS.changeActive(0,1));
    }


    @Test
    public void ActivateCardDuplicate() throws SQLException {
        boolean flag = false;
        try {
            LibraryCard lb = new LibraryCard();
            lb.setIssuedAt((Date) Utils.toSqlDate("1/1/2000"));
            lb.setAccountID(1);
            lb.setActive(0);
            LCS.addLibraryCard(lb);
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }
}
