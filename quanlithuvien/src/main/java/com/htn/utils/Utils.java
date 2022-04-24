/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
    
    public static Date upDownDate(Date date, int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, number);
        
        return Utils.toSqlDate(Utils.xuatNgayThangNam2(calendar.getTime()));
    }
    
    
    public static Date getCurrentDate() {
        LocalDate lcd = LocalDate.now();
        
        return toSqlDateHyphenVersion(lcd.toString());
    }
    
    public static Date getCurrentDate2() {
        Calendar calendar = Calendar.getInstance();
        
        return Utils.toSqlDate(Utils.xuatNgayThangNam2(calendar.getTime()));
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
    
    
    public static Date toSqlDateHyphenVersion(String ntn) {
        try {
            java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(ntn);
            
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
    

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
          }

    public static String xuatNgayThangNam2(java.util.Date ntn) {
        if (ntn != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            return formatter.format(ntn);
        }else
            return "n/a";
    }

    
    public static long findDateDifference(String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date d1 = sdf.parse(startDate);
        java.util.Date d2 = sdf.parse(endDate);
  
        // Calucalte time difference
        // in milliseconds
        long difference_In_Time = d2.getTime() - d1.getTime();
        
        
        // Calucalte time difference in
        // seconds, minutes, hours, years,
        // and days
        long difference_In_Seconds = (difference_In_Time / 1000) % 60;

        long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

        long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;

        long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));

        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
        
        return difference_In_Days;
    }
    
    public static String xuatNgayThangNam3(java.util.Date ntn) {
        if (ntn != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            return formatter.format(ntn);
        }else
            return "n/a";
    }
}
