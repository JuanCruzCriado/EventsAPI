package com.events.org.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.events.org.dto.EventTypeCreateRequest;
import com.events.org.dto.EventTypeResponse;
import com.events.org.service.EventTypeCatalogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/event-types")
public class EventTypeController {

    private final EventTypeCatalogService eventTypeService;

    public EventTypeController(EventTypeCatalogService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @PostMapping
    public ResponseEntity<EventTypeResponse> create(@Valid @RequestBody EventTypeCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventTypeService.create(request));
    }

    @GetMapping
    public List<EventTypeResponse> showAll() {
        return eventTypeService.showAll();
    }
}
