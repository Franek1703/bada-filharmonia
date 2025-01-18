package com.bada_project.filharmonia.controller;

import com.bada_project.filharmonia.model.Event;
import com.bada_project.filharmonia.model.Hall;
import com.bada_project.filharmonia.model.Ticket;
import com.bada_project.filharmonia.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class ReservationController {

    @Value("${event.price}")
    private String eventPrice;
    @Value("${event.netPrice}")
    private String eventNetPrice;

    @PostMapping("/reservation/event")
    public String getEventDetails(@ModelAttribute Event event, Model model) {
        // Mock data for demonstration purposes
        int soldTickets = 400; // TODO: Replace this with a database query to get the actual number of tickets sold (number of tickets for this event id)

        model.addAttribute("eventName", event.getName());
        model.addAttribute("eventDescription", event.getDescription());
        model.addAttribute("eventDate", event.getDate());
        model.addAttribute("hallName", event.getHall().getName());
        model.addAttribute("totalSeats", event.getHall().getCapacity());
        model.addAttribute("soldTickets", soldTickets);
        model.addAttribute("availableSeats", event.getHall().getCapacity() - soldTickets);
        model.addAttribute("eventID", event.getId());
        model.addAttribute("eventPrice", eventPrice);
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

        //TODO load events. Only future
        //TODO load event halls

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

    @GetMapping("/check-login")
    public String checkLogin(@RequestParam("eventId") Long eventId, Principal principal) {

        if (principal == null) {
            return "redirect:/user/login/check-login";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = (UserModel) authentication.getDetails();

        Ticket ticket = new Ticket(
                1,
                LocalDate.now().toString(),
                Double.parseDouble(eventPrice),
                Double.parseDouble(eventNetPrice),
                "Standard",
                user.getId(),
                eventId.intValue()
        );



        // User is logged in
            // TODO: Add logic to handle ticket reservation and save to database
            return "redirect:/reservation/success";

    }

}
