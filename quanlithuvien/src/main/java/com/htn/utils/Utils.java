/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author admin
 */
public class Utils {
    public static Alert showBox(String msg, Alert.AlertType type) {
      Alert a = new Alert(type);
      a.setContentText(msg);

      return a;
    }
    
    public static Date toSqlDate(String ntn) {
        try {
            java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(ntn);
            
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            return sqlDate;
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static String xuatNgayThangNam(java.sql.Date ntn) {
        if (ntn != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            return formatter.format(ntn);
        }else
            return "n/a";
    }
    
}
