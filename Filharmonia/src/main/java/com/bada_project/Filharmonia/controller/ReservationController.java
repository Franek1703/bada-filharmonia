package com.bada_project.filharmonia.controller;

import com.bada_project.filharmonia.model.Event;
import com.bada_project.filharmonia.model.Hall;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.stream.Collectors;


@Controller
public class ReservationController {

    @PostMapping("/reservation/event")
    public String getEventDetails(@ModelAttribute Event event, Model model) {
        // Mock data for demonstration purposes
        int soldTickets = 500; // TODO: Replace this with a database query to get the actual number of tickets sold

        model.addAttribute("eventName", event.getName());
        model.addAttribute("eventDescription", event.getDescription());
        model.addAttribute("eventDate", event.getDate());
        model.addAttribute("hallName", event.getHall().getName());
        model.addAttribute("totalSeats", event.getHall().getCapacity());
        model.addAttribute("soldTickets", soldTickets);
        model.addAttribute("availableSeats", event.getHall().getCapacity() - soldTickets);

        // Check if the hall is fully booked
        if (soldTickets >= event.getHall().getCapacity()) {
            model.addAttribute("isFullyBooked", true);
        } else {
            model.addAttribute("isFullyBooked", false);
        }

        return "event_details";
    }


    @GetMapping("/reservation")
    public String getReservations(Model model) {
        // Mock data for events
        List<Event> events = List.of(
                new Event(1, "2025-01-20", "Concert A", "Description for Concert A", new Hall("Main Hall", 500,1)),
                new Event(2, "2025-01-20", "Concert B", "Description for Concert B", new Hall("Main Hall", 500,1)),
                new Event(3, "2025-01-23", "Concert C", "Description for Concert C", new Hall("Main Hall", 500,1)),
                new Event(4, "2025-01-21", "Concert C", "Description for Concert C", new Hall("Main Hall", 500,1)),
                new Event(5, "2025-01-20", "Concert A", "Description for Concert A", new Hall("Main Hall", 500,1)),
                new Event(6, "2025-01-20", "Concert B", "Description for Concert B", new Hall("Main Hall", 500,1)),
                new Event(7, "2025-01-23", "Concert C", "Description for Concert C", new Hall("Main Hall", 500,1)),
                new Event(8, "2025-01-21", "Concert C", "Description for Concert C, Description for Concert C,Description for Concert C,Description for Concert C,Description for Concert C,Description for Concert C", new Hall("Main Hall", 500,1))
        );


        // Group events by date
        Map<String, List<Event>> groupedEvents = events.stream()
                .collect(Collectors.groupingBy(
                        Event::getDate,
                        TreeMap::new, // Use TreeMap for natural order
                        Collectors.toList()
                ));


        model.addAttribute("groupedEvents", groupedEvents);
        return "reservation";
    }
}
