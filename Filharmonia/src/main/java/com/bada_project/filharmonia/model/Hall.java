package com.bada_project.filharmonia.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;

public class Hall {

    // Getters and Setters
//    @Id
    private int id;
    private String name;
    private int capacity;

    public Hall() {}

    public Hall(String name, int capacity, int id) {
        this.name = name;
        this.capacity = capacity;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
