/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.pojo;

/**
 *
 * @author Administrator
 */
public enum LendingStatus {
    RETURNED(0), BORROWING(1);

    private final int numStatus;

    LendingStatus(int numType) {
        this.numStatus = numType;
    }

    public int toInt() {
        return numStatus;
    }
}
