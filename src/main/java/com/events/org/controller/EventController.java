package com.events.org.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.events.org.dto.EventCreateRequest;
import com.events.org.dto.EventCreateResponse;
import com.events.org.dto.EventResponse;
import com.events.org.service.EventService;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAll() {
        return ResponseEntity.ok(eventService.getAll());
    }

    @PostMapping
    public ResponseEntity<EventCreateResponse> create(@Valid @RequestBody EventCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.create(request));
    }
}
