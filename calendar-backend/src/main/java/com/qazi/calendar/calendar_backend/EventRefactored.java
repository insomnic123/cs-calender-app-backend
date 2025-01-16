package com.qazi.calendar.calendar_backend;

import jakarta.persistence.*;

import java.util.Dictionary;
import java.util.Hashtable;

@Entity
public class EventRefactored {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String allDay;
    private String colour;
    private String tag;

    @Transient
    private Dictionary<String, String> tags = new Hashtable<>();

    // Default Constructor
    public EventRefactored() {
        this.title = " ";
        this.description = " ";
        this.allDay = "f";
        this.colour = "#5a1e75"; // Default color
        this.tag = "null";
    }

    public EventRefactored(String title) {
        this.title = title;
        this.description = " ";
        this.allDay = "f";
        this.colour = "#5a1e75"; // Default color
    }

    // Constructor with all required fields
    public EventRefactored(String title, String colour) {
        this.title = title;
        this.description = " ";
        this.allDay = "f";
        this.colour = colour;
        this.tag = "null";
    }

    // Constructor with all fields
    public EventRefactored(String title, String description, String allDay, String colour) {
        this.title = title;
        this.description = description;
        this.allDay = allDay;
        this.colour = colour;
        this.tag = "null";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAllDay() {
        return allDay;
    }

    public void setAllDay(String allDay) {
        this.allDay = allDay;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setTag(String colour){
        this.tag = colour;
    }

    public String getTag() {
        return tag;
    }


    public String findTag(String colour) {
        return (String) tags.get(colour);
    }
}

