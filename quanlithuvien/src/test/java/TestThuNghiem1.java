/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.htn.pojo.Account;
import com.htn.services.BookServices;
import com.htn.pojo.Book;
import com.htn.services.AccountServices;
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
public class TestThuNghiem1 {
    private static Connection conn;
    private static final AccountServices s = new AccountServices();

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
    public void testPrintBookName() throws SQLException {
        List<Account> accs = s.getTraSachAccount(null);

        System.err.println(accs);
        accs.forEach(book -> System.err.println(book.getBirthday().toString()));
        accs.forEach(book -> System.err.println(book.getLendingTicketID()));
        accs.forEach(c -> Assertions.assertNotNull(c.getName()));
    }
}
