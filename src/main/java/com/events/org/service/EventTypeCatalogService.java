package com.events.org.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.events.org.dto.EventTypeCreateRequest;
import com.events.org.dto.EventTypeResponse;
import com.events.org.entity.EventType;
import com.events.org.repository.EventTypeRepository;

@Service
public class EventTypeCatalogService {

    private final EventTypeRepository eventTypeRepository;

    public EventTypeCatalogService(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    @Transactional
    public EventTypeResponse create(EventTypeCreateRequest request) {
        EventType eventType = new EventType(request.description().trim());
        return EventTypeResponse.from(eventTypeRepository.save(eventType));
    }

    @Transactional(readOnly = true)
    public List<EventTypeResponse> showAll() {
        return eventTypeRepository.findAll().stream().map(EventTypeResponse::from).toList();
    }
}
