/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.htn.pojo.LendingTicket;
import com.htn.services.LendingTicketServices;
import com.htn.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
        
/**
 *
 * @author n
 */
public class TestLendingTicket {
    private static Connection conn;
    private static final LendingTicketServices s = new LendingTicketServices();

    @BeforeAll
    public static void beforeAll() throws SQLException {
        conn = JdbcUtils.getConn(); 
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null)
            conn.close();
    }

    @Test
    public void testLT() throws SQLException {
        LendingTicket lt = s.getLendingTicketByAccountID(3);

        System.err.println(lt.getId());
        Assertions.assertNotNull(lt.getId());
    }
}
