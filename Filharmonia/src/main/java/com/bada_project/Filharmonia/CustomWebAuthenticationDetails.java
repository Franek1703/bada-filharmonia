package com.bada_project.filharmonia;

public class CustomWebAuthenticationDetails {
    private final String firstName;
    private final String lastName;

    public CustomWebAuthenticationDetails(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
