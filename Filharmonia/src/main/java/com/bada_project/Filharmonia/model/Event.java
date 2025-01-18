package com.bada_project.filharmonia.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@Entity
public class Event {
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
}
