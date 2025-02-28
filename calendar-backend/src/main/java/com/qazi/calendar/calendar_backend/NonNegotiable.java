package com.qazi.calendar.calendar_backend;

import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class NonNegotiable extends Event {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String startTime;
    private String endTime;

    // Default Constructor
//    public NonNegotiable() {
//        super();
//        LocalDateTime now = LocalDateTime.now();
//        this.startTime = LocalDateTime.parse(now.format(formatter));
//        this.endTime = LocalDateTime.parse(now.plusMinutes(30).format(formatter)); // Sets default to 30 mins from selected time
//    }

    // For quirks in processing file
    public NonNegotiable(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Default Constructor -- Individuals will be required to enter a title
    public NonNegotiable(String title, String startTime, String endTime) {
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public NonNegotiable(String title, String description, String startTime, String endTime) {
        super(title, description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // getters and setters
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String toString() {
        return this.getStartTime() + " " + this.getEndTime();
    }
}

