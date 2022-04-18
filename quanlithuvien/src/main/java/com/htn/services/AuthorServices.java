/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.Author;
import com.htn.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class AuthorServices {
    public List<Author> getAuthor (String kw) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM author WHERE name like concat ('%',?,'%');";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw == null)
                kw = "";
            
            stm.setString(1, kw);

            ResultSet rs = stm.executeQuery();

            List<Author> authors = new ArrayList<>();

            while (rs.next()) {
                Author author = new Author(rs);
                authors.add(author);
            }
         return authors;
         }
    }
    
    public boolean addAuthor (Author a) throws SQLException{
        String sql = "INSERT INTO author (name) " + "VALUES (?)";
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, a.getName());

            stm.executeUpdate();

            conn.commit();
        }
        return true;
    }
    
     public boolean updateAuthor (Author a) throws SQLException{
       String sql = "UPDATE author SET name = ? WHERE id in (?);";
       try (Connection conn = JdbcUtils.getConn()) {
               conn.setAutoCommit(false);
               PreparedStatement stm = conn.prepareStatement(sql);
               stm.setString(1, a.getName());
               stm.setInt(2, a.getId());

               stm.executeUpdate();

               conn.commit();
       }

       return true;
    }
     
     public boolean deleteAuthor (int i) throws SQLException{
        String sql = "DELETE FROM author WHERE id =?";
        try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, "" + i);
                
                stm.executeUpdate();

                conn.commit();
        }
        return true;
    }
}
