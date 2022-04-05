/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class JdbcUtils {
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
    
    public static Connection getConn () throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/librarydb", "root", "123456789");
    }
}
