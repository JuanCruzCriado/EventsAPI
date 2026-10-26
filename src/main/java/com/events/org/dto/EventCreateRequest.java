package com.events.org.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventCreateRequest(
        @NotNull UUID eventTypeId,
        @NotBlank String description,
        @NotBlank @Email @Size(max = 255) String email,
        @NotNull LocalDate programmedDate,
        UUID packId) {
}
