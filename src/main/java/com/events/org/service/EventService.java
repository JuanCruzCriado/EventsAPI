package com.events.org.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.events.org.dto.EventCreateRequest;
import com.events.org.dto.EventCreateResponse;
import com.events.org.dto.EventResponse;
import com.events.org.entity.Event;
import com.events.org.entity.EventState;
import com.events.org.entity.EventType;
import com.events.org.entity.Pack;
import com.events.org.repository.EventRepository;
import com.events.org.repository.EventTypeRepository;
import com.events.org.repository.PackRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventTypeRepository eventTypeRepository;
    private final PackRepository packRepository;

    public EventService(EventRepository eventRepository,
                        EventTypeRepository eventTypeRepository,
                        PackRepository packRepository) {
        this.eventRepository = eventRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.packRepository = packRepository;
    }

    @Transactional(readOnly = true)
    public List<EventResponse> getAll() {
        return eventRepository.findAll().stream()
                .map(EventResponse::from)
                .toList();
    }

    @Transactional
    public EventCreateResponse create(EventCreateRequest request) {
        EventType eventType = eventTypeRepository.findById(request.eventTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de evento no encontrado"));

        Pack pack = null;
        if (request.packId() != null) {
            pack = packRepository.findById(request.packId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pack no encontrado"));
            if (!pack.getEventType().getId().equals(eventType.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "El pack seleccionado no corresponde al tipo de evento");
            }
        }

        Event event = new Event();
        event.setEventType(eventType);
        event.setDescription(request.description().trim());
        event.setEmail(request.email().trim());
        event.setProgrammedDate(request.programmedDate());
        event.setPack(pack);
        event.setState(EventState.REQUESTED);

        return EventCreateResponse.from(eventRepository.save(event));
    }
}
