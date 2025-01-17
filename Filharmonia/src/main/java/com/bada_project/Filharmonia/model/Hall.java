package com.bada_project.filharmonia.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Hall {

    // Getters and Setters
    private int id;
    private String name;
    private int capacity;

    public Hall() {}

    public Hall(String name, int capacity, int id) {
        this.name = name;
        this.capacity = capacity;
        this.id = id;
    }

}
