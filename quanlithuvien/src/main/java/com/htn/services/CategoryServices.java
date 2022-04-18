/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.pojo.Category;
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
public class CategoryServices {
    public List<Category> getCategory (String kw) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM category WHERE name like concat ('%',?,'%');";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw == null)
                kw = "";
            
            stm.setString(1, kw);

            ResultSet rs = stm.executeQuery();

            List<Category> cates = new ArrayList<>();

            while (rs.next()) {
                Category cate = new Category(rs);
                cates.add(cate);
            }
         return cates;
         }
    }
    
    public boolean addCategory (Category a) throws SQLException{
        String sql = "INSERT INTO category (name) " + "VALUES (?)";
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, a.getName());

            stm.executeUpdate();

            conn.commit();
        }
        return true;
    }
    
    public boolean updateCategory (Category a) throws SQLException{
       String sql = "UPDATE category SET name = ? WHERE id in (?);";
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
    
    public boolean deleteCategory (int i) throws SQLException{
        String sql = "DELETE FROM category WHERE id =?";
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
