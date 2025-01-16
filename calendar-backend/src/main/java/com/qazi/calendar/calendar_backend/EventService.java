package com.qazi.calendar.calendar_backend;

import com.qazi.calendar.calendar_backend.EventTest;
import com.qazi.calendar.calendar_backend.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public List<EventTest> getAllEvents() {
        return eventRepository.findAll();
    }

    public EventTest createEvent(EventTest event) {
        EventTest savedEvent = eventRepository.save(event);
        messagingTemplate.convertAndSend("/topic/events", savedEvent); // sent to websocket clients
        return savedEvent;
    }

    public EventTest getEventById(String id) {
        return eventRepository.findById(id).orElse(null);
    }

    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
        messagingTemplate.convertAndSend("/topic/events", "Event with ID " + id + " deleted");
    }
}