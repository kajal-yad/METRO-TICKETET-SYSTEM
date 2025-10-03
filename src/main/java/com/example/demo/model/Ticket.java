package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Ticket {
	@Id
    private String id;
    private String fromStation;
    private String toStation;
    private int price;
    private LocalDateTime createdAt;
    private int usageCount;
    

    public Ticket() {}

    public Ticket(String id, String from, String to) {
        this.id = id;
        this.fromStation = from;
        this.toStation = to;
       
        this.createdAt = LocalDateTime.now();
        this.usageCount = 0;
    }

    // getters and setters
    public String getId() { return id; }
    public String getFromStation() { return fromStation; }
    public String getToStation() { return toStation; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public int getUsageCount() { return usageCount; }
    public void setUsageCount(int usageCount) { this.usageCount = usageCount; }
}
