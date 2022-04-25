package com.htn.services;

import com.htn.pojo.LibraryCard;
import com.htn.pojo.ReservationTicket;
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

}
