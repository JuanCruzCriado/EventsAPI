package com.events.org.dto;

import java.util.List;
import java.util.UUID;

import com.events.org.entity.Pack;

public record PackResponse(
        UUID id,
        String description,
        UUID eventTypeId,
        String eventTypeDescription,
        List<ServiceResponse> services) {

    public static PackResponse from(Pack pack, List<ServiceResponse> services) {
        return new PackResponse(
                pack.getId(),
                pack.getDescription(),
                pack.getEventType().getId(),
                pack.getEventType().getDescription(),
                services);
    }
}
