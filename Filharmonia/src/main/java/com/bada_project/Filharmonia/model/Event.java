package com.bada_project.filharmonia.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Event {
    // Getters and Setters
    private int id;
    private String date;
    private String name;
    private String description;
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

}
