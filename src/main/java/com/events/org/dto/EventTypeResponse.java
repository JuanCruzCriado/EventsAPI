package com.events.org.dto;

import java.util.UUID;

import com.events.org.entity.EventType;

public record EventTypeResponse(UUID id, String description) {
    public static EventTypeResponse from(EventType eventType) {
        return new EventTypeResponse(eventType.getId(), eventType.getDescription());
    }
}
