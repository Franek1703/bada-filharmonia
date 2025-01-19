package com.bada_project.filharmonia.service;

import com.bada_project.filharmonia.dao.EventDAO;
import com.bada_project.filharmonia.dao.TicketDAO;
import com.bada_project.filharmonia.model.Event;
import com.bada_project.filharmonia.model.Ticket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TicketService {

    public TicketDAO ticketDAO;
    public EventDAO eventDAO;

    public TicketService(TicketDAO ticketDAO, EventDAO eventDAO) {
        this.ticketDAO = ticketDAO;
        this.eventDAO = eventDAO;
    }

    public List<Ticket> getCurrentTickets(int userId) {
        List<Ticket> tickets = ticketDAO.listByUserId(userId); // Get the tickets for the user

        // Fetch the full Event details (including date) for each ticket
        for (Ticket ticket : tickets) {
            Event event = eventDAO.get(ticket.getEvent().getId());  // Fetch event by ID
            ticket.setEvent(event);  // Set the full Event object to the ticket
        }

        // Filter tickets that are for future events
        List<Ticket> currentTickets = tickets.stream()
                .filter(ticket -> ticket.getEvent() != null && ticket.getEvent().getDate() != null)
                .filter(ticket -> {
                    try {
                        // Strip time part from the date string and parse the date only
                        String eventDateString = ticket.getEvent().getDate().split(" ")[0]; // Take only the date part
                        return LocalDate.parse(eventDateString).isAfter(LocalDate.now());  // Check if the event date is in the future
                    } catch (Exception e) {
                        System.out.println("Invalid date for ticket ID: " + ticket.getId());
                        return false; // Exclude tickets with invalid dates
                    }
                })
                .collect(Collectors.toList());

        // Return an empty list if no current tickets are found
        if (currentTickets.isEmpty()) {
            return Collections.emptyList();
        }
        return currentTickets;
    }

    public List<Ticket> getExpiredTickets(int userId) {
        List<Ticket> tickets = ticketDAO.listByUserId(userId); // Get the tickets for the user

        // Fetch the full Event details (including date) for each ticket
        for (Ticket ticket : tickets) {
            Event event = eventDAO.get(ticket.getEvent().getId());  // Fetch event by ID
            ticket.setEvent(event);  // Set the full Event object to the ticket
        }

        // Filter tickets that are for future events
        List<Ticket> expiredTickets = tickets.stream()
                .filter(ticket -> ticket.getEvent() != null && ticket.getEvent().getDate() != null)
                .filter(ticket -> {
                    try {
                        // Strip time part from the date string and parse the date only
                        String eventDateString = ticket.getEvent().getDate().split(" ")[0]; // Take only the date part
                        return LocalDate.parse(eventDateString).isBefore(LocalDate.now());  // Check if the event date is in the future
                    } catch (Exception e) {
                        System.out.println("Invalid date for ticket ID: " + ticket.getId());
                        return false; // Exclude tickets with invalid dates
                    }
                })
                .collect(Collectors.toList());

        // Return an empty list if no current tickets are found
        if (expiredTickets.isEmpty()) {
            return Collections.emptyList();
        }
        return expiredTickets;
    }
}
