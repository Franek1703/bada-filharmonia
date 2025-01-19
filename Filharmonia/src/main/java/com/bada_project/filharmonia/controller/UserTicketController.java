package com.bada_project.filharmonia.controller;

import com.bada_project.filharmonia.dao.EventDAO;
import com.bada_project.filharmonia.dao.TicketDAO;
import com.bada_project.filharmonia.model.Event;
import com.bada_project.filharmonia.model.Ticket;
import com.bada_project.filharmonia.model.UserModel;
import com.bada_project.filharmonia.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import java.util.List;

@Controller
public class UserTicketController {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private EventDAO eventDAO;

    @GetMapping("/user/tickets")
    public String getUserTickets(Authentication authentication, Model model) {
        UserModel user = (UserModel) authentication.getDetails();
        int userId = user.getId();

        TicketService service = new TicketService(ticketDAO, eventDAO);
        List<Ticket> currentTickets = service.getCurrentTickets(userId);
        List<Ticket> expiredTickets = service.getExpiredTickets(userId);


        model.addAttribute("currentTickets", currentTickets);
        model.addAttribute("expiredTickets", expiredTickets);
        return "user_tickets";
    }
}

