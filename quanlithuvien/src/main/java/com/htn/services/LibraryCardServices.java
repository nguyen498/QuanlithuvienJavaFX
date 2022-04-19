/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.services;

import com.htn.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.htn.pojo.LibraryCard;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class LibraryCardServices {
    public List<LibraryCard> getCards (String kw) throws SQLException{
    
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT librarycard.cardNumber, account.name, account.gender, account.birthdate, account.accountType ,librarycard.issuedAt, librarycard.active\n" +
                         "FROM librarydb.librarycard, librarydb.account\n" +
                         "WHERE librarycard.cardNumber = account.id and name like concat ('%',?,'%');;";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw == null)
                kw = "";
            
            stm.setString(1, kw);

            ResultSet rs = stm.executeQuery();

            List<LibraryCard> cards = new ArrayList<>();

            while (rs.next()) {
                LibraryCard card = new LibraryCard(rs);
                cards.add(card);
            }
         return cards;
         }
    }
}
