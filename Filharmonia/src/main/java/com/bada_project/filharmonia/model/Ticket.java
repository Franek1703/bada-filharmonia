package com.bada_project.filharmonia.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;

import org.springframework.security.core.userdetails.User;

//@Entity
public class Ticket {

    private int id;
    private String purchaseDate;
    private double grossPrice;
    private double netPrice;
    private String category;
    private UserModel user;
    private Event event;
//    @Id

    public Ticket() {}

    public Ticket(int id, String purchaseDate, double grossPrice, double netPrice, String category, UserModel user, Event event) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.grossPrice = grossPrice;
        this.netPrice = netPrice;
        this.category = category;
        this.user = user;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public double getGrossPrice() {
        return grossPrice;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public String getCategory() {
        return category;
    }

    public UserModel getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }


    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setGrossPrice(double grossPrice) {
        this.grossPrice = grossPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setId(int id) {
        this.id = id;
    }
}
