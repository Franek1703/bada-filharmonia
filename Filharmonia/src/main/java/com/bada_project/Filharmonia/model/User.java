package com.bada_project.filharmonia.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
public class User {
//    @Id
    private String name;
    private String surname;
    private int id;
    private String phoneNumber;
}
