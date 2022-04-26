/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.htn.Test;

import com.htn.pojo.Account;
import com.htn.pojo.Author;
import com.htn.pojo.Book;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.htn.services.BookServices;
import com.htn.utils.JdbcUtils;
import com.htn.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author n
 */
public class BookServicesTest {
    BookServices service = new BookServices();
    @Test
    public void GetBookReturnOrNot() throws SQLException
    {
        List<Book> acc = service.getBook(GetAllBook().get(0).getName());
        assertNotEquals(0, acc.size());
    }
    @Test
    public void GetBookDoesNotReturn() throws SQLException
    {
        List<Book> acc = service.getBook("fbdewfwuyefuywbjhfbwuysfuwefjwefuyewgfuy");
        assertEquals(0, acc.size());
    }
    @Test
    public void GetBookReturnCorrectName() throws SQLException
    {
        Book temp = GetAllBook().get(0);
        List<Book> acc = service.getBook(temp.getName());
        for(int i = 0;i < acc.size();i++)
        {
            assertTrue(acc.get(i).getName().contains(temp.getName()));
        }
    }


    @Test
    public void GetBookIDReturnRight() throws SQLException
    {
        Book temp = GetAllBook().get(0);
        assertEquals(temp.getId(),service.getBookByID(temp.getId()).getId());
    }

    @Test
    public void GetBookIDReturnWentInputWrongID() throws SQLException
    {
        assertNull(service.getBookByID(-99));
    }

    @Test
    public void AddBookTest() throws SQLException
    {
        Book a = new Book();
        a.setName("Toi ac va trung phat");
        a.setDescription("abc");
        a.setDateOfPurcharse((Date)Utils.toSqlDate("1/1/2001"));
        a.setPrice(100);
        a.setPublicationPlace("HCM");
        a.setStatus(0);
        assertTrue(service.addBook(a));
    }

    @Test
    public void AddBookTestNull() throws ParseException, SQLException
    {
        boolean flag = false;
        try
        {
            Book temp = new Book();
            service.addBook(temp);
        }catch(SQLException e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddBookTestNameNull() throws SQLException
    {
        Book a = new Book();
        boolean flag = false;
        try {
            a.setDescription("abc");
            a.setDateOfPurcharse((Date) Utils.toSqlDate("1/1/2001"));
            a.setPublicationPlace("HCM");
            a.setStatus(0);
            a.setPrice(100);
            assertTrue(service.addBook(a));
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddBookTestNameBlank() throws SQLException
    {
        Book a = new Book();
        boolean flag = false;
        try {
            a.setName("                   ");
            a.setDescription("abc");
            a.setDateOfPurcharse((Date) Utils.toSqlDate("1/1/2001"));
            a.setPublicationPlace("HCM");
            a.setStatus(0);
            a.setPrice(100);
            assertTrue(service.addBook(a));
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddBookTestDateOfPurcharceNull() throws SQLException
    {
        Book a = new Book();
        boolean flag = false;
        try {
            a.setName("Toi ac");
            a.setDescription("abc");
            a.setPublicationPlace("HCM");
            a.setStatus(0);
            a.setPrice(100);
            assertTrue(service.addBook(a));
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddBookTestDateOfPurcharceBlank() throws SQLException
    {
        Book a = new Book();
        boolean flag = false;
        try {
            a.setName("Toi ac");
            a.setDescription("abc");
            a.setDateOfPurcharse((Date) Utils.toSqlDate("    "));
            a.setPublicationPlace("HCM");
            a.setStatus(0);
            a.setPrice(100);
            assertTrue(service.addBook(a));
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddBookTestDateOfPurcharceInvalid() throws SQLException
    {
        Book a = new Book();
        boolean flag = false;
        try {
            a.setName("Toi ac");
            a.setDescription("abc");
            a.setDateOfPurcharse((Date) Utils.toSqlDate("123"));
            a.setPublicationPlace("HCM");
            a.setStatus(0);
            a.setPrice(100);
            assertTrue(service.addBook(a));
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }


    @Test
    public void AddBookTestPublicationPlaceNull() throws SQLException
    {
        Book a = new Book();
        boolean flag = false;
        try {
            a.setName("Toi ac");
            a.setDescription("abc");
            a.setDateOfPurcharse((Date) Utils.toSqlDate("1/1/2001"));
            a.setStatus(0);
            a.setPrice(100);
            assertTrue(service.addBook(a));
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddBookTestPriceNull() throws SQLException
    {
        Book a = new Book();
        boolean flag = false;
        try {
            a.setName("Toi ac");
            a.setDescription("abc");
            a.setDateOfPurcharse((Date) Utils.toSqlDate("1/1/2001"));
            a.setPublicationPlace("HCM");
            a.setStatus(0);
            assertTrue(service.addBook(a));
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddBookTestNegativePrice() throws SQLException
    {
        Book temp = new Book();
        temp.setName("bbd  ");
        temp.setStatus(0);
        temp.setDateOfPurcharse((Date)Utils.toSqlDate("1/1/2001"));
        temp.setDescription("  ");
        temp.setPublicationPlace("HCM");
        temp.setPrice(-100);
        assertFalse(service.addBook(temp));
    }

    @Test
    public void UpdateBookTest() throws SQLException {
        Book temp = new Book();
        temp.setId(6);
        temp.setName("bbd  ");
        temp.setStatus(0);
        temp.setDateOfPurcharse((Date)Utils.toSqlDate("1/1/2001"));
        temp.setDescription("  ");
        temp.setPublicationPlace("HCM");
        assertTrue(service.updateBook(temp));
    }

    @Test
    public void UpdateNonExistBookTest() throws SQLException {
        Book temp = new Book();
        boolean flag = false;
        try {
            temp.setId(-99);
            temp.setName("bbd  ");
            temp.setStatus(0);
            temp.setDateOfPurcharse((Date)Utils.toSqlDate("1/1/2001"));
            temp.setDescription("  ");
            temp.setPublicationPlace("HCM");
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void DeleteBookTest() throws SQLException {
        assertTrue(service.deleteBook(7));
    }

    @Test
    public void DeleteNonExistBookTest() throws SQLException {
        assertFalse(service.deleteBook(-99));
    }


    public List<Book> GetAllBook() throws SQLException {
        List<Book> acc = new ArrayList<Book>();
        ResultSet a = JdbcUtils.getConn().prepareStatement("select * from book").executeQuery();
        while(a.next())
        {
            acc.add(new Book(a));
        }
        return acc;
    }
}
