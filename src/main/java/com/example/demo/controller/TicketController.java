package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;

@Controller
public class TicketController {
	 private final TicketService ticketService;

	    public TicketController(TicketService ticketService) {
	        this.ticketService = ticketService;
	    }

	    @GetMapping("/")
	    public String index() {
	        return "index";
	    }

	    @PostMapping("/book")
	    public String bookTicket(@RequestParam String from, @RequestParam String to, Model model) {
	        Ticket ticket = ticketService.bookTicket(from, to);
	        model.addAttribute("ticketId", ticket.getId());
	        return "index";
	    }

	    @PostMapping("/use")
	    public String useTicket(@RequestParam String ticketId, Model model) {
	        String message = ticketService.useTicket(ticketId);
	        model.addAttribute("message", message);
	        // If the ticket was successfully used, also show the Ticket ID
	        if (message.startsWith("Ticket Used Successfully")) {
	            model.addAttribute("ticketId", ticketId);
	        }
	        return "index";
	    }
}
