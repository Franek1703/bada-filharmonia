package com.bada_project.filharmonia.controller;

import com.bada_project.filharmonia.model.Ticket;
import com.bada_project.filharmonia.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import java.util.List;

@Controller
public class UserTicketController {

    @GetMapping("/user/tickets")
    public String getUserTickets(Authentication authentication, Model model) {
        UserModel user = (UserModel) authentication.getDetails();
        int userId = user.getId();

        // TODO: Pobierz aktualne i przeterminowane bilety z bazy danych na podstawie userId
//        List<Ticket> currentTickets = TicketService.getCurrentTickets(userId);
//        List<Ticket> expiredTickets = TicketService.getExpiredTickets(userId);
        List<Ticket> currentTickets = List.of(
                new Ticket(101, "2025-01-20", 50.0, 40.0, "VIP", 1, 10),
                new Ticket(102, "2025-02-15", 30.0, 25.0, "Standard", 1, 12)
        );

        List<Ticket> expiredTickets = List.of(
                new Ticket(201, "2024-12-01", 50.0, 40.0, "VIP", 1, 8),
                new Ticket(202, "2024-11-10", 30.0, 25.0, "Standard", 1, 9)
        );

        model.addAttribute("currentTickets", currentTickets);
        model.addAttribute("expiredTickets", expiredTickets);
        return "user_tickets";
    }
}

