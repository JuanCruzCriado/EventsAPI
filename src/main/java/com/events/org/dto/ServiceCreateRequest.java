package com.events.org.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ServiceCreateRequest(
        @NotBlank String description,
        @NotNull @PositiveOrZero Long price) {
}
