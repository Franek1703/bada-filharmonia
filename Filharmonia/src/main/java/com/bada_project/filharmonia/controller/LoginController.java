package com.bada_project.filharmonia.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {


    @GetMapping("/admin/logout")
    public String logoutRedirect(Authentication auth) {
        if (auth != nul l && auth.isAuthenticated()) {
            return "redirect:/main";
        }
        return "login"; // widok logowania
    }

    @PostMapping("/user/logout/main")
    public String logoutMain(HttpServletRequest request, HttpServletResponse response) {
        logoutUser(request, response);
        return "redirect:/main";
    }

    @PostMapping("/user/logout/reservation")
    public String logoutReservation(HttpServletRequest request, HttpServletResponse response) {
        logoutUser(request, response);
        return "redirect:/reservation";
    }

    @PostMapping("/user/logout/event_details")
    public String logoutEventDetails(HttpServletRequest request, HttpServletResponse response) {
        logoutUser(request, response);
        return "redirect:/reservation/event";
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();
    }
}

