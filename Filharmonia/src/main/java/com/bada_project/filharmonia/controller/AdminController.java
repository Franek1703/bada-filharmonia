package com.bada_project.filharmonia.controller;

import com.bada_project.filharmonia.dao.EventDAO;
import com.bada_project.filharmonia.dao.HallDAO;
import com.bada_project.filharmonia.dao.UserDAO;
import com.bada_project.filharmonia.model.Event;
import com.bada_project.filharmonia.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private HallDAO hallDAO;

    @GetMapping("/admin/event/add")
    public String addEventForm(Model model) {
        List<Hall> halls = hallDAO.list();
        model.addAttribute("halls", halls);
        return "admin_add_event";
    }

    public static List<Hall> getTestHalls() {
        List<Hall> halls = new ArrayList<>();
        halls.add(new Hall("Main Hall", 500, 1));
        halls.add(new Hall("Chamber Hall", 200, 2));
        halls.add(new Hall("Symphony Hall", 800, 3));
        halls.add(new Hall("Jazz Hall", 150, 4));
        halls.add(new Hall("Opera Hall", 700, 5));
        return halls;
    }

    @PostMapping("/admin/event/add")
    public String addEvent(Event event, @RequestParam String hallId) {
        int hallIdInt = Integer.parseInt(hallId);
        Hall hall = hallDAO.get(hallIdInt);

        event.setHall(hall);
        eventDAO.save(event);

        return "redirect:/admin";
    }
}

