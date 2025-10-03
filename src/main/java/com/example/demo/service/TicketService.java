package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;

@Service
public class TicketService {
	 private final TicketRepository ticketRepository;

	    public TicketService(TicketRepository ticketRepository) {
	        this.ticketRepository = ticketRepository;
	    }

	    public Ticket bookTicket(String from, String to) {
	        String id = UUID.randomUUID().toString();
	        Ticket ticket = new Ticket(id, from, to);
	        return ticketRepository.save(ticket);
	    }

	    public String useTicket(String id) {
	        Ticket ticket = ticketRepository.findById(id).orElse(null);
	        if(ticket == null) return "Invalid Ticket ID";

	        LocalDateTime expiryTime = ticket.getCreatedAt().plusHours(18);
	        if(LocalDateTime.now().isAfter(expiryTime)) return "Ticket Expired";

	        if(ticket.getUsageCount() >= 2) return "Ticket Already Used Twice";

	        ticket.setUsageCount(ticket.getUsageCount() + 1);
	        ticketRepository.save(ticket);
	        return "Ticket Used Successfully, usage count: " + ticket.getUsageCount();
	    }
}
