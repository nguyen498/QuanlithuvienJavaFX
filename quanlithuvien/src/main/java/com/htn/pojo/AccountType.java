/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.pojo;

/**
 *
 * @author Administrator
 */
public enum AccountType {
    ADMIN(0), STUDENT(1);

    private final int numType;

    AccountType(int numType) {
        this.numType = numType;
    }

    public int toInt() {
        return numType;
    }
}
