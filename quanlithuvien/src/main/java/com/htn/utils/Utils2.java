/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.utils;

/**
 *
 * @author admin
 */
public class Utils2 {
    public static java.util.Date convertSqlDateToUtilDate(java.sql.Date sqlDate){
        java.util.Date  utilDate = new java.util.Date(sqlDate.getTime());
        return utilDate;
    }
}
