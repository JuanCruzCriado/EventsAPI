package com.events.org.dto;

import java.util.UUID;

import com.events.org.entity.ServiceEntity;

public record ServiceResponse(UUID id, String description, Long price) {
    public static ServiceResponse from(ServiceEntity service) {
        return new ServiceResponse(service.getId(), service.getDescription(), service.getPrice());
    }
}
