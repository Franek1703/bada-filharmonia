package com.bada_project.filharmonia.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;

public class Event {
    // Getters and Setters
//    @Id
    private int id;
    private String date;
    private String name;
    private String description;
//    @ManyToOne
//    @JoinColumn(name = "hall_id")
    private Hall hall;

    public Event() {}

    // Constructor
    public Event(int id, String date, String name, String description, Hall hall) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.hall = hall;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Hall getHall() {
        return hall;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
