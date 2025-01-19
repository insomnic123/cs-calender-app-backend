package com.qazi.calendar.calendar_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Manages HTTP Requests

@RestController
@RequestMapping("/api/events")
public class HelloController {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;  // Used for sending WebSocket messages

    @GetMapping
    public ResponseEntity<List<EventTest>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventTest> updateEvent(@PathVariable String id, @RequestBody EventTest updatedEvent) {
        Optional<EventTest> existingEvent = eventRepository.findById(id);

        if (existingEvent.isPresent()) {
            EventTest event = existingEvent.get();
            event.setName(updatedEvent.getName());
            event.setStartTime(updatedEvent.getStartTime());
            event.setEndTime(updatedEvent.getEndTime());
            eventRepository.save(event);

            // Broadcast the updated event to WebSocket clients
            messagingTemplate.convertAndSend("/topic/events", event);

            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);

            // Optionally, broadcast the deleted event or a deletion message to WebSocket clients
            messagingTemplate.convertAndSend("/topic/events", "Event with ID " + id + " was deleted.");

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EventTest> createEvent(@RequestBody EventTest event) {
        eventRepository.save(event);

        // Broadcast the new event to WebSocket clients
        messagingTemplate.convertAndSend("/topic/events", event);

        return ResponseEntity.ok(event);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventTest> getEventById(@PathVariable String id) {
        EventTest event = eventService.getEventById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/greet")
    public String welcome() {
        return "Hi terri idaiygfaeifgiqe";
    }
}

