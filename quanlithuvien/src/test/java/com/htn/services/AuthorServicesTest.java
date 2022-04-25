/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.Account;
import com.htn.pojo.Author;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.htn.utils.JdbcUtils;
import com.htn.utils.Utils;
import com.mysql.cj.jdbc.JdbcConnection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author n
 */
public class AuthorServicesTest {
    AuthorServices service = new AuthorServices();
    @Test
    public void GetAuthorReturnOrNot() throws SQLException
    {
        List<Author> acc = service.getAuthor(GetAllAuthor().get(0).getName());
        assertNotEquals(0, acc.size());
    }
    @Test
    public void GetAuthorDoesNotReturn() throws SQLException
    {
        List<Author> acc = service.getAuthor("fbdewfwuyefuywbjhfbwuysfuwefjwefuyewgfuy");
        assertEquals(0, acc.size());
    }
    @Test
    public void GetAuthorReturnCorrectName() throws SQLException
    {
        String a = GetAllAuthor().get(0).getName();
        List<Author> acc = service.getAuthor(a);
        for(int i = 0;i < acc.size();i++)
        {
            assertTrue(acc.get(i).getName().contains(a));
        }
    }


    @Test
    public void AddAuthorTest() throws ParseException, SQLException
    {
        Author temp = new Author();
        temp.setId(2);
        temp.setName("Nam");
        assertTrue(service.addAuthor(temp));
    }

    @Test
    public void AddAuthorTestNull() throws ParseException, SQLException
    {
        boolean flag = false;
        try
        {
            Author temp = new Author();
            service.addAuthor(temp);
        }catch(SQLException e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAuthorTestNameBlank() throws ParseException, SQLException
    {
        boolean flag = false;
        try
        {
            Author temp = new Author();
            temp.setName("           ");
            service.addAuthor(temp);
        }catch(SQLException e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAuthorTestNameInvalid() throws ParseException, SQLException
    {
        boolean flag = false;
        try
        {
            Author temp = new Author();
            temp.setName("\"/[.*+?^${}()|[\\\\]\\\\\\\\]/g, '\\\\\\\\$&'\"");
            service.addAuthor(temp);
        }catch(SQLException e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void UpdateAuthorTest() throws SQLException {
        Author temp = new Author();
        temp.setId(2);
        temp.setName("Tom");
        assertTrue(service.updateAuthor(temp));
    }

    @Test
    public void UpdateNonExistAuthorTest() throws SQLException {
        boolean flag = false;
        try {
            Author temp = new Author();
            temp.setId(-99);
            temp.setName("Tom");
            service.updateAuthor(temp);
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void DeleteAuthorTest() throws SQLException {
        assertTrue(service.deleteAuthor(2));
    }

    public List<Author> GetAllAuthor() throws SQLException {
        List<Author> acc = new ArrayList<>();
        ResultSet a = JdbcUtils.getConn().prepareStatement("Select * from author").executeQuery();
        while(a.next())
        {
            acc.add(new Author(a));
        }
        return acc;
    }
    
}
