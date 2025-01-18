package com.bada_project.filharmonia.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@Entity
public class Ticket {

    private int ticketNumber;
    private String purchaseDate;
    private double grossPrice;
    private double netPrice;
    private String category;
    private int clientId;
    private int eventId;
//    @Id
    private Long id;

    public Ticket() {}

    public Ticket(int ticketNumber, String purchaseDate, double grossPrice, double netPrice, String category, int clientId, int eventId) {
        this.ticketNumber = ticketNumber;
        this.purchaseDate = purchaseDate;
        this.grossPrice = grossPrice;
        this.netPrice = netPrice;
        this.category = category;
        this.clientId = clientId;
        this.eventId = eventId;
    }
}
