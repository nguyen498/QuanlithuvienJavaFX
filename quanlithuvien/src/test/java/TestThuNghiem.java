/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.htn.services.BookServices;
import com.htn.pojo.Book;
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
public class TestThuNghiem {
    private static Connection conn;
    private static final BookServices s = new BookServices();

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
        List<Book> books = s.getBook(null);

        books.forEach(book -> System.err.println(book.getName()));
        books.forEach(c -> Assertions.assertNotNull(c.getName()));
    }
}
