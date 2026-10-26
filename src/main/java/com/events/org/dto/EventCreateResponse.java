package com.events.org.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.events.org.entity.Event;
import com.events.org.entity.EventState;

public record EventCreateResponse(
        UUID id,
        UUID eventTypeId,
        String description,
        String email,
        LocalDate programmedDate,
        UUID packId,
        int stateCode,
        String state,
        String stateDisplayName) {

    public static EventCreateResponse from(Event event) {
        EventState state = event.getState();
        return new EventCreateResponse(
                event.getId(),
                event.getEventType().getId(),
                event.getDescription(),
                event.getEmail(),
                event.getProgrammedDate(),
                event.getPack() == null ? null : event.getPack().getId(),
                state.getCode(),
                state.getName(),
                state.getDisplayName());
    }
}
