package com.events.org.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PackCreateRequest(
        @NotBlank String description,
        @NotNull UUID eventTypeId,
        @NotNull List<@NotNull UUID> serviceIds) {
}
