package com.events.org.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.events.org.entity.Event;
import com.events.org.entity.EventState;

public record EventResponse(
        UUID id,
        UUID eventTypeId,
        String eventTypeDescription,
        String description,
        String email,
        LocalDate programmedDate,
        UUID packId,
        String packDescription,
        int stateCode,
        String state,
        String stateDisplayName) {

    public static EventResponse from(Event event) {
        EventState state = event.getState();
        return new EventResponse(
                event.getId(),
                event.getEventType().getId(),
                event.getEventType().getDescription(),
                event.getDescription(),
                event.getEmail(),
                event.getProgrammedDate(),
                event.getPack() == null ? null : event.getPack().getId(),
                event.getPack() == null ? null : event.getPack().getDescription(),
                state.getCode(),
                state.getName(),
                state.getDisplayName());
    }
}
