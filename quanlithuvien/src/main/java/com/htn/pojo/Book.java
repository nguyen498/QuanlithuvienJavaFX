/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htn.pojo;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Book {
    private int id;
    private String name;
    private String description;
    private double price;
    private Date dateOfPurcharse;
    private String publicationPlace;
    private int status;

    public Book() {
    }

    public Book(int id, String name, String description, double price, Date dateOfPurcharse, String publicationPlace, int status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dateOfPurcharse = dateOfPurcharse;
        this.publicationPlace = publicationPlace;
        this.status = status;
    }
    
    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the dateOfPurcharse
     */
    public Date getDateOfPurcharse() {
        return dateOfPurcharse;
    }

    /**
     * @param dateOfPurcharse the dateOfPurcharse to set
     */
    public void setDateOfPurcharse(Date dateOfPurcharse) {
        this.dateOfPurcharse = dateOfPurcharse;
    }

    /**
     * @return the publicationPlace
     */
    public String getPublicationPlace() {
        return publicationPlace;
    }

    /**
     * @param publicationPlace the publicationPlace to set
     */
    public void setPublicationPlace(String publicationPlace) {
        this.publicationPlace = publicationPlace;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
}
