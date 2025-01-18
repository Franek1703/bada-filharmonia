package com.bada_project.filharmonia.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
public class UserModel {
//    @Id
    private String firstName;
    private String lastName;
    private int id;
    private String phoneNumber;

    public UserModel(String name, String surname, int id, String phoneNumber) {
        this.firstName = name;
        this.lastName = surname;
        this.id = id;
        this.phoneNumber = phoneNumber;
    }


}
