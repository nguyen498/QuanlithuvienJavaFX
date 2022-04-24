/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htn.services;

import com.htn.pojo.Account;
import com.htn.utils.JdbcUtils;
import com.htn.utils.Utils;

import java.sql.*;
import java.text.ParseException;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Admin
 */
public class AccountServicesTest {
    AccountServices service = new AccountServices();

    @Test
    public void GetAccountReturnOrNot() throws SQLException {
        assertNotEquals(0, GetAllAccount().size());
    }

    @Test
    public void GetAccountDoesNotReturn() throws SQLException {
        List<Account> acc = service.getAccount("fbdewfwuyefuywbjhfbwuysfuwefjwefuyewgfuy");
        assertEquals(0, acc.size());
    }

    @Test
    public void GetAccountReturnCorrectName() throws SQLException {
        List<Account> acc = service.getAccount("Nguyên");
        for (int i = 0; i < acc.size(); i++) {
            assertTrue(acc.get(i).getName().contains("Nguyên"));
        }
    }


    @Test
    public void GetAccountIDReturnRight() throws SQLException {
        assertTrue(service.getAccountByID(1).getName().contains("Nguyên"));
    }


    @Test
    public void GetAccountIDReturnWentInputWrongID() throws SQLException {
        assertNull(service.getAccountByID(-99));
    }

    @Test
    public void AddAccountTest() throws ParseException, SQLException {
        Account temp = new Account();
        temp.setName("Tom");
        temp.setGender("Nam");
        temp.setBirthday((Date) Utils.toSqlDate("1/1/2000"));
        temp.setPassword("123");
        temp.setUsername("Tom123");
        temp.setType(0);
        assertTrue(service.addAccount(temp));
    }

    @Test
    public void AddAccountTestInvalidChar() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("/[.*+?^${}()|[\\]\\\\]/g, '\\\\$&'");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2000"));
            temp.setPassword("123");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountTestNull() throws SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountTestNameNull() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2000"));
            temp.setPassword("123");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountTestNameBlank() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("              ");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2000"));
            temp.setPassword("123");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountTestGenderNull() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2000"));
            temp.setPassword("123");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountTestGenderBlank() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setGender("         ");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2000"));
            temp.setPassword("123");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountTestBirthdayNull() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setGender("Nam");
            temp.setPassword("123");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountTestBirthdayBlank() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("   "));
            temp.setPassword("123");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }


    @Test
    public void AddAccountTestBirthdayBeyondCurrentDay() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2135"));
            temp.setPassword("123");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }


    @Test
    public void AddAccountTestBirthdayInvalid() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("-1/-1/-1"));
            temp.setPassword("123");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountTestPasswordNull() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2001"));
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountTestPasswordBlank() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2001"));
            temp.setPassword("                      ");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (SQLException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountTestWeakPassword() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2001"));
            temp.setPassword("1");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (Exception e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountUserNameNull() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2001"));
            temp.setPassword("1");
            temp.setType(0);
            service.addAccount(temp);
        } catch (Exception e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountUserNameBlank() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2001"));
            temp.setPassword("1");
            temp.setUsername("                ");
            temp.setType(0);
            service.addAccount(temp);
        } catch (Exception e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddAccountUserNameExists() throws ParseException, SQLException {
        boolean flag = false;
        try {
            Account temp = new Account();
            temp.setName("Tom");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2001"));
            temp.setPassword("1");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.addAccount(temp);
        } catch (Exception e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void UpdateAccountTest() throws SQLException {
        Account temp = new Account();
        temp.setId(7);
        temp.setName("Tom");
        temp.setGender("Nam");
        temp.setBirthday((Date) Utils.toSqlDate("1/1/2001"));
        temp.setPassword("123");
        temp.setUsername("Tom123");
        temp.setType(0);
        assertTrue(service.updateAccount(temp));
    }

    @Test
    public void UpdateNonExistingAccountTest() throws SQLException {
        Account temp = new Account();
        boolean flag = false;
        try {
            temp.setId(-99);
            temp.setName("Tom");
            temp.setGender("Nam");
            temp.setBirthday((Date) Utils.toSqlDate("1/1/2001"));
            temp.setPassword("123");
            temp.setUsername("Tom123");
            temp.setType(0);
            service.updateAccount(temp);
        } catch (Exception e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void DeleteAccountTest() throws SQLException {
        assertTrue(service.deleteAccount(7));
    }


    @Test
    public void DeleteAllAccountTest() throws SQLException {
        List<Account> acc = new ArrayList<Account>(GetAllAccount());
        for (int i = 0; i < acc.size(); i++) {
            service.deleteAccount(acc.get(i).getId());
        }
        acc = new ArrayList<Account>(GetAllAccount());
        assertNotEquals(0, acc.size());
    }

    @Test
    public void DeleteNonExistingAccountTest() throws SQLException {
        boolean flag = false;
        try {
            service.deleteAccount(-99);
        } catch (Exception e) {
            flag = true;
        }
        assertTrue(flag);
    }

    public List<Account> GetAllAccount() throws SQLException {
        Connection conn = JdbcUtils.getConn();
        PreparedStatement stm = conn.prepareStatement("Select * from Account");
        ResultSet acc = stm.executeQuery();
        List<Account> accounts = new ArrayList<Account>();
        while (acc.next()) {
            Account a = new Account(acc);
            accounts.add(a);
        }
        return accounts;
    }

}
